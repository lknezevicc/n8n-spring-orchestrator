package hr.lknezevic.dto.workflow;

import java.util.Map;

public record ParametersDto(
        Map<String, Object> additionalProperties
) {}
