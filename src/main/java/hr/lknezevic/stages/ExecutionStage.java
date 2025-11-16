package hr.lknezevic.stages;

import hr.lknezevic.actions.execution.DeleteExecutionAction;
import hr.lknezevic.actions.execution.GetExecutionAction;
import hr.lknezevic.actions.execution.GetListExecutionAction;
import hr.lknezevic.actions.execution.RetryExecutionAction;

public interface ExecutionStage {
    GetExecutionAction get(String executionId);
    GetListExecutionAction list();
    DeleteExecutionAction delete(String executionId);
    RetryExecutionAction retry(String executionId);
}
