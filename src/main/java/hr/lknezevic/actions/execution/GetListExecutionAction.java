package hr.lknezevic.actions.execution;

import hr.lknezevic.dto.ExecutionListResponse;
import hr.lknezevic.enums.ExecutionStatus;
import hr.lknezevic.reactive.http.action.AbstractHttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.reactive.http.util.UriQueryParameterBuilder;
import hr.lknezevic.utils.ConstantsUtility;

public final class GetListExecutionAction extends AbstractHttpAction<ExecutionListResponse> {
    private final boolean includeData;
    private final ExecutionStatus executionStatus;
    private final String workflowId;
    private final String projectId;
    private final int limit;
    private final String cursor;

    public GetListExecutionAction(HttpExecutor httpExecutor) {
        this(
                httpExecutor,
                false,
                null,
                null,
                null,
                100,
                null
        );
    }

    GetListExecutionAction(HttpExecutor httpExecutor, boolean includeData, ExecutionStatus executionStatus,
                                   String workflowId, String projectId, int limit, String cursor) {
        super(httpExecutor);
        this.includeData = includeData;
        this.executionStatus = executionStatus;
        this.workflowId = workflowId;
        this.projectId = projectId;
        this.limit = limit;
        this.cursor = cursor;
    }

    public GetListExecutionAction withIncludeData(boolean includeData) {
        return new GetListExecutionAction(
                httpExecutor,
                includeData,
                this.executionStatus,
                this.workflowId,
                this.projectId,
                this.limit,
                this.cursor
        );
    }

    public GetListExecutionAction withExecutionStatus(ExecutionStatus status) {
        return new GetListExecutionAction(
                httpExecutor,
                this.includeData,
                status,
                this.workflowId,
                this.projectId,
                this.limit,
                this.cursor
        );
    }

    public GetListExecutionAction withWorkflowId(String workflowId) {
        return new GetListExecutionAction(
                httpExecutor,
                this.includeData,
                this.executionStatus,
                workflowId,
                this.projectId,
                this.limit,
                this.cursor
        );
    }

    public GetListExecutionAction withProjectId(String projectId) {
        return new GetListExecutionAction(
                httpExecutor,
                this.includeData,
                this.executionStatus,
                this.workflowId,
                projectId,
                this.limit,
                this.cursor
        );
    }

    public GetListExecutionAction withLimit(int limit) {
        return new GetListExecutionAction(
                httpExecutor,
                this.includeData,
                this.executionStatus,
                this.workflowId,
                this.projectId,
                limit,
                this.cursor
        );
    }

    public GetListExecutionAction withCursor(String cursor) {
        return new GetListExecutionAction(
                httpExecutor,
                this.includeData,
                this.executionStatus,
                this.workflowId,
                this.projectId,
                this.limit,
                cursor
        );
    }

    @Override
    protected HttpRequestSpec<ExecutionListResponse> getRequestSpec() {
        String uriPath = new UriQueryParameterBuilder(ConstantsUtility.EXECUTIONS_URI)
                .withParam(ConstantsUtility.INCLUDE_DATA_QUERY, includeData)
                .withParam(ConstantsUtility.STATUS_QUERY,
                        validExecutionStatusFilter(executionStatus) ? executionStatus : null)
                .withParam(ConstantsUtility.WORKFLOW_ID_QUERY, workflowId)
                .withParam(ConstantsUtility.PROJECT_ID_QUERY, projectId)
                .withParam(ConstantsUtility.LIMIT_QUERY, normalizeLimit(limit))
                .withParam(ConstantsUtility.CURSOR_QUERY, cursor)
                .build();

        return HttpRequestSpec.get(uriPath, ExecutionListResponse.class);
    }

    private boolean validExecutionStatusFilter(ExecutionStatus executionStatus) {
        if  (executionStatus == null) return false;

        return switch (executionStatus) {
            case CANCELLED, ERROR, RUNNING, SUCCESS, WAITING -> true;
            default -> false;
        };
    }

    private int normalizeLimit(int limit) {
        return (limit > 0 && limit <= 250) ? limit : 100;
    }
}
