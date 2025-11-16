package hr.lknezevic.stages.impl;

import hr.lknezevic.actions.execution.DeleteExecutionAction;
import hr.lknezevic.actions.execution.GetListExecutionAction;
import hr.lknezevic.actions.execution.RetryExecutionAction;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.stages.ExecutionStage;
import hr.lknezevic.actions.execution.GetExecutionAction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExecutionStageImpl implements ExecutionStage {
    private final HttpClientModule httpClient;

    @Override
    public GetExecutionAction get(String executionId) {
        return new GetExecutionAction(httpClient, executionId);
    }

    @Override
    public GetListExecutionAction list() {
        return new GetListExecutionAction(httpClient);
    }

    @Override
    public DeleteExecutionAction delete(String executionId) {
        return new DeleteExecutionAction(httpClient, executionId);
    }

    @Override
    public RetryExecutionAction retry(String executionId) {
        return new RetryExecutionAction(httpClient, executionId);
    }
}
