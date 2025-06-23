package com.nuvei.cashier.code.prompt;

import java.util.Map;

import com.nuvei.cashier.code.ClassRole;

public interface IPromptProvider {

    String getPrompt(ClassRole classRole, Map<String, Object> variables);

}
