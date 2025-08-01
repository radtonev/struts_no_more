package com.nuvei.cashier.code.parser;

import dev.langchain4j.internal.Utils;

public class MarkdownResponseParser implements IResponseParser<String> {

    private final String language;

    public MarkdownResponseParser() {
        this("java");
    }

    public MarkdownResponseParser(String language) {
        this.language = language;
    }

    @Override
    public String parse(String llmResponse) {
        if (Utils.isNullOrEmpty(llmResponse)) {
            return null;
        }

        llmResponse = llmResponse.replaceAll("(?i)" + language, language);
        String fence = "```" + language;
        int start = llmResponse.indexOf(fence);
        if (start == -1) return null;
        start = llmResponse.indexOf('\n', start) + 1;

        int end = llmResponse.indexOf("```", start);
        if (end == -1) return null;

        return llmResponse.substring(start, end).trim();
    }

}
