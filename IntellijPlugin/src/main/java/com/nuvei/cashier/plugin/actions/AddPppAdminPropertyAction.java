package com.nuvei.cashier.plugin.actions;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.nuvei.cashier.plugin.services.AiLauncher;
import com.nuvei.cashier.plugin.settings.StrutsNoMoreSettings;
import com.nuvei.cashier.plugin.utils.LogStreamer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class AddPppAdminPropertyAction extends AnAction {
    private static final List<String> allowedPlaces = new ArrayList<>();

    static {
        allowedPlaces.add("EditorPopup"); // Context menu in the editor
        allowedPlaces.add("ProjectViewPopup"); // Context menu in the project view
        allowedPlaces.add("FileEditorPopup"); // Context menu in the file editor
    }

    private final AiLauncher aiLauncher = new AiLauncher();

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }

        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (file == null || !file.isValid() || !file.isInLocalFileSystem()) {
            return;
        }
        String className = file.getNameWithoutExtension();
        AddPppAdminPropertyDialog dialog = new AddPppAdminPropertyDialog(className);
        try {
            if (dialog.showAndGet()) {
                generateFiles(project, file, dialog);
            }
        } catch (Exception ex) {
            Messages.showErrorDialog(ex.getMessage(), "Invalid Input");
        }

    }

    /**
     * To enable the action only for Java files, we check the file extension.
     * It can check for the name of the file as well. <br>
     * <b>todo: check if the file is from the accepted list of files, e.g., MerchantSite.java, ...etc.</b>
     */
    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(false); // Default state
        if (!allowedPlaces.contains(e.getPlace())) return;
        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
        Editor hostEditor = e.getData(CommonDataKeys.HOST_EDITOR);
        if (file == null && hostEditor != null) {
            file = hostEditor.getVirtualFile();
        }
        if (file != null && "java".equals(file.getExtension())) {
            e.getPresentation().setEnabledAndVisible(true);
        }
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT; // Use background thread for updates
    }

    private void generateFiles(Project project, VirtualFile file, AddPppAdminPropertyDialog dialog) {
        String pppAdminDirectory = ApplicationManager.getApplication().getService(StrutsNoMoreSettings.class).getPppAdminDirectory();
        String filePath = file.getCanonicalPath();
        if (filePath == null) {
            Messages.showErrorDialog(project, "File path is null. Cannot generate files.", "Error");
            return;
        }
        LoadingDialog loadingDialog = new LoadingDialog(dialog.getLoadingMessage(file, pppAdminDirectory));
        LogStreamer logStreamer = new LogStreamer(loadingDialog.getLogsTextArea());
        try {
            logStreamer.startStreaming();
            loadingDialog.startLoading(() -> {
                try {
                    aiLauncher.launch(dialog, filePath, pppAdminDirectory);
                } catch (Exception e) {
                    Messages.showErrorDialog(e.getMessage(), "Failed Property Generation.");
                }
            });
        } catch (Exception e) {
            Messages.showErrorDialog(e.getMessage(), "Something Went Wrong.");
        } finally {
            logStreamer.stopStreaming();
        }
    }
}