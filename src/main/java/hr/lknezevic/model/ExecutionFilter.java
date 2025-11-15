package hr.lknezevic.model;

import hr.lknezevic.enums.ExecutionStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecutionFilter {
    @Builder.Default
    private final boolean includeData = false;

    @Builder.Default
    private final ExecutionStatus status = ExecutionStatus.UNKNOWN;

    @Builder.Default
    private final String workflowId = "";

    @Builder.Default
    private final String projectId = "";

    @Builder.Default
    private final int limit = 100;

    @Builder.Default
    private final String cursor = "";
}
