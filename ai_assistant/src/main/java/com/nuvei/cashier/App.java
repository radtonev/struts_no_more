package com.nuvei.cashier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import org.slf4j.Logger;

import com.nuvei.cashier.ai.client.GeminiAssistant;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;

public class App {

    static private final Logger logger = org.slf4j.LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        ChatModel model = GeminiAssistant.getModel("gemini-2.0-flash-lite");
        String userMessage = GeminiAssistant.getTemplate(
            Paths.get("src/main/resources/templates/add-field-prompt.tpl.txt"))
            .apply(Map.of("javaEntityClassContent",
                        Files.readString(Paths.get("src/test/java/com/nuvei/cashier/classes/Product.java.txt")),
                        "javaDTOClassContent",
                        Files.readString(Paths.get("src/test/java/com/nuvei/cashier/classes/ProductDTO.java.txt")),
                        "fieldName", "createdAt", "fieldType", "LocalDateTime"))
            .text();
        ChatResponse response = model.chat(UserMessage.from(userMessage));
        String aiResponseText = response.aiMessage().text();

        logger.info("AI> {}", aiResponseText);

        userMessage = GeminiAssistant.getTemplate(
            Paths.get("src/main/resources/templates/extract-source-prompt.tpl.txt"))
            .apply("Entity Java Class")
            .text();
        response = model.chat(SystemMessage.from(aiResponseText), UserMessage.from(userMessage));
        String responseText = response.aiMessage().text();

        logger.info("AI> {}", responseText);

        userMessage = GeminiAssistant.getTemplate(
            Paths.get("src/main/resources/templates/extract-source-prompt.tpl.txt"))
            .apply("DTO Java Class")
            .text();
        response = model.chat(SystemMessage.from(aiResponseText), UserMessage.from(userMessage));
        responseText = response.aiMessage().text();

        logger.info("AI> {}", responseText);

        userMessage = GeminiAssistant.getTemplate(
            Paths.get("src/main/resources/templates/extract-source-prompt.tpl.txt"))
            .apply("SQL statement")
            .text();
        response = model.chat(SystemMessage.from(aiResponseText), UserMessage.from(userMessage));
        responseText = response.aiMessage().text();

        logger.info("AI> {}", responseText);

        userMessage = GeminiAssistant.getTemplate(
                        Paths.get("src/main/resources/templates/verify.tpl.txt"))
                .apply(Map.of("fieldName", "createdAt", "fieldType", "LocalDateTime"))
                .text();
        response = model.chat(SystemMessage.from(responseText), UserMessage.from(userMessage));
        responseText = response.aiMessage().text();

        logger.info("AI> {}", responseText);
    }
}
