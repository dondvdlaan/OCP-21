package dev.manyroads.projects.searchengine.stage4;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

class SearchEngine  {

    Supplier<String> userImport = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");
    Repository<Person> repo;
    UI engineUI;

    public SearchEngine(Repository<Person> repo, SearchEngineUI searchEngineUI) {
        this.repo = repo;
        this.engineUI = searchEngineUI;
    }

    static List<Person> searchList(Repository<Person> peopleRepo, String keyWord) {
        return peopleRepo.getList().stream().filter(p -> p.toString().toLowerCase().contains(keyWord.toLowerCase())).toList();
    }

    public void run(String pathToFile ) {
        engineUI.intakePeopleFromFile(repo, pathToFile);
        engineUI.menu(repo);
    }
}
