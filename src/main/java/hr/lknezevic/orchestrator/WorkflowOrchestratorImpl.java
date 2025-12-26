package hr.lknezevic.orchestrator;

import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.stages.ExecutionStage;
import hr.lknezevic.stages.ResultStack;
import hr.lknezevic.stages.WebhookStage;
import hr.lknezevic.stages.WorkflowStage;
import hr.lknezevic.stages.impl.ExecutionStageImpl;
import hr.lknezevic.stages.impl.WebhookStageImpl;
import hr.lknezevic.stages.impl.WorkflowStageImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkflowOrchestratorImpl implements WorkflowOrchestrator {
    private final HttpExecutor httpExecutor;

    @Override
    public ExecutionStage executions() {
        return new ExecutionStageImpl(httpExecutor);
    }

    @Override
    public WebhookStage webhooks() {
        return new WebhookStageImpl(httpExecutor);
    }

    @Override
    public WorkflowStage workflows() {
        return new WorkflowStageImpl(httpExecutor);
    }

    @Override
    public ResultStack results() {
        throw new UnsupportedOperationException("ResultStack not yet implemented!");
    }
}
