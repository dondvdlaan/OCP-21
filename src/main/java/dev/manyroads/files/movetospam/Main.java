package dev.manyroads.files.movetospam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The method checks that ${username}/spam directory exists and then moves the specified file into it.
 * <p>
 * username directory is a home directory for a user account;
 * spam directory is a directory where spam messages go. It may not exist yet.
 * Move spam message in ${username}/spam
 */
public class Main {

    static void moveToSpam(String username, File msg) throws IOException {
        String pathToSpam = username + File.separator + "spam";

        File spamDirectory = new File(pathToSpam);
        if (!spamDirectory.exists()) {
            spamDirectory.mkdir();
        }

        File spamMsg = new File(pathToSpam + File.separator + msg.getName());
        // Move msg to
        msg.renameTo(spamMsg);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String baseDir = "/temp2";
        File test1 = new File(baseDir+File.separator+"test1.txt");
        test1.createNewFile();
        Thread.sleep(2000);
        try (FileWriter writer = new FileWriter(test1)) {
            writer.write("holita");
        }
        moveToSpam(baseDir,test1);
    }
}
