package dev.manyroads.Recursion;

public class Main {

    static int faculteit(int n) {
        if (n == 1) return 1;
        return n * faculteit(n - 1);
    }

    /**
     * recursive function that finds out whether the positive number is a power of 2. For example,
     * 64 is a power of 2, but 12 is not.
     * @param n
     * @return
     */
    static boolean isPowerOf2(double n) {
        if (n == 1) return true;
        if (n > 1 && n < 2) return false;
        return isPowerOf2(n / 2);
    }

    public static void main(String[] args) {
        //System.out.println(faculteit(5));
        System.out.println(isPowerOf2(10));
    }
}
