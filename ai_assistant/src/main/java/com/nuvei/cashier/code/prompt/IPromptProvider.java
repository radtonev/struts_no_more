package com.nuvei.cashier.code.prompt;

import java.util.Map;

public interface IPromptProvider {

    String getPrompt(Map<String, Object> variables);
}
