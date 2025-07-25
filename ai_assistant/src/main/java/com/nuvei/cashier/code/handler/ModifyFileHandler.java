package com.nuvei.cashier.code.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuvei.cashier.ai.ICodeAssistant;
import com.nuvei.cashier.code.ClassRole;
import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.InputParameters;
import com.nuvei.cashier.code.prompt.PromptProviderFactory;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.internal.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handler to modify a file based on the provided context and input parameters.
 * It uses an AI Code Assistant to generate a modified version of the file content.
 */
public class ModifyFileHandler extends AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(ModifyFileHandler.class);

    private final ICodeAssistant codeAssistant;

    public ModifyFileHandler(ICodeAssistant codeAssistant) {
        this.codeAssistant = codeAssistant;
    }

    @Override
    public void process(HandlerContext ctx) throws Exception {
        if ((ClassRole.CACHE_DTO.equals(ctx.getClassRole()) || ClassRole.CACHE_LOADER.equals(ctx.getClassRole()))
                && !ctx.getInputParameters().fieldCacheable()) {
            logger.debug("Skipping modification for file because field is not cacheable");
            return;
        }
        String prompt = PromptProviderFactory.createPromptProvider(ctx.getClassRole()).getPrompt(getVariables(ctx));
        List<ChatMessage> messages = new ArrayList<>();
        if (Utils.isNotNullOrBlank(ctx.getPreviousLlmResponse())) {
            messages.add(AiMessage.from(String.format("%s:\r\n %s", "Previous response", ctx.getPreviousLlmResponse())));
        }
        messages.add(UserMessage.from(prompt));
        ctx.setLlmResponse(codeAssistant.modifyCode(messages.toArray(new ChatMessage[0])));

        logger.debug("Get modified content from the AI assistant");
    }

    private Map<String, Object> getVariables(HandlerContext ctx) {
        InputParameters p = ctx.getInputParameters();
        return Map.of("originalContent", ctx.getOriginalContent(), "fieldName", p.fieldName(), "fieldType",
                p.fieldType(), "fieldSize", p.fieldSize(), "fieldTooltip", p.fieldTooltip(), "fieldDefaultValue",
                p.fieldDefaultValue(), "fieldNullable", p.fieldNullable());
    }
}
