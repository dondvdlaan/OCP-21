package dev.manyroads.enumset;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Scanner;

public class Main {

    static EnumSet<Language> getRequirementsByRole(Role role) {
        return switch (role) {
            case WEB_DEVELOPER -> EnumSet.of(Language.HTML, Language.CSS, Language.JAVA_SCRIPT);
            case DATA_SCIENTIST -> EnumSet.of(Language.PYTHON);
            case JAVA_EXPERT -> EnumSet.of(Language.JAVA);
            case GAME_DEVELOPER -> EnumSet.of(Language.C_SHARP);
            case COMPETITIVE_CODER -> EnumSet.of(Language.C_PLUS_PLUS);
        };

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String role = scanner.next();
        try {
            EnumSet<Language> languages = getRequirementsByRole(Role.valueOf(role));
            System.out.println("Job Code : " + role);
            System.out.println("Prerequisite : " + languages);

        } catch (IllegalArgumentException e) {
            System.out.println("no such vacancy");
            ;
        }
    }
}

enum Language {
    JAVA, C_PLUS_PLUS, PYTHON, C_SHARP, JAVA_SCRIPT, HTML, CSS
}

enum Role {
    WEB_DEVELOPER, DATA_SCIENTIST, JAVA_EXPERT, GAME_DEVELOPER, COMPETITIVE_CODER
}
