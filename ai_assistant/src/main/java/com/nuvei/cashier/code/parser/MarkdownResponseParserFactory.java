package com.nuvei.cashier.code.parser;

public class MarkdownResponseParserFactory implements IResponseParserFactory<String> {

    @Override
    public IResponseParser<String> createParser(String language) {
        return new MarkdownResponseParser(language);
    }
}
