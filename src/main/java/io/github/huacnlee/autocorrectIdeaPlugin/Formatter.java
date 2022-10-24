package io.github.huacnlee.autocorrectIdeaPlugin;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import io.github.huacnlee.AutoCorrect;

public class Formatter {
    // https://plugins.jetbrains.com/docs/intellij/working-with-text.html#safely-replacing-selected-text-in-the-document
    public static void format(Project project, Document doc) {
        final String filepath = FileDocumentManager.getInstance().getFile(doc).getPath();
        Config config = Config.getCurrent(project.getBasePath());
        if (config.isIgnored(filepath)) {
            System.out.printf("%s is ignored.\n", filepath);
            return;
        }

        String text = doc.getText();

        final String newText = AutoCorrect.formatFor(text, filepath);
        WriteCommandAction.runWriteCommandAction(project, () -> {
            int startOffset = 0;
            int endOffset = doc.getTextLength();
            doc.replaceString(startOffset, endOffset, newText);
        });
    }

}
