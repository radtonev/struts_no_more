package com.nuvei.cashier.code.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuvei.cashier.code.HandlerContext;

public class LoggingHandler extends AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoggingHandler.class);

    @Override
    public void handle(HandlerContext ctx) throws Exception {
        logger.info("Starting step for file {}", ctx.getClassFile());

        fireNext(ctx);
        
        logger.info("Exiting step");
    }
}
