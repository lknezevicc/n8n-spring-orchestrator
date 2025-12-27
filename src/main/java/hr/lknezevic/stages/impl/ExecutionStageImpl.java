package hr.lknezevic.stages.impl;

import hr.lknezevic.actions.execution.DeleteExecutionAction;
import hr.lknezevic.actions.execution.GetExecutionAction;
import hr.lknezevic.actions.execution.GetListExecutionAction;
import hr.lknezevic.actions.execution.RetryExecutionAction;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.stages.ExecutionStage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ExecutionStageImpl implements ExecutionStage {
    private final HttpExecutor httpExecutor;

    @Override
    public GetExecutionAction get(String executionId) {
        return new GetExecutionAction(httpExecutor, executionId);
    }

    @Override
    public GetListExecutionAction list() {
        return new GetListExecutionAction(httpExecutor);
    }

    @Override
    public DeleteExecutionAction delete(String executionId) {
        return new DeleteExecutionAction(httpExecutor, executionId);
    }

    @Override
    public RetryExecutionAction retry(String executionId) {
        return new RetryExecutionAction(httpExecutor, executionId);
    }
}
