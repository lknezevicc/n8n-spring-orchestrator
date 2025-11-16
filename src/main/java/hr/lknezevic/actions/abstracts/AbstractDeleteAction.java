package hr.lknezevic.actions.abstracts;

import hr.lknezevic.modules.HttpClientModule;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractDeleteAction extends BaseAction<Void> {
    protected AbstractDeleteAction(HttpClientModule httpClient) {
        super(httpClient, Void.class);
    }

    @Override
    public CompletableFuture<Void> async() {
        return httpClient.deleteAsync(buildUri());
    }
}
