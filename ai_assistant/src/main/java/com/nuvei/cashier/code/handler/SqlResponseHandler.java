package com.nuvei.cashier.code.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuvei.cashier.code.ClassRole;
import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.parser.IResponseParser;
import com.nuvei.cashier.code.parser.IResponseParserFactory;

/**
 * Handler to process SQL responses.
 * This handler parses the SQL DDL statement from the LLM response and sets it in the context.
 * It is specifically used for entity classes to generate migration scripts.
 */
public class SqlResponseHandler extends AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(SqlResponseHandler.class);

    private final IResponseParserFactory<String> parserFactory;

    public SqlResponseHandler(IResponseParserFactory<String> parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override
    protected void process(HandlerContext ctx) throws Exception {
        // For the entity class, we also parse the SQL DDL statement and write it to a migration script file.
        if (ClassRole.ENTITY.equals(ctx.getClassRole())) {
            IResponseParser<String> parser = parserFactory.createParser("sql");
            ctx.setDdlStatement(parser.parse(ctx.getLlmResponse()));

            logger.debug("Parsed response content for SQL statement");
        }
    }
}
