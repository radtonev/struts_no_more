package com.nuvei.cashier.code.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuvei.cashier.code.HandlerContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Handler to write SQL DDL statements to a migration script file.
 * This handler is specifically used for entity classes to generate and save SQL files.
 */
public class WriteSqlFileHandler extends AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(WriteSqlFileHandler.class);

    private final String shardSuffix;

    public WriteSqlFileHandler(String shardSuffix) {
        this.shardSuffix = shardSuffix;
    }

    @Override
    public void process(HandlerContext ctx) throws Exception {
        if (ctx.getDdlStatement() != null) {
            Path sqlFilePath = getSqlFilePath(ctx);
            Files.writeString(sqlFilePath, ctx.getDdlStatement(), StandardOpenOption.CREATE_NEW,
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

            logger.debug("Write sql file: {}", sqlFilePath);
        }
    }

    private Path getSqlFilePath(HandlerContext ctx) {
        String sqlFileName = String.format("%s_%s_%s.sql",
                LocalDateTime.now().atZone(ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                ctx.getInputParameters().storyId(), shardSuffix);

        return ctx.getBasePath()
                .resolve(ctx.getDdlStatementPath())
                .resolve(sqlFileName);
    }
}
