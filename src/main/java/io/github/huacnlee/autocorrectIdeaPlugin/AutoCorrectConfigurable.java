package io.github.huacnlee.autocorrectIdeaPlugin;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

// https://plugins.jetbrains.com/docs/intellij/settings-tutorial.html
public class AutoCorrectConfigurable implements Configurable {
    private AppSettingsComponent mySettingsComponent;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "AutoCorrect config";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return mySettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mySettingsComponent = new AppSettingsComponent();
        return mySettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettingsState settings = AppSettingsState.getInstance();
        boolean modified = !mySettingsComponent.getEnable() != settings.enable;
        modified |= mySettingsComponent.getEnableFormatOnSave() != settings.enableFormatOnSave;
        return modified;
    }

    @Override
    public void apply() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settings.enable = mySettingsComponent.getEnable();
        settings.enableFormatOnSave = mySettingsComponent.getEnableFormatOnSave();
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        mySettingsComponent.setEnable(settings.enable);
        mySettingsComponent.setEnableFormatOnSave(settings.enableFormatOnSave);
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }

}
