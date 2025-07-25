package com.nuvei.cashier.code.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.parser.IResponseParser;
import com.nuvei.cashier.code.parser.IResponseParserFactory;
import dev.langchain4j.internal.Utils;

public class JavaResponseHandler extends AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(JavaResponseHandler.class);

    private final IResponseParserFactory<String> parserFactory;

    public JavaResponseHandler(IResponseParserFactory<String> parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override
    protected void process(HandlerContext ctx) throws Exception {
        IResponseParser<String> parser = parserFactory.createParser("java");
        String parsedContent = parser.parse(ctx.getLlmResponse());
        if (Utils.isNotNullOrBlank(parsedContent)) {
            ctx.setModifiedContent(parsedContent);

            logger.debug("Parsed response content for Java");
        }
    }
}
