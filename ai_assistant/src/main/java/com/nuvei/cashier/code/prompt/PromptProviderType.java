package com.nuvei.cashier.code.prompt;

import java.io.IOException;

import com.nuvei.cashier.code.ClassRole;

public enum PromptProviderType {

    MODIFY_ENTITY_PROMPT(createModifyEntityPromptProvider(), ClassRole.ENTITY);

    private final IPromptProvider promptProvider;
    private final ClassRole classRole;

    private static IPromptProvider createModifyEntityPromptProvider() {
        try {
            return new ModifyEntityPromptProvider();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create ModifyEntityPromptProvider", e);
        }
    }

    private PromptProviderType(IPromptProvider promptProvider, ClassRole classRole) {
        this.promptProvider = promptProvider;
        this.classRole = classRole;
    }

    public IPromptProvider getPromptProvider() {
        return promptProvider;
    }

    public ClassRole getClassRole() {
        return classRole;
    }
}
