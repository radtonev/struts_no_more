package com.nuvei.cashier.code.handler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.nuvei.cashier.code.ClassRole;
import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.parser.IResponseParser;
import com.nuvei.cashier.code.parser.IResponseParserFactory;

public class WriteDdlFileHandler extends AbstractHandler {

    private final IResponseParserFactory<String> parserFactory;
    private final String shardSuffix;

    public WriteDdlFileHandler(IResponseParserFactory<String> parserFactory, String shardSuffix) {
        this.parserFactory = parserFactory;
        this.shardSuffix = shardSuffix;
    }

    @Override
    public void handle(HandlerContext ctx) throws Exception {
        // For the entity class, we also parse the SQL DDL statement and write it to migration script file.
        if (ClassRole.ENTITY.equals(ctx.getClassRole())) {
            IResponseParser<String> parser = parserFactory.createParser("sql");
            ctx.setDdlStatement(parser.parse(ctx.getLlmResponse()));
            if (ctx.getDdlStatement() != null) {
                String ddlFileName = String.format("%s_%s_%s.sql",
                        LocalDateTime.now().atZone(ZoneId.systemDefault())
                                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                        ctx.getInputParameters().storyId(), shardSuffix);
                Path ddlFilePath = ctx.getBasePath().resolve(ctx.getDdlStatementPath()).resolve(ddlFileName);
                Files.writeString(ddlFilePath, ctx.getDdlStatement(), StandardOpenOption.CREATE_NEW,
                        StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            }
        }

        fireNext(ctx);
    }

}
