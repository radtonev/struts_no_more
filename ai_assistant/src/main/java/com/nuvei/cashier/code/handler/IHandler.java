package com.nuvei.cashier.code.handler;

import com.nuvei.cashier.code.HandlerContext;

public interface IHandler {

    void setNext(IHandler next);
    
    void handle(HandlerContext ctx) throws Exception;
}
