package org.openintents.gradle.fdroid

import org.gradle.api.tasks.TaskAction

class LintTask extends FDroidTask {

    @TaskAction
    void exec() {
        def arguments = ['lint']

        runCommand(arguments)
    }
}