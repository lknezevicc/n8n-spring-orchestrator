package hr.lknezevic.orchestrator;

import hr.lknezevic.stages.ExecutionStage;
import hr.lknezevic.stages.ResultStack;
import hr.lknezevic.stages.WebhookStage;
import hr.lknezevic.stages.WorkflowStage;

public sealed interface WorkflowOrchestrator permits WorkflowOrchestratorImpl {
    ExecutionStage executions();
    WebhookStage webhooks();
    WorkflowStage workflows();
    ResultStack results();
}
