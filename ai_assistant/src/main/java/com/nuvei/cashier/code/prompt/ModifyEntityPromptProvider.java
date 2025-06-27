package com.nuvei.cashier.code.prompt;

import java.io.IOException;
import java.util.Map;

import com.nuvei.cashier.ai.util.TemplateUtil;

import dev.langchain4j.model.input.PromptTemplate;

public class ModifyEntityPromptProvider implements IPromptProvider {

    private final PromptTemplate template;

    public ModifyEntityPromptProvider() throws IOException {
        this.template = TemplateUtil.getTemplateFromResource("/prompt/modify_entity_prompt.tpl.txt");
    }

    @Override
    public String getPrompt(Map<String, Object> variables) {
        return template.apply(variables).text();
    }

}
