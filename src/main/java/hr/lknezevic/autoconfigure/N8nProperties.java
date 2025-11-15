package hr.lknezevic.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "n8n")
public class N8nProperties {
    // === n8n properties === //
    private String apiUrl;
    private String apiKey;

    private HttpClientProperties httpClientProperties = new HttpClientProperties();
    private StorageProperties storageProperties = new StorageProperties();
    private SchedulerProperties schedulerProperties = new SchedulerProperties();

    @Data
    public static class HttpClientProperties {
        private long connectionTimeoutMs = 30000;
        private long readTimeoutMs = 5000;
        private long writeTimeoutMs = 5000;
        private int retryTimes = 3;
        private long retryBackoffMs = 3000;
        private int maxInMemorySizeBytes = 16 * 1024 * 1024;
    }

    @Data
    public static class StorageProperties {
        private StorageType type = StorageType.REDIS;
        private String redisKeyPrefix = "n8n:executionId:";
    }

    public enum StorageType {
        IN_MEMORY,
        REDIS
    }

    @Data
    public static class SchedulerProperties {
        private int maxThreads = 20;
        private int queueCapacity = 100;
        private int ttlSeconds = 60;
    }
}
