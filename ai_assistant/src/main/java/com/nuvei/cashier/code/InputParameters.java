package com.nuvei.cashier.code;

import java.nio.file.Path;

public record InputParameters(
    Path file,
    String fieldName,
    String fieldType,
    String fieldSize,
    String fieldTooltip,
    String fieldDefaultValue,
    boolean fieldCached,
    boolean fieldNullable,
    String storyId
) {

}
