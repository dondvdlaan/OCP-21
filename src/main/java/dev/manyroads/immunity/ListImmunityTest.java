package dev.manyroads.immunity;

import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Make stringList immutable by using List.of, returning List.copyOf
 * or Collections.unmodifiableList(strings);
 */
public class ListImmunityTest {

    private final List<String> stringList;

    public ListImmunityTest() {
        this.stringList = new ArrayList<>(Arrays.asList("Geeks", "for", "Geeks"));
    }

    public List<String> getStringList() {
        this.stringList.add("ee");
        return this.stringList;
        //return List.copyOf(this.strings);
    }

    public static void main(String[] args) {
        ListImmunityTest listImmunityTest = new ListImmunityTest();
        List<String> nogEenLijst = listImmunityTest.getStringList();
        nogEenLijst.add("cc");
        System.out.println("nogEenLijst: " + nogEenLijst);
        listImmunityTest.stringList.add("dd");
        System.out.println("listimmunityTest: " + listImmunityTest.stringList);
        System.out.println();
        System.out.println(FileSystems.getDefault().getSeparator());
    }
}
