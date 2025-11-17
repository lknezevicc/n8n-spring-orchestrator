package hr.lknezevic.actions.workflow;

import hr.lknezevic.actions.abstracts.AbstractGetAction;
import hr.lknezevic.dto.WorkflowResponse;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;
import hr.lknezevic.utils.UriQueryParameterBuilder;
import lombok.With;

public final class GetWorkflowAction extends AbstractGetAction<WorkflowResponse> {
    private final String workflowId;
    @With
    private final boolean excludePinnedData;

    public GetWorkflowAction(HttpClientModule httpClient, String workflowId) {
        super(httpClient, WorkflowResponse.class);
        this.workflowId = workflowId;
        this.excludePinnedData = true;
    }

    private GetWorkflowAction(HttpClientModule httpClient, String workflowId, boolean excludePinnedData) {
        super(httpClient, WorkflowResponse.class);
        this.workflowId = workflowId;
        this.excludePinnedData = excludePinnedData;
    }

    @Override
    protected String buildUri() {
        return new UriQueryParameterBuilder(ConstantsUtility.WORKFLOWS_URI + "/" + workflowId)
                .withParam(ConstantsUtility.EXCLUDE_PINNED_DATA_QUERY, excludePinnedData)
                .build();
    }
}
