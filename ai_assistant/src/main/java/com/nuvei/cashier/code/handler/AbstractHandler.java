package com.nuvei.cashier.code.handler;

import com.nuvei.cashier.code.HandlerContext;

public abstract class AbstractHandler implements IHandler {

    protected IHandler next;

    @Override
    public void setNext(IHandler next) {
        this.next = next;
    }

    @Override
    public void handle(HandlerContext ctx) throws Exception {
        process(ctx);
        fireNext(ctx);
    }

    protected abstract void process(HandlerContext ctx) throws Exception;

    protected void fireNext(HandlerContext ctx) throws Exception {
        if (next != null) {
            next.handle(ctx);
        }
    }
}
