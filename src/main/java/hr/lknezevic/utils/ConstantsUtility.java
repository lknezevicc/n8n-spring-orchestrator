package hr.lknezevic.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantsUtility {
    public static final String N8N_API_KEY_HEADER = "X-N8N-API-KEY";

    public static final String EXECUTIONS_URI = "/api/v1/executions";
    public static final String WORKFLOWS_URI = "/api/v1/workflows";

    public static final String INCLUDE_DATA_QUERY = "includeData";
    public static final String STATUS_QUERY = "status";
    public static final String WORKFLOW_ID_QUERY = "workflowId";
    public static final String PROJECT_ID_QUERY = "projectId";
    public static final String LIMIT_QUERY = "limit";
    public static final String CURSOR_QUERY = "cursor";
    public static final String ACTIVE_QUERY = "active";
    public static final String TAGS_QUERY = "tags";
    public static final String NAME_QUERY = "name";
    public static final String EXCLUDE_PINNED_DATA_QUERY = "excludePinnedData";

    public static final String LOAD_WORKFLOW_BODY = "loadWorkflow";
    public static final String DESTINATION_PROJECT_ID_BODY = "destinationProjectId";
}
