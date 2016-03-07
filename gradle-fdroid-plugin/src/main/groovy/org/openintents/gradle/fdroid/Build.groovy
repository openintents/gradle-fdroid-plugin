package org.openintents.gradle.fdroid

import org.gradle.api.tasks.TaskAction

class Build extends FDroidTask {

    @TaskAction
    void exec() {
        def arguments = ['build']

        arguments += ['-v -l', packageId]

        runCommand(arguments)
    }
}