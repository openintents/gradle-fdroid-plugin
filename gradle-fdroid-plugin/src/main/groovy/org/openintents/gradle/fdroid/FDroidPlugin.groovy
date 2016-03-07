package org.openintents.gradle.fdroid

import org.gradle.api.Plugin
import org.gradle.api.Project

public class FDroidPlugin implements Plugin<Project> {

    public final static String TASK_GROUP = "fdroid"

    void apply(Project project) {
        String fdroidPath = getFDroidPath(project)

        def extension = project.extensions.create("fdroid", FDroidPluginExtension, project, fdroidPath)
        project.task 'fdroidBuild', type: Build, group: 'publishing', description: 'builds the apk using fdroid'
        project.task 'fdroidLint',  type:Lint, group: 'verification', description: 'checks for lint errors of the meta data using fdroid'
        project.task 'fdroidRewriteMeta', type: RewriteMeta, group: 'documentation', description: 'rewrites the meta data using fdroid'
    }

    private static String getFDroidPath(project) {
        def fdroidPath
        if (project.hasProperty('fdroid.dir')) {
            fdroidPath = "${project.fdroid}"
        } else {
            throw new IllegalStateException("fdroid could not be found in project '${project.name}' ${project.ext.fdroid}. Please add property 'fdroid' with value of the path to fdroid")
        }
        fdroidPath
    }
}