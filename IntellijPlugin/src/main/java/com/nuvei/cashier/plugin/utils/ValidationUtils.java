package com.nuvei.cashier.plugin.utils;

import javax.swing.*;

public class ValidationUtils {
    public static final InputVerifier javaFieldNameVerifier = new InputVerifier() {
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            return text.matches("[a-zA-Z_][a-zA-Z0-9_]*");
        }
    };
}
