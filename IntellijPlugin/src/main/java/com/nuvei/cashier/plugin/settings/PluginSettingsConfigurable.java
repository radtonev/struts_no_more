package com.nuvei.cashier.plugin.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class PluginSettingsConfigurable implements Configurable {

    private final JTextField directoryField = new JTextField();
    private final JTextField geminiApiKeyField = new JTextField();
    private final JTextField geminiModelField = new JTextField();

    @Override
    public @Nullable JComponent createComponent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(getPppAdminPathPanel());
        panel.add(getGeminiApiKeyPanel());
        panel.add(getGeminiModelPanel());
        return panel;
    }

    @NotNull
    private JPanel getPppAdminPathPanel() {
        JPanel pppAdminAddress = new JPanel();

        pppAdminAddress.setLayout(new BoxLayout(pppAdminAddress, BoxLayout.X_AXIS));
        pppAdminAddress.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel label = new JLabel("PPP Admin Project Directory:");
        directoryField.setToolTipText("Enter the directory path for the PPP Admin project.");

        JButton srcButton = getSrcButton();

        pppAdminAddress.add(label);
        pppAdminAddress.add(directoryField);
        pppAdminAddress.add(srcButton);
        pppAdminAddress.add(Box.createHorizontalGlue());

        return pppAdminAddress;
    }

    @NotNull
    private JPanel getGeminiApiKeyPanel() {
        JPanel geminiApiKeyPanel = new JPanel();
        geminiApiKeyPanel.setLayout(new BoxLayout(geminiApiKeyPanel, BoxLayout.X_AXIS));

        JLabel label = new JLabel("Gemini API Key:");
        geminiApiKeyField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        geminiApiKeyField.setToolTipText("Enter the Gemini API key.");

        geminiApiKeyPanel.add(label);
        geminiApiKeyPanel.add(geminiApiKeyField);

        return geminiApiKeyPanel;
    }

    @NotNull
    private JPanel getGeminiModelPanel() {
        JPanel geminiModelPanel = new JPanel();
        geminiModelPanel.setLayout(new BoxLayout(geminiModelPanel, BoxLayout.X_AXIS));

        JLabel label = new JLabel("Gemini Model:");
        geminiModelField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        geminiModelField.setToolTipText("Enter the Gemini model name.");

        geminiModelPanel.add(label);
        geminiModelPanel.add(geminiModelField);

        return geminiModelPanel;
    }

    @NotNull
    private JButton getSrcButton() {
        JButton srcButton = new JButton("Open Src");
        srcButton.setToolTipText("Open the JetBrains file explorer.");
        srcButton.addActionListener(e -> {
            FileChooserDescriptor descriptor = new FileChooserDescriptor(true, true, false, false, false, false);
            VirtualFile[] files = FileChooserFactory.getInstance().createFileChooser(descriptor, null, null).choose(null);
            VirtualFile file = files.length > 0 ? files[0] : null;
            if (file != null) {
                directoryField.setText(file.getPath());
            }
        });
        return srcButton;
    }

    @Override
    public boolean isModified() {
        StrutsNoMoreSettings settings = ApplicationManager.getApplication().getService(StrutsNoMoreSettings.class);
        return !directoryField.getText().equals(settings.getPppAdminDirectory()) ||
                !geminiApiKeyField.getText().equals(settings.getGeminiApiKey()) ||
                !geminiModelField.getText().equals(settings.getGeminiModel());
    }

    @Override
    public void apply() {
        StrutsNoMoreSettings settings = ApplicationManager.getApplication().getService(StrutsNoMoreSettings.class);
        settings.setPppAdminDirectory(directoryField.getText());
        settings.setGeminiApiKey(geminiApiKeyField.getText());
        settings.setGeminiModel(geminiModelField.getText());
    }

    @Override
    public void reset() {
        StrutsNoMoreSettings settings = ApplicationManager.getApplication().getService(StrutsNoMoreSettings.class);
        directoryField.setText(settings.getPppAdminDirectory());
        geminiApiKeyField.setText(settings.getGeminiApiKey());
        geminiModelField.setText(settings.getGeminiModel());
    }

    @Override
    public String getDisplayName() {
        return "Struts No More Settings";
    }
}