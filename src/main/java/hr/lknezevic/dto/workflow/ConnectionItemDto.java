package hr.lknezevic.dto.workflow;

public record ConnectionItemDto(
        String node,
        String type,
        int index
) {}
