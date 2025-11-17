package hr.lknezevic.stages;

import hr.lknezevic.actions.webhook.TriggerWebhookAction;

public interface WebhookStage {
    TriggerWebhookAction trigger(String webhookPath);
}
