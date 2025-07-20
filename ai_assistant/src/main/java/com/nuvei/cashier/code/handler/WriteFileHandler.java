package com.nuvei.cashier.code.handler;

import java.nio.file.Files;
import java.nio.file.Path;

import com.nuvei.cashier.code.HandlerContext;

public class WriteFileHandler extends AbstractHandler {

    @Override
    public void handle(HandlerContext ctx) throws Exception {
        Path classFile = ctx.getBasePath().resolve(ctx.getClassFile()).normalize();
        if (ctx.getModifiedContent() != null) {
            // Write the modified content back to the file
            // TODO: Write to the same file
            Files.writeString(classFile.getParent().resolve(classFile.getFileName() + ".new"), ctx.getModifiedContent());
        }

        fireNext(ctx);
    }

}
