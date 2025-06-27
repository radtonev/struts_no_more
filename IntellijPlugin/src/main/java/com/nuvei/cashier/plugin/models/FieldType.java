package com.nuvei.cashier.plugin.models;

public class FieldType {
    private String name;
    private Integer fieldSize;
    private String defaultValue;
    private String regex;

    public FieldType() {
    }

    public FieldType(String name, Integer fieldSize, String defaultValue, String regex) {
        this.name = name;
        this.fieldSize = fieldSize;
        this.defaultValue = defaultValue;
        this.regex = regex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(Integer size) {
        this.fieldSize = size;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return name;
    }
}
