package hr.lknezevic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hr.lknezevic.enums.ExecutionMode;
import hr.lknezevic.enums.ExecutionStatus;

import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExecutionResponse(
        Long id,
        Map<String, Object> data,
        boolean finished,
        ExecutionMode mode,
        Integer retryOf,
        Integer retrySuccessId,
        LocalDateTime startedAt,
        LocalDateTime stoppedAt,
        Long workflowId,
        LocalDateTime waitTill,
        Map<String, Object> customData,
        ExecutionStatus status
) { }
