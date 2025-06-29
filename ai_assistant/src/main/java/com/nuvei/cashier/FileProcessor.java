package com.nuvei.cashier;

import java.nio.file.Path;
import java.util.Objects;

import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.code.handler.IHandler;
import com.nuvei.cashier.workflow.IWorkflow;
import com.nuvei.cashier.workflow.WorkflowClass;
import com.nuvei.cashier.workflow.WorkflowFactory;

public class FileProcessor {

    private final IHandler pipeline;

    public FileProcessor(IHandler pipeline) {
        this.pipeline = pipeline;
    }

    public void process(Path file) throws Exception {
        Objects.requireNonNull(file, "File cannot be null");

        IWorkflow workflow = WorkflowFactory.createWorkflow(file);

        for (WorkflowClass wfClass : workflow.getWorkflowClasses()) {
            // Create a context for the pipeline
            HandlerContext ctx = new HandlerContext(
                    // Specify the file to process
                    wfClass.file(),
                    // Specify the class role
                    wfClass.classRole(),
                    // Specify field details
                    "fieldName", "fieldType", "fieldSize", "fieldTooltip", "fieldDefaultValue",
                    false, false,
                    // Specify the story ID
                    "storyId",
                    // Specify the path to the migration statements
                    workflow.getDdlStatementPath()
            );
            // Start processing the workflow
            pipeline.handle(ctx);
        }

        
    }
}
