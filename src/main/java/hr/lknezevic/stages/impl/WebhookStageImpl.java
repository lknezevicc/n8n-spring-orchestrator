package hr.lknezevic.stages.impl;

import hr.lknezevic.actions.webhook.TriggerWebhookAction;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.stages.WebhookStage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WebhookStageImpl implements WebhookStage {
    private final HttpClientModule httpClient;

    @Override
    public TriggerWebhookAction trigger(String webhookPath) {
        return new TriggerWebhookAction(httpClient, webhookPath);
    }
}
