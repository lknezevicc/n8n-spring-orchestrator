package hr.lknezevic.dto.workflow;

import java.time.LocalDateTime;

public record TagDto(
        String id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }
