package com.nuvei.cashier.ai;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.chat.response.ChatResponse;

public interface ICodeAssistant {

    ChatResponse modifyCode(ChatMessage...messages);
}
