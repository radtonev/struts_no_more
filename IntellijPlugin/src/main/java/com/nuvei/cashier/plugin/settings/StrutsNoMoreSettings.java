package com.nuvei.cashier.plugin.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
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
    }

    public String getPppAdminDirectory() {
        return state.pppAdminDirectory;
    }

    public void setPppAdminDirectory(String directory) {
        state.pppAdminDirectory = directory;
    }

    public static class State {
        public String pppAdminDirectory = "";
    }
}