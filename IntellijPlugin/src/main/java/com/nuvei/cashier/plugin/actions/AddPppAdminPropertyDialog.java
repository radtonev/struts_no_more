package com.nuvei.cashier.plugin.actions;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.nuvei.cashier.plugin.utils.ValidationUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class AddPppAdminPropertyDialog extends DialogWrapper {

    private final JTextField propertyNameField = new JTextField();
    private final JTextField hintField = new JTextField();
    private final JTextField storyNumberField = new JTextField();
    private final JCheckBox cachedCheckbox = new JCheckBox("Cached");
    private final JComboBox<String> propertyTypeComboBox = new ComboBox<>(new String[]{"String", "Integer", "Checkbox"});
    private final String className;

    public AddPppAdminPropertyDialog(String className) {
        super(true);
        this.className = className;
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

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMinimumSize(new Dimension(400, panel.getMinimumSize().height));

        createTextField(panel, propertyNameField, "Property Name:", "Enter the name of the property to be added.");
        createTextField(panel, hintField, "Hint contents:", "Enter the contents of the hint of the property.");
        createTextField(panel, storyNumberField, "PBI number:", "Enter your story number");
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
        comboBoxPanel.add(propertyTypeComboBox);
        panel.add(comboBoxPanel);

        return panel;
    }
}