package dev.manyroads.projects.searchengine.stage2.example1;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    public static Repository<Person> search(Repository<Person> personRepository,
                                            String searchWord) {
        final String word = searchWord.toLowerCase();
        Repository<Person> resultRepository = new Repository<>();
        List<Person> persons = personRepository.getAll()
                .stream()
                .filter(person -> person.getFirstName().toLowerCase().contains(word) ||
                        person.getLastName().toLowerCase().contains(word) ||
                        person.getEmail().toLowerCase().contains(word))
                .collect(Collectors.toList());
        if (persons.isEmpty()) {
            throw new SearchException();
        }
        resultRepository.addAll(persons);
        return resultRepository;
    }
}
