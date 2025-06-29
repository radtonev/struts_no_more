package com.nuvei.cashier.workflow;

import java.nio.file.Path;
import java.util.List;

import com.nuvei.cashier.code.ClassRole;

public class MerchantWorkflow implements IWorkflow {

    private final Path file = Path.of("src/test/resources/Product.java.txt");
    private final List<WorkflowClass> workflowClasses = List.of(
            new WorkflowClass(file, ClassRole.ENTITY),
            new WorkflowClass(Path.of("src/main/java/com/MerchantDTO.java"), ClassRole.DTO)
    );
    private final Path ddlStatementPath = Path.of("src/main/java/db/migrations");

    @Override
    public Path getFile() {
        return file;
    }

    @Override
    public List<WorkflowClass> getWorkflowClasses() {
        return workflowClasses;
    }

    @Override
    public Path getDdlStatementPath() {
        return ddlStatementPath;
    }
}
