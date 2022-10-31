package io.github.huacnlee.autocorrectIdeaPlugin

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ex.ExternalAnnotatorBatchInspection

class AutoCorrectExternalAnnotatorBatchInspection : LocalInspectionTool(), ExternalAnnotatorBatchInspection {
    override fun getShortName(): String {
        return "AutoCorrectLinter"
    }

    override fun getStaticDescription(): String {
        return "AutoCorrect linter annotator style."
    }
}