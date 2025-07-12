package com.nuvei.cashier.workflow;

import java.nio.file.Path;
import java.util.List;

public interface IWorkflow {

    Path getFile();

    List<WorkflowClass> getWorkflowClasses();

    Path getDdlStatementPath();
}
