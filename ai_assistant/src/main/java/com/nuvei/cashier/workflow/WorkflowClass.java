package com.nuvei.cashier.workflow;

import com.nuvei.cashier.code.ClassRole;
import java.nio.file.Path;

public record WorkflowClass(
    Path file,
    ClassRole classRole
) {}
