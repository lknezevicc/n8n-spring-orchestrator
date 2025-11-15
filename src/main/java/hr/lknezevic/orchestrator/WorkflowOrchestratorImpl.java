package hr.lknezevic.orchestrator;

import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.stages.ExecutionStage;
import hr.lknezevic.stages.impl.ExecutionStageImpl;
import hr.lknezevic.stages.WebhookStage;
import hr.lknezevic.stages.WorkflowStage;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Executor;

@RequiredArgsConstructor
public class WorkflowOrchestratorImpl implements WorkflowOrchestrator {
    private final HttpClientModule httpClient;

    @Override
    public ExecutionStage executions() {
        return new ExecutionStageImpl(httpClient);
    }

    @Override
    public WebhookStage webhooks() {
        return null;
    }

    @Override
    public WorkflowStage workflows() {
        return null;
    }
}
