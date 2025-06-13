package com.nuvei.cashier.plugin.actions;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

import static com.nuvei.cashier.plugin.utils.ValidationUtils.javaFieldNameVerifier;

public class AddPppAdminPropertyDialog extends DialogWrapper {

    private final JTextField propertyNameField = new JTextField();
    private final JTextField hintField = new JTextField();
    private final JCheckBox cachedCheckbox = new JCheckBox("Cached");
    private final JComboBox<String> propertyTypeComboBox = new ComboBox<>(new String[]{"String", "Integer", "Boolean", "Date"});
    private final String className;

    public AddPppAdminPropertyDialog(String className) {
        super(true);
        this.className = className;
        setTitle("Add a New PPP Admin Property to " + className);
        init();
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

    public void validateField() {
        if(!propertyNameField.isValid()) throw new RuntimeException( "Invalid property name");
        if(!hintField.isValid()) throw new RuntimeException("Invalid hint field");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMinimumSize(new Dimension(400, panel.getMinimumSize().height));

        // Property Name Field
        JPanel propertyPanel = new JPanel(new BorderLayout());
        propertyPanel.add(new JLabel("Property Name:"), BorderLayout.WEST);
        propertyPanel.add(propertyNameField, BorderLayout.CENTER);
        propertyNameField.setToolTipText("Enter the name of the property to be added.");
        propertyNameField.setInputVerifier(javaFieldNameVerifier);
        panel.add(propertyPanel);

        // Property Name Field
        JPanel hintPanel = new JPanel(new BorderLayout());
        hintPanel.add(new JLabel("Hint contents:"), BorderLayout.WEST);
        hintPanel.add(hintField, BorderLayout.CENTER);
        hintField.setToolTipText("Enter the contents of the hint of the property.");
        panel.add(hintPanel);

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