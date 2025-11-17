package hr.lknezevic.stages.impl;

import hr.lknezevic.actions.execution.GetListExecutionAction;
import hr.lknezevic.actions.workflow.*;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.stages.WorkflowStage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkflowStageImpl implements WorkflowStage {
    private final HttpClientModule httpClient;

    @Override
    public ActivateWorkflowAction activate(String workflowId) {
        return new ActivateWorkflowAction(httpClient, workflowId);
    }

    @Override
    public DeactivateWorkflowAction deactivate(String workflowId) {
        return new DeactivateWorkflowAction(httpClient, workflowId);
    }

    @Override
    public DeleteWorkflowAction delete(String workflowId) {
        return new DeleteWorkflowAction(httpClient, workflowId);
    }

    @Override
    public GetWorkflowAction get(String workflowId) {
        return new GetWorkflowAction(httpClient, workflowId);
    }

    @Override
    public GetListExecutionAction list() {
        return new GetListWorkflowAction(httpClient);
    }

    @Override
    public TransferWorkflowToAnotherProjectAction transferToAnotherProject(String workflowId, String destinationProjectId) {
        return new TransferWorkflowToAnotherProjectAction(httpClient, workflowId, destinationProjectId);
    }
}
