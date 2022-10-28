package io.github.huacnlee.autocorrectIdeaPlugin;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import io.github.huacnlee.AutoCorrect;
import io.github.huacnlee.LintResult;

import javax.annotation.Nullable;

public class AutoCorrectExecutor {
    // https://plugins.jetbrains.com/docs/intellij/working-with-text.html#safely-replacing-selected-text-in-the-document
    public static void format(@Nullable Project project, Document doc) {
        var file = FileDocumentManager.getInstance().getFile(doc);
        final String filepath = file == null ? "text" : file.getPath();

        if (project != null) {
            Config config = Config.getCurrent(project.getBasePath());
            if (config.isIgnored(filepath)) {
                System.out.printf("%s is ignored.\n", filepath);
                return;
            }
        }

        String text = doc.getText();

        final String newText = AutoCorrect.formatFor(text, filepath);
        WriteCommandAction.runWriteCommandAction(project, () -> {
            int startOffset = 0;
            int endOffset = doc.getTextLength();
            doc.replaceString(startOffset, endOffset, newText);
        });
    }

    @Nullable
    public static LintResult lint(@Nullable Project project, Document doc) {
        var file = FileDocumentManager.getInstance().getFile(doc);
        final String filepath = file == null ? "text" : file.getPath();

        if (project != null) {
            Config config = Config.getCurrent(project.getBasePath());
            if (config.isIgnored(filepath)) {
                System.out.printf("%s is ignored.\n", filepath);
                return null;
            }
        }

        String text = doc.getText();
        return AutoCorrect.lintFor(text, filepath);
    }

}
