package com.nuvei.cashier.plugin.services;

import com.intellij.openapi.diagnostic.Logger;
import com.nuvei.cashier.ai.CodeAssistant;
import com.nuvei.cashier.ai.ICodeAssistant;
import com.nuvei.cashier.ai.client.GeminiAssistant;
import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.InputParameters;
import com.nuvei.cashier.code.handler.IHandler;
import com.nuvei.cashier.code.parser.IResponseParserFactory;
import com.nuvei.cashier.code.parser.MarkdownResponseParserFactory;
import com.nuvei.cashier.code.pipeline.PipelineFactory;
import com.nuvei.cashier.plugin.actions.AddPppAdminPropertyDialog;
import com.nuvei.cashier.plugin.settings.StrutsNoMoreSettings;
import dev.langchain4j.internal.Utils;
import dev.langchain4j.model.chat.ChatModel;

import java.nio.file.Paths;

public class AiLauncher {
    private static final Logger LOG = Logger.getInstance(AiLauncher.class);

    public void launch(AddPppAdminPropertyDialog dialog, String filePath, StrutsNoMoreSettings settings) throws Exception {
        InputParameters inputParameters = new InputParameters(Paths.get(filePath),
                dialog.getPropertyName(), dialog.getType(),
                dialog.getFieldSize(), dialog.getHint(), dialog.getDefaultValue(), dialog.isCached(),
                dialog.isNullable(), dialog.getStoryNumber());
        HandlerContext context = new HandlerContext(inputParameters);
        LOG.info("Context created: " + context);

        String modelName = Utils.getOrDefault(settings.getGeminiModel(), GeminiAssistant.DEFAULT_GEMINI_MODEL);
        ChatModel model = GeminiAssistant.getModel(modelName, settings.getGeminiApiKey());
        ICodeAssistant codeAssistant = new CodeAssistant(model);

        IResponseParserFactory<String> parserFactory = new MarkdownResponseParserFactory();

        IHandler pipeline = PipelineFactory.createPipeline(codeAssistant, parserFactory);

        pipeline.handle(context);
    }
}
