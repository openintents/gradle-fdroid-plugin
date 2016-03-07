package org.openintents.gradle.fdroid

import org.gradle.api.DefaultTask

public class FDroidTask extends DefaultTask {

    protected pluginEx = project.extensions.findByType(FDroidPluginExtension)

    protected handleCommandOutput(def text)  {
        logger.info text
    }

    protected void runCommand(def parameters) {
        FDroidCommand command = [fdroid: pluginEx.getFDroid(), parameters: parameters]
        logger.info "running command: $command"
        handleCommandOutput(command.execute().text)
    }
}