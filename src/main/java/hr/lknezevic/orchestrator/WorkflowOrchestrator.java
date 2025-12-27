package hr.lknezevic.orchestrator;

import hr.lknezevic.stages.*;

public sealed interface WorkflowOrchestrator permits WorkflowOrchestratorImpl {
    ExecutionStage executions();
    WebhookStage webhooks();
    WorkflowStage workflows();
    ResultStack results();
    ActionBuilderStage actionBuilder();
}
