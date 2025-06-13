package com.nuvei.cashier.plugin.utils;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.ui.ComponentValidator;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.ui.DocumentAdapter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;

public class ValidationUtils {
    /**
     * <a href=https://plugins.jetbrains.com/docs/intellij/validation-errors.html>JetBrains Tutorial on Validation errors</a>
     */
    public static void attachJavaFieldNameValidator(Disposable parentDisposable, JTextField port) {
        new ComponentValidator(parentDisposable)
                .withValidator(() -> {
                    String text = port.getText();
                    String regex = "[a-zA-Z_][a-zA-Z0-9_]*";
                    if (!text.matches(regex)) {
                        return new ValidationInfo("Expected format: a valid Java field name (starts with a letter or underscore, followed by letters, digits, or underscores).", port);
                    }
                    return null;
                })
                .installOn(port);
        port.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                ComponentValidator.getInstance(port).ifPresent(ComponentValidator::revalidate);
            }
        });
    }

    public static void attachPbiNumberValidator(Disposable parentDisposable, JTextField port) {
        new ComponentValidator(parentDisposable)
                .withValidator(() -> {
                    String text = port.getText();
                    String regex = "(PBI_\\d+|pbi_\\d+|\\d+)";
                    if (!text.matches(regex)) {
                        return new ValidationInfo("Expected format: PBI_12345, pbi_12345, or 12345.", port);
                    }
                    return null;
                }).installOn(port);
        port.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                ComponentValidator.getInstance(port).ifPresent(ComponentValidator::revalidate);
            }
        });
    }
}