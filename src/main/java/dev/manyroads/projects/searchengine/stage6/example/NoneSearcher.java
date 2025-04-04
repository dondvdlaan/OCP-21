package dev.manyroads.projects.searchengine.stage6.example;

import java.util.*;

public class NoneSearcher extends SearchEngine{

    private final Repository repository;

    public NoneSearcher(Repository repository) {
        this.repository = repository;
    }
    @Override
    public Set<Integer> searchingData(String searchData) {
        Map<String, List<Integer>> invertedIndex = repository.getInvertedIndex();

        String[] searchContent = searchData.toLowerCase(Locale.ROOT).split(SEPARATOR);
        Set<Integer> resultIndex = new HashSet<>();
        for(String each: invertedIndex.keySet()) {
            resultIndex.addAll(invertedIndex.get(each));
        }
        for (String eachSearch : searchContent) {
            resultIndex.removeAll(invertedIndex.get(eachSearch));
        }

        return resultIndex;
    }
}
