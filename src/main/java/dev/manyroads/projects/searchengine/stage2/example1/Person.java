package dev.manyroads.projects.searchengine.stage2.example1;


public class Person {
    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName) {
        this.firstName = firstName;
        this.lastName = "";
        this.email = "";
    }

    public Person(String firstName, String lastName) {
        this(firstName);
        if (lastName != null) {
            this.lastName = lastName;
        }
    }


    public Person(String firstName, String lastName, String email) {
        this(firstName, lastName);
        if (email != null) {
            this.email = email;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + email;
    }
}
