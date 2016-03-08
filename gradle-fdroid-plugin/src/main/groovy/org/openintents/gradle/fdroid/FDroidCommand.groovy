package org.openintents.gradle.fdroid

class FDroidCommand {

    def fdroid
    def parameters

    def execute() {
        command().execute()
    }

    private command() {
        [fdroid] + parameters
    }

    String toString() {
        command().toString()
    }
}