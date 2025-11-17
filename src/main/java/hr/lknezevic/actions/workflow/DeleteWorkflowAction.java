package hr.lknezevic.actions.workflow;

import hr.lknezevic.actions.abstracts.AbstractDeleteAction;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;
import hr.lknezevic.utils.UriQueryParameterBuilder;

public final class DeleteWorkflowAction extends AbstractDeleteAction {
    private final String workflowId;

    public DeleteWorkflowAction(HttpClientModule httpClient, String workflowId) {
        super(httpClient);
        this.workflowId = workflowId;
    }

    @Override
    protected String buildUri() {
        return new UriQueryParameterBuilder(ConstantsUtility.WORKFLOWS_URI + "/" + workflowId)
                .build();
    }
}
