package dev.manyroads.comparable.ratings;

import java.util.*;

/**
 * A website uses the Rating class to store information about ratings of publications.
 * It has two integer fields: upVotes and downVotes , and implements the Comparable interface.
 * Your task is to write its compareTo method. Two Rating objects must be compared by a "net rating"
 * which is calculated as a difference between the number of upvotes and the number of downvotes.
 */
class Rating implements Comparable<Rating> {
    private int upVotes;
    private int downVotes;

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    @Override
    public int compareTo(Rating rating) {
        return Integer.compare(getResult(), rating.getResult());
    }

    int getResult() {
        return upVotes - downVotes;
    }
}

// do not change the code below
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Rating> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            int[] votes = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Rating rating = createRating(votes[0], votes[1]);
            list.add(rating);
        }
        Collections.sort(list);
        Checker.check(list);
    }

    private static Rating createRating(int up, int down) {
        Rating rating = new Rating();
        rating.setUpVotes(up);
        rating.setDownVotes(down);
        return rating;
    }
}

class Checker {
    static void check(List<Rating> list) {
        for (int i = 1; i < list.size(); i++) {
            var curr = list.get(i);
            var prev = list.get(i - 1);
            if (curr.getUpVotes() - curr.getDownVotes() < prev.getUpVotes() - prev.getDownVotes()) {
                System.out.println("Incorrect sorted order");
                return;
            }
        }
        System.out.println("Correct sorted order");
    }
}
