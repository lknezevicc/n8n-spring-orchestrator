package hr.lknezevic.actions.abstracts;

import hr.lknezevic.modules.HttpClientModule;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractPatchAction<T> extends BaseAction<T> {
    protected AbstractPatchAction(HttpClientModule httpClient, Class<T> responseClass) {
        super(httpClient, responseClass);
    }

    protected abstract Object buildRequest();

    @Override
    public CompletableFuture<T> async() {
        return httpClient.patchAsync(buildUri(), buildRequest(), responseClass);
    }
}
