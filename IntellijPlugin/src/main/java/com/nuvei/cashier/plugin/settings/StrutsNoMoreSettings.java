package com.nuvei.cashier.plugin.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.nuvei.cashier.ai.client.GeminiAssistant;
import dev.langchain4j.internal.Utils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "StrutsNoMoreSettings", storages = @Storage("StrutsNoMoreSettings.xml"))
public class StrutsNoMoreSettings implements PersistentStateComponent<StrutsNoMoreSettings.State> {

    private final State state = new State();

    @Nullable
    @Override
    public State getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull State state) {
        this.state.pppAdminDirectory = state.pppAdminDirectory;
        this.state.geminiApiKey = state.geminiApiKey;
        this.state.geminiModel = Utils.getOrDefault(state.geminiModel, GeminiAssistant.DEFAULT_GEMINI_MODEL);
    }

    public String getPppAdminDirectory() {
        return state.pppAdminDirectory;
    }

    public void setPppAdminDirectory(String directory) {
        state.pppAdminDirectory = directory;
    }
    public String getGeminiApiKey() {
        return state.geminiApiKey;
    }

    public void setGeminiApiKey(String directory) {
        state.geminiApiKey = directory;
    }
    public String getGeminiModel() {
        return state.geminiModel;
    }

    public void setGeminiModel(String directory) {
        state.geminiModel = directory;
    }

    public static class State {
        public String pppAdminDirectory = "";
        public String geminiApiKey = "";
        public String geminiModel = "";
    }
}