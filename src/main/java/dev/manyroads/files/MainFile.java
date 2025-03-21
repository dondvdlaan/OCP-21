package dev.manyroads.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Here's a file containing a sequence of integers. Each number starts with a new line.
 * Download it and write a Java program that calculates the sum of these numbers.
 */
public class MainFile {

    static int sumNumber(String pathToFile) {

        File file = new File(pathToFile);
        int tot = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                tot += scanner.nextInt();
            }
        } catch (InputMismatchException ex) {
            System.out.println("FoutjeI: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        } finally {
        }
        return tot;
    }

    static Optional<Integer> sumNumberII(String pathToFile) {
        int tot = 0;
        try {
            return Arrays.stream(new String(
                            Files.readAllBytes(Path.of(pathToFile)))
                            .split("\\s+"))
                    .map(Integer::parseInt)
                    .reduce(Integer::sum);
        } catch (InputMismatchException ex) {
            System.out.println("FoutjeI: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        }
        return Optional.of(tot);
    }

    static int countEvenNumbers(String pathToFile) {

        File file = new File(pathToFile);
        int tot = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                int temp = scanner.nextInt();
                if (temp == 0) break;
                tot += temp % 2 == 0 ? 1 : 0;
            }
        } catch (InputMismatchException ex) {
            System.out.println("FoutjeI: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        } finally {
        }
        return tot;
    }

    static long countEvenNumbersII(String pathToFile) {

        try {
            return Arrays.stream(new String(
                            Files.readAllBytes(Path.of(pathToFile)))
                            .split("\\s+"))
                    .map(Integer::parseInt)
                    .filter(i -> i % 2 == 0)
                    .count();
        } catch (InputMismatchException ex) {
            System.out.println("FoutjeI: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        }
        return 0;
    }

    /**
     * Here is a file containing a sequence of integers separated by spaces.
     * Download it and write a Java program that finds the greatest number in this file.
     *
     * @param filePath
     * @return
     */
    static List<Integer> greatestNumber(String filePath) {
        try {
            return Arrays.stream(new String(
                            Files.readAllBytes(Path.of(filePath)))
                            .split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (InputMismatchException ex) {
            System.out.println("FoutjeI: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        }
        return List.of(0);
    }

    static Optional<Integer> greatestNumberII(String filePath) {
        try {
            return Arrays.stream(new String(
                            Files.readAllBytes(Path.of(filePath)))
                            .split("\\s+"))
                    .map(Integer::parseInt)
                    .reduce(Integer::max);
        } catch (InputMismatchException ex) {
            System.out.println("FoutjeI: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        }
        return Optional.of(0);
    }

    /**
     * Here is a file containing a sequence of integers separated by spaces.
     * Download it and write a Java program that counts numbers that are greater than or equal to 9999.
     * Enter the count.
     *
     * @param filePath
     * @return
     */
    static long countNumbers(String filePath) {
        try {
            return Arrays.stream(new String(
                            Files.readAllBytes(Path.of(filePath)))
                            .split("\\s+"))
                    .map(Integer::parseInt)
                    .filter(i -> i >= 9999)
                    .count();
        } catch (InputMismatchException ex) {
            System.out.println("FoutjeI: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        }
        return 0;
    }

    /**
     * Here's a file that stores data on the world population since 1950, according to the United States Census Bureau (2017).
     * Download the file and write a Java program to find out in what year the largest increase in population occurred as compared
     * to the previous year.
     * The file has two columns: year and population. Take a look at it to understand the format better.
     * <p>
     * Enter the resulting year.
     *
     * @param filePath
     * @return
     */
    static int worldPopulation(String filePath) {
        File file = new File(filePath);
        List<String> sYearPop = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            String header = scanner.nextLine();
            while (scanner.hasNext()) {
                sYearPop.add(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        //sYearPop.forEach(System.out::println);
        int[] aYear = sYearPop.stream().mapToInt(string -> Integer.parseInt(string.substring(0, 4))).toArray();
        //long test = Long.parseLong("2,557,628,654".replaceAll(",",""));
        long[] aPop = sYearPop.stream().mapToLong(string -> Long.parseLong(string.replaceAll(",", "").substring(5))).toArray();
        //Arrays.stream(aYear).forEach(System.out::println);
        //Arrays.stream(aPop).forEach(System.out::println);
        long highestInc = 0;
        long diff = 0;
        int index = 0;
        for (int i = 0; i < aPop.length - 1; i++) {
            diff = aPop[i + 1] - aPop[i];
            if (diff > highestInc) {
                highestInc = diff;
                index = i + 1;
            }
            //System.out.printf("year %d inc %d\n", aYear[i + 1], diff);
        }
        return aYear[index];
    }

    public static void main(String[] args) {
//        System.out.println("tot: " + sumNumber("dataset_91033.txt"));
//        System.out.println("tot: " + sumNumberII("dataset_91033.txt").get());
//        System.out.println("tot: " + countEvenNumbers("dataset_91065.txt"));
//        System.out.println("tot: " + countEvenNumbersII("dataset_91065.txt"));
//        System.out.println("largest: " + Collections.max(greatestNumber("dataset_91007.txt")));
//        greatestNumberII("dataset_91007.txt").ifPresent(System.out::println);
        //System.out.println("tot: " + countNumbers("dataset_91022.txt"));
        System.out.println("Year highest inc: " + worldPopulation("dataset_91069.txt"));


    }
}
