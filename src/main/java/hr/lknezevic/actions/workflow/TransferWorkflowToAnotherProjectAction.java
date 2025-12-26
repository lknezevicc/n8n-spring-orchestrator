package hr.lknezevic.actions.workflow;

import hr.lknezevic.reactive.http.action.AbstractHttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.utils.ConstantsUtility;

import java.util.Map;

public final class TransferWorkflowToAnotherProjectAction extends AbstractHttpAction<Void> {
    private final String workflowId;
    private final String destinationProjectId;

    public TransferWorkflowToAnotherProjectAction(HttpExecutor httpExecutor, String workflowId, String destinationProjectId) {
        super(httpExecutor);
        this.workflowId = workflowId;
        this.destinationProjectId = destinationProjectId;
    }

    @Override
    protected HttpRequestSpec<Void> getRequestSpec() {
        String uriPath = ConstantsUtility.WORKFLOWS_URI + "/" + workflowId + "/transfer";
        Object requestBody = Map.of(ConstantsUtility.DESTINATION_PROJECT_ID_BODY, destinationProjectId);

        return HttpRequestSpec.put(uriPath, requestBody, Void.class);
    }
}
