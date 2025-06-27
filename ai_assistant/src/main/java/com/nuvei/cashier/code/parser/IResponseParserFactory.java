package com.nuvei.cashier.code.parser;

public interface IResponseParserFactory<T> {

    IResponseParser<T> createParser(String language);
}
