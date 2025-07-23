package com.nuvei.cashier.code;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class HandlerContext {

    private InputParameters inputParameters;

    private Path basePath = Path.of("");

    private Path classFile = Path.of("");
    private ClassRole classRole = ClassRole.UNKNOWN;
    private String originalContent;
    private String modifiedContent;

    private String ddlStatement;
    private Path ddlStatementPath = Path.of("");

    private String previousLlmResponse = "Begin conversation.";
    private String llmResponse;

    private Map<String, Object> metadata = new HashMap<>();

    public HandlerContext(InputParameters inputParameters) {
        this.inputParameters = inputParameters;
    }

    public InputParameters getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(InputParameters inputParameters) {
        this.inputParameters = inputParameters;
    }

    public Path getBasePath() {
        return basePath;
    }

    public void setBasePath(Path basePath) {
        this.basePath = basePath;
    }

    public Path getClassFile() {
        return classFile;
    }

    public void setClassFile(Path file) {
        this.classFile = file;
    }

    public ClassRole getClassRole() {
        return classRole;
    }

    public void setClassRole(ClassRole classRole) {
        this.classRole = classRole;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public String getModifiedContent() {
        return modifiedContent;
    }

    public void setModifiedContent(String modifiedContent) {
        this.modifiedContent = modifiedContent;
    }

    public String getDdlStatement() {
        return ddlStatement;
    }

    public void setDdlStatement(String ddlStatement) {
        this.ddlStatement = ddlStatement;
    }

    public Path getDdlStatementPath() {
        return ddlStatementPath;
    }

    public void setDdlStatementPath(Path ddlStatementPath) {
        this.ddlStatementPath = ddlStatementPath;
    }

    public String getPreviousLlmResponse() {
        return previousLlmResponse;
    }

    public void setPreviousLlmResponse(String previousLlmResponse) {
        this.previousLlmResponse = previousLlmResponse;
    }

    public String getLlmResponse() {
        return llmResponse;
    }

    public void setLlmResponse(String llmResponse) {
        this.llmResponse = llmResponse;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "HandlerContext [inputParameters=" + inputParameters + ", basePath=" + basePath + ", classFile="
                + classFile + ", classRole=" + classRole + ", originalContent=" + originalContent + ", modifiedContent="
                + modifiedContent + ", ddlStatement=" + ddlStatement + ", ddlStatementPath=" + ddlStatementPath
                + ", previousLlmResponse=" + previousLlmResponse + ", llmResponse=" + llmResponse + ", metadata="
                + metadata + "]";
    }
}
