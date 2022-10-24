package io.github.huacnlee.autocorrectIdeaPlugin;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.EditorEventMulticaster;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class ConfigFileListener {
    private final Project project;
    private final AtomicBoolean LISTENING = new AtomicBoolean(false);

    public ConfigFileListener(@NotNull Project project) {
        this.project = project;
    }

    private void startListener() {
        if (LISTENING.compareAndSet(false, true))
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                public void run() {
                    ApplicationManager.getApplication().runWriteAction(new Runnable() {
                        public void run() {
                            EditorEventMulticaster multicaster = EditorFactory.getInstance().getEventMulticaster();
                            multicaster.addDocumentListener(new ConfigFileDocumentListener(), ConfigFileListener.this.project);
                        }
                    });
                }
            });
    }

    public static void start(@NotNull Project project) {
        ConfigFileListener listener = ServiceManager.getService(project, ConfigFileListener.class);
        listener.startListener();
    }

    private void fileChanged(@NotNull VirtualFile file) {
        System.out.printf("AutoCorrect config before changed: %s.\n", file.getName());
        boolean isAutoCorrectFile = file.getName().endsWith(".autocorrectrc") || file.getName().endsWith(".autocorrectignore");
        if (isAutoCorrectFile && !project.isDisposed()) {
            System.out.printf("AutoCorrect config has changed: %s.\n", file.getName());
            reloadConfig();
        }
    }

    private void reloadConfig() {
        Config.initCurrent(project.getBasePath());
    }

    /**
     * Document Listener
     */
    private class ConfigFileDocumentListener extends DocumentAdapter {
        private ConfigFileDocumentListener() {
        }

        public void documentChanged(DocumentEvent event) {
            VirtualFile file = FileDocumentManager.getInstance().getFile(event.getDocument());
            if (file != null) {
                ConfigFileListener.this.fileChanged(file);
            }
        }
    }
}

