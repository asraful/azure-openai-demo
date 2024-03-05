package com.learn.demo.ai.service;

import com.azure.ai.openai.models.Choice;
import com.azure.ai.openai.models.Completions;
import com.azure.ai.openai.models.CompletionsOptions;
import com.azure.ai.openai.models.CompletionsUsage;
import com.learn.demo.ai.config.ClientFactory;
import com.learn.demo.ai.config.ConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompletionsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompletionsService.class);

    private final ClientFactory clientFactory;
    private final ConfigurationProperties config;

    @Autowired
    public CompletionsService(ClientFactory clientFactory, ConfigurationProperties config) {
        this.clientFactory = clientFactory;
        this.config = config;
    }

    public String complete() {

        LOGGER.info("Received request for chat completion [{}]");

        List<String> prompt = new ArrayList<>();
        prompt.add("When was Microsoft founded?");

        Completions completions = clientFactory.getOpenAIClient().getCompletions(config.getDeploymentId(), new CompletionsOptions(prompt));

        System.out.printf("Model ID=%s is created at %s.%n", completions.getId(), completions.getCreatedAt());
        for (Choice choice : completions.getChoices()) {
            System.out.printf("Index: %d, Text: %s.%n", choice.getIndex(), choice.getText());
        }

        CompletionsUsage usage = completions.getUsage();
        System.out.printf("Usage: number of prompt token is %d, "
                        + "number of completion token is %d, and number of total tokens in request and response is %d.%n",
                usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());
        return completions.getChoices().get(0).getText();
    }
}
