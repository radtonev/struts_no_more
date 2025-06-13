package com.nuvei.cashier.plugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;


public class AddPppAdminPropertyAction extends AnAction {

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
//        todo: check if the file is an expected file ( merchantSite ... )
        AddPppAdminPropertyDialog dialog = new AddPppAdminPropertyDialog(className);

        try {
            if (dialog.showAndGet()) {
                dialog.validateField();
                String propertyName = dialog.getPropertyName();
                boolean isCached = dialog.isCached();

                generateFiles(project, file, propertyName, dialog.getType(), isCached);
            }
        } catch (Exception ex) {
            Messages.showErrorDialog(ex.getMessage(), "Invalid Input");
        }

    }

    private void generateFiles(Project project, VirtualFile file, String propertyName,String type, boolean isCached) {
        Messages.showInfoMessage(
                "Generating files for class: " + file.getNameWithoutExtension() +
                        ", property: " + propertyName +
                        ", cached: " + isCached +
                        ", type: " +  type +
                        ", file location: " +  file.getCanonicalPath() +
                        " in project: " + project.getName(),
                "File Generation"
        );
    }
    /*
    https://intellij-support.jetbrains.com/hc/en-us/community/posts/360006981360-How-to-modify-files-in-plugin
    Project project = event.getProject();
    VirtualFile file = event.getData(PlatformDataKeys.VIRTUAL_FILE);
    Document document = event.getData(PlatformDataKeys.EDITOR).getDocument();
    try {
        String newContent = changeContent(document.getText());
        Runnable r = () -> {
            document.setReadOnly(false);
            document.setText(newContent);
        };
        WriteCommandAction.runWriteCommandAction(project, r);
    } catch (Exception e) {
    }
*/
}