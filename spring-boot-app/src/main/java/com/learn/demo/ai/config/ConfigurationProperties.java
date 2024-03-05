package com.learn.demo.ai.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ConfigurationProperties {
    @Value("${spring.ai.azure.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.azure.openai.endpoint}")
    private String apiEndpoint;

    @Value("${spring.ai.azure.openai.chat.model}")
    private String deploymentId;

    @Value("${app.azure.cognitive.search.index}")
    private String cognitiveSearchIndex;

    @Value("${app.azure.cognitive.search.endpoint}")
    private String cognitiveSearchEndpoint;

    @Value("${app.azure.cognitive.search.key}")
    private String cognitiveSearchKey;
    @Value("${app.azue.cognitive.search.user.provided.data.vectorizationSource}")
    private String vectorizationSource;
}
