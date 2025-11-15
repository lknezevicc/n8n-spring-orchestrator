package hr.lknezevic.utils;

import hr.lknezevic.autoconfigure.N8nProperties;
import hr.lknezevic.exceptions.N8nClientException;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.*;
import reactor.core.Exceptions;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@UtilityClass
public class WebClientBuilderUtility {

    public static WebClient create(N8nProperties properties) {
        return WebClient.builder()
                .baseUrl(properties.getApiUrl())
                .clientConnector(httpConnector(properties))
                .defaultHeaders(headers -> defaultHeaders(headers, properties))
                .exchangeStrategies(exchangeStrategies(properties))
                .filter(retryAndErrorFilter(properties))
                .build();
    }

    private static ReactorClientHttpConnector httpConnector(N8nProperties properties) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) properties.getHttpClientProperties().getConnectionTimeoutMs())
                .responseTimeout(Duration.ofMillis(properties.getHttpClientProperties().getReadTimeoutMs()))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(
                                properties.getHttpClientProperties().getReadTimeoutMs(),
                                TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(
                                properties.getHttpClientProperties().getWriteTimeoutMs(),
                                TimeUnit.MILLISECONDS))
                );

        return new ReactorClientHttpConnector(httpClient);
    }

    private static void defaultHeaders(HttpHeaders headers, N8nProperties properties) {
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(ConstantsUtility.N8N_API_KEY_HEADER, properties.getApiKey());
    }

    private static ExchangeStrategies exchangeStrategies(N8nProperties properties) {
        return ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(properties.getHttpClientProperties().getMaxInMemorySizeBytes()))
                .build();
    }

    private static ExchangeFilterFunction retryAndErrorFilter(N8nProperties properties) {
        return ((request, next) -> {
            boolean shouldRetry = isIdempotent(request.method());

            Mono<ClientResponse> response = next.exchange(request);

            if (shouldRetry) {
                Retry retrySpec = retrySpec(properties, request);
                response = response.retryWhen(retrySpec);
            }

            return response.flatMap(WebClientBuilderUtility::handleError);
        });
    }

    private static Retry retrySpec(N8nProperties properties, ClientRequest request) {
        return Retry.backoff(properties.getHttpClientProperties().getRetryTimes(),
                        Duration.ofMillis(properties.getHttpClientProperties().getRetryBackoffMs()))
                .jitter(0.5)
                .filter(WebClientBuilderUtility::isRetryable)
                .onRetryExhaustedThrow(
                        (spec, rs) ->
                                new N8nClientException("Retries exhausted for " + request.url(), rs.failure())
                );
    }

    private static Mono<ClientResponse> handleError(ClientResponse response) {
        if (response.statusCode().isError()) {
            return response.bodyToMono(String.class)
                    .defaultIfEmpty("No error message provided")
                    .flatMap(errorBody -> Mono.error(
                            new N8nClientException("n8n API error: " + errorBody,
                                    response.statusCode().value()))
                    );
        }

        return Mono.just(response);
    }

    private static boolean isIdempotent(HttpMethod method) {
        return method == HttpMethod.GET
                || method == HttpMethod.HEAD
                || method == HttpMethod.OPTIONS;
    }

    private static boolean isRetryable(Throwable t) {
        Throwable root = Exceptions.unwrap(t);

        return (root instanceof ConnectException)
                || (root instanceof SocketTimeoutException)
                || (root instanceof TimeoutException)
                || (root instanceof IOException)
                || (root instanceof ReadTimeoutException);
    }
}
