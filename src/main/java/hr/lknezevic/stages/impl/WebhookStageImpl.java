package hr.lknezevic.stages.impl;

import hr.lknezevic.actions.webhook.TriggerWebhookAction;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.stages.WebhookStage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WebhookStageImpl implements WebhookStage {
    private final HttpExecutor httpExecutor;

    @Override
    public TriggerWebhookAction trigger(String webhookPath) {
        return new TriggerWebhookAction(httpExecutor, webhookPath);
    }
}
