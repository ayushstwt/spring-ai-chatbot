package com.narainox.springAI_chat;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
@RequestMapping("/api/ai")
public class AIController {
    private OllamaChatModel client;

    private static final String PROMPT = "what is java language";

    public AIController(OllamaChatModel client) {
        this.client = client;
    }

    @GetMapping("/prompt")
    public Flux<ChatResponse> promptResponse(
            @RequestParam("prompt") String prompt
    ) {
        Prompt promptOb = new Prompt(prompt);
        Flux<ChatResponse> response = client.stream(promptOb);
        return response;

    }
}
