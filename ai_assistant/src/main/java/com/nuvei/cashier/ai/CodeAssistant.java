package com.nuvei.cashier.ai;

import com.nuvei.cashier.ai.util.TemplateUtil;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;

public class CodeAssistant implements ICodeAssistant {

    private final ICodeAssistant codeAssistant;

    public CodeAssistant(ChatModel model) {
        this.codeAssistant = AiServices.builder(ICodeAssistant.class)
                .chatModel(model)
                .systemMessageProvider(chatmemoryId -> TemplateUtil.getTemplateFromResource("/templates/system-message.tpl.txt").template())
                .build();
    }

    @Override
    public String modifyCode(ChatMessage... messages) {
        return codeAssistant.modifyCode(messages);
    }

}
