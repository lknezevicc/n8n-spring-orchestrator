package hr.lknezevic.actions;

import java.util.concurrent.CompletableFuture;

public interface AsyncAction<T> {
    CompletableFuture<T> async();
}
