package com.nuvei.cashier.code.prompt;

import com.nuvei.cashier.code.ClassRole;

public enum PromptProviderType {

    MODIFY_ENTITY_PROMPT(new DefaultPromptProvider("/templates/modify_entity_prompt.tpl.txt"), ClassRole.ENTITY),
    MODIFY_DTO_PROMPT(new DefaultPromptProvider("/templates/modify_dto_prompt.tpl.txt"), ClassRole.DTO),
    MODIFY_CACHE_DTO_PROMPT(new DefaultPromptProvider("/templates/modify_dto_prompt.tpl.txt"), ClassRole.CACHE_DTO),
    MODIFY_CACHE_LOADER_PROMPT(new DefaultPromptProvider("/templates/copy_property_prompt.tpl.txt"), ClassRole.CACHE_LOADER),
    MODIFY_SERVICE_PROMPT(new DefaultPromptProvider("/templates/modify_service_prompt.tpl.txt"), ClassRole.SERVICE),
    MODIFY_ACTION_PROMPT(new DefaultPromptProvider("/templates/modify_action_prompt.tpl.txt"), ClassRole.ACTION),
    MODIFY_JSP_PROMPT(new DefaultPromptProvider("/templates/modify_jsp_prompt.tpl.txt"), ClassRole.JSP),
    MODIFY_RESOURCE_PROMPT(new DefaultPromptProvider("/templates/modify_resource_prompt.tpl.txt"), ClassRole.RESOURCE);

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
