package hr.lknezevic.stages.impl;

import hr.lknezevic.reactive.http.action.HttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.action.builder.HttpActions;
import hr.lknezevic.reactive.http.action.builder.HttpMethodStep;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.stages.ActionBuilderStage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ActionBuilderStageImpl implements ActionBuilderStage {
    private final HttpExecutor httpExecutor;

    @Override
    public HttpMethodStep builder() {
        return HttpActions.builder(httpExecutor);
    }

    @Override
    public <T> HttpAction<T> fromSpec(HttpRequestSpec<T> spec) {
        return HttpActions.fromSpec(httpExecutor, spec);
    }
}
