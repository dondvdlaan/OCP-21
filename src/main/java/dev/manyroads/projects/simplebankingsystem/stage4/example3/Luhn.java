package dev.manyroads.projects.simplebankingsystem.stage4.example3;

public class Luhn {

    public static String generateValidChecksum(String numberSequence) {
        int sum = 0;
        for (int i = 0; i < numberSequence.length(); i++) {
            int num = numberSequence.charAt(i) - '0';
            if (i % 2 == 0) {
                num *= 2;
                if (num > 9) {
                    num -= 9;
                }
            }
            sum += num;
        }
        if (sum % 10 == 0) {
            return "0";
        }
        return String.valueOf(10 - (sum % 10));
    }

}
