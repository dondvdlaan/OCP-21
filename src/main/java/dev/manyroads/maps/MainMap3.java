package dev.manyroads.maps;

import java.util.Collections;
import java.util.Map;

/**
 * The Map interface has a standard method getOrDefault. It returns the value to which the specified key is mapped,
 * or defaultValue if this map contains no mapping for the key.
 * <p>
 * Let's implement a similar method getOrMin. It behaves in the same way if the specified key is present in a map,
 * but returns the minimum value otherwise.
 * <p>
 * For instance, we have a map with the entries A: 1, B: 2, C: 3
 * <p>
 * If we pass B, then 2 is expected
 * If we pass D, then 1 is expected
 * <p>
 * Sample Input 1:
 * 3
 * A 1
 * B 2
 * C 3
 * B
 * Sample Output 1:
 * 2
 */
public class MainMap3 {

    static Integer getOrMin(Map<String, Integer> map, String key) {
        Integer lowest = map.values().stream().reduce(Math::min).orElse(null);
        if (lowest != null) {
            return map.getOrDefault(key, lowest);
        }
        return null;
    }

    static Integer getOrMin2(Map<String, Integer> map, String key) {
        return map.containsKey(key) ? map.get(key) : Collections.min(map.values());
    }

    static Integer getOrMin3(Map<String, Integer> map, String key) {
        return map.getOrDefault(key,Collections.min(map.values()));
    }

    public static void main(String[] args) {
        Map<String, Integer> map = Map.of("A", 1, "B", 2, "C", 3);
        System.out.println(getOrMin(Map.of(), "Z"));
        System.out.println(getOrMin2(map, "Z"));
        System.out.println(getOrMin3(map, "B"));
    }
}
