package io.github.huacnlee.autocorrectIdeaPlugin

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.event.DocumentAdapter
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import io.github.huacnlee.autocorrectIdeaPlugin.Config.Companion.initCurrent
import java.util.concurrent.atomic.AtomicBoolean

class ConfigFileListener(private val project: Project) {
    private val LISTENING = AtomicBoolean(false)
    private fun startListener() {
        if (LISTENING.compareAndSet(false, true)) ApplicationManager.getApplication().invokeLater {
            ApplicationManager.getApplication().runWriteAction {
                val multicaster = EditorFactory.getInstance().eventMulticaster
                multicaster.addDocumentListener(ConfigFileDocumentListener(), project)
            }
        }
    }

    private fun fileChanged(file: VirtualFile) {
        System.out.printf("AutoCorrect config before changed: %s.\n", file.name)
        val isAutoCorrectFile = file.name.endsWith(".autocorrectrc") || file.name.endsWith(".autocorrectignore")
        if (isAutoCorrectFile && !project.isDisposed) {
            System.out.printf("AutoCorrect config has changed: %s.\n", file.name)
            reloadConfig()
        }
    }

    private fun reloadConfig() {
        initCurrent(project.basePath!!)
    }

    /**
     * Document Listener
     */
    private inner class ConfigFileDocumentListener() : DocumentAdapter() {
        override fun documentChanged(event: DocumentEvent) {
            val file = FileDocumentManager.getInstance().getFile(event.document)
            if (file != null) {
                fileChanged(file)
            }
        }
    }

    companion object {
        fun start(project: Project) {
            val listener = ServiceManager.getService(project, ConfigFileListener::class.java)
            listener.startListener()
        }
    }
}