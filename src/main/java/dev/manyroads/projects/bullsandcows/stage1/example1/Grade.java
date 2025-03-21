package dev.manyroads.projects.bullsandcows.stage1.example1;

public class Grade {
    private int bulls;
    private int cows;

    public Grade(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
    }

    public Grade() {
        this.bulls = 0;
        this.cows = 0;
    }

    public int getBulls() {
        return bulls;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public int getCows() {
        return cows;
    }

    public void setCows(int cows) {
        this.cows = cows;
    }

    public void addBull() {
        bulls++;
    }

    public void addCow() {
        cows++;
    }

    public boolean isSolved() {
        return bulls == 4;
    }

    @Override
    public String toString() {
        String text = "Grade: ";
        if (bulls > 0 && cows > 0) {
            return text + bulls + (bulls == 1 ? " bull" : " bulls") + " and "
                    + cows + (cows == 1 ? " cow." : " cows.");
        }
        if (bulls > 0) {
            return text + bulls + (bulls == 1 ? " bull" : " bulls") + ".";
        }
        if (cows > 0) {
            return text + cows + (cows == 1 ? " cow." : " cows.");
        }
        return text + "None.";
    }
}
