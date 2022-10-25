package io.github.huacnlee.autocorrectIdeaPlugin

import io.github.huacnlee.AutoCorrect
import io.github.huacnlee.Ignorer
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class Config internal constructor(private val basePath: String) {
    private val ignorer: Ignorer

    init {
        ignorer = Ignorer(basePath)
        var configStr: String? = "{}"
        try {
            configStr = Files.readString(Paths.get(basePath, ".autocorrectrc"))
        } catch (ex: IOException) {
        }
        AutoCorrect.loadConfig(configStr)
    }

    fun isIgnored(filepath: String): Boolean {
        var filepath = filepath
        filepath = filepath.replace(basePath, "")
        filepath = filepath.replaceFirst("/".toRegex(), "")
        return ignorer.isIgnored(filepath)
    }

    companion object {
        var current: Config? = null
        fun getCurrent(basePath: String): Config? {
            if (current == null) {
                current = Config(basePath)
            }
            if (current!!.basePath !== basePath) {
                current = Config(basePath)
            }
            return current
        }

        @JvmStatic
        fun initCurrent(basePath: String) {
            current = Config(basePath)
        }
    }
}