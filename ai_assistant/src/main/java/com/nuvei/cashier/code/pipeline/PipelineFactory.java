package com.nuvei.cashier.code.pipeline;

import com.nuvei.cashier.ai.ICodeAssistant;
import com.nuvei.cashier.code.handler.IHandler;
import com.nuvei.cashier.code.handler.LoggingHandler;
import com.nuvei.cashier.code.handler.ModifyFileHandler;
import com.nuvei.cashier.code.handler.ReadFileHandler;
import com.nuvei.cashier.code.handler.ResolvePathsHandler;
import com.nuvei.cashier.code.parser.IResponseParserFactory;

public class PipelineFactory {

    public static IHandler createPipeline(ICodeAssistant codeAssistant, IResponseParserFactory<String> parserFactory) {
        // Instantiate the core handlers of the pipeline
        IHandler logger = new LoggingHandler();
        IHandler resolve = new ResolvePathsHandler();
        IHandler read = new ReadFileHandler();
        IHandler modify = new ModifyFileHandler(codeAssistant, parserFactory);

        // Chain the handlers together
        logger.setNext(resolve);
        resolve.setNext(read);
        read.setNext(modify);

        return logger;
    }
}
