package hr.lknezevic.modules.impl;

import hr.lknezevic.autoconfigure.N8nProperties;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.WebClientBuilderUtility;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.concurrent.CompletableFuture;

@Log4j2
public class N8nClientModule implements HttpClientModule {
    private final WebClient webClient;
    private final Scheduler scheduler;

    public N8nClientModule(N8nProperties properties, Scheduler scheduler) {
        this.scheduler = scheduler;
        webClient = WebClientBuilderUtility.create(properties)
                .mutate()
                .filter(loggingFilter())
                .build();
    }

    @Override
    public <T> CompletableFuture<T> getAsync(String uri, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType)
                .subscribeOn(scheduler)
                .toFuture();
    }

    @Override
    public <T> CompletableFuture<T> getAsync(String uri, ParameterizedTypeReference<T> responseType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType)
                .subscribeOn(scheduler)
                .toFuture();
    }

    @Override
    public <T> T get(String uri, Class<T> responseType) {
        return getAsync(uri, responseType).join();
    }

    @Override
    public <T> T get(String uri, ParameterizedTypeReference<T> responseType) {
        return getAsync(uri, responseType).join();
    }

    @Override
    public <T> CompletableFuture<T> postAsync(String uri, Object request, Class<T> responseType) {
        WebClient.RequestBodySpec spec = webClient.post().uri(uri);

        Mono<T> mono = (request == null)
                ? spec.retrieve().bodyToMono(responseType)
                : spec.bodyValue(request).retrieve().bodyToMono(responseType);

        return mono.subscribeOn(scheduler).toFuture();
    }

    @Override
    public <T> CompletableFuture<T> postAsync(String uri, Object request, ParameterizedTypeReference<T> responseType) {
        WebClient.RequestBodySpec spec = webClient.post().uri(uri);

        Mono<T> mono = (request == null)
                ? spec.retrieve().bodyToMono(responseType)
                : spec.bodyValue(request).retrieve().bodyToMono(responseType);

        return mono.subscribeOn(scheduler).toFuture();
    }

    @Override
    public <T> T post(String uri, Object request, Class<T> responseType) {
        return postAsync(uri, request, responseType).join();
    }

    @Override
    public <T> T post(String uri, Object request, ParameterizedTypeReference<T> responseType) {
        return postAsync(uri, request, responseType).join();
    }

    @Override
    public <T> CompletableFuture<T> putAsync(String uri, Object request, Class<T> responseType) {
        WebClient.RequestBodySpec spec = webClient.put().uri(uri);

        Mono<T> mono = (request == null)
                ? spec.retrieve().bodyToMono(responseType)
                : spec.bodyValue(request).retrieve().bodyToMono(responseType);

        return mono.subscribeOn(scheduler).toFuture();
    }

    @Override
    public <T> CompletableFuture<T> putAsync(String uri, Object request, ParameterizedTypeReference<T> responseType) {
        WebClient.RequestBodySpec spec = webClient.put().uri(uri);

        Mono<T> mono = (request == null)
                ? spec.retrieve().bodyToMono(responseType)
                : spec.bodyValue(request).retrieve().bodyToMono(responseType);

        return mono.subscribeOn(scheduler).toFuture();
    }

    @Override
    public <T> T put(String uri, Object request, Class<T> responseType) {
        return putAsync(uri, request, responseType).join();
    }

    @Override
    public <T> T put(String uri, Object request, ParameterizedTypeReference<T> responseType) {
        return putAsync(uri, request, responseType).join();
    }

    @Override
    public <T> CompletableFuture<T> patchAsync(String uri, Object request, Class<T> responseType) {
        WebClient.RequestBodySpec spec = webClient.patch().uri(uri);

        Mono<T> mono = (request == null)
                ? spec.retrieve().bodyToMono(responseType)
                : spec.bodyValue(request).retrieve().bodyToMono(responseType);

        return mono.subscribeOn(scheduler).toFuture();
    }

    @Override
    public <T> CompletableFuture<T> patchAsync(String uri, Object request, ParameterizedTypeReference<T> responseType) {
        WebClient.RequestBodySpec spec = webClient.patch().uri(uri);

        Mono<T> mono = (request == null)
                ? spec.retrieve().bodyToMono(responseType)
                : spec.bodyValue(request).retrieve().bodyToMono(responseType);

        return mono.subscribeOn(scheduler).toFuture();
    }

    @Override
    public <T> T patch(String uri, Object request, Class<T> responseType) {
        return patchAsync(uri, request, responseType).join();
    }

    @Override
    public <T> T patch(String uri, Object request, ParameterizedTypeReference<T> responseType) {
        return patchAsync(uri, request, responseType).join();
    }

    @Override
    public CompletableFuture<Void> deleteAsync(String uri) {
        return webClient.delete()
                .uri(uri)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribeOn(scheduler)
                .toFuture();
    }

    @Override
    public void delete(String uri) {
        deleteAsync(uri).join();
    }

    private ExchangeFilterFunction loggingFilter() {
        return ((request, next) -> {
            log.debug("[N8N REQUEST] {} {}", request.method(), request.url());

            return next.exchange(request)
                    .flatMap(response -> {
                        log.debug("[N8N RESPONSE] {} {} -> {}",
                                request.method(),
                                request.url(),
                                response.statusCode()
                        );

                        return Mono.just(response);
                    });
        });
    }
}
