package com.nuvei.cashier.workflow;

public enum WorkflowType {

    MERCHANT_SITE(new MerchantSiteWorkflow());

    private final IWorkflow workflow;

    private WorkflowType(IWorkflow workflow) {
        this.workflow = workflow;
    }

    public IWorkflow getWorkflow() {
        return workflow;
    }
}
