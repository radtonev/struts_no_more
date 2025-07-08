package com.nuvei.cashier.workflow;

import java.nio.file.Path;
import java.util.List;

import com.nuvei.cashier.code.ClassRole;

public class MerchantSiteWorkflow implements IWorkflow {

    private final Path file = Path.of("src/main/java/com/safecharge/ppp/persistence/dto/entities/MerchantSite.java");
    private final List<WorkflowClass> workflowClasses = List.of(new WorkflowClass(file, ClassRole.ENTITY),
            new WorkflowClass(Path.of("src/main/java/com/safecharge/ppp/persistence/dto/output/MerchantSiteDTO.java"),
                    ClassRole.DTO),
            new WorkflowClass(Path.of(
                    "src/main/java/com/safecharge/ppp/persistence/cache/merchantsitesettings/MerchantSiteCacheDTO.java"),
                    ClassRole.CACHE_DTO));
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
