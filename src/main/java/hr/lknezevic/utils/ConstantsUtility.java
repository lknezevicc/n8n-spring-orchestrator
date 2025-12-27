package hr.lknezevic.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantsUtility {
    public final String N8N_API_KEY_HEADER = "X-N8N-API-KEY";

    public final String EXECUTIONS_URI = "/api/v1/executions";
    public final String WORKFLOWS_URI = "/api/v1/workflows";

    public final String INCLUDE_DATA_QUERY = "includeData";
    public final String STATUS_QUERY = "status";
    public final String WORKFLOW_ID_QUERY = "workflowId";
    public final String PROJECT_ID_QUERY = "projectId";
    public final String LIMIT_QUERY = "limit";
    public final String CURSOR_QUERY = "cursor";
    public final String ACTIVE_QUERY = "active";
    public final String TAGS_QUERY = "tags";
    public final String NAME_QUERY = "name";
    public final String EXCLUDE_PINNED_DATA_QUERY = "excludePinnedData";

    public final String LOAD_WORKFLOW_BODY = "loadWorkflow";
    public final String DESTINATION_PROJECT_ID_BODY = "destinationProjectId";
}
