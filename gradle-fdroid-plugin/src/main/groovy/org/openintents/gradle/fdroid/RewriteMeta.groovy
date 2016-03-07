package org.openintents.gradle.fdroid

import org.gradle.api.tasks.TaskAction

class RewriteMeta extends FDroidTask {

    @TaskAction
    void exec() {
        def arguments = ['rewriteMeta']

        arguments += [packageId]

        runCommand(arguments)
    }
}