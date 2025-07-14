package com.nuvei.cashier.plugin.utils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LogStreamer {

    private final JTextArea logsTextArea;
    private SwingWorker<Void, String> logWorker;

    public LogStreamer(JTextArea logsTextArea) {
        this.logsTextArea = logsTextArea;
    }

    public void startStreaming() {
        this.logWorker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("application.log"))))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        publish(line); // Send log line to EDT
                    }
                }
                return null;
            }

            @Override
            protected void process(List chunks) {
                for (Object log : chunks) {
                    logsTextArea.append(log + "\n");
                }
            }
        };
        logWorker.execute();
    }

    public void stopStreaming() {
        logWorker.cancel(true);
    }
}