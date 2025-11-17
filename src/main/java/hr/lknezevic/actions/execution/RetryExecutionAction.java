package hr.lknezevic.actions.execution;

import hr.lknezevic.actions.abstracts.AbstractPostAction;
import hr.lknezevic.dto.ExecutionResponse;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;
import hr.lknezevic.utils.UriQueryParameterBuilder;
import lombok.With;

import java.util.Map;

public final class RetryExecutionAction extends AbstractPostAction<ExecutionResponse> {
    private final String executionId;
    @With private final boolean loadWorkflow;

    public RetryExecutionAction(HttpClientModule httpClient, String executionId) {
        super(httpClient, ExecutionResponse.class);
        this.executionId = executionId;
        this.loadWorkflow = false;
    }

    private RetryExecutionAction(HttpClientModule httpClient, String executionId, boolean loadWorkflow) {
        super(httpClient, ExecutionResponse.class);
        this.executionId = executionId;
        this.loadWorkflow = loadWorkflow;
    }

    @Override
    protected Object buildRequest() {
        return Map.of(ConstantsUtility.LOAD_WORKFLOW_BODY, loadWorkflow);
    }

    @Override
    protected String buildUri() {
        return ConstantsUtility.EXECUTIONS_URI + "/" + executionId + "/retry";
    }
}
