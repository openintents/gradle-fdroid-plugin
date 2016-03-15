package org.openintents.gradle.fdroid.model

public class MetaDataTxtFileParser {

    private static final String BLOCK_COMMENT_START_CHARACTERS = "/*";
    private static final String BLOCK_COMMENT_END_CHARACTERS = "*/";

    public void parse(Reader r) {
        BufferedReader reader = new BufferedReader(r);
        MetaData metaData = new MetaData();
        String line;
        StringBuilder datum = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            line = stripOffTrailingComment(line).trim();

            if (line.length() == 0) {
                continue;
            }

            if (line.startsWith(BLOCK_COMMENT_START_CHARACTERS)) {
                inComment = true;
                continue;
            }

            if (inComment && line.endsWith(BLOCK_COMMENT_END_CHARACTERS)) {
                inComment = false;
                continue;
            }

            if (inComment) {
                continue;
            }

        }
    }
}