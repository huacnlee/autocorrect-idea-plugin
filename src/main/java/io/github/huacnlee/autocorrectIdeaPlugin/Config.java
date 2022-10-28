package io.github.huacnlee.autocorrectIdeaPlugin;

import io.github.huacnlee.AutoCorrect;
import io.github.huacnlee.Ignorer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Config {
    private final String basePath;
    private final Ignorer ignorer;

    static Config current;

    public static Config getCurrent(String basePath) {
        if (Config.current == null) {
            Config.current = new Config(basePath);
        }
        if (!Objects.equals(Config.current.basePath, basePath)) {
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
        var configPath = Paths.get(basePath, ".autocorrectrc");
        if (Files.exists(configPath)) {
            try {
                configStr = Files.readString(configPath);
            } catch (IOException ex) {
                // Ignore error
                System.out.println("------- Load AutoCorrect config file error -------------");
                System.out.println(ex.toString());
            }
        }
        AutoCorrect.loadConfig(configStr);
    }

    public boolean isIgnored(String filepath) {
        filepath = filepath.replace(this.basePath, "");
        filepath = filepath.replaceFirst("/", "");
        return this.ignorer.isIgnored(filepath);
    }
}
