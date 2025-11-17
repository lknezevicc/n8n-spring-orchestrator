package hr.lknezevic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hr.lknezevic.dto.workflow.*;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WorkflowResponse(
        String name,
        List<NodeDto> nodeDtos,
        Map<String, ConnectionDto> connections,
        SettingsDto settingsDto,
        StaticDataDto staticDataDto,
        List<SharedDto> sharedDto
) {}
