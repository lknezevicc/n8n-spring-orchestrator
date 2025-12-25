package hr.lknezevic.orchestrator;

import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.stages.ExecutionStage;
import hr.lknezevic.stages.ResultStack;
import hr.lknezevic.stages.impl.ExecutionStageImpl;
import hr.lknezevic.stages.WebhookStage;
import hr.lknezevic.stages.WorkflowStage;
import hr.lknezevic.stages.impl.WebhookStageImpl;
import hr.lknezevic.stages.impl.WorkflowStageImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkflowOrchestratorImpl implements WorkflowOrchestrator {
    private final HttpClientModule httpClient;

    @Override
    public ExecutionStage executions() {
        return new ExecutionStageImpl(httpClient);
    }

    @Override
    public WebhookStage webhooks() {
        return new WebhookStageImpl(httpClient);
    }

    @Override
    public WorkflowStage workflows() {
        return new WorkflowStageImpl(httpClient);
    }

    @Override
    public ResultStack results() {
        throw new UnsupportedOperationException("ResultStack not yet implemented!");
    }
}
