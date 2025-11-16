package hr.lknezevic.actions.abstracts;

import hr.lknezevic.modules.HttpClientModule;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractGetAction<T> extends BaseAction<T> {
    protected AbstractGetAction(HttpClientModule httpClient, Class<T> responseClass) {
        super(httpClient, responseClass);
    }

    @Override
    public CompletableFuture<T> async() {
        return httpClient.getAsync(buildUri(), responseClass);
    }
}
