package hr.lknezevic.stages.impl;

import hr.lknezevic.actions.workflow.*;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.stages.WorkflowStage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkflowStageImpl implements WorkflowStage {
    private final HttpExecutor httpExecutor;

    @Override
    public ActivateWorkflowAction activate(String workflowId) {
        return new ActivateWorkflowAction(httpExecutor, workflowId);
    }

    @Override
    public DeactivateWorkflowAction deactivate(String workflowId) {
        return new DeactivateWorkflowAction(httpExecutor, workflowId);
    }

    @Override
    public DeleteWorkflowAction delete(String workflowId) {
        return new DeleteWorkflowAction(httpExecutor, workflowId);
    }

    @Override
    public GetWorkflowAction get(String workflowId) {
        return new GetWorkflowAction(httpExecutor, workflowId);
    }

    @Override
    public GetListWorkflowAction list() {
        return new GetListWorkflowAction(httpExecutor);
    }

    @Override
    public TransferWorkflowToAnotherProjectAction transferToAnotherProject(String workflowId, String destinationProjectId) {
        return new TransferWorkflowToAnotherProjectAction(httpExecutor, workflowId, destinationProjectId);
    }
}
