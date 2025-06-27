package com.nuvei.cashier.code.handler;

import com.nuvei.cashier.code.HandlerContext;

public abstract class AbstractHandler implements IHandler {

    protected IHandler next;

    @Override
    public void setNext(IHandler next) {
        this.next = next;
    }

    protected void fireNext(HandlerContext ctx) throws Exception {
        if (next != null) {
            next.handle(ctx);
        }
    }
}
