package com.safecharge.strutsnomore.plugin.actions;

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

        if (dialog.showAndGet()) {
            String propertyName = dialog.getPropertyName();
            boolean isCached = dialog.isCached();

            generateFiles(project, file, propertyName, dialog.getType(), isCached);
        }
    }

    private void generateFiles(Project project, VirtualFile file, String propertyName,String type, boolean isCached) {
        Messages.showInfoMessage(
                "Generating files for class: " + file.getNameWithoutExtension() +
                        ", property: " + propertyName +
                        ", cached: " + isCached +
                        ", type: " +  type +
                        " in project: " + project.getName(),
                "File Generation"
        );
        /*try {
            // Base directory for generated files
            String baseDir = project.getBasePath();
            if (baseDir == null) {
                throw new IllegalStateException("Project base path is null");
            }

            // Generate SQL file
            String sqlDir = baseDir + "/src/main/resources/sql";
            String sqlFileName = "add_" + propertyName.toLowerCase() + ".sql";
            String sqlContent = "ALTER TABLE your_table ADD COLUMN " + propertyName + " VARCHAR(255);";
            writeFile(sqlDir, sqlFileName, sqlContent);

            // Generate JSP file
            String jspDir = baseDir + "/src/main/webapp/WEB-INF/jsp";
            String jspFileName = propertyName.toLowerCase() + ".jsp";
            String jspContent = "<%-- JSP for " + propertyName + " --%>\n" +
                    "<div>\n" +
                    "    <label>" + propertyName + ":</label>\n" +
                    "    <input type=\"text\" name=\"" + propertyName + "\" />\n" +
                    "</div>";
            writeFile(jspDir, jspFileName, jspContent);

            // Update Struts configuration
            String strutsConfigPath = baseDir + "/src/main/resources/struts-config.xml";
            String strutsEntry = "<action path=\"/" + propertyName.toLowerCase() + "\" " +
                    "type=\"com.yourcompany.struts.actions." + propertyName + "Action\" " +
                    "name=\"" + propertyName + "Form\" scope=\"request\" validate=\"true\">\n" +
                    "    <forward name=\"success\" path=\"/WEB-INF/jsp/" + jspFileName + "\" />\n" +
                    "</action>";
            appendToFile(strutsConfigPath, strutsEntry);

            System.out.println("Files generated successfully for property: " + propertyName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

    /*private void writeFile(String directoryPath, String fileName, String content) throws IOException {
        Path dirPath = Paths.get(directoryPath);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }
        Path filePath = dirPath.resolve(fileName);
        Files.write(filePath, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private void appendToFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        Files.write(path, ("\n" + content).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }*/
}