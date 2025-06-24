package com.nuvei.cashier.code.parser;

public interface IResponseParser<T> {

  T parse(String llmResponse);
}
