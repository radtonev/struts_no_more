package com.nuvei.cashier.code.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuvei.cashier.ai.util.FileUtil;
import com.nuvei.cashier.code.HandlerContext;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Handler to write the modified content back to the file system.
 * It overwrites the content of the file.
 */
public class WriteFileHandler extends AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(WriteFileHandler.class);

    @Override
    public void process(HandlerContext ctx) throws Exception {
        Path classFile = ctx.getBasePath().resolve(FileUtil.normalizeAdminPath(ctx.getClassFile())).normalize();
        if (ctx.getModifiedContent() != null) {
            // Write the modified content back to the file
            // TODO: Write to the same file
            Files.writeString(classFile, ctx.getModifiedContent());

            logger.debug("Write modified content.");
        }
    }
}
