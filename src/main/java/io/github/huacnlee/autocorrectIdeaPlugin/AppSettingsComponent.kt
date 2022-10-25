package io.github.huacnlee.autocorrectIdeaPlugin

import com.intellij.ui.components.JBCheckBox
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class AppSettingsComponent {
    val panel: JPanel
    private val enableStatus = JBCheckBox("Enable", true)
    private val enableFormatOnSaveStatus = JBCheckBox("Format On Save", true)

    init {
        panel = FormBuilder.createFormBuilder() //                .addComponent(enableStatus, 1)
            //                .addComponent(new JLabel("Turn on/off AutoCorrect."))
            //                .addVerticalGap(2)
            //                .addSeparator()
            //                .addVerticalGap(2)
            .addComponent(enableFormatOnSaveStatus, 1)
            .addComponent(JLabel("Turn on/off autoformatting file on save."))
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    val preferredFocusedComponent: JComponent
        get() = enableStatus
    var enable: Boolean
        get() = enableStatus.isSelected
        set(newStatus) {
            enableStatus.isSelected = newStatus
        }
    var enableFormatOnSave: Boolean
        get() = enableFormatOnSaveStatus.isSelected
        set(newStatus) {
            enableFormatOnSaveStatus.isSelected = newStatus
        }
}