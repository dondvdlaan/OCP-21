package dev.manyroads.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // A word character: [a-zA-Z_0-9]
        Pattern p = Pattern.compile("\\w*b");
        Matcher m = p.matcher("abcb");
        boolean b = m.matches();
        System.out.println(b);
    }
}
