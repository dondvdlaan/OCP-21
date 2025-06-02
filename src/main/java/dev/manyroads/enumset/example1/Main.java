package dev.manyroads.enumset.example1;

import java.util.EnumSet;
import java.util.Scanner;

public class Main {

    enum Language {
        JAVA, C_PLUS_PLUS, PYTHON, C_SHARP, JAVA_SCRIPT, HTML, CSS
    }

    enum Role {
        WEB_DEVELOPER(EnumSet.of(Language.HTML, Language.CSS, Language.JAVA_SCRIPT)),
        DATA_SCIENTIST(EnumSet.of(Language.PYTHON)),
        JAVA_EXPERT(EnumSet.of(Language.JAVA)),
        GAME_DEVELOPER(EnumSet.of(Language.C_SHARP)),
        COMPETITIVE_CODER(EnumSet.of(Language.C_PLUS_PLUS));
        private final EnumSet<Language> languages;

        public EnumSet<Language> getLanguages() {
            return languages;
        }

        Role(EnumSet<Language> languages) {
            this.languages = languages;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String roleName = scanner.next();
        try {
            Role role = Role.valueOf(roleName);
            EnumSet<Language> languages = getRequirementsByRole(role);
            System.out.println("Job Code : " + role);
            System.out.println("Prerequisite : " + languages);
        } catch (IllegalArgumentException e) {
            System.out.println("Thank you for considering us but there is no vacancy.");
        }
    }

    public static EnumSet<Language> getRequirementsByRole(Role role) {
        return role.getLanguages();
    }
}

