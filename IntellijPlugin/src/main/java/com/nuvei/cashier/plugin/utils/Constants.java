package com.nuvei.cashier.plugin.utils;

import com.nuvei.cashier.plugin.models.FieldType;

public interface Constants {
    String VARCHAR = "Varchar";
    String BIGINT = "Bigint";
    String INT = "Int";
    String BOOLEAN = "Boolean";
    String TEXT = "Text";

    FieldType[] fieldTypes = new FieldType[]{new FieldType(BOOLEAN, 1, "0", "0|1"),
            new FieldType(INT, 4, "0", "\\d+"),
            new FieldType(BIGINT, 8, "0", "\\d+"),
            new FieldType(VARCHAR, 255, "N/A", ".*"),
            new FieldType(TEXT, 65535, "N/A", ".*")
    };
}
