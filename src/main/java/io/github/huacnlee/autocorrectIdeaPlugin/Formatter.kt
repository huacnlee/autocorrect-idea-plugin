package io.github.huacnlee.autocorrectIdeaPlugin

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import io.github.huacnlee.AutoCorrect

object Formatter {
    // https://plugins.jetbrains.com/docs/intellij/working-with-text.html#safely-replacing-selected-text-in-the-document
    @JvmStatic
    fun format(project: Project, doc: Document) {
        var file = FileDocumentManager.getInstance().getFile(doc);
        val filepath = FileDocumentManager.getInstance().getFile(doc)!!.path
        val config = Config.getCurrent(project.basePath)
        if (config.isIgnored(filepath)) {
            System.out.printf("%s is ignored.\n", file!!.name)
            return
        }
        val text = doc.text
        val newText = AutoCorrect.formatFor(text, filepath)
        WriteCommandAction.runWriteCommandAction(project) {
            val startOffset = 0
            val endOffset = doc.textLength
            doc.replaceString(startOffset, endOffset, newText)
        }
    }
}