package com.nuvei.cashier.code.prompt;

import com.nuvei.cashier.code.ClassRole;

public enum PromptProviderType {

    MODIFY_ENTITY_PROMPT(new DefaultPromptProvider("/templates/modify_entity_prompt.tpl.txt"), ClassRole.ENTITY),
    MODIFY_DTO_PROMPT(new DefaultPromptProvider("/templates/modify_dto_prompt.tpl.txt"), ClassRole.DTO),
    MODIFY_CACHE_DTO_PROMPT(new DefaultPromptProvider("/templates/modify_dto_prompt.tpl.txt"), ClassRole.CACHE_DTO),;

    private final IPromptProvider promptProvider;
    private final ClassRole classRole;

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
