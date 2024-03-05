package com.learn.demo.ai.service;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.*;
import com.learn.demo.ai.config.ClientFactory;
import com.learn.demo.ai.config.ConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

    private final ClientFactory clientFactory;
    private final ConfigurationProperties config;

    @Autowired
    public ChatService(ClientFactory clientFactory, ConfigurationProperties config) {
        this.clientFactory = clientFactory;
        this.config = config;
    }

    public String chat() {


        LOGGER.info("Received request for chat [{}]");

        OpenAIClient client = clientFactory.getOpenAIClient();


        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestSystemMessage("You are a helpful assistant. You will talk like a pirate."));
        chatMessages.add(new ChatRequestUserMessage("Can you help me?"));
        chatMessages.add(new ChatRequestAssistantMessage("Of course, me hearty! What can I do for ye?"));
        chatMessages.add(new ChatRequestUserMessage("What's the best way to train a parrot?"));

        ChatCompletions chatCompletions = client.getChatCompletions(config.getDeploymentId(), getChatCompletionsOptions(chatMessages));

        System.out.printf("Model ID=%s is created at %s.%n", chatCompletions.getId(), chatCompletions.getCreatedAt());
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            System.out.printf("Index: %d, Chat Role: %s.%n", choice.getIndex(), message.getRole());
            System.out.println("Message:");
            System.out.println(message.getContent());
        }

        System.out.println();
        CompletionsUsage usage = chatCompletions.getUsage();
        System.out.printf("Usage: number of prompt token is %d, "
                        + "number of completion token is %d, and number of total tokens in request and response is %d.%n",
                usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());

        return "ok";
    }

    protected ChatCompletionsOptions getChatCompletionsOptions(List<ChatRequestMessage> messages) {
        ChatCompletionsOptions chatOptions = new ChatCompletionsOptions(messages);
        chatOptions.setMaxTokens(512);
        chatOptions.setTemperature(0.1);
        chatOptions.setTopP(1.0);
        chatOptions.setLogitBias(new HashMap<>());
        chatOptions.setN(1);
        chatOptions.setFrequencyPenalty(0.0);
        chatOptions.setPresencePenalty(0.0);
        return chatOptions;
    }
}
