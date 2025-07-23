package com.nuvei.cashier.code.handler;

import java.util.Map;

import com.nuvei.cashier.ai.ICodeAssistant;
import com.nuvei.cashier.code.ClassRole;
import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.InputParameters;
import com.nuvei.cashier.code.parser.IResponseParser;
import com.nuvei.cashier.code.parser.IResponseParserFactory;
import com.nuvei.cashier.code.prompt.PromptProviderFactory;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.internal.Utils;

public class ModifyFileHandler extends AbstractHandler {

    private final ICodeAssistant codeAssistant;
    private final IResponseParserFactory<String> parserFactory;

    public ModifyFileHandler(ICodeAssistant codeAssistant, IResponseParserFactory<String> parserFactory) {
        this.codeAssistant = codeAssistant;
        this.parserFactory = parserFactory;
    }

    @Override
    public void handle(HandlerContext ctx) throws Exception {
        if ((ClassRole.CACHE_DTO.equals(ctx.getClassRole()) || ClassRole.CACHE_LOADER.equals(ctx.getClassRole()))
                && !ctx.getInputParameters().fieldCacheable()) {
            fireNext(ctx);
            return;
        }
        String prompt = PromptProviderFactory.createPromptProvider(ctx.getClassRole()).getPrompt(getVariables(ctx));
        if (Utils.isNotNullOrBlank(ctx.getLlmResponse())) {
            ctx.setPreviousLlmResponse(ctx.getLlmResponse());
        }
        ctx.setLlmResponse(codeAssistant.modifyCode(SystemMessage.from(ctx.getPreviousLlmResponse()), UserMessage.from(prompt)));
        IResponseParser<String> parser = parserFactory.createParser("java");
        ctx.setModifiedContent(parser.parse(ctx.getLlmResponse()));

        fireNext(ctx);
    }

    private Map<String, Object> getVariables(HandlerContext ctx) {
        InputParameters p = ctx.getInputParameters();
        return Map.of("originalContent", ctx.getOriginalContent(), "fieldName", p.fieldName(), "fieldType",
                p.fieldType(), "fieldSize", p.fieldSize(), "fieldTooltip", p.fieldTooltip(), "fieldDefaultValue",
                p.fieldDefaultValue(), "fieldNullable", p.fieldNullable());
    }
}
