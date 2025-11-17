package hr.lknezevic.actions.workflow;

import hr.lknezevic.actions.abstracts.AbstractPostAction;
import hr.lknezevic.dto.WorkflowResponse;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;
import hr.lknezevic.utils.UriQueryParameterBuilder;

public final class DeactivateWorkflowAction extends AbstractPostAction<WorkflowResponse> {
    private final String workflowId;

    public DeactivateWorkflowAction(HttpClientModule httpClient, String workflowId) {
        super(httpClient, WorkflowResponse.class);
        this.workflowId = workflowId;
    }

    @Override
    protected Object buildRequest() {
        return null;
    }

    @Override
    protected String buildUri() {
        return ConstantsUtility.WORKFLOWS_URI + "/" + workflowId + "/deactivate";
    }
}
