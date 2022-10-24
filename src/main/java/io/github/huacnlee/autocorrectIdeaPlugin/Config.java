package io.github.huacnlee.autocorrectIdeaPlugin;

import io.github.huacnlee.AutoCorrect;
import io.github.huacnlee.Ignorer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {
    private final String basePath;
    private final Ignorer ignorer;

    static Config current;

    public static Config getCurrent(String basePath) {
        if (Config.current == null) {
            Config.current = new Config(basePath);
        }
        if (Config.current.basePath != basePath) {
            Config.current = new Config(basePath);
        }

        return Config.current;
    }

    public static void initCurrent(String basePath) {
        Config.current = new Config(basePath);
    }

    Config(String basePath) {
        this.basePath = basePath;
        this.ignorer = new Ignorer(basePath);

        String configStr = "{}";
        try {
            configStr = Files.readString(Paths.get(basePath, ".autocorrectrc"));
        } catch (IOException ex) {
        }
        AutoCorrect.loadConfig(configStr);
    }

    public boolean isIgnored(String filepath) {
        filepath = filepath.replace(this.basePath, "");
        filepath = filepath.replaceFirst("/", "");
        return this.ignorer.isIgnored(filepath);
    }
}
