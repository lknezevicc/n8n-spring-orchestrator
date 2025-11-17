package hr.lknezevic.actions.workflow;

import hr.lknezevic.actions.abstracts.AbstractGetAction;
import hr.lknezevic.dto.WorkflowListResponse;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;
import hr.lknezevic.utils.UriQueryParameterBuilder;
import lombok.AccessLevel;
import lombok.With;

@With
public final class GetListWorkflowAction extends AbstractGetAction<WorkflowListResponse> {
    @With(AccessLevel.NONE)
    private final HttpClientModule httpClient;
    private final boolean active;
    private final String tags;
    private final String name;
    private final String projectId;
    private final boolean excludePinnedData;
    private final int limit;
    private final String cursor;

    public GetListWorkflowAction(HttpClientModule httpClient) {
        super(httpClient, WorkflowListResponse.class);
        this.httpClient = httpClient;
        this.active = true;
        this.tags = null;
        this.name = null;
        this.projectId = null;
        this.excludePinnedData = true;
        this.limit = 100;
        this.cursor = null;
    }

    private GetListWorkflowAction(HttpClientModule httpClient, boolean active, String tags, String name,
                                  String projectId, boolean excludePinnedData, int limit, String cursor) {
        super(httpClient, WorkflowListResponse.class);
        this.httpClient = httpClient;
        this.active = active;
        this.tags = tags;
        this.name = name;
        this.projectId = projectId;
        this.excludePinnedData = excludePinnedData;
        this.limit = limit;
        this.cursor = cursor;
    }

    @Override
    protected String buildUri() {
        return new UriQueryParameterBuilder(ConstantsUtility.WORKFLOWS_URI)
                .withParam(ConstantsUtility.ACTIVE_QUERY, active)
                .withParam(ConstantsUtility.TAGS_QUERY, tags)
                .withParam(ConstantsUtility.NAME_QUERY, name)
                .withParam(ConstantsUtility.PROJECT_ID_QUERY, projectId)
                .withParam(ConstantsUtility.EXCLUDE_PINNED_DATA_QUERY, excludePinnedData)
                .withParam(ConstantsUtility.LIMIT_QUERY, (limit > 0 && limit <= 250) ? limit : 100)
                .withParam(ConstantsUtility.CURSOR_QUERY, cursor)
                .build();
    }
}
