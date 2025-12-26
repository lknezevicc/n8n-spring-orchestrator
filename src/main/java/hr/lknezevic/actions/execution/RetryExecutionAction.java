package hr.lknezevic.actions.execution;

import hr.lknezevic.dto.ExecutionResponse;
import hr.lknezevic.reactive.http.action.AbstractHttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.utils.ConstantsUtility;

import java.util.Map;

public final class RetryExecutionAction extends AbstractHttpAction<ExecutionResponse> {
    private final String executionId;
    private final boolean loadWorkflow;

    public RetryExecutionAction(HttpExecutor httpExecutor, String executionId) {
        this(httpExecutor, executionId, false);
    }

    private RetryExecutionAction(HttpExecutor httpExecutor, String executionId, boolean loadWorkflow) {
        super(httpExecutor);
        this.executionId = executionId;
        this.loadWorkflow = loadWorkflow;
    }

    public RetryExecutionAction withLoadWorkflow(boolean loadWorkflow) {
        return new RetryExecutionAction(this.httpExecutor, this.executionId, loadWorkflow);
    }

    @Override
    protected HttpRequestSpec<ExecutionResponse> getRequestSpec() {
        String uriPath = ConstantsUtility.EXECUTIONS_URI + "/" + executionId + "/retry";
        Object requestBody = Map.of(ConstantsUtility.LOAD_WORKFLOW_BODY, loadWorkflow);

        return HttpRequestSpec.post(uriPath, requestBody, ExecutionResponse.class);
    }
}
