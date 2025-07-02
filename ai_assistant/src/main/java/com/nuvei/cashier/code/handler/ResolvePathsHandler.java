package com.nuvei.cashier.code.handler;

import java.nio.file.Path;
import com.nuvei.cashier.code.HandlerContext;

public class ResolvePathsHandler extends AbstractHandler{

    @Override
    public void handle(HandlerContext ctx) throws Exception {
        Path basePath = getBasePath(ctx.getInputParameters().file(), ctx.getClassFile());
        ctx.setBasePath(basePath);

        fireNext(ctx);
    }

    private Path getBasePath(Path fullPath, Path relativePath) {
        // Resolve the parent of the relative path (exclude the relative part from full path)
        Path basePath = fullPath;
        for (int i = relativePath.getNameCount(); i > 0; i--) {
            basePath = basePath.getParent();
        }

        return basePath;
    }
}
