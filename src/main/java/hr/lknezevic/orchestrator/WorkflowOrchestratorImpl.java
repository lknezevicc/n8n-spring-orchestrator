package hr.lknezevic.orchestrator;

import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.stages.*;
import hr.lknezevic.stages.impl.ActionBuilderStageImpl;
import hr.lknezevic.stages.impl.ExecutionStageImpl;
import hr.lknezevic.stages.impl.WebhookStageImpl;
import hr.lknezevic.stages.impl.WorkflowStageImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class WorkflowOrchestratorImpl implements WorkflowOrchestrator {
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

    @Override
    public ActionBuilderStage actionBuilder() {
        return new ActionBuilderStageImpl(httpExecutor);
    }
}
