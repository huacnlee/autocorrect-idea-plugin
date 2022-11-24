package io.github.huacnlee.autocorrectIdeaPlugin

import com.intellij.ide.actionsOnSave.*
import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

private const val CONFIGURABLE_ID = "settings.autocorrect"

// https://plugins.jetbrains.com/docs/intellij/settings-tutorial.html
class AutoCorrectConfigurable : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "AutoCorrect"
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return mySettingsComponent!!.preferredFocusedComponent
    }

    override fun createComponent(): JComponent? {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.getInstance()
        return (
                mySettingsComponent!!.enableFormatOnSave != settings.enableFormatOnSave
                        || mySettingsComponent!!.enableLint != settings.enableLint
                )
    }

    override fun apply() {
        val settings = AppSettingsState.getInstance()
        settings.enable = mySettingsComponent!!.enable
        settings.enableLint = mySettingsComponent!!.enableLint
        settings.enableFormatOnSave = mySettingsComponent!!.enableFormatOnSave
    }

    override fun reset() {
        val settings = AppSettingsState.getInstance()
        mySettingsComponent!!.enable = settings.enable
        mySettingsComponent!!.enableLint = settings.enableLint
        mySettingsComponent!!.enableFormatOnSave = settings.enableFormatOnSave
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }

    class AutoCorrectOnSaveInfoProvider : ActionOnSaveInfoProvider() {
        override fun getActionOnSaveInfos(context: ActionOnSaveContext):
                List<ActionOnSaveInfo> = listOf(AutoCorrectOnSaveActionInfo(context))

        override fun getSearchableOptions(): Collection<String> {
            return listOf("Run AutoCorrect on Save")
        }
    }

    private class AutoCorrectOnSaveActionInfo(actionOnSaveContext: ActionOnSaveContext) :
        ActionOnSaveBackedByOwnConfigurable<AutoCorrectConfigurable>(
            actionOnSaveContext,
            CONFIGURABLE_ID,
            AutoCorrectConfigurable::class.java
        ) {

        @Suppress("DialogTitleCapitalization")
        override fun getActionOnSaveName() = "Run AutoCorrect on Save"

        override fun getCommentAccordingToStoredState() =
            getComment("enableFormatOnSave")

        private fun getComment(s: String): ActionOnSaveComment? {
            if (s == "enableFormatOnSave") {
                return ActionOnSaveComment.info("Turn on/off autoformatting file on save.")
            };

            return ActionOnSaveComment.info("");
        }

        override fun isActionOnSaveEnabledAccordingToStoredState() = AppSettingsState.getInstance().enableFormatOnSave

        override fun isActionOnSaveEnabledAccordingToUiState(configurable: AutoCorrectConfigurable) =
            configurable.mySettingsComponent?.enableFormatOnSave == true

        override fun setActionOnSaveEnabled(configurable: AutoCorrectConfigurable, enabled: Boolean) {
            configurable.mySettingsComponent?.enableFormatOnSave = enabled
        }

        override fun getActionLinks() = listOf(createGoToPageInSettingsLink(CONFIGURABLE_ID))
    }
}