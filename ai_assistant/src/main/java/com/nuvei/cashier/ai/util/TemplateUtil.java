package com.nuvei.cashier.ai.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import dev.langchain4j.model.input.PromptTemplate;

public class TemplateUtil {

    private TemplateUtil() {
        // Utility class, no instantiation allowed
    }

    public static PromptTemplate getTemplate(String templateString) {
        return PromptTemplate.from(templateString);
    }

    public static PromptTemplate getTemplate(Path templatePath) throws IOException {
        return getTemplate(Files.readString(templatePath));
    }

    public static PromptTemplate getTemplate(InputStream templateStream) throws IOException {
        return getTemplate(new String(templateStream.readAllBytes(), StandardCharsets.UTF_8));
    }

    public static PromptTemplate getTemplateFromResource(String resourcePath) {
        try {
            try (InputStream inputStream = TemplateUtil.class.getResourceAsStream(resourcePath)) {
                if (inputStream == null) {
                    throw new IOException("Resource not found: " + resourcePath);
                }
                return getTemplate(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
