package com.nuvei.cashier.code.prompt;

import java.io.IOException;

import com.nuvei.cashier.code.ClassRole;

public class PromptProviderFactory implements IPromptProviderFactory {

    private final IPromptProvider modifyEntityPromptProvider;

    public PromptProviderFactory() throws IOException {
        this.modifyEntityPromptProvider = new ModifyEntityPromptProvider();
    }

    @Override
    public IPromptProvider createPromptProvider(ClassRole classRole) {
        switch (classRole) {
            case ENTITY:
                return modifyEntityPromptProvider;
            case DTO:
            case SERVICE:
            default:
                throw new IllegalArgumentException("Unsupported class role: " + classRole);
        }
    }

}
