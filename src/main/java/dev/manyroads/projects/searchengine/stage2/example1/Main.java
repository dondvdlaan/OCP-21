package dev.manyroads.projects.searchengine.stage2.example1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int numberOfPeople = Integer.parseInt(scanner.nextLine().trim());
        Repository<Person> personRepository = new Repository<>();
        for (int i = 0; i < numberOfPeople; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            Person person = null;
            switch (input.length) {
                case 1:
                    person = new Person(input[0]);
                    break;
                case 2:
                    person = new Person(input[0], input[1]);
                    break;
                case 3:
                    person = new Person(input[0], input[1], input[2]);
                    break;
                default:
            }
            personRepository.add(person);
        }
        System.out.println("Enter the number of search queries:");
        int queriesNumber = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < queriesNumber; i++) {
            try {
                System.out.println("Found people: \n" +
                        SearchService.search(
                                personRepository,
                                scanner.nextLine()));
            } catch (SearchException e) {
                System.out.println(e.getMessage());
            }
        }


    }

}
