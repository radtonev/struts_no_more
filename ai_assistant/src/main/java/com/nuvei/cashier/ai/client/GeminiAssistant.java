package com.nuvei.cashier.ai.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import org.slf4j.Logger;

import dev.langchain4j.internal.Utils;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.input.PromptTemplate;

public class GeminiAssistant {

    static private final Logger logger = org.slf4j.LoggerFactory.getLogger(GeminiAssistant.class);

    private static final String DEFAULT_GEMINI_MODEL = "gemini-2.0-flash";

    public static ChatModel getModel(String modelName) {
        logger.debug("Using Gemini model: {}", modelName);

        return GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .modelName(modelName)
                .timeout(Duration.ofSeconds(60))
                .maxRetries(3)
                .build();
    }

    public static ChatModel getModel() {
        return getModel(Utils.getOrDefault(System.getenv("GEMINI_MODEL"), DEFAULT_GEMINI_MODEL));
    }

    public static PromptTemplate getTemplate(String templateString) {
        return PromptTemplate.from(templateString);
    }

    public static PromptTemplate getTemplate(Path templatePath) throws IOException {
        return getTemplate(Files.readString(templatePath));
    }

}
