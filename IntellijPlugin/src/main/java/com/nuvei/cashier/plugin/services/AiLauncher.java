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
import dev.langchain4j.model.chat.ChatModel;

import java.nio.file.Paths;

public class AiLauncher {
    private static final Logger LOG = Logger.getInstance(AiLauncher.class);

    public void launch(AddPppAdminPropertyDialog dialog, String filePath, String pppAdminDirectory) throws Exception {
        InputParameters inputParameters = new InputParameters(Paths.get(filePath),
                dialog.getPropertyName(), dialog.getType(),
                dialog.getFieldSize(), dialog.getHint(), dialog.getDefaultValue(), dialog.isCached(),
                dialog.isNullable(), dialog.getStoryNumber());
        HandlerContext context = new HandlerContext(inputParameters);
        LOG.info("Context created: " + context);

        ChatModel model = GeminiAssistant.getModel();
        ICodeAssistant codeAssistant = new CodeAssistant(model);

        IResponseParserFactory<String> parserFactory = new MarkdownResponseParserFactory();

        IHandler pipeline = PipelineFactory.createPipeline(codeAssistant, parserFactory);

        pipeline.handle(context);
    }
}
