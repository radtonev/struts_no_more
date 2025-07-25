package com.nuvei.cashier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.InputParameters;
import com.nuvei.cashier.code.handler.IHandler;
import com.nuvei.cashier.workflow.IWorkflow;
import com.nuvei.cashier.workflow.WorkflowClass;
import com.nuvei.cashier.workflow.WorkflowFactory;
import java.util.Objects;

public class FileProcessor {

    private static final Logger logger = LoggerFactory.getLogger(FileProcessor.class);

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

        for (WorkflowClass wfClass : workflow.getWorkflowClasses()) {
            ctx.setDdlStatementPath(workflow.getDdlStatementPath());
            ctx.setClassFile(wfClass.file());
            ctx.setClassRole(wfClass.classRole());

            ctx.setPreviousLlmResponse(ctx.getLlmResponse());

            // Empty some context variables
            ctx.setOriginalContent(null);
            ctx.setModifiedContent(null);
            ctx.setDdlStatement(null);
            ctx.setLlmResponse(null);

            // Start processing the workflow
            logger.debug("Starting pipeline for file {}", ctx.getClassFile());

            pipeline.handle(ctx);

            logger.debug("Exiting pipeline");
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
