package com.learn.demo.ai.web;

import com.learn.demo.ai.dto.DataSummary;
import com.learn.demo.ai.dto.Question;
import com.learn.demo.ai.service.ChatCompletionsWithCustomData;
import com.learn.demo.ai.service.ChatService;
import com.learn.demo.ai.service.CompletionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompletionsController {

    private CompletionsService service;
    private ChatService chatService;

    private ChatCompletionsWithCustomData chatCompletionsWithYourData;

    private String message = "How can you help me?";

    public CompletionsController(CompletionsService service, ChatService chatService, ChatCompletionsWithCustomData chatCompletionsWithYourData) {
        this.service = service;
        this.chatService = chatService;
        this.chatCompletionsWithYourData = chatCompletionsWithYourData;
    }

    @PostMapping("/ai/generate")
    public ResponseEntity<String> completion(@RequestBody String message) {
        if (message.isEmpty()) {
            message = message;
        }
        return ResponseEntity.ok(service.complete());
    }

    @PostMapping("/ai/chat")
    public ResponseEntity<String> chat(@RequestBody String message) {
        if (message.isEmpty()) {
            message = message;
        }
        chatService.chat();
        return ResponseEntity.ok("ok");
    }


    @PostMapping("/api/cognitive/search")
    public ResponseEntity<DataSummary> cognitiveSearch(@RequestBody Question question) throws InterruptedException {
        DataSummary dataSummary = chatCompletionsWithYourData.chatWithCustomData(question.getUserQuestion());
        return ResponseEntity.ok(dataSummary);
    }

    @PostMapping("/api/cognitive/hello")
    public ResponseEntity<DataSummary> hello(@RequestBody String message) {
        if (message.isEmpty()) {
            message = message;
        }
        System.out.println("Hello , controller " + message);

        DataSummary dataSummary = new DataSummary();
        dataSummary.setData("Hello Data");
        return ResponseEntity.ok(dataSummary);
    }
}