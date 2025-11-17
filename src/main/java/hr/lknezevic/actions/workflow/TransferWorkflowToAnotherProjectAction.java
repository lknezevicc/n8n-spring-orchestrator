package hr.lknezevic.actions.workflow;

import hr.lknezevic.actions.abstracts.AbstractPutAction;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;
import hr.lknezevic.utils.UriQueryParameterBuilder;

import java.util.Map;

public final class TransferWorkflowToAnotherProjectAction extends AbstractPutAction<Void> {
    private final String workflowId;
    private final String destinationProjectId;

    public TransferWorkflowToAnotherProjectAction(HttpClientModule httpClient, String workflowId, String destinationProjectId) {
        super(httpClient, Void.class);
        this.workflowId = workflowId;
        this.destinationProjectId = destinationProjectId;
    }

    @Override
    protected Object buildRequest() {
        return Map.of(ConstantsUtility.DESTINATION_PROJECT_ID_BODY, destinationProjectId);
    }

    @Override
    protected String buildUri() {
        return new UriQueryParameterBuilder(ConstantsUtility.WORKFLOWS_URI + "/" + workflowId + "/transfer")
                .build();
    }
}
