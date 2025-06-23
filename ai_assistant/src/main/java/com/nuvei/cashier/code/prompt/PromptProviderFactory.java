package com.nuvei.cashier.code.prompt;

import java.io.IOException;
import java.util.Map;

import com.nuvei.cashier.code.ClassRole;

public class PromptProviderFactory implements IPromptProvider {

    private final IPromptProvider modifyEntityPromptProvider;

    public PromptProviderFactory() throws IOException {
        this.modifyEntityPromptProvider = new ModifyEntityPromptProvider();
    }

    @Override
    public String getPrompt(ClassRole classRole, Map<String, Object> variables) {
        switch (classRole) {
            case ENTITY:
                return modifyEntityPromptProvider.getPrompt(classRole, variables);
            case DTO:
            case SERVICE:
            default:
                throw new IllegalArgumentException("Unsupported class role: " + classRole);
        }
    }

}
