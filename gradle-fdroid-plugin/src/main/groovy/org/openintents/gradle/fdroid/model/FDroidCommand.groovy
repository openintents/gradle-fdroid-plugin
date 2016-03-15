package org.openintents.gradle.fdroid.model

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