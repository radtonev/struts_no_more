package com.nuvei.cashier.code.parser;

public interface IResponseParser<T> {
  /** Extracts the altered code or structured result from raw LLM text. */
  T parse(String llmRawResponse);
}
