package com.nuvei.cashier.ai;

import dev.langchain4j.data.message.ChatMessage;

public interface ICodeAssistant {

    String modifyCode(ChatMessage...messages);
}
