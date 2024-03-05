package com.learn.demo.ai.service;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.*;
import com.learn.demo.ai.config.ClientFactory;
import com.learn.demo.ai.config.ConfigFactory;
import com.learn.demo.ai.config.ConfigurationProperties;
import com.learn.demo.ai.dto.DataSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ChatCompletionsWithCustomData {


    private final ClientFactory clientFactory;

    private final ConfigFactory configFactory;

    private final ConfigurationProperties config;


    @Autowired
    public ChatCompletionsWithCustomData(ClientFactory clientFactory, ConfigFactory configFactory, ConfigurationProperties config) {
        this.clientFactory = clientFactory;
        this.configFactory = configFactory;
        this.config = config;
    }



    public DataSummary chatWithCustomData(String query) {

        OpenAIClient client = clientFactory.getOpenAIClient();
        AzureCognitiveSearchChatExtensionConfiguration searchConfiguration = configFactory.getAzureCognitiveSearchChatExtensionConfiguration();

        ///fallback
        if(query == null || query.isEmpty()) {
            query = "birth rate";
        }
        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestUserMessage(query));
        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages)
                .setDataSources(Arrays.asList(searchConfiguration));

        ChatCompletions chatCompletions = client.getChatCompletions(config.getDeploymentId(), chatCompletionsOptions);


        StringBuilder content = new StringBuilder();
        StringBuilder context = new StringBuilder();

        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            System.out.printf("Answer: %s%n%n", message.getContent());

            content.append(message.getContent());
            // If Azure OpenAI chat extensions are configured, this array represents the incremental steps performed
            // by those extensions while processing the chat completions request.
            List<ChatResponseMessage> contextMessages = message.getContext().getMessages();
            for (ChatResponseMessage contextMessage : contextMessages) {
                System.out.println("Context Message: ");
                System.out.println("   - " + contextMessage.getContent());

                context.append(contextMessage.getContent());
            }
        }
        DataSummary dataSummary = new DataSummary();
        dataSummary.setData(content.toString());
        //dataSummary.setContextMessage(context.toString());
        return dataSummary;
    }
}
