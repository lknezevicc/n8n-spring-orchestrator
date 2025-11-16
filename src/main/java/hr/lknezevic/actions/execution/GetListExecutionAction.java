package hr.lknezevic.actions.execution;

import hr.lknezevic.actions.abstracts.AbstractGetAction;
import hr.lknezevic.dto.ExecutionListResponse;
import hr.lknezevic.enums.ExecutionStatus;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;
import hr.lknezevic.utils.UriQueryParameterBuilder;
import lombok.AccessLevel;
import lombok.With;

@With
public final class GetListExecutionAction extends AbstractGetAction<ExecutionListResponse> {
    @With(AccessLevel.NONE)
    private final HttpClientModule httpClient;
    private final boolean includeData;
    private final ExecutionStatus executionStatus;
    private final String workflowId;
    private final String projectId;
    private final int limit;
    private final String cursor;

    public GetListExecutionAction(HttpClientModule httpClient) {
        super(httpClient, ExecutionListResponse.class);
        this.httpClient = httpClient;
        this.includeData = false;
        this.executionStatus = null;
        this.workflowId = null;
        this.projectId = null;
        this.limit = 100;
        this.cursor = null;
    }

    private GetListExecutionAction(HttpClientModule httpClient, boolean includeData, ExecutionStatus executionStatus,
                                   String workflowId, String projectId, int limit, String cursor) {
        super(httpClient, ExecutionListResponse.class);
        this.httpClient = httpClient;
        this.includeData = includeData;
        this.executionStatus = executionStatus;
        this.workflowId = workflowId;
        this.projectId = projectId;
        this.limit = limit;
        this.cursor = cursor;
    }

    @Override
    protected String buildUri() {
        return new UriQueryParameterBuilder(ConstantsUtility.EXECUTIONS_URI)
                .withParam(ConstantsUtility.GET_EXECUTIONS_INCLUDE_DATA_QUERY, includeData)
                .withParam(ConstantsUtility.GET_EXECUTIONS_STATUS_QUERY,
                        validExecutionStatusFilter(executionStatus) ? executionStatus : null)
                .withParam(ConstantsUtility.GET_EXECUTIONS_WORKFLOW_ID_QUERY, workflowId)
                .withParam(ConstantsUtility.GET_EXECUTIONS_PROJECT_ID_QUERY, projectId)
                .withParam(ConstantsUtility.GET_EXECUTIONS_LIMIT_QUERY, (limit > 0 && limit <= 250) ? limit : 100)
                .withParam(ConstantsUtility.GET_EXECUTIONS_CURSOR_QUERY, cursor)
                .build();
    }

    private boolean validExecutionStatusFilter(ExecutionStatus executionStatus) {
        if  (executionStatus == null) return false;

        return switch (executionStatus) {
            case CANCELLED, ERROR, RUNNING, SUCCESS, WAITING -> true;
            default -> false;
        };
    }
}
