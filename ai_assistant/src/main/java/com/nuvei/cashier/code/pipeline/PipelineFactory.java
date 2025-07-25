package com.nuvei.cashier.code.pipeline;

import com.nuvei.cashier.ai.ICodeAssistant;
import com.nuvei.cashier.code.handler.IHandler;
import com.nuvei.cashier.code.handler.JavaResponseHandler;
import com.nuvei.cashier.code.handler.JspResponseHandler;
import com.nuvei.cashier.code.handler.ModifyFileHandler;
import com.nuvei.cashier.code.handler.ReadFileHandler;
import com.nuvei.cashier.code.handler.ResolvePathsHandler;
import com.nuvei.cashier.code.handler.SqlResponseHandler;
import com.nuvei.cashier.code.handler.WriteFileHandler;
import com.nuvei.cashier.code.handler.WriteSqlFileHandler;
import com.nuvei.cashier.code.parser.IResponseParserFactory;

public class PipelineFactory {

    public static IHandler createPipeline(ICodeAssistant codeAssistant, IResponseParserFactory<String> parserFactory) {
        // Instantiate the core handlers of the pipeline
        IHandler resolve = new ResolvePathsHandler();
        IHandler read = new ReadFileHandler();
        IHandler modify = new ModifyFileHandler(codeAssistant);
        IHandler parseJava = new JavaResponseHandler(parserFactory);
        IHandler parseJsp = new JspResponseHandler(parserFactory);
        IHandler parseSql = new SqlResponseHandler(parserFactory);
        IHandler writeDdl = new WriteSqlFileHandler("dbm");
        IHandler write = new WriteFileHandler();

        // Chain the handlers together
        resolve.setNext(read);
        read.setNext(modify);
        modify.setNext(parseJava);
        parseJava.setNext(parseJsp);
        parseJsp.setNext(parseSql);
        parseSql.setNext(writeDdl);
        writeDdl.setNext(write);

        return resolve;
    }
}
