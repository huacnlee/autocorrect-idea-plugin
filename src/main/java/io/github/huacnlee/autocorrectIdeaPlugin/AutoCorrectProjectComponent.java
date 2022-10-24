package io.github.huacnlee.autocorrectIdeaPlugin;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;

public class AutoCorrectProjectComponent implements ProjectComponent {
    protected Project project;

    public AutoCorrectProjectComponent(Project project) {
        this.project = project;
    }
}
