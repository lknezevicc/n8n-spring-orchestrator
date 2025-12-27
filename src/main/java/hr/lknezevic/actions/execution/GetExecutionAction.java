package hr.lknezevic.actions.execution;

import hr.lknezevic.dto.ExecutionResponse;
import hr.lknezevic.reactive.http.action.AbstractHttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.reactive.http.util.UriQueryParameterBuilder;
import hr.lknezevic.utils.ConstantsUtility;

public final class GetExecutionAction extends AbstractHttpAction<ExecutionResponse> {
    private final String executionId;
    private final boolean includeData;

    public GetExecutionAction(HttpExecutor httpExecutor, String executionId) {
        this(httpExecutor, executionId, false);
    }

    GetExecutionAction(HttpExecutor httpExecutor, String executionId, boolean includeData) {
        super(httpExecutor);
        this.executionId = executionId;
        this.includeData = includeData;
    }

    public GetExecutionAction withIncludeData(boolean includeData) {
        return new GetExecutionAction(this.httpExecutor, this.executionId, includeData);
    }

    @Override
    protected HttpRequestSpec<ExecutionResponse> getRequestSpec() {
        String uriPath = new UriQueryParameterBuilder(ConstantsUtility.EXECUTIONS_URI + "/" + executionId)
                .withParam(ConstantsUtility.INCLUDE_DATA_QUERY, includeData)
                .build();

        return HttpRequestSpec.get(uriPath, ExecutionResponse.class);
    }
}
