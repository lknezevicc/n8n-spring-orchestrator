package hr.lknezevic.actions.webhook;

import hr.lknezevic.actions.abstracts.AbstractPostAction;
import hr.lknezevic.modules.HttpClientModule;
import lombok.With;

public final class TriggerWebhookAction extends AbstractPostAction<Void> {
    private final String webhookPath;
    @With
    private final Object payload;

    public TriggerWebhookAction(HttpClientModule httpClient, String webhookPath) {
        super(httpClient, Void.class);
        this.webhookPath = webhookPath;
        this.payload = null;
    }

    private TriggerWebhookAction(HttpClientModule httpClient, String webhookPath, Object payload) {
        super(httpClient, Void.class);
        this.webhookPath = webhookPath;
        this.payload = payload;
    }

    @Override
    protected Object buildRequest() {
        return payload;
    }

    @Override
    protected String buildUri() {
        return webhookPath.startsWith("/") ? webhookPath : "/" + webhookPath;
    }
}
