package org.openintents.gradle.fdroid

import org.gradle.api.Project

public class FDroidPluginExtension {

    String fdroidPath
    def fdroid

    private final Project project

    FDroidPluginExtension(Project project, String fdroidPath) {
        this.project = project
        this.fdroidPath = fdroidPath
    }

    def getFDroid() {
        fdroid ?: "$fdroidPath/fdroid"
    }
}