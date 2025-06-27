package com.nuvei.cashier.plugin.actions;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class LoadingDialog extends DialogWrapper {

    private final JProgressBar progressBar = new JProgressBar();
    private final JLabel messageLabel;
    private final JTextArea logsTextArea = new JTextArea(10, 50);

    public LoadingDialog(String message) {
        super(true);
        setTitle("Loading");
        messageLabel = new JLabel(message, SwingConstants.LEFT);
        init();
        setOKActionEnabled(false);
    }

    public JTextArea getLogsTextArea() {
        return logsTextArea;
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(400, 400));

        // Message Label
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(messageLabel);

        // Progress Bar
        progressBar.setIndeterminate(true);
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(progressBar);

        // Logs Section
        JPanel logsPanel = new JPanel();
        logsPanel.setLayout(new BorderLayout());
        logsPanel.setBorder(JBUI.Borders.empty(10));
        logsPanel.add(new JScrollPane(logsTextArea), BorderLayout.CENTER);
        logsTextArea.setEditable(false);
        panel.add(Box.createVerticalStrut(10));
        panel.add(logsPanel);

        return panel;
    }

    public void startLoading(Runnable task) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                task.run();
                return null;
            }

            @Override
            protected void done() {
                progressBar.setIndeterminate(false);
                progressBar.setValue(100);
                messageLabel.setText("Loading complete.");
                setOKActionEnabled(true);
            }
        };
        worker.execute();
        show(); // Show the dialog
    }
}