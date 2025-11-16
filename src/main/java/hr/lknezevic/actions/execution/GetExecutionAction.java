package hr.lknezevic.actions.execution;

import hr.lknezevic.actions.abstracts.AbstractGetAction;
import hr.lknezevic.dto.ExecutionResponse;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;
import hr.lknezevic.utils.UriQueryParameterBuilder;
import lombok.With;

public final class GetExecutionAction extends AbstractGetAction<ExecutionResponse> {
    private final String executionId;
    @With
    private final boolean includeData;

    public GetExecutionAction(HttpClientModule httpClient, String executionId) {
        super(httpClient, ExecutionResponse.class);
        this.executionId = executionId;
        this.includeData = false;
    }

    private GetExecutionAction(HttpClientModule httpClient, String executionId, boolean includeData) {
        super(httpClient, ExecutionResponse.class);
        this.executionId = executionId;
        this.includeData = includeData;
    }

    @Override
    protected String buildUri() {
        return new UriQueryParameterBuilder(ConstantsUtility.EXECUTIONS_URI + "/" + executionId)
                .withParam(ConstantsUtility.GET_EXECUTIONS_INCLUDE_DATA_QUERY, includeData)
                .build();
    }
}
