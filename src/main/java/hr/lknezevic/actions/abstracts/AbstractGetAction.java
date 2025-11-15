package hr.lknezevic.actions.abstracts;

import hr.lknezevic.modules.HttpClientModule;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractGetAction<TResponse> extends BaseAction<TResponse> {
    private final Class<TResponse> responseClass;

    protected AbstractGetAction(HttpClientModule httpClient, Class<TResponse> responseClass) {
        super(httpClient);
        this.responseClass = responseClass;
    }

    @Override
    public CompletableFuture<TResponse> async() {
        String uri = buildUri();
        return httpClient.getAsync(uri, responseClass);
    }
}
