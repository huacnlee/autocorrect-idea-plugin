package io.github.huacnlee.autocorrectIdeaPlugin;

import com.intellij.ide.actionsOnSave.impl.ActionsOnSaveFileDocumentManagerListener;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class AutoCorrectActionOnSave extends ActionsOnSaveFileDocumentManagerListener.ActionOnSave {
    @Override
    public boolean isEnabledForProject(@NotNull Project project) {
        return AppSettingsState.getInstance().enableFormatOnSave;
    }

    @Override
    public void processDocuments(@NotNull Project project, @NotNull Document @NotNull [] documents) {
        if (!AppSettingsState.getInstance().enableFormatOnSave) {
            return;
        }

        for (Document doc : documents) {
            VirtualFile file = FileDocumentManager.getInstance().getFile(doc);
            if (file == null) {
                return;
            }

            AutoCorrectExecutor.format(project, doc);
        }
    }
}
