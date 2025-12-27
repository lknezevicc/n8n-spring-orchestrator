package hr.lknezevic.actions.webhook;

import hr.lknezevic.reactive.http.action.AbstractHttpAction;
import hr.lknezevic.reactive.http.action.HttpRequestSpec;
import hr.lknezevic.reactive.http.transport.HttpExecutor;

public final class TriggerWebhookAction extends AbstractHttpAction<Void> {
    private final String webhookPath;
    private final Object payload;

    public TriggerWebhookAction(HttpExecutor httpExecutor, String webhookPath) {
        this(httpExecutor, webhookPath, null);
    }

    TriggerWebhookAction(HttpExecutor httpExecutor, String webhookPath, Object payload) {
        super(httpExecutor);
        this.webhookPath = webhookPath;
        this.payload = payload;
    }

    public TriggerWebhookAction withPayload(Object payload) {
        return new TriggerWebhookAction(this.httpExecutor, this.webhookPath, payload);
    }

    @Override
    protected HttpRequestSpec<Void> getRequestSpec() {
        String uriPath = webhookPath.startsWith("/") ? webhookPath : "/" + webhookPath;

        return HttpRequestSpec.post(uriPath, payload, Void.class);
    }
}
