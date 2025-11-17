package hr.lknezevic.actions.execution;

import hr.lknezevic.actions.abstracts.AbstractDeleteAction;
import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.utils.ConstantsUtility;
import hr.lknezevic.utils.UriQueryParameterBuilder;

public final class DeleteExecutionAction extends AbstractDeleteAction {
    private final String executionId;

    public DeleteExecutionAction(HttpClientModule httpClient, String executionId) {
        super(httpClient);
        this.executionId = executionId;
    }

    @Override
    protected String buildUri() {
        return new UriQueryParameterBuilder(ConstantsUtility.EXECUTIONS_URI + "/" + executionId)
                .build();
    }
}
