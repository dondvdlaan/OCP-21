package dev.manyroads.patterns.templatemethod;

    import java.util.Scanner;

/**
 * Social networks
 * There are three classes: an abstract class called SocialNetwork, and two concrete classes, Instagram and Facebook.
 * Your task is to implement the abstract class SocialNetwork with a template method called connect() and three
 * abstract methods using the following algorithm:
 * <p>
 * Log in
 * Post a message
 * Log out
 * Make Instagram and Facebook inherit from the SocialNetwork class and implement the methods according to the console output.
 * Sample Input 1:
 * <p>
 * instagram
 * Sample Output 1:
 * <p>
 * Log into Instagram
 * Post: Hello, Instagram!
 * Log out of Instagram
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next().toLowerCase();
        switch (name) {
            case "instagram" -> {
                SocialNetwork instagram = new Instagram();
                instagram.connect();
            }
            case "facebook" -> {
                SocialNetwork facebook = new Facebook();
                facebook.connect();
            }
        }
    }
}

abstract class SocialNetwork {
    String name;

    void connect() {
        logIn();
        postMessage();
        logOut();
    }

    abstract void logIn();

    abstract void postMessage();

    abstract void logOut();
}

class Instagram extends SocialNetwork {
    public Instagram() {
        super.name = getClass().getSimpleName();
    }

    @Override
    void logIn() {
        System.out.println("Log into " + name);
    }

    @Override
    void postMessage() {
        System.out.println("Post: Hello, " + name + "!");
    }

    @Override
    void logOut() {
        System.out.println("Log out of " + name);
    }
}

class Facebook extends SocialNetwork {
    public Facebook() {
        super.name = getClass().getSimpleName();
    }

    @Override
    void logIn() {
        System.out.println("Log into " + name);
    }

    @Override
    void postMessage() {
        System.out.println("Post: Hello, " + name + "!");
    }

    @Override
    void logOut() {
        System.out.println("Log out of " + name);
    }
}
