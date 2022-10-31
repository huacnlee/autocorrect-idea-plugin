package io.github.huacnlee.autocorrectIdeaPlugin

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.markup.TextAttributes
import java.awt.Color

class AutoCorrectTextAttributes {
    fun errorAttribute(): TextAttributes {
        val attribute = DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE.defaultAttributes.clone()
        attribute.effectColor = Color.YELLOW
        return attribute
    }

    fun warningAttribute(): TextAttributes {
        val attribute = DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE.defaultAttributes.clone()
        attribute.effectColor = Color.CYAN
        return attribute
    }
}