package hr.lknezevic.dto.workflow;

public record SharedDto(
        String role,
        String workflowId,
        String projectId,
        ProjectDto projectDto
) {}
