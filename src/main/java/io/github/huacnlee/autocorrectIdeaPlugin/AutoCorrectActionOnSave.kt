package io.github.huacnlee.autocorrectIdeaPlugin

import com.intellij.ide.actionsOnSave.impl.ActionsOnSaveFileDocumentManagerListener.ActionOnSave
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import io.github.huacnlee.autocorrectIdeaPlugin.Formatter.format

class AutoCorrectActionOnSave : ActionOnSave() {
    override fun isEnabledForProject(project: Project): Boolean {
        return AppSettingsState.instance.enableFormatOnSave
    }

    override fun processDocuments(project: Project, documents: Array<Document>) {
        if (!AppSettingsState.instance.enableFormatOnSave) {
            return
        }
        for (doc in documents) {
            val file = FileDocumentManager.getInstance().getFile(doc) ?: return
            format(project, doc)
        }
    }
}