package io.github.huacnlee.autocorrectIdeaPlugin;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class AutoCorrectExternalAnnotator implements Annotator {
    private static final String MESSAGE_PREFIX = "AutoCorrect: ";

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
       String text = element.getText();

        System.out.println("----------------------------------------");
        System.out.printf("%s\n", text);

    }
}
