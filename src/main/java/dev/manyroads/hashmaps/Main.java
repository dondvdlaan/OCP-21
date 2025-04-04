package dev.manyroads.hashmaps;

import java.util.HashMap;
import java.util.Map;

/**
 * ou're working with the two maps that contain String keys and String values.
 * <p>
 * You need to implement the calcTheSamePairs method. It should print how many common pairs
 * both maps contain. The pair is considered to be the same when the element from the first
 * map has the same key and the same value as the element from the second map.
 */
public class Main {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        int commonPair = 0;
        for (Map.Entry<String, String> e : map1.entrySet()) {
            for (Map.Entry<String, String> e2 : map2.entrySet()) {
                commonPair += e.equals(e2) ? 1 : 0;
            }
        }
        System.out.println(commonPair);

        // Alternative
        map1.entrySet().retainAll(map2.entrySet());
        System.out.println(map1.size());

        //aLTERNTIVE2
        long same = map1.entrySet().stream().filter(entry -> map2.entrySet().contains(entry)).count();
        System.out.println(same);
    }

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        map1.put("keyA", "valueA");
        map1.put("keyB", "valueB");
        map1.put("keyC", "valueC");
        map1.put("keyD", "valueD");
        map1.put("keyE", "valueE");
        Map<String, String> map2 = new HashMap<>();
        map2.put("key1", "value1");
        map2.put("keyA", "valueA");
        map2.put("key2", "value2");
        map2.put("keyB", "valueB");
        map2.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println();
        calcTheSamePairs(map1, map2);
    }

}
