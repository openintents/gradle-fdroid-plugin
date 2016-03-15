package org.openintents.gradle.fdroid

import org.gradle.api.tasks.TaskAction

import java.util.regex.Matcher

class ImportTask extends FDroidTask {

    String remoteName
    String vcsUrl
    String subDirectory

    @TaskAction
    void exec() {
        def arguments = ['import']

        if (!vcsUrl) {
            remoteName = remoteName ?: "origin"
            def vcsUrlProc = "git remote get-url $remoteName".execute()
            vcsUrlProc.waitFor()

            if (vcsUrlProc.exitValue() != 0) {
                throw new IllegalStateException(vcsUrlProc.err.text + vcsUrlProc.text)
            }

            vcsUrl = vcsUrlProc.text.trim()
            vcsUrl = convertToKnownPlatform(vcsUrl)
        }
        arguments += ["--url=$vcsUrl"]

        if (project.projectDir != project.rootDir) {
            subDirectory = subDirectory ?: project.rootProject.relativePath(project.projectDir)
            arguments += ["--subdir=$subDirectory"]
        }

        runCommand(arguments)
    }

    String convertToKnownPlatform(String vcsUrl) {
        switch (vcsUrl) {
            case ~/git@gitorious.org:([^\/]*)\/([^\.]*).git/:
                def m = Matcher.lastMatcher[0]
                return "https://gitorious.org/${m[1]}/${m[2]}"
            case ~/git@github.com:([^\/]*)\/([^\.]*).git/:
                def m = Matcher.lastMatcher[0]
                return "https://github.com/${m[1]}/${m[2]}"
            case ~/git@bitbucket.org:([^\/]*)\/([^\.]*).git/:
                def m = Matcher.lastMatcher[0]
                return "https://bitbucket.org/${m[1]}/${m[2]}"
            default:
                return vcsUrl
        }
    }
}