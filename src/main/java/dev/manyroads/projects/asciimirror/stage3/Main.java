package dev.manyroads.projects.asciimirror.stage3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static String printCow(){
        return """
                            ^__^
                    _______/(oo)
                /\\/(       /(__)
                   | w----||   \s
                   ||     ||   \s
                """;
    }
    static void printFile(File file){
        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNext()){
                System.out.println(scanner.nextLine());
            }
        }catch (FileNotFoundException ex){
            System.out.println("File not found!");
        }
    }

    /**
     * In this stage, make your program read files. If users inputted a file path that does not exist,
     * print out the error with the File not found substring. Otherwise, print out the file content line-by-line.
     * No need to format the content of the files with whitespace; output files with lines as they are in the file.
     *
     * For your convenience, there is a .txt example file in the attachments.
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Input the file path:");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        File file = new File(filePath);
            System.out.println(file.toPath());

        if (file.exists()){
            printFile(file);
        } else System.out.println("File not found!");
    }
}
