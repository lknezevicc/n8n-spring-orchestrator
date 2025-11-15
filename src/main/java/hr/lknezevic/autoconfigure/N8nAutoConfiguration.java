package hr.lknezevic.autoconfigure;

import hr.lknezevic.modules.HttpClientModule;
import hr.lknezevic.modules.impl.N8nClientModule;
import hr.lknezevic.orchestrator.WorkflowOrchestrator;
import hr.lknezevic.orchestrator.WorkflowOrchestratorImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@AutoConfiguration
@EnableConfigurationProperties(N8nProperties.class)
public class N8nAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "n8nScheduler")
    public Scheduler n8nScheduler(N8nProperties properties) {
        return Schedulers.newBoundedElastic(
                properties.getSchedulerProperties().getMaxThreads(),
                properties.getSchedulerProperties().getQueueCapacity(),
                "n8n-sdk",
                properties.getSchedulerProperties().getTtlSeconds(),
                true
        );
    }

    @Bean
    @ConditionalOnMissingBean(name = "n8nClient")
    public HttpClientModule n8nClient(N8nProperties properties, @Qualifier("n8nScheduler") Scheduler scheduler) {
        return new N8nClientModule(properties, scheduler);
    }

    @Bean
    @ConditionalOnMissingBean
    public WorkflowOrchestrator getWorkflowOrchestrator(@Qualifier("n8nClient") HttpClientModule httpClientModule) {
        return new WorkflowOrchestratorImpl(httpClientModule);
    }

}
