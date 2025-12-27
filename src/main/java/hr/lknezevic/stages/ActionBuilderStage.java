package hr.lknezevic.stages;

import hr.lknezevic.reactive.http.action.HttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.action.builder.HttpMethodStep;

public interface ActionBuilderStage {
    HttpMethodStep builder();
    <T> HttpAction<T> fromSpec(HttpRequestSpec<T> spec);
}
