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

    private JTextField directoryField;
    private JPanel panel;

    @Override
    public @Nullable JComponent createComponent() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(getPppAdminPathPanel());
        return panel;
    }

    @NotNull
    private JPanel getPppAdminPathPanel() {
        JPanel pppAdminAddress = new JPanel();

        pppAdminAddress.setLayout(new BoxLayout(pppAdminAddress, BoxLayout.X_AXIS));

        JLabel label = new JLabel("PPP Admin Project Directory:");
        directoryField = new JTextField();
        directoryField.setPreferredSize(new Dimension(300, 25));
        directoryField.setToolTipText("Enter the directory path for the PPP Admin project.");

        JButton srcButton = getSrcButton();

        pppAdminAddress.add(label);
        pppAdminAddress.add(directoryField);
        pppAdminAddress.add(srcButton);
        return pppAdminAddress;
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
        return !directoryField.getText().equals(settings.getPppAdminDirectory());
    }

    @Override
    public void apply() {
        StrutsNoMoreSettings settings = ApplicationManager.getApplication().getService(StrutsNoMoreSettings.class);
        settings.setPppAdminDirectory(directoryField.getText());
    }

    @Override
    public void reset() {
        StrutsNoMoreSettings settings = ApplicationManager.getApplication().getService(StrutsNoMoreSettings.class);
        directoryField.setText(settings.getPppAdminDirectory());
    }

    @Override
    public String getDisplayName() {
        return "PPP Admin Settings";
    }
}