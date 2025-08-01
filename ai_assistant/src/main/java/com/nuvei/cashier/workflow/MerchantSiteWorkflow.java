package com.nuvei.cashier.workflow;

import com.nuvei.cashier.code.ClassRole;
import java.nio.file.Path;
import java.util.List;

public class MerchantSiteWorkflow implements IWorkflow {

    private final Path file = Path
            .of("persistence/src/main/java/com/safecharge/ppp/persistence/dto/entities/MerchantSite.java");
    private final List<WorkflowClass> workflowClasses = List.of(new WorkflowClass(file, ClassRole.ENTITY),
            new WorkflowClass(
                    Path.of("persistence/src/main/java/com/safecharge/ppp/persistence/dto/output/MerchantSiteDTO.java"),
                    ClassRole.DTO),
            new WorkflowClass(Path.of(
                    "persistence/src/main/java/com/safecharge/ppp/persistence/cache/merchantsitesettings/MerchantSiteCacheDTO.java"),
                    ClassRole.CACHE_DTO),
            new WorkflowClass(Path.of(
                    "persistence/src/main/java/com/safecharge/ppp/persistence/cache/merchantsitesettings/MerchantSiteSettingsCacheLoader.java"),
                    ClassRole.CACHE_LOADER),
            new WorkflowClass(Path.of(
                    "persistence/../ppp/src/main/java/com/safecharge/ppp/bean/MerchantSiteManagerBean.java"),
                    ClassRole.SERVICE),
            new WorkflowClass(Path.of("../Cashier Admin/src/main/java/com/safecharge/ppp/api/web/service/dto/output/MerchantSiteDTO.java"),
                    ClassRole.DTO),
            new WorkflowClass(Path.of("../Cashier Admin/src/main/java/com/safecharge/pppadmin/struts/form/admin/MerchantSiteForm.java"),
                    ClassRole.DTO),
            new WorkflowClass(Path.of("../Cashier Admin/src/main/java/com/safecharge/pppadmin/struts/action/admin/EditMerchantSiteAction.java"),
                    ClassRole.ACTION),
            new WorkflowClass(Path.of("../Cashier Admin/src/main/java/com/safecharge/pppadmin/struts/action/admin/SaveMerchantSiteAction.java"),
                    ClassRole.ACTION),
            new WorkflowClass(Path.of("../Cashier Admin/src/main/webapp/admin/merchantsiteform.jsp"),
                    ClassRole.JSP),
            new WorkflowClass(Path.of("../Cashier Admin/src/main/resources/ApplicationResources.properties"), 
                    ClassRole.RESOURCE));

    private final Path ddlStatementPath = Path.of("ppp/src/main/db/migrations/");

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
