package org.openintents.gradle.fdroid

import groovy.transform.Memoized
import org.gradle.api.DefaultTask
import org.openintents.gradle.fdroid.model.FDroidCommand

public class FDroidTask extends DefaultTask {

    protected pluginEx = project.extensions.findByType(FdroidPluginExtension)

    def packageId

    @Memoized
    def getPackageId() {
        packageId ?: pluginEx.packageId
    }


    protected handleCommandOutput(def text)  {
        logger.info text
    }

    protected void runCommand(def parameters) {
        FDroidCommand command = [fdroid: pluginEx.getFDroid(), parameters: parameters]
        logger.info "running command: $command"
        handleCommandOutput(command.execute().text)
    }
}