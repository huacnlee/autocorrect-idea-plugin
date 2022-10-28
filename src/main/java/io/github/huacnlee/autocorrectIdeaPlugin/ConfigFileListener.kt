package io.github.huacnlee.autocorrectIdeaPlugin

import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileContentChangeEvent
import com.intellij.openapi.vfs.newvfs.events.VFileEvent

class ConfigFileListener : BulkFileListener {
    override fun after(events: MutableList<out VFileEvent>) {
        for (event in events) {
            if (event !is VFileContentChangeEvent) continue
            val file = event.file
            if (!file.name.endsWith(".autocorrectrc")) continue

            for (project in ProjectManager.getInstance().openProjects) {
                Config.initCurrent(project.basePath)
            }
        }
    }
}