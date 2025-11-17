package hr.lknezevic.stages;

import hr.lknezevic.actions.workflow.*;

public interface WorkflowStage {
    ActivateWorkflowAction activate(String workflowId);
    DeactivateWorkflowAction deactivate(String workflowId);
    DeleteWorkflowAction delete(String workflowId);
    GetWorkflowAction get(String workflowId);
    GetListWorkflowAction list();
    TransferWorkflowToAnotherProjectAction transferToAnotherProject(String workflowId, String destinationProjectId);
}
