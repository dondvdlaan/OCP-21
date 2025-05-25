package dev.manyroads.files.managingfiles;


import java.io.File;
import java.util.Arrays;

public class Main {
    static public void deleteDirRecursively(File dir) {
        File[] children = dir.listFiles();
        Arrays.stream(children).forEach(System.out::println);
        for (File child : children) {
            System.out.println("child: " + child.getName());
            System.out.println("child.isDirectory(): " + child.isDirectory());
            if (child.isDirectory()) {
                deleteDirRecursively(child);
            } else {
                System.out.println("delete");
                child.delete();
            }
        }

        System.out.println("deleting dir: " + dir.getName() + " " + dir.delete());
    }

    public static void main(String[] args) throws InterruptedException {
        File file = new File("/test/ggg/tata/ohoh");

        boolean createdNewDirectory = file.mkdirs();
        if (createdNewDirectory) {
            System.out.println("It was successfully created.");
        } else {
            System.out.println("It was not created.");
        }

        Thread.sleep(2000);

        boolean renamed = file.renameTo(new File("/home/art/Documents/dir/newname.txt"));

        //deleteDirRecursively(new File("/test/"));

//        if (file.delete()) {
//            System.out.println("It was successfully removed.");
//        } else {
//            System.out.println("It was not removed.");
//        }
    }


}

