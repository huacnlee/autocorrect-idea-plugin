package io.github.huacnlee.autocorrectIdeaPlugin;

import com.intellij.AppTopics;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileDocumentManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;

// https://github.com/intellij-rust/intellij-rust/blob/dcfaac54d5fe6bf0d6097ddc08fc9e26343c11bd/src/main/kotlin/org/rust/cargo/RustfmtWatcher.kt#L53
public class FileSaveListener implements BulkFileListener, FileDocumentManagerListener {

    @Override
    public void beforeDocumentSaving(Document doc) {
        // Get current project
        Project project = ProjectManager.getInstance().getOpenProjects()[0];
        if (project == null) {
            return;
        }

        if (!AppSettingsState.getInstance().enableFormatOnSave) {
            return;
        }

        VirtualFile file = FileDocumentManager.getInstance().getFile(doc);
        if (file == null) {
            return;
        }

        Formatter.format(project, doc);
    }
}