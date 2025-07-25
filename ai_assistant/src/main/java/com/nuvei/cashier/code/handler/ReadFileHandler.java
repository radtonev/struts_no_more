package com.nuvei.cashier.code.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuvei.cashier.ai.util.FileUtil;
import com.nuvei.cashier.code.HandlerContext;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Handler to read the content of a file specified in the HandlerContext.
 * It reads the content of the class file and sets it as the original content
 * in the context for further processing.
 */
public class ReadFileHandler extends AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(ReadFileHandler.class);
    
    @Override
    public void process(HandlerContext ctx) throws Exception {
        Path classFile = ctx.getBasePath().resolve(FileUtil.normalizeAdminPath(ctx.getClassFile())).normalize();
        ctx.setOriginalContent(Files.readString(classFile));

        logger.debug("Read file: {}", classFile);
    }
}
