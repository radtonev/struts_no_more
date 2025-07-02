package com.nuvei.cashier.code.handler;

import java.nio.file.Files;

import com.nuvei.cashier.code.HandlerContext;

public class ReadFileHandler extends AbstractHandler {
    
    @Override
    public void handle(HandlerContext ctx) throws Exception {
        ctx.setOriginalContent(Files.readString(ctx.getClassFile()));

        fireNext(ctx);
    }
}
