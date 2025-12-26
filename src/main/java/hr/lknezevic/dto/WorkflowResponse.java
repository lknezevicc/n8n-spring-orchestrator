package hr.lknezevic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hr.lknezevic.dto.workflow.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WorkflowResponse(
        String id,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<NodeDto> nodes,
        Map<String, ConnectionDto> connections,
        SettingsDto settings,
        StaticDataDto staticData,
        List<TagDto> tags,
        List<SharedDto> shared
) {}
