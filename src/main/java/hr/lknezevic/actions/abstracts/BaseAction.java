package hr.lknezevic.actions.abstracts;

import hr.lknezevic.actions.Action;
import hr.lknezevic.modules.HttpClientModule;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseAction<T> implements Action<T> {
    protected final HttpClientModule httpClient;
    protected final Class<T> responseClass;

    protected abstract String buildUri();

    @Override
    public T sync() {
        return async().join();
    }
}
