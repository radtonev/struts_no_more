package com.nuvei.cashier.code;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CodeContext {

    private Path file;
    private String originalContent;
    private String modifiedContent;

    private String fieldName;
    private String fieldType;
    private String fieldTooltip;
    private String fieldDefaultValue;
    private boolean fieldNullable;

    private String storyId;

    private String llmTemplatePath;
    private String llmResponse;

    private Map<String, Object> metadata = new HashMap<>();

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
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

    public String getLlmTemplatePath() {
        return llmTemplatePath;
    }

    public void setLlmTemplatePath(String llmTemplatePath) {
        this.llmTemplatePath = llmTemplatePath;
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
        return "CodeContext [file=" + file + ", originalContent=" + originalContent + ", modifiedContent="
                + modifiedContent + ", fieldName=" + fieldName + ", fieldType=" + fieldType + ", fieldTooltip="
                + fieldTooltip + ", fieldDefaultValue=" + fieldDefaultValue + ", fieldNullable=" + fieldNullable
                + ", storyId=" + storyId + ", llmTemplatePath=" + llmTemplatePath + ", llmResponse=" + llmResponse
                + ", metadata=" + metadata + "]";
    }

}
