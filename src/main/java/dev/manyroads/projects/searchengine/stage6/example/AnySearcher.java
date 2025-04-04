package dev.manyroads.projects.searchengine.stage6.example;

import java.util.*;

public class AnySearcher extends SearchEngine {

    private final Repository repository;

    public AnySearcher(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Integer> searchingData(String searchData) {
        Set<Integer> resultIndex = new HashSet<>();

        Map<String, List<Integer>> invertedIndex = repository.getInvertedIndex();
        String[] searchContent = searchData.toLowerCase(Locale.ROOT).split(SEPARATOR);

        Arrays.stream(searchContent).filter(invertedIndex::containsKey)
                .map(invertedIndex::get)
                .forEach(resultIndex::addAll);

        return resultIndex;
    }
}
