package dev.manyroads.lists;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

/**
 * Implement a method that returns an element by the specified index from a list.
 * The method must return an element by its regular or backward index.
 * <p>
 * In the regular order, elements have indexes 0, 1, 2, ..., n – 1. In the backward order,
 * the same elements have indexes -n, -n + 1, ..., -2, -1; where n is the size of a list.
 * <p>
 * If the specified index is out of bounds, the method must throw the standard IndexOutOfBoundsException.
 * <p>
 * Example. The input list consists of four elements: [100 202 300 401]. If the specified index is 0,
 * the method should return 100; if the specified index is -1 the method should return 401;
 * if the specified index is -2, the method should return 300.
 * Input:
 * 100 202 300 401
 * 0
 * Output: 100
 */
public class MainList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> lst = Arrays.stream(scanner.nextLine().split("\\s+")).toList();
        int i = Integer.parseInt(scanner.nextLine());
        System.out.println(returnIndexedElement(lst, i));
    }

    private static <T> T returnIndexedElement(List<T> lst, int index) {
        int size = lst.size();
        if (index < -size || index > size - 1) throw new IndexOutOfBoundsException();
        return index >= 0 ? lst.get(index) : lst.get(index + size);
    }
}
