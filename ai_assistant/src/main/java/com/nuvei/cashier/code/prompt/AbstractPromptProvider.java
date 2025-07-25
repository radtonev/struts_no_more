package com.nuvei.cashier.code.prompt;

import com.nuvei.cashier.ai.util.TemplateUtil;
import dev.langchain4j.model.input.PromptTemplate;
import java.util.Map;

public abstract class AbstractPromptProvider implements IPromptProvider {

    protected final PromptTemplate template;

    public AbstractPromptProvider(String templateResourcePath) {
        this.template = TemplateUtil.getTemplateFromResource(templateResourcePath);
    }

    @Override
    public String getPrompt(Map<String, Object> variables) {
        return template.apply(variables).text();
    }
}
