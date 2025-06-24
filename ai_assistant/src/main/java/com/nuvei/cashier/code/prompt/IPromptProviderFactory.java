package com.nuvei.cashier.code.prompt;

import com.nuvei.cashier.code.ClassRole;

public interface IPromptProviderFactory {

    IPromptProvider createPromptProvider(ClassRole classRole);
}
