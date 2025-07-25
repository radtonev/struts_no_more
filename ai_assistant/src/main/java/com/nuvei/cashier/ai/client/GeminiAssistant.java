package com.nuvei.cashier.ai.client;

import org.slf4j.Logger;

import dev.langchain4j.internal.Utils;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import java.time.Duration;

public class GeminiAssistant {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GeminiAssistant.class);

    // We need a model with at least output tokens: 65,536
    private static final String DEFAULT_GEMINI_MODEL = "gemini-2.5-flash-lite-preview-06-17";

    public static ChatModel getModel(String modelName) {
        logger.debug("Using Gemini model: {}", modelName);

        return GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .modelName(modelName)
                .timeout(Duration.ofSeconds(10))
                .maxRetries(3)
                .logRequestsAndResponses(false)
                .build();
    }

    public static ChatModel getModel() {
        return getModel(Utils.getOrDefault(System.getenv("GEMINI_MODEL"), DEFAULT_GEMINI_MODEL));
    }

}
