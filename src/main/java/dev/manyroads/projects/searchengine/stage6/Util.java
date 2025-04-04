package dev.manyroads.projects.searchengine.stage6;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

public class Util {

    static Supplier<String> userImport = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");

}
