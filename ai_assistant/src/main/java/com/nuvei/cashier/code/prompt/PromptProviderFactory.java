package com.nuvei.cashier.code.prompt;

import com.nuvei.cashier.code.ClassRole;

public class PromptProviderFactory {
    
    public static IPromptProvider createPromptProvider(ClassRole classRole) {
        for (PromptProviderType type : PromptProviderType.values()) {
            if (type.getClassRole().equals(classRole)) {
                return type.getPromptProvider();
            }
        }

        throw new IllegalArgumentException("Unsupported class role: " + classRole);
    }
}
