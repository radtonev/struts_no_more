package com.nuvei.cashier.workflow;

import java.nio.file.Path;

import com.nuvei.cashier.code.ClassRole;

public record WorkflowClass(
    Path file,
    ClassRole classRole
) {}
