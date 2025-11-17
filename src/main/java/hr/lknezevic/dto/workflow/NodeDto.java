package hr.lknezevic.dto.workflow;

import java.util.List;

public record NodeDto(
        String id,
        String name,
        String webhookId,
        boolean disabled,
        boolean notesInFlow,
        String notes,
        String type,
        int typeVersion,
        boolean executeOnce,
        boolean alwaysOutputData,
        boolean retryOnFail,
        int maxTries,
        int waitBetweenTries,
        String onError,
        List<Integer> position,
        ParametersDto parametersDto,
        CredentialsDto credentialsDto
) {}
