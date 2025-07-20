package com.nuvei.cashier.code.handler;

import java.nio.file.Files;
import java.nio.file.Path;

import com.nuvei.cashier.code.HandlerContext;

public class ReadFileHandler extends AbstractHandler {
    
    @Override
    public void handle(HandlerContext ctx) throws Exception {
        Path classFile = ctx.getBasePath().resolve(ctx.getClassFile()).normalize();
        ctx.setOriginalContent(Files.readString(classFile));

        fireNext(ctx);
    }
}
