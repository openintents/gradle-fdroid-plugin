package org.openintents.gradle.fdroid

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.openintents.gradle.fdroid.model.MetaData
import org.openintents.gradle.fdroid.model.MetaDataTxtFileParser

class BootstrapTripleTTask extends DefaultTask {

    static def LISTING_PATH = "listing/"
    static def MAX_CHARACTER_LENGTH_FOR_TITLE = 30
    static def MAX_CHARACTER_LENGTH_FOR_SHORT_DESCRIPTION = 80

    static def FILE_NAME_FOR_CONTACT_EMAIL = "contactEmail"
    static def FILE_NAME_FOR_CONTACT_WEBSITE = "contactWebsite"
    static def FILE_NAME_FOR_TITLE = "title"
    static def FILE_NAME_FOR_SHORT_DESCRIPTION = "shortdescription"

    @TaskAction
    void exec() {
        MetaData metaData
        if (doesMetaDataExists()) {
            metaData = parseMetaData()
        } else {
            metaData = new MetaData()
        }

        fillMetaFromTripleTFolder(metaData)
        metaData.writeAsText()
    }

    boolean doesMetaDataExists() {
        project.projectDir.list().find{it=~/^.fdroid.(yml|txt|json|xml)$/} != null
    }

    MetaData parseMetaData() {
        def file = project.projectDir.listFiles().find{it=~/^.fdroid.txt$/}
        if (file.exists()) {
            return new MetaDataTxtFileParser().parse(file.newReader())
        }
        return new MetaData()
    }

    private MetaData fillMetaFromTripleTFolder(MetaData metaData) {
        def inputFolder = project.projectDir
        def dir = inputFolder + "/en-US"
        File listingDir = new File(dir, LISTING_PATH)
        if (listingDir.exists()) {
            File fileTitle = new File(listingDir, FILE_NAME_FOR_TITLE)
            File fileShortDescription = new File(listingDir, FILE_NAME_FOR_SHORT_DESCRIPTION)

            metaData.name = readAndTrimFile(fileTitle, MAX_CHARACTER_LENGTH_FOR_TITLE, false)
            metaData.description = readAndTrimFile(fileShortDescription, MAX_CHARACTER_LENGTH_FOR_SHORT_DESCRIPTION, false)
        }

        def fileContactEmail = new File(inputFolder, FILE_NAME_FOR_CONTACT_EMAIL)
        metaData.authorEmail = readAndTrimFile(fileContactEmail, Integer.MAX_VALUE, false)
        def fileContactWeb = new File(inputFolder, FILE_NAME_FOR_CONTACT_WEBSITE)
        metaData.webSite = readAndTrimFile(fileContactWeb, Integer.MAX_VALUE, false)
    }

    def static readAndTrimFile(File file, int maxCharLength, boolean errorOnSizeLimit) {
        if (file.exists()) {
            def message = file.text
            if (message.length() > maxCharLength) {
                if (errorOnSizeLimit) {
                    throw new LimitExceededException(file, maxCharLength)
                }

                return message.substring(0, maxCharLength)
            }
            return message
        }

        return ""
    }

    static class LimitExceededException extends IllegalArgumentException {

        private String message

        LimitExceededException(File file, int limit) {
            String place = file.parentFile.parentFile.name + " -> " + file.name;
            message = "File \'" + place + "\' has reached the limit of " + limit + " characters."
        }

        @Override
        String getMessage() {
            return message;
        }
    }
}