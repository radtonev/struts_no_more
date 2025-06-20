package com.nuvei.cashier.code.handler;

import com.nuvei.cashier.code.CodeContext;

public interface IHandler {

    void setNext(IHandler next);
    void handle(CodeContext ctx) throws Exception;

}
