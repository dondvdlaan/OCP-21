package dev.manyroads.projects.searchengine.stage5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class SearchEngine {

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

    static List<Person> searchListIndexed(Repository<Person> repo, String keyWord) {
        List<Person> listPersons = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> e : repo.getInvertedIndex().entrySet()) {
            if (Objects.equals(e.getKey(), keyWord)) {
                for (int i = 0; i < e.getValue().size(); i++) {
                    listPersons.add(repo.getList().get(e.getValue().get(i)));
                }
            }
        }
        return listPersons;
    }

    /**
     * Improved version
     *
     * @param repo
     * @param keyWord
     * @return
     */
    static List<Person> searchListIndexed2(Repository<Person> repo, String keyWord) {
        List<Person> listPersons = new ArrayList<>();
        // alternative
        for (Integer e : repo.getInvertedIndex().get(keyWord)) {
            listPersons.add(repo.getList().get(e));
        }
        //return listPersons;
        return repo.getInvertedIndex().get(keyWord).stream().
                map(i -> repo.getList().get(i))
                .toList();
    }

    public void run(String pathToFile) {
        engineUI.intakePeopleFromFile(repo, pathToFile);
        engineUI.menu(repo);
    }
}
