package hr.lknezevic.dto.workflow;

import java.util.List;

public record ConnectionDto(
        List<List<ConnectionItemDto>> main
) {}
