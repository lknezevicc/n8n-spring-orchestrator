package hr.lknezevic.modules;

import org.springframework.core.ParameterizedTypeReference;

import java.util.concurrent.CompletableFuture;

public interface HttpClientModule {
    // === GET ===
    <T> CompletableFuture<T> getAsync(String uri, Class<T> responseType);
    <T> CompletableFuture<T> getAsync(String uri, ParameterizedTypeReference<T> responseType);
    <T> T get(String uri, Class<T> responseType);
    <T> T get(String uri, ParameterizedTypeReference<T> responseType);

    // ==== POST ====
    <T> CompletableFuture<T> postAsync(String uri, Object request, Class<T> responseType);
    <T> CompletableFuture<T> postAsync(String uri, Object request, ParameterizedTypeReference<T> responseType);
    <T> T post(String uri, Object request, Class<T> responseType);
    <T> T post(String uri, Object request, ParameterizedTypeReference<T> responseType);

    // === PUT ===
    <T> CompletableFuture<T> putAsync(String uri, Object request, Class<T> responseType);
    <T> CompletableFuture<T> putAsync(String uri, Object request, ParameterizedTypeReference<T> responseType);
    <T> T put(String uri, Object request, Class<T> responseType);
    <T> T put(String uri, Object request, ParameterizedTypeReference<T> responseType);

    // ==== PATCH ====
    <T> CompletableFuture<T> patchAsync(String uri, Object request, Class<T> responseType);
    <T> CompletableFuture<T> patchAsync(String uri, Object request, ParameterizedTypeReference<T> responseType);
    <T> T patch(String uri, Object request, Class<T> responseType);
    <T> T patch(String uri, Object request, ParameterizedTypeReference<T> responseType);

    // ==== DELETE ====
    CompletableFuture<Void> deleteAsync(String uri);
    void delete(String uri);
}
