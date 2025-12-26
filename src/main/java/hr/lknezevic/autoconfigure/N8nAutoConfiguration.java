package hr.lknezevic.autoconfigure;

import hr.lknezevic.orchestrator.WorkflowOrchestrator;
import hr.lknezevic.orchestrator.WorkflowOrchestratorImpl;
import hr.lknezevic.reactive.http.config.HttpClientConfig;
import hr.lknezevic.reactive.http.transport.HttpExecutor;
import hr.lknezevic.reactive.http.transport.WebClientFactory;
import hr.lknezevic.reactive.http.transport.WebClientHttpExecutor;
import hr.lknezevic.utils.ConstantsUtility;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(N8nProperties.class)
public class N8nAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "n8nHttpExecutor")
    public HttpExecutor n8nHttpExecutor(N8nProperties properties) {
        HttpClientConfig httpClientConfig = properties.getHttpClientConfig()
                .withHeader("Content-Type", "application/json")
                .withHeader("Accept", "application/json")
                .withHeader(ConstantsUtility.N8N_API_KEY_HEADER, properties.getApiKey());

        return new WebClientHttpExecutor(
                WebClientFactory.createWithDefaultFilters(httpClientConfig)
        );
    }

    @Bean
    @ConditionalOnMissingBean(name = "workflowOrchestrator")
    public WorkflowOrchestrator workflowOrchestrator(@Qualifier("n8nHttpExecutor") HttpExecutor httpExecutor) {
        return new WorkflowOrchestratorImpl(httpExecutor);
    }

}
