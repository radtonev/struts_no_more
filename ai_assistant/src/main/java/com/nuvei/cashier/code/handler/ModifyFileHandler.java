package com.nuvei.cashier.code.handler;

import java.util.Map;

import com.nuvei.cashier.ai.ICodeAssistant;
import com.nuvei.cashier.code.ClassRole;
import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.parser.IResponseParser;
import com.nuvei.cashier.code.parser.IResponseParserFactory;
import com.nuvei.cashier.code.prompt.IPromptProviderFactory;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;

public class ModifyFileHandler extends AbstractHandler {
    private final ICodeAssistant codeAssistant;
    private final IPromptProviderFactory promptProviderFactory;
    private final IResponseParserFactory<String> parserFactory;

    public ModifyFileHandler(ICodeAssistant codeAssistant, IPromptProviderFactory promptProviderFactory,
            IResponseParserFactory<String> parserFactory) {
        this.codeAssistant = codeAssistant;
        this.promptProviderFactory = promptProviderFactory;
        this.parserFactory = parserFactory;
    }

    @Override
    public void handle(HandlerContext ctx) throws Exception {
        String prompt = promptProviderFactory.createPromptProvider(ctx.getClassRole()).getPrompt(getVariables(ctx));
        ChatResponse chatResponse = codeAssistant.modifyCode(UserMessage.from(prompt));
        ctx.setLlmResponse(chatResponse.aiMessage().text());
        IResponseParser<String> parser = parserFactory.createParser("java");
        ctx.setModifiedContent(parser.parse(ctx.getLlmResponse()));
        if (ClassRole.ENTITY.equals(ctx.getClassRole())) {
            parser = parserFactory.createParser("sql");
            ctx.setDdlStatement(parser.parse(ctx.getLlmResponse()));
        }

        fireNext(ctx);
    }

    private Map<String, Object> getVariables(HandlerContext ctx) {
        return Map.of("originalContent", ctx.getOriginalContent(), "fieldName", ctx.getFieldName(), "fieldType",
                ctx.getFieldType(), "fieldSize", ctx.getFieldSize(), "fieldTooltip", ctx.getFieldTooltip(),
                "fieldDefaultValue", ctx.getFieldDefaultValue(), "fieldNullable", ctx.isFieldNullable());
    }
}
