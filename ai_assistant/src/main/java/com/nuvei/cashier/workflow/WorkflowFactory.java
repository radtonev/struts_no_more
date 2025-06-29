package com.nuvei.cashier.workflow;

import java.nio.file.Path;

public class WorkflowFactory {
    public static IWorkflow createWorkflow(Path file) {
        for (WorkflowType type : WorkflowType.values()) {
            if (file.endsWith(type.getWorkflow().getFile())) {
                return type.getWorkflow();
            }
        }
        throw new IllegalArgumentException("Unsupported workflow file: " + file);
    }
}
