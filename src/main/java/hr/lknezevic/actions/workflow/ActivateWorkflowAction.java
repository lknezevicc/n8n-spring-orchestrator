package hr.lknezevic.actions.workflow;

import hr.lknezevic.actions.abstracts.AbstractPostAction;
import hr.lknezevic.dto.WorkflowResponse;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;

public final class ActivateWorkflowAction extends AbstractPostAction<WorkflowResponse> {
    private final String workflowId;

    public ActivateWorkflowAction(HttpClientModule httpClient, String workflowId) {
        super(httpClient, WorkflowResponse.class);
        this.workflowId = workflowId;
    }

    @Override
    protected Object buildRequest() {
        return null;
    }

    @Override
    protected String buildUri() {
        return ConstantsUtility.WORKFLOWS_URI + "/" + workflowId + "/activate";
    }
}
