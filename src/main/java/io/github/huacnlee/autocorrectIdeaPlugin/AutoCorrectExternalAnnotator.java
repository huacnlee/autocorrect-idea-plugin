package io.github.huacnlee.autocorrectIdeaPlugin;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import io.github.huacnlee.LineResult;
import io.github.huacnlee.LintResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;

import java.util.List;

public class AutoCorrectExternalAnnotator extends ExternalAnnotator<Editor, LintResult> {
    private static final String MESSAGE_PREFIX = "AutoCorrect: ";

    @Nullable
    @Override
    public Editor collectInformation(@NotNull PsiFile file, @NotNull Editor editor, boolean hasErrors) {
        return editor;
    }

    @Nullable
    @Override
    public LintResult doAnnotate(Editor editor) {
        return AutoCorrectExecutor.lint(editor.getProject(), editor.getDocument());
    }


    @Override
    public void apply(@NotNull PsiFile file, LintResult result, @NotNull AnnotationHolder holder) {
        Document doc = PsiDocumentManager.getInstance(file.getProject()).getDocument(file);
        for (LineResult line : result.getLines()) {
            int additionLines = line.getOld().split("\n").length - 1;
            int startOffset = doc.getLineStartOffset((int) line.getLine() - 1) + (int) line.getCol() - 1;
            int endOffset = doc.getLineStartOffset((int) line.getLine() + additionLines - 1) + (int) line.getCol() + line.getOld().length() - 1;
            var range = new TextRange(startOffset, endOffset);

            var severity = HighlightSeverity.WEAK_WARNING;
            if (line.getSeverity() == 2) {
                severity = HighlightSeverity.INFORMATION;
            }

            holder.newAnnotation(severity, line.getNew())
                    .range(range)
//                .withFix()
                    .create();
        }
    }
}
