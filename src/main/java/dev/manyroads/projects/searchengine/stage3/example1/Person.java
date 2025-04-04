package dev.manyroads.projects.searchengine.stage3.example1;


/**
 * Created by ag on 04-Jun-20 8:40 PM
 */
public class Person {

    private final String firstName;
    private final String lastName;
    private final String email; // optional

    public Person(String firstName) {
        this(firstName, "", "");
    }

    public Person(String firstName, String lastName) {
        this(firstName, lastName, "");
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String
                .format("%s %s %s", getFirstName(), getLastName(), getEmail())
                .trim(); // if email is empty trim to accommodate first and last name only
    }
}