package dev.manyroads.projects.bullsandcows.stage1.example1;

public class Code {
    private static final int CODE_LENGTH = 4;
    private final String string;

    public Code(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public Grade check(Code code) {
        if (this.string.equals(code.string)) {
            return new Grade(4, 0);
        }
        Grade grade = new Grade();
        String temp = this.string;
        for (int i = 0; i < CODE_LENGTH; i++) {
            char ch1 = string.charAt(i);
            char ch2 = code.string.charAt(i);
            if (ch1 == ch2) {
                grade.addBull();
                continue;
            }
            String s = String.valueOf(ch2);
            if (temp.contains(s)) {
                temp = temp.replace(s, "");
                grade.addCow();
            }
        }
        return grade;
    }

    @Override
    public String toString() {
        return string;
    }
}
