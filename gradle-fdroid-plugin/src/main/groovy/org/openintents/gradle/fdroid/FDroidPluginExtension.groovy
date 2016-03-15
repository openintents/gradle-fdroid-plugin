package org.openintents.gradle.fdroid

import org.gradle.api.Project
import com.android.build.gradle.api.ApplicationVariant

public class FdroidPluginExtension {

    String fdroidPath
    def fdroid
    def packageId
    def vcsUrl
    def vcsRemoteName
    def vcsSubDirectory

    private final Project project

    FdroidPluginExtension(Project project, String fdroidPath) {
        this.project = project
        this.fdroidPath = fdroidPath
    }

    def getFDroid() {
        fdroid ?: "$fdroidPath/fdroid"
    }

    def getPackageId() {
        packageId ?: project.android.applicationVariants.first().mergedFlavor.applicationId
    }
}