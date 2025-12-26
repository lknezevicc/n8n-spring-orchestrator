package hr.lknezevic.actions.execution;

import hr.lknezevic.dto.ExecutionResponse;
import hr.lknezevic.reactive.http.action.AbstractHttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.utils.ConstantsUtility;

public final class DeleteExecutionAction extends AbstractHttpAction<ExecutionResponse> {
    private final String executionId;

    public DeleteExecutionAction(HttpExecutor httpExecutor, String executionId) {
        super(httpExecutor);
        this.executionId = executionId;
    }

    @Override
    protected HttpRequestSpec<ExecutionResponse> getRequestSpec() {
        String uriPath = ConstantsUtility.EXECUTIONS_URI + "/" + executionId;

        return HttpRequestSpec.delete(uriPath, ExecutionResponse.class);
    }
}
