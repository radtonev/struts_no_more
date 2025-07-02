package com.nuvei.cashier;

import java.util.Objects;

import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.InputParameters;
import com.nuvei.cashier.code.handler.IHandler;
import com.nuvei.cashier.workflow.IWorkflow;
import com.nuvei.cashier.workflow.WorkflowClass;
import com.nuvei.cashier.workflow.WorkflowFactory;

public class FileProcessor {

    private final IHandler pipeline;

    public FileProcessor(IHandler pipeline) {
        this.pipeline = pipeline;
    }

    public void process(InputParameters inputParameters) throws Exception {
        // Validate input parameters
        validateInputParameters(inputParameters);

        // Create a workflow based on the input file
        IWorkflow workflow = WorkflowFactory.createWorkflow(inputParameters.file());

        // Create a context for the pipeline
        HandlerContext ctx = new HandlerContext(inputParameters);
        ctx.setDdlStatementPath(workflow.getDdlStatementPath());

        for (WorkflowClass wfClass : workflow.getWorkflowClasses()) {
            // Update context for the pipeline
            ctx.setClassFile(wfClass.file());
            ctx.setClassRole(wfClass.classRole());

            // Start processing the workflow
            pipeline.handle(ctx);
        }

    }

    private void validateInputParameters(InputParameters inputParameters) {
        Objects.requireNonNull(inputParameters.file(), "File cannot be null");
        Objects.requireNonNull(inputParameters.fieldName(), "Field name cannot be null");
        Objects.requireNonNull(inputParameters.fieldType(), "Field type cannot be null");
        Objects.requireNonNull(inputParameters.fieldSize(), "Field size cannot be null");
        Objects.requireNonNull(inputParameters.fieldTooltip(), "Field tooltip cannot be null");
        Objects.requireNonNull(inputParameters.fieldDefaultValue(), "Field default value cannot be null");
        Objects.requireNonNull(inputParameters.storyId(), "User story ID cannot be null");
    }
}
