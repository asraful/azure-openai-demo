package com.learn.demo.ai.config;

import com.azure.ai.openai.models.*;
import org.springframework.stereotype.Component;

@Component
public class ConfigFactory {
    private final ConfigurationProperties config;

    public ConfigFactory(ConfigurationProperties config) {
        this.config = config;
    }

    public AzureCognitiveSearchChatExtensionConfiguration getAzureCognitiveSearchChatExtensionConfiguration() {
        return new AzureCognitiveSearchChatExtensionConfiguration(
                        new AzureCognitiveSearchChatExtensionParameters(config.getCognitiveSearchEndpoint(), config.getCognitiveSearchIndex())
                                .setAuthentication(new OnYourDataApiKeyAuthenticationOptions(config.getCognitiveSearchKey()))
                                .setQueryType(AzureCognitiveSearchQueryType.VECTOR_SIMPLE_HYBRID) // SIMPLE, VECTOR, or Hybrid
                                .setInScope(true)
                                .setTopNDocuments(2)
                                // the deployment name of the embedding model when you are using a vector or hybrid query type
                                .setEmbeddingDependency(new OnYourDataDeploymentNameVectorizationSource(config.getVectorizationSource()))
                                .setFieldsMapping(
                                        new AzureCognitiveSearchIndexFieldMappingOptions()
                                                //.setTitleField("HotelName")
                                                .setTitleField("content"))
                        //.setContentFields(Arrays.asList("Description"))

                );
    }

}
