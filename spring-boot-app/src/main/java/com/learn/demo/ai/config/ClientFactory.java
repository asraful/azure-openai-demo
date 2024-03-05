package com.learn.demo.ai.config;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {

    private ConfigurationProperties config;
    @Autowired
    public ClientFactory(ConfigurationProperties config) {
        this.config = config;
    }

    public OpenAIClient getOpenAIClient() {
        return new OpenAIClientBuilder()
                .endpoint(config.getApiEndpoint())
                .credential(new AzureKeyCredential(config.getApiKey()))
                .buildClient();
    }
}
