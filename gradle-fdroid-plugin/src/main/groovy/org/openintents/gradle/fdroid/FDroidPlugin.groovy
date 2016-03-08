package org.openintents.gradle.fdroid

import org.gradle.api.Plugin
import org.gradle.api.Project

public class FdroidPlugin implements Plugin<Project> {

    void apply(Project project) {
        String fdroidPath = getFdroidPath(project)

        def extension = project.extensions.create("fdroid", FdroidPluginExtension, project, fdroidPath)
        project.task 'fdroidBuild', type: Build, group: 'publishing', description: 'builds the apk using fdroid'
        project.task 'fdroidLint', type: Lint, group: 'verification', description: 'checks for lint errors of the meta data using fdroid'
        project.task 'fdroidRewriteMeta', type: RewriteMeta, group: 'help', description: 'rewrites the meta data using fdroid'
    }

    private static String getFdroidPath(project) {

        Properties properties = new Properties()
        properties.load(project.rootProject.file('local.properties').newDataInputStream())
        def fdroidPath = properties.getProperty('fdroid.dir')
        if (!fdroidPath) {
            throw new IllegalStateException("fdroid could not be found in local.properties. Please add property 'fdroid.dir' with value of the path to fdroid")
        }
        fdroidPath
    }
}
