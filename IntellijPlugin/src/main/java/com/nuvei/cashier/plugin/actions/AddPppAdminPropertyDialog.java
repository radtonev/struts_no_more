package com.nuvei.cashier.plugin.actions;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.ComponentValidator;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.nuvei.cashier.plugin.models.FieldType;
import com.nuvei.cashier.plugin.utils.Constants;
import com.nuvei.cashier.plugin.utils.ValidationUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class AddPppAdminPropertyDialog extends DialogWrapper {
    private static final Logger LOG = Logger.getInstance(AddPppAdminPropertyDialog.class);

    private final JTextField propertyNameField = new JTextField();
    private final JTextField hintField = new JTextField();
    private final JTextField storyNumberField = new JTextField();
    private final JCheckBox cachedCheckbox = new JCheckBox("Cached");
    private final JCheckBox nullableCheckbox = new JCheckBox("Nullable");
    private final JTextField fieldSize = new JTextField();
    private final JTextField defaultValue = new JTextField();
    private final JComboBox<FieldType> propertyTypeComboBox = new ComboBox<>(Constants.fieldTypes);

    public AddPppAdminPropertyDialog(String className) {
        super(true);
        setTitle("Add a New PPP Admin Property to " + className);
        init();
    }

    private static void createTextField(JPanel panel, JTextField textField, String label, String tooltip) {
        JPanel propertyPanel = new JPanel();
        propertyPanel.setLayout(new BoxLayout(propertyPanel, BoxLayout.X_AXIS));
        propertyPanel.add(new JLabel(label));
        textField.setToolTipText(tooltip);
        propertyPanel.add(textField);
        panel.add(propertyPanel);
    }

    @Override
    protected void doOKAction() {
        if (!isValid()) {
            LOG.error("Validation failed for AddPppAdminPropertyDialog.");
            Messages.showErrorDialog("Please fill in all required fields correctly.", "Invalid Input");
            return;
        }
        LOG.info("Validation passed for AddPppAdminPropertyDialog. Proceeding with generation.");
        super.doOKAction();
    }

    public String getPropertyName() {
        return propertyNameField.getText();
    }

    public String getType() {
        FieldType selectedItem = (FieldType) propertyTypeComboBox.getSelectedItem();
        if (selectedItem != null) {
            return selectedItem.getName();
        }
        return "";
    }

    public boolean isCached() {
        return cachedCheckbox.isSelected();
    }

    public boolean isNullable() {
        return nullableCheckbox.isSelected();
    }

    public String getHint() {
        return hintField.getText();
    }

    public String getStoryNumber() {
        return storyNumberField.getText();
    }

    public String getFieldSize() {
        if (!getType().equals(Constants.VARCHAR))
            return "";
        return fieldSize.getText();
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

        // Cached Checkbox
        JPanel cachedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cachedCheckbox.setToolTipText("Check this box if the property should be cached.");
        cachedPanel.add(cachedCheckbox);
        nullableCheckbox.setToolTipText("Check this box if the property can be null.");
        cachedPanel.add(nullableCheckbox);
        panel.add(cachedPanel);

        // Combo Box
        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboBoxPanel.add(new JLabel("Property Type:"));
        propertyTypeComboBox.setToolTipText("Select the type of the property.");
        propertyTypeComboBox.addActionListener(e -> onTypeChange());
        comboBoxPanel.add(propertyTypeComboBox);
        comboBoxPanel.add(new JLabel("Size:"));
        fieldSize.setPreferredSize(new Dimension(100, 35));
        comboBoxPanel.add(fieldSize);
        createTextField(panel, defaultValue, "Default value:", "Enter the default value of the property.");

        onTypeChange();

        ValidationUtils.attachPbiNumberValidator(this.getDisposable(), storyNumberField);
        ValidationUtils.attachJavaFieldNameValidator(this.getDisposable(), propertyNameField);
        ValidationUtils.attachFieldTypeValidator(this.getDisposable(), propertyTypeComboBox, defaultValue);
        ValidationUtils.attachPositiveIntegerValidator(this.getDisposable(), fieldSize);

        panel.add(comboBoxPanel);

        return panel;
    }

    private void onTypeChange() {
        FieldType selectedType = (FieldType) propertyTypeComboBox.getSelectedItem();
        if (selectedType != null) {
            fieldSize.setEnabled(Constants.VARCHAR.equals(selectedType.getName()));
            fieldSize.setText(selectedType.getFieldSize().toString());
            defaultValue.setText(selectedType.getDefaultValue());
            defaultValue.setToolTipText("Expected format: '" + selectedType.getRegex() + "'");
        }
    }

    public String getLoadingMessage(VirtualFile file, String pppAdminDirectory) {
        return String.format(
                "<html>Generating files for class: %s<br/>" +
                        "Property: %s<br/>" +
                        "Hint: %s<br/>" +
                        "Story number: %s<br/>" +
                        "Cached: %s<br/>" +
                        "Type: %s<br/>" +
                        "Field size: %s<br/>" +
                        "Default value: %s<br/>" +
                        "File location: %s<br/>" +
                        "PPP Admin Path: %s</html>",
                file.getNameWithoutExtension(), getPropertyName(), getHint(), getStoryNumber(),
                isCached(), getType(), getFieldSize(), getDefaultValue(), file.getCanonicalPath(),
                pppAdminDirectory
        );
    }

    public boolean isValid() {
        ComponentValidator.getInstance(propertyNameField).ifPresent(ComponentValidator::revalidate);
        ComponentValidator.getInstance(storyNumberField).ifPresent(ComponentValidator::revalidate);
        ComponentValidator.getInstance(propertyTypeComboBox).ifPresent(ComponentValidator::revalidate);
        ComponentValidator.getInstance(defaultValue).ifPresent(ComponentValidator::revalidate);
        ComponentValidator.getInstance(fieldSize).ifPresent(ComponentValidator::revalidate);
        return !propertyNameField.getText().isEmpty() && propertyNameField.isValid() && hintField.isValid() &&
                !storyNumberField.getText().isEmpty() && storyNumberField.isValid() &&
                fieldSize.isValid() && defaultValue.isValid() && propertyTypeComboBox.isValid();
    }
}