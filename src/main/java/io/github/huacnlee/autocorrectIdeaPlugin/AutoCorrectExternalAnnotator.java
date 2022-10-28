package io.github.huacnlee.autocorrectIdeaPlugin;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.InspectionsBundle;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import io.github.huacnlee.LineResult;
import io.github.huacnlee.LintResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;


public class AutoCorrectExternalAnnotator extends ExternalAnnotator<Editor, LintResult> {

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

            var message = line.getNew();
            holder.newAnnotation(severity, message)
                    .range(range)
                    .withFix(new AutoCorrectFixIntentionAction(line, range))
                    .create();
        }
    }

    class AutoCorrectFixIntentionAction implements IntentionAction {
        private LineResult line;
        private TextRange range;

        public AutoCorrectFixIntentionAction(LineResult line, TextRange range) {
            this.line = line;
            this.range = range;
        }

        @Override
        public @IntentionName @NotNull String getText() {
            return "AutoCorrect Fix";
        }

        @Override
        public @NotNull @IntentionFamilyName String getFamilyName() {
            return "AutoCorrect";
        }

        @Override
        public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
            return true;
        }

        @Override
        public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
            Document doc = PsiDocumentManager.getInstance(file.getProject()).getDocument(file);
            WriteCommandAction.runWriteCommandAction(project, () -> {
                doc.replaceString(this.range.getStartOffset(), this.range.getEndOffset(), this.line.getNew());
            });
        }

        @Override
        public boolean startInWriteAction() {
            return false;
        }
    }
}
