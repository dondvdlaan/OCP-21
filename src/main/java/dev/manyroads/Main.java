package dev.manyroads;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(4);
        List<Integer> l2 = new ArrayList<>();
        l2.add(1);
        l2.add(4);
        for(Integer i: l1){
            if(l2.contains(i)) System.out.println("Yahoo "+i);
        }
    }
}
