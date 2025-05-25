package dev.manyroads.files.rename;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        String pad = "/temp/bestanden/";
        String newFile = "haha.txt";
        File newName = new File(pad + "haha.txt");
        // Init
        if (newName.exists() && newName.delete()) System.out.printf("File %s deleted \n", newName.getName());

        File file = new File(pad + "picture.txt");
        System.out.println("File created: " + file.createNewFile());
        System.out.println("name: " + file.getName());
        System.out.println("path: " + file.getAbsolutePath());
        System.out.println("Is file: " + file.isFile());
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("holita");
        }

        System.out.println();

        //Thread.sleep(2000);
        if (file.isFile()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    System.out.println("File contend: " + scanner.nextLine());
                }
                scanner.close();
                Thread.sleep(1000);
            }
        } else System.out.printf(String.format("File %s not found! \n", file.getName()));


        newName = new File(pad + newFile);
        if (file.isFile() && file.renameTo(newName)) System.out.println("File renamed to: " + newName.getName());
        System.out.println("path: " + newName.getAbsolutePath());
        System.out.println("Is file: " + newName.isFile());

        if (newName.isFile()) {
            try (Scanner scanner = new Scanner(newName);) {
                while (scanner.hasNext()) {
                    System.out.printf("Reading new file: %s %s \n", newName.getName(), scanner.nextLine());
                }
                scanner.close();
                System.out.printf("File %s deleted: %b", newName.getName(), newName.delete());
            }

        } else System.out.printf(String.format("File %s not found!", newName.getName()));
    }
}
