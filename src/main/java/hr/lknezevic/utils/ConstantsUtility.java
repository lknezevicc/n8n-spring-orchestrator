package hr.lknezevic.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantsUtility {
    public static final String N8N_API_KEY_HEADER = "X-N8N-API-KEY";

    public static final String GET_EXECUTIONS_URI = "/api/v1/executions";
    public static final String GET_EXECUTIONS_INCLUDE_DATA_QUERY = "includeData";
    public static final String GET_EXECUTIONS_STATUS_QUERY = "status";
    public static final String GET_EXECUTIONS_WORKFLOW_ID_QUERY = "workflowId";
    public static final String GET_EXECUTIONS_PROJECT_ID_QUERY = "projectId";
    public static final String GET_EXECUTIONS_LIMIT_QUERY = "limit";
    public static final String GET_EXECUTIONS_CURSOR_QUERY = "cursor";
}
