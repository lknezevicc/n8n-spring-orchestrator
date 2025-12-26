package hr.lknezevic.dto.workflow;

public record SettingsDto(
        boolean saveExecutionProgress,
        boolean saveManualExecutions,
        String saveDataErrorExecution,
        String saveDataSuccessExecution,
        int executionTimeout,
        String errorWorkflow,
        String timezone,
        String executionOrder,
        String callerPolicy,
        String callerIds,
        int timeSavedPerExecution,
        boolean availableInMCP
) {}
