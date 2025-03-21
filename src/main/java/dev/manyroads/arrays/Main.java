package dev.manyroads.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Create an array of longs named longNumbers with three elements 100000000001, 100000000002, 100000000003.
 */
public class Main {

    public static void main(String[] args) {

        long[] longNumbers = {100000000001L, 100000000002L, 100000000003L};

        Arrays.stream(longNumbers).mapToObj(e -> Long.valueOf(e).toString()).forEach(System.out::println);
        //System.out.println(Arrays.toString(longNumbers));
    }
}

/**
 * Write a program that accomplishes the following tasks: 1. Accepts an integer 'n' as input which
 * indicates the number of elements to be in the array. 2. Afterward, accepts 'n' number of integers
 * as input for elements of the array. 3. The program should then calculate the sum of all the elements
 * in the array which are multiples of 3. 4. Print the sum of these numbers as output.
 */
class Test2 {
    public static void main(String[] args) {
        // create scanner instance to read input
        Scanner scanner = new Scanner(System.in);

        // read the number of elements
        int n = scanner.nextInt();

        // create your array here
        int[] iArray = new int[n];

        // use a loop to read the array elements
        for (int i = 0; i < n; i++) {
            iArray[i] = scanner.nextInt();
        }

        // use a loop to calculate the sum of elements that are multiples of 3
        int sumMultiplesOf3 = 0;
        for (int i = 0; i < n; i++) {
            if (iArray[i] % 3 == 0) sumMultiplesOf3 += iArray[i];
        }
        System.out.println("sumMultiplesOf3: " + sumMultiplesOf3);
        // close the scanner
        scanner.close();
    }
}

/**
 * Create an array of chars named characters with four elements 'a', 'z', 'e', 'd' and output it.
 */
class Test3 {
    public static void main(String[] args) {


        // create your array here
        char[] characters = {'a', 'z', 'e', 'd'};

        System.out.println(Arrays.toString(characters));
    }
}

/**
 * Write a program that reads an array of ints and finds the index of the first maximum in that array.
 * Input data format
 * The first line contains the number of elements in the array.
 * <p>
 * The second line contains the array elements separated by spaces.
 * An array always has at least one element.
 * <p>
 * Output data format
 * Only a single integer value: the index of the first maximum.
 * Sample Input 1:
 * 5
 * 2 5 3 4 5
 * Sample Output 1:
 * 1
 */
class Test4 {
    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//        int nrElem = scanner.nextInt();
//        String line = scanner.nextLine();
//        line = scanner.nextLine();
//        String re = "\\s+";
//        String[] sInts  = line.split(re);
//        int iHighest = 0;
//        for (int i = 0; i < nrElem; i++) {
//            iHighest = Integer.parseInt(sInts[iHighest]) < Integer.parseInt(sInts[i]) ? i : iHighest;
//        }
//        System.out.println(iHighest);

//        Scanner scanner = new Scanner(System.in);
//        int nrElem = scanner.nextInt();
//        int[] ints = new int[nrElem];
//        int iHighest = 0;
//        for (int i = 0; i < nrElem; i++) {
//            ints[i]= scanner.nextInt();
//            iHighest = ints[iHighest] < ints[i] ? i : iHighest;
//        }
//        System.out.println(iHighest);

//        Scanner scanner = new Scanner(System.in);
//        List<Integer> lInts = IntStream.range(0, scanner.nextInt())
//                .map(n -> scanner.nextInt())
//                .boxed()
//                .toList();
//        System.out.println(lInts.indexOf(Collections.max(lInts)));

        // Werkt niet
//        var arr = new Scanner(System.in).tokens().skip(1).map(Integer::parseInt).toList();
//        System.out.print(arr.indexOf(Collections.max(arr)));
    }
}

/**
 * Check if an array is sorted ascending
 * Write a program that reads an array of int's and checks if the array is sorted ascending
 * (from smallest to largest number).
 * <p>
 * Input data format
 * The first line contains the size of an array.
 * The second line contains elements of the array separated by spaces.
 * Sample Input 1:
 * 4
 * 1 2 3 4
 * Sample Output 1:
 * true
 */
class Test5 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int nrElem = scanner.nextInt();
//        int[] ints = new int[nrElem];
//        boolean asc = true;
//        for (int i = 0; i < nrElem; i++) {
//            ints[i] = scanner.nextInt();
//        }
//        for (int i = 0; i < nrElem - 1; i++) {
//            asc = ints[i + 1] > ints[i];
//            if (!asc) { break; }
//        }
//        System.out.println(asc);

//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] numbers = new int[n];
//
//        for (int i = 0; i < numbers.length; i++) {
//            numbers[i] = scanner.nextInt();
//        }
//
//        boolean asc = true;
//        for (int i = 0; i < numbers.length - 1 && asc; i++) {
//            if (numbers[i] > numbers[i + 1]) {
//                asc = false;
//            }
//        }
//        System.out.println(asc);

//        Scanner scanner = new Scanner(System.in);
//
//        int size = scanner.nextInt();
//        int[] arr1 = new int[size];
//        int[] arr2 = new int[size];
//        for (int i = 0; i < size; i++) {
//            int b = scanner.nextInt();
//            arr1[i] = b;
//            arr2[i] = b;
//        }
//
//
//        Arrays.sort(arr2);
//        System.out.println(Arrays.equals(arr1, arr2));

        Scanner scanner = new Scanner(System.in);
        int nrElem = scanner.nextInt();
        final int[] ints = IntStream.generate(scanner::nextInt).limit(nrElem).toArray();
        System.out.println(IntStream.range(0, nrElem - 1).allMatch(i -> ints[i + 1] > ints[i]));

    }
}

/**
 * The maximum product of adjacent elements
 * Write a program that reads an array of ints and outputs the maximum product of two adjacent elements
 * in the given array of non-negative numbers.
 * Input data format
 * The first line of the input contains the number of elements in the array.
 * The second line contains the elements of the array separated by spaces.
 * The array always has at least two elements.
 * <p>
 * Sample Input 1:
 * 2
 * 5 3
 * Sample Output 1:
 * 15
 */
class Test6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nrElem = scanner.nextInt();
        int highestProd = 0;
        final int[] ints = IntStream.generate(scanner::nextInt)
                .limit(nrElem)
                .toArray();
        for (int i = 0, res; i < nrElem - 1; i++) {
            res = ints[i] * ints[i + 1];
            highestProd = Math.max(res, highestProd);
        }
        IntStream.range(0, nrElem - 1).map(i -> ints[i] * ints[i + 1]).max().ifPresent(System.out::println);
        System.out.println(highestProd);
    }
}

/**
 * Sum array elements greater than a value
 * Write a program that reads an array of ints and an integer number n.
 * The program must sum all the array elements greater than n.
 * <p>
 * Input data format
 * The first line contains the size of an array.
 * The second line contains the elements of the array separated by spaces.
 * The third line contains the number n.
 * <p>
 * Output data format
 * Only a single number representing the sum.
 * <p>
 * Sample Input 1:
 * 5
 * 5 8 11 2 10
 * 8
 * Sample Output 1:
 * 21
 */
class Test7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nrElem = scanner.nextInt();
        final int[] ints = scanner.tokens().limit(nrElem).mapToInt(Integer::parseInt).toArray();
        //final int[] ints = IntStream.generate(scanner::nextInt).limit(nrElem).toArray();
        final int n = scanner.nextInt();
        int sum = 0;
        for (int i = 0, res; i < nrElem; i++) {
            sum += ints[i] > n ? ints[i] : 0;
        }
        System.out.println(sum);
        Arrays.stream(ints).filter(nr -> nr > n).reduce(Integer::sum).ifPresent(System.out::println);
        System.out.println(Arrays.stream(ints).filter(nr -> nr > n).sum());
    }
}

/**
 * You need to implement the createCube method. It should create a 3x3x3 three-dimensional array with the following content:
 * <p>
 * [0, 1, 2]  [3, 4, 5]  [6, 7, 8]
 * [0, 1, 2]  [3, 4, 5]  [6, 7, 8]
 * [0, 1, 2]  [3, 4, 5]  [6, 7, 8]
 * <p>
 * The elements should be of type int.
 */
class Test8 {

    public static int[][][] createCube() {
        int[][][] drieD = new int[3][3][3];
        for (int i = 0, el = 0; i < drieD.length; i++) {
            el = 0;
            for (int j = 0; j < drieD[i].length; j++) {
                for (int k = 0; k < drieD[i][j].length; k++) {
                    drieD[i][j][k] = el++;
                }
            }
        }

        return drieD;
    }

    public static int[][][] createCube2() {
        int[][] row = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        return new int[][][]{row, row, row};
    }

    public static void printCube(int[][][] cube) {
        for (int[][] row : cube) {
            for (int[] col : row) {
                for (int nest : col) {
                    System.out.print(nest + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printCube(createCube2());
    }
}

/**
 * Swap the columns
 * Given a two-dimensional array (matrix) and the two numbers: i and j.
 * Swap the columns with indexes i and j within the matrix.
 * <p>
 * Input contains matrix dimensions n and m, not exceeding 100, then the elements of the matrix,
 * then the indexes i and j.
 * <p>
 * Sample Input 1:
 * 3 4
 * 11 12 13 14
 * 21 22 23 24
 * 31 32 33 34
 * 0 1
 * Sample Output 1:
 * 12 11 13 14
 * 22 21 23 24
 * 32 31 33 34
 */
class Test9 {
    static void printMat(int[][] mat) {
        for (int[] row : mat) {
            for (int el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    static int[][] swapCols(int[][] mat, int col1, int col2) {
        int[][] copyMat = mat.clone();
        int temp;
        // swap cols
        for (int i = 0; i < copyMat.length; i++) {
            temp = copyMat[i][col1];
            copyMat[i][col1] = copyMat[i][col2];
            copyMat[i][col2] = temp;
        }
        return copyMat;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int j = scanner.nextInt();
        int[][] mat = new int[i][j];
        for (int k = 0; k < mat.length; k++) {
            for (int l = 0; l < mat[k].length; l++) {
                mat[k][l] = scanner.nextInt();
            }
        }
        int col1 = scanner.nextInt();
        int col2 = scanner.nextInt();
        //swapCols(mat,col1,col2);
        //printMat(mat);
        //System.out.println();
        printMat(swapCols(mat, col1, col2));

    }
}

/**
 * Symmetric matrix
 * Given the number n, not exceeding 10, and a matrix of size n × n.
 * Check whether this matrix is symmetric in relation to the main diagonal. Output the word “YES”,
 * if it is symmetric and the word “NO” otherwise.
 * Keep in mind that the main diagonal runs from the top left corner to the bottom right corner.
 * Sample Input 1:
 * 3
 * 0 1 2
 * 1 2 3
 * 2 3 4
 * Sample Output 1:
 * YES
 */
class Test10 {

    static int[][] scanMat() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] mat = new int[n][n];
        for (int k = 0; k < mat.length; k++) {
            for (int l = 0; l < mat[k].length; l++) {
                mat[k][l] = scanner.nextInt();
            }
        }
        scanner.close();
        return mat;
    }

    static void printMat(int[][] mat) {
        System.out.println();
        for (int[] row : mat) {
            for (int el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    static boolean checkMatIsSymmetric(int[][] mat) {
        boolean isSymmetric = true;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                isSymmetric = isSymmetric && mat[i][j] == mat[j][i];
//                if (mat[i][j] != mat[j][i]) {
//                    isSymmetric = false;
//                    break;
//                }
            }
        }
        return isSymmetric;
    }

    static void alternativeChackSymmetricMat() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[][] array = new int[size][size];
        int[][] transposedArray = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = scanner.nextInt();
                transposedArray[j][i] = array[i][j];
            }
        }
        scanner.close();
        System.out.println(Arrays.deepEquals(array, transposedArray) ? "YES" : "NO");
    }

    public static void main(String[] args) {
        int[][] mat = scanMat();
        printMat(mat);
        System.out.println(checkMatIsSymmetric(mat) ? "YES" : "NO");

        // Alternative
//        alternativeChackSymmetricMat();

    }
}

/**
 * Reverse elements
 * In this task, you need to implement reverseElements method. It should reverse all rows of the
 * twoDimArray as in the example below.
 * 0 0 9 9              9 9 0 0
 * 1 2 3 4 will become: 4 3 2 1
 * 5 6 7 8              8 7 6 5
 * It is guaranteed that twoDimArray has at least 1 row.
 * <p>
 * P.S. You don't need to print anything in this task or create a new array: just modify the existing twoDimArray.
 */
class Test11 {

    static void printMat(int[][] mat) {
        System.out.println();
        for (int[] row : mat) {
            for (int el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    static void reverseElements(int[][] twoDimArray) {
        int temp = 0;
        for (int k = 0, halfway, lengthCols; k < twoDimArray.length; k++) {
            lengthCols = twoDimArray[k].length;
            halfway = twoDimArray[k].length / 2;
            for (int l = 0; l < halfway; l++) {
                temp = twoDimArray[k][l];
                twoDimArray[k][l] = twoDimArray[k][lengthCols - 1 - l];
                twoDimArray[k][lengthCols - 1 - l] = temp;
            }
        }
    }

    // Alternative
    static void reverseElements2(int[][] twoDimArray) {
        for (int k = 0; k < twoDimArray.length; k++) {
            twoDimArray[k] =reverseOneDArray(twoDimArray[k]);
        }
    }

    static int[] reverseOneDArray(int[] oneDimArray) {
        int len = oneDimArray.length;
        return IntStream.range(0, len).map(i -> oneDimArray[len -1 - i]).toArray();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] twoDimArray = new int[n][m];
        for (int k = 0; k < twoDimArray.length; k++) {
            for (int l = 0; l < twoDimArray[k].length; l++) {
                twoDimArray[k][l] = scanner.nextInt();
            }
        }
        scanner.close();
        printMat(twoDimArray);
        reverseElements2(twoDimArray);
        //reverseElements(twoDimArray);
        printMat(twoDimArray);
    }
}
