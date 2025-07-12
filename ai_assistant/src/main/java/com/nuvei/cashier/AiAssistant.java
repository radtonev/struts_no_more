package com.nuvei.cashier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuvei.cashier.ai.CodeAssistant;
import com.nuvei.cashier.ai.ICodeAssistant;
import com.nuvei.cashier.ai.client.GeminiAssistant;
import com.nuvei.cashier.code.handler.IHandler;
import com.nuvei.cashier.code.parser.IResponseParserFactory;
import com.nuvei.cashier.code.parser.MarkdownResponseParserFactory;
import com.nuvei.cashier.code.pipeline.PipelineFactory;

import dev.langchain4j.model.chat.ChatModel;

public class AiAssistant {

    private static final Logger logger = LoggerFactory.getLogger(AiAssistant.class);

    public static void main(String[] args) {
        try {
            AiAssistantCli cli = new AiAssistantCli(args);

            ChatModel model = GeminiAssistant.getModel();
            ICodeAssistant codeAssistant = new CodeAssistant(model);

            IResponseParserFactory<String> parserFactory = new MarkdownResponseParserFactory();
            
            IHandler pipeline = PipelineFactory.createPipeline(codeAssistant, parserFactory);
            FileProcessor fileProcessor = new FileProcessor(pipeline);

            fileProcessor.process(cli.getInputParameters());
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
            System.exit(255);
        }
    }
}
