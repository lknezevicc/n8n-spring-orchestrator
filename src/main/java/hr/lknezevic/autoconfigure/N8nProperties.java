package hr.lknezevic.autoconfigure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.lknezevic.reactive.http.config.HttpClientConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "n8n")
public final class N8nProperties {

    private final String baseUrl;
    @JsonIgnore
    private final String apiKey;

    @NestedConfigurationProperty
    private final HttpClientConfig httpClientConfig;

    @ConstructorBinding
    public N8nProperties(String baseUrl, String apiKey, HttpClientConfig httpClientConfig) {
        if (baseUrl == null || baseUrl.isBlank()) throw new IllegalArgumentException("baseUrl cannot be null or blank");
        if (apiKey == null || apiKey.isBlank()) throw new IllegalArgumentException("apiKey cannot be null or blank");

        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.httpClientConfig = httpClientConfig != null
                ? httpClientConfig
                : HttpClientConfig.withDefaults(baseUrl);
    }

    @Override
    public String toString() {
        return "N8nProperties{baseUrl=" + baseUrl + ", httpClientConfig=" + httpClientConfig + "}";
    }
}
