package hr.lknezevic.actions.workflow;

import hr.lknezevic.dto.WorkflowResponse;
import hr.lknezevic.reactive.http.action.AbstractHttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.reactive.http.util.UriQueryParameterBuilder;
import hr.lknezevic.utils.ConstantsUtility;

public final class GetWorkflowAction extends AbstractHttpAction<WorkflowResponse> {
    private final String workflowId;
    private final boolean excludePinnedData;

    public GetWorkflowAction(HttpExecutor httpExecutor, String workflowId) {
        this(httpExecutor, workflowId, true);
    }

    GetWorkflowAction(HttpExecutor httpExecutor, String workflowId, boolean excludePinnedData) {
        super(httpExecutor);
        this.workflowId = workflowId;
        this.excludePinnedData = excludePinnedData;
    }

    public GetWorkflowAction withExcludePinnedData(boolean excludePinnedData) {
        return new GetWorkflowAction(this.httpExecutor, this.workflowId, excludePinnedData);
    }

    @Override
    protected HttpRequestSpec<WorkflowResponse> getRequestSpec() {
        String uriPath = new UriQueryParameterBuilder(ConstantsUtility.WORKFLOWS_URI + "/" + workflowId)
                .withParam(ConstantsUtility.EXCLUDE_PINNED_DATA_QUERY, excludePinnedData)
                .build();

        return HttpRequestSpec.get(uriPath, WorkflowResponse.class);
    }
}
