package dev.manyroads.files.filesnio2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesNio2Test {

    static final String sPaadje = "E:\\temp\\testbestandje.txt";
    File bestandje;

    public FilesNio2Test(File bestandje) {
        this.bestandje = bestandje;
    }

    public static void main(String[] args) throws IOException {
        //getByFileReader(sPaadje);
        //getByFiles(Path.of(sPaadje));
        //String basePath = new File("").getAbsolutePath();
        //System.out.println(basePath);
        //getByFiles(Path.of("testbestandjeII.txt"));                   // relative path as from basePath
        //getByFiles(Paths.get("testbestandjeII.txt"));                 // relative path as from basePath
        //File bestnadjeII = new File("papa/testbestandjeII.txt");    // From File (IO) to Path(NIO.2)
        //Path pathBestandjeII = bestnadjeII.toPath();                    // relative path as from basePath
        //System.out.println(pathBestandjeII);
        //System.out.println(pathBestandjeII.getParent());
        //System.out.println(pathBestandjeII.toAbsolutePath());
        getByFileWriter("E:\\temp","testbestandje.txt", "oh jee!°!");
    }

    /**
     * NIO.2 system
     *
     * @param paadje
     * @throws IOException
     */
    private static void getByFiles(Path paadje) throws IOException {
        System.out.println(Files.readString(paadje));
    }

    /**
     * Legacy IO system
     *
     * @param paadje
     * @throws IOException
     */
    private static void getByFileReader(String paadje) throws IOException {
        FilesNio2Test filesNio2Test = new FilesNio2Test(new File(paadje));
        try (var reader = new BufferedReader(new FileReader(filesNio2Test.bestandje))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * Legacy IO system
     *
     * @throws IOException
     */
    private static void getByFileWriter(String dir, String file, String berichtje)  {

        try (FileWriter writer = new FileWriter(dir + "\\" + file)) {
            writer.write(berichtje);
        }catch (IOException ex){
            System.out.println("writer: "+ex.getMessage());
    }
}

}

