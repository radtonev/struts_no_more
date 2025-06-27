package com.nuvei.cashier.code.parser;

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
        String fence = "```" + language;
        int start = llmResponse.indexOf(fence);
        if (start == -1) return null;
        start = llmResponse.indexOf('\n', start) + 1;

        int end = llmResponse.indexOf("```", start);
        if (end == -1) return null;

        return llmResponse.substring(start, end).trim();
    }

}
