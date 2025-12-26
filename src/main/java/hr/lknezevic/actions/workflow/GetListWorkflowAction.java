package hr.lknezevic.actions.workflow;

import hr.lknezevic.dto.WorkflowListResponse;
import hr.lknezevic.reactive.http.action.AbstractHttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.reactive.http.util.UriQueryParameterBuilder;
import hr.lknezevic.utils.ConstantsUtility;

public final class GetListWorkflowAction extends AbstractHttpAction<WorkflowListResponse> {
    private final boolean active;
    private final String tags;
    private final String name;
    private final String projectId;
    private final boolean excludePinnedData;
    private final int limit;
    private final String cursor;

    public GetListWorkflowAction(HttpExecutor httpExecutor) {
        this(
                httpExecutor,
                true,
                null,
                null,
                null,
                true,
                100,
                null
        );
    }

    private GetListWorkflowAction(HttpExecutor httpExecutor, boolean active, String tags, String name,
                                  String projectId, boolean excludePinnedData, int limit, String cursor) {
        super(httpExecutor);
        this.active = active;
        this.tags = tags;
        this.name = name;
        this.projectId = projectId;
        this.excludePinnedData = excludePinnedData;
        this.limit = limit;
        this.cursor = cursor;
    }

    public GetListWorkflowAction withActive(boolean active) {
        return new GetListWorkflowAction(
                httpExecutor,
                active,
                this.tags,
                this.name,
                this.projectId,
                this.excludePinnedData,
                this.limit,
                this.cursor
        );
    }

    public GetListWorkflowAction withTags(String tags) {
        return new GetListWorkflowAction(
                httpExecutor,
                this.active,
                tags,
                this.name,
                this.projectId,
                this.excludePinnedData,
                this.limit,
                this.cursor
        );
    }

    public GetListWorkflowAction withName(String name) {
        return new GetListWorkflowAction(
                httpExecutor,
                this.active,
                this.tags,
                name,
                this.projectId,
                this.excludePinnedData,
                this.limit,
                this.cursor
        );
    }

    public GetListWorkflowAction withProjectId(String projectId) {
        return new GetListWorkflowAction(
                httpExecutor,
                this.active,
                this.tags,
                this.name,
                projectId,
                this.excludePinnedData,
                this.limit,
                this.cursor
        );
    }

    public GetListWorkflowAction withExcludePinnedData(boolean excludePinnedData) {
        return new GetListWorkflowAction(
                httpExecutor,
                this.active,
                this.tags,
                this.name,
                this.projectId,
                excludePinnedData,
                this.limit,
                this.cursor
        );
    }

    public GetListWorkflowAction withLimit(int limit) {
        return new GetListWorkflowAction(
                httpExecutor,
                this.active,
                this.tags,
                this.name,
                this.projectId,
                this.excludePinnedData,
                normalizeLimit(limit),
                this.cursor
        );
    }

    public GetListWorkflowAction withCursor(String cursor) {
        return new GetListWorkflowAction(
                httpExecutor,
                this.active,
                this.tags,
                this.name,
                this.projectId,
                this.excludePinnedData,
                this.limit,
                cursor
        );
    }

    @Override
    protected HttpRequestSpec<WorkflowListResponse> getRequestSpec() {
        String uriPath = new UriQueryParameterBuilder(ConstantsUtility.WORKFLOWS_URI)
                .withParam(ConstantsUtility.ACTIVE_QUERY, active)
                .withParam(ConstantsUtility.TAGS_QUERY, tags)
                .withParam(ConstantsUtility.NAME_QUERY, name)
                .withParam(ConstantsUtility.PROJECT_ID_QUERY, projectId)
                .withParam(ConstantsUtility.EXCLUDE_PINNED_DATA_QUERY, excludePinnedData)
                .withParam(ConstantsUtility.LIMIT_QUERY, (limit > 0 && limit <= 250) ? limit : 100)
                .withParam(ConstantsUtility.CURSOR_QUERY, cursor)
                .build();

        return HttpRequestSpec.get(uriPath, WorkflowListResponse.class);
    }

    private int normalizeLimit(int limit) {
        return (limit > 0 && limit <= 250) ? limit : 100;
    }
}
