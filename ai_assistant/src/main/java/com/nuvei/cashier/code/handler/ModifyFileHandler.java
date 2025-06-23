package com.nuvei.cashier.code.handler;

import java.util.Map;

import com.nuvei.cashier.code.ClassRole;
import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.parser.IResponseParser;
import com.nuvei.cashier.code.prompt.IPromptProvider;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;

public class ModifyFileHandler extends AbstractHandler {
    private final ChatModel chatModel;
    private final IPromptProvider promptProvider;
    private final IResponseParser<String> parser;

    public ModifyFileHandler(ChatModel chatModel, IPromptProvider promptProvider, IResponseParser<String> parser) {
        this.chatModel = chatModel;
        this.promptProvider = promptProvider;
        this.parser = parser;
    }

    @Override
    public void handle(HandlerContext ctx) throws Exception {
        String prompt = promptProvider.getPrompt(ctx.getClassRole(), getVariables(ctx));
        ChatResponse chatResponse = chatModel.chat(UserMessage.from(prompt));
        ctx.setLlmResponse(chatResponse.aiMessage().text());
        ctx.setModifiedContent(parser.parse(ctx.getLlmResponse()));

        fireNext(ctx);
    }

    private Map<String, Object> getVariables(HandlerContext ctx) {
        return Map.of(
                "originalContent", ctx.getOriginalContent(),
                "fieldName", ctx.getFieldName(),
                "fieldType", ctx.getFieldType(),
                "fieldSize", ctx.getFieldSize(),
                "fieldTooltip", ctx.getFieldTooltip(),
                "fieldDefaultValue", ctx.getFieldDefaultValue(),
                "fieldNullable", ctx.isFieldNullable()
        );
    }
}
