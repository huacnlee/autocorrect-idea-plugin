package io.github.huacnlee.autocorrectIdeaPlugin;

import com.intellij.ui.components.JBBox;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;

public class AppSettingsComponent {
    private final JPanel myMainPanel;
    private final JBCheckBox enableStatus = new JBCheckBox("Enable", true);
    private final JBCheckBox enableFormatOnSaveStatus = new JBCheckBox("Format On Save", true);
    private final JBCheckBox enableLintStatus = new JBCheckBox("Enable Lint", true);

    public AppSettingsComponent() {

        myMainPanel = FormBuilder.createFormBuilder()
                .addComponent(enableFormatOnSaveStatus, 1)
                .addComponent(new JLabel("Turn on/off autoformatting file on save."))
                .addComponent(enableLintStatus, 1)
                .addComponent(new JLabel("Turn on/off AutoCorrect lint."))
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return enableStatus;
    }

    public boolean getEnable() {
        return enableStatus.isSelected();
    }

    public void setEnable(boolean newStatus) {
        enableStatus.setSelected(newStatus);
    }

    public boolean getEnableFormatOnSave() {
        return enableFormatOnSaveStatus.isSelected();
    }

    public void setEnableFormatOnSave(boolean newStatus) {
        enableFormatOnSaveStatus.setSelected(newStatus);
    }

    public boolean getEnableLint() {
        return enableLintStatus.isSelected();
    }

    public void setEnableLint(boolean newStatus) {
        enableLintStatus.setSelected(newStatus);
    }

}
