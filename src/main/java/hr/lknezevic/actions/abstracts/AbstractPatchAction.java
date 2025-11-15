package hr.lknezevic.actions.abstracts;

import hr.lknezevic.modules.HttpClientModule;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractPatchAction<TResponse> extends BaseAction<TResponse> {
    private final Object request;
    private final Class<TResponse> responseClass;

    protected AbstractPatchAction(HttpClientModule httpClient, Object request, Class<TResponse> responseClass) {
        super(httpClient);
        this.request = request;
        this.responseClass = responseClass;
    }

    @Override
    public CompletableFuture<TResponse> async() {
        String uri = buildUri();
        return httpClient.patchAsync(uri, request, responseClass);
    }
}
