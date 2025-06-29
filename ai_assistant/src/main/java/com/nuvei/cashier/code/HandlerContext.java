package com.nuvei.cashier.code;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class HandlerContext {

    private Path file;
    private ClassRole classRole;
    private String originalContent;
    private String modifiedContent;

    private String ddlStatement;
    private Path ddlStatementPath;

    private String fieldName;
    private String fieldType;
    private String fieldSize;
    private String fieldTooltip;
    private String fieldDefaultValue;
    private boolean fieldCached;
    private boolean fieldNullable;

    private String storyId;

    private String llmResponse;

    private Map<String, Object> metadata = new HashMap<>();

    public HandlerContext(Path file, ClassRole classRole, String fieldName, String fieldType, String fieldSize,
            String fieldTooltip, String fieldDefaultValue, boolean fieldCached, boolean fieldNullable, String storyId,
            Path ddlStatementPath) {
        this.file = file;
        this.classRole = classRole;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldSize = fieldSize;
        this.fieldTooltip = fieldTooltip;
        this.fieldDefaultValue = fieldDefaultValue;
        this.fieldCached = fieldCached;
        this.fieldNullable = fieldNullable;
        this.storyId = storyId;
        this.ddlStatementPath = ddlStatementPath;
    }

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(String fieldSize) {
        this.fieldSize = fieldSize;
    }

    public String getFieldTooltip() {
        return fieldTooltip;
    }

    public void setFieldTooltip(String fieldTooltip) {
        this.fieldTooltip = fieldTooltip;
    }

    public String getFieldDefaultValue() {
        return fieldDefaultValue;
    }

    public void setFieldDefaultValue(String fieldDefaultValue) {
        this.fieldDefaultValue = fieldDefaultValue;
    }

    public boolean isFieldCached() {
        return fieldCached;
    }

    public void setFieldCached(boolean fieldCached) {
        this.fieldCached = fieldCached;
    }

    public boolean isFieldNullable() {
        return fieldNullable;
    }

    public void setFieldNullable(boolean fieldNullable) {
        this.fieldNullable = fieldNullable;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
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
        return "HandlerContext [file=" + file + ", classRole=" + classRole + ", originalContent=" + originalContent
                + ", modifiedContent=" + modifiedContent + ", ddlStatement=" + ddlStatement + ", ddlStatementPath="
                + ddlStatementPath + ", fieldName=" + fieldName + ", fieldType=" + fieldType + ", fieldSize="
                + fieldSize + ", fieldTooltip=" + fieldTooltip + ", fieldDefaultValue=" + fieldDefaultValue
                + ", fieldCached=" + fieldCached + ", fieldNullable=" + fieldNullable + ", storyId=" + storyId
                + ", llmResponse=" + llmResponse + ", metadata=" + metadata
                + "]";
    }
}
