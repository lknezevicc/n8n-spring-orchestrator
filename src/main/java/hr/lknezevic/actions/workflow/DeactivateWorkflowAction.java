package hr.lknezevic.actions.workflow;

import hr.lknezevic.dto.WorkflowResponse;
import hr.lknezevic.reactive.http.action.AbstractHttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.utils.ConstantsUtility;

public final class DeactivateWorkflowAction extends AbstractHttpAction<WorkflowResponse> {
    private final String workflowId;

    public DeactivateWorkflowAction(HttpExecutor httpExecutor, String workflowId) {
        super(httpExecutor);
        this.workflowId = workflowId;
    }

    @Override
    protected HttpRequestSpec<WorkflowResponse> getRequestSpec() {
        String uriPath = ConstantsUtility.WORKFLOWS_URI + "/" + workflowId + "/deactivate";

        return HttpRequestSpec.post(uriPath, null, WorkflowResponse.class);
    }
}
