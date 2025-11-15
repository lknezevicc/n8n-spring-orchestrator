package hr.lknezevic.stages;

import hr.lknezevic.actions.execution.GetExecutionAction;
import hr.lknezevic.actions.execution.GetListExecutionAction;

public interface ExecutionStage {
    GetExecutionAction get(String executionId);
    GetListExecutionAction list();
}
