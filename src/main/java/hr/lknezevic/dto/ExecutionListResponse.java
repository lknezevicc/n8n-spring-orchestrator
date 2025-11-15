package hr.lknezevic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExecutionListResponse(
        List<ExecutionResponse> data,
        String nextCursor
) {}
