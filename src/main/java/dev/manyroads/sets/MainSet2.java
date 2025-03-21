package dev.manyroads.sets;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 */
public class MainSet2 {
    public static void main(String[] args) {
        SortedSet<Integer> sortedSet = new TreeSet<>(List.of(0,1,2,3,4,5,6,7,8,9));

        SortedSet<Integer> subSet = sortedSet.subSet(3, 7); // 3,4,5,6
        System.out.println();
        int firstSortedSet = sortedSet.first(); // 0
        System.out.println(firstSortedSet);
        int firstSubset = subSet.first();       // 3
        System.out.println(firstSubset);
    }
}
