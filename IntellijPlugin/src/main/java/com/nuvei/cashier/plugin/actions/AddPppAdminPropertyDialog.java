package com.nuvei.cashier.plugin.actions;

import java.awt.*;

import javax.swing.*;

import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.nuvei.cashier.plugin.utils.Constants;
import com.nuvei.cashier.plugin.utils.ValidationUtils;

public class AddPppAdminPropertyDialog extends DialogWrapper {

    private final JTextField propertyNameField = new JTextField();
    private final JTextField hintField = new JTextField();
    private final JTextField storyNumberField = new JTextField();
    private final JCheckBox cachedCheckbox = new JCheckBox("Cached");
    private final JTextField fieldSize = new JTextField();
    private final JTextField defaultValue = new JTextField();
    private final JComboBox<String> propertyTypeComboBox = new ComboBox<>(Constants.fieldTypes);

    public AddPppAdminPropertyDialog(String className) {
        super(true);
        setTitle("Add a New PPP Admin Property to " + className);
        init();
    }

    private static void createTextField(JPanel panel, JTextField textField, String label, String tooltip) {
        JPanel propertyPanel = new JPanel();
        propertyPanel.setLayout(new BoxLayout(propertyPanel, BoxLayout.X_AXIS));
        propertyPanel.add(new JLabel(label));
        propertyPanel.add(textField);
        textField.setToolTipText(tooltip);
        propertyPanel.add(textField);
        panel.add(propertyPanel);
    }

    public String getPropertyName() {
        return propertyNameField.getText();
    }

    public String getType() {
        return (String) propertyTypeComboBox.getSelectedItem();
    }

    public boolean isCached() {
        return cachedCheckbox.isSelected();
    }

    public String getHint() {
        return hintField.getText();
    }

    public String getStoryNumber() {
        return storyNumberField.getText();
    }

    public Integer getFieldSize() {
        if (!getType().equals(Constants.VARCHAR))
            return null;
        try {
            String sizeText = fieldSize.getText();
            return Integer.parseInt(sizeText);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public String getDefaultValue() {
        return defaultValue.getText();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMinimumSize(new Dimension(400, panel.getMinimumSize().height));

        createTextField(panel, propertyNameField, "Property Name:", "Enter the name of the property to be added.");
        createTextField(panel, hintField, "Hint contents:", "Enter the contents of the hint of the property.");
        createTextField(panel, storyNumberField, "PBI number:", "Enter the number of your task");
        createTextField(panel, defaultValue, "Default value:", "Enter the default value of the property.");
        ValidationUtils.attachPbiNumberValidator(this.getDisposable(), storyNumberField);
        ValidationUtils.attachJavaFieldNameValidator(this.getDisposable(), propertyNameField);


        // Cached Checkbox
        JPanel cachedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cachedPanel.add(cachedCheckbox);
        cachedCheckbox.setToolTipText("Check this box if the property should be cached.");
        panel.add(cachedPanel);

        // Combo Box
        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboBoxPanel.add(new JLabel("Property Type:"));
        propertyTypeComboBox.setToolTipText("Select the type of the property.");
        propertyTypeComboBox.addActionListener(e -> {
            String type = getType();
            fieldSize.setEnabled(type.equals(Constants.VARCHAR));
            defaultValue.setText(getDefaultValueForType(type));
            ValidationUtils.attachPatternValidator(getPatternForType(type), this.getDisposable(), defaultValue);

        });
        comboBoxPanel.add(propertyTypeComboBox);

        comboBoxPanel.add(new JLabel("Size:"));
        comboBoxPanel.add(fieldSize);
        panel.add(comboBoxPanel);

        return panel;
    }

    private String getDefaultValueForType(String type) {
        switch (type) {
        case Constants.INT:
        case Constants.BIGINT:
        case Constants.BOOLEAN:
            return "0";
        case Constants.TEXT:
        case Constants.VARCHAR:
            return "N/A";
        default:
            return "";
        }
    }
    private String getPatternForType(String type) {
        switch (type) {
        case Constants.INT:
        case Constants.BIGINT:
            return "\\d+";
        case Constants.BOOLEAN:
            return "0|1";
        default:
            return ".*";
        }
    }
}