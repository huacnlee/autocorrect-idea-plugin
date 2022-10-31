package io.github.huacnlee.autocorrectIdeaPlugin

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.markup.EffectType
import com.intellij.openapi.editor.markup.TextAttributes
import java.awt.Color

class AutoCorrectTextAttributes {
    fun errorAttribute(): TextAttributes {
        val attribute = TextAttributes();
        attribute.effectColor = Color.ORANGE
        attribute.effectType = EffectType.WAVE_UNDERSCORE
        return attribute
    }

    fun warningAttribute(): TextAttributes {
        val attribute = TextAttributes()
        attribute.effectColor = Color.CYAN
        attribute.effectType = EffectType.WAVE_UNDERSCORE
        return attribute
    }
}