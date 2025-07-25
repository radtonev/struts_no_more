package com.nuvei.cashier.code.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuvei.cashier.code.HandlerContext;
import java.nio.file.Path;

/**
 * Handler to resolve the base path for the file being processed.
 * This handler sets the base path in the context if it is not already set.
 */
public class ResolvePathsHandler extends AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResolvePathsHandler.class);

    @Override
    public void process(HandlerContext ctx) throws Exception {
        // If the base path is empty, change it
        if (Path.of("").equals(ctx.getBasePath())) {
            Path basePath = getBasePath(ctx.getInputParameters().file(), ctx.getClassFile());
            ctx.setBasePath(basePath);

            logger.debug("Resolve basePath: {}", basePath);
        }
    }

    private Path getBasePath(Path fullPath, Path relativePath) {
        // Resolve the parent of the relative path (exclude the relative part from the full path)
        Path basePath = fullPath;
        for (int i = relativePath.getNameCount(); i > 0; i--) {
            basePath = basePath.getParent();
        }

        return basePath;
    }
}
