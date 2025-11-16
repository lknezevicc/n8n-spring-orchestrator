package hr.lknezevic.actions.execution;

import hr.lknezevic.actions.abstracts.AbstractPostAction;
import hr.lknezevic.dto.ExecutionResponse;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;
import hr.lknezevic.utils.UriQueryParameterBuilder;
import lombok.With;

import java.util.Map;

public class RetryExecutionAction extends AbstractPostAction<ExecutionResponse> {
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
        return Map.of(ConstantsUtility.POST_EXECUTIONS_LOAD_WORKFLOW, loadWorkflow);
    }

    @Override
    protected String buildUri() {
        return new UriQueryParameterBuilder(ConstantsUtility.EXECUTIONS_URI + "/" + executionId + "/retry")
                .build();
    }
}
