package dev.manyroads.projects.searchengine.stage6.example;

import java.util.*;

public class AllSearcher extends SearchEngine {

    private final Repository repository;

    public AllSearcher(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Integer> searchingData(String searchData) {

        Map<String, List<Integer>> invertedIndex = repository.getInvertedIndex();

        String[] searchContent = searchData.toLowerCase(Locale.ROOT).split(SEPARATOR);
        List<Integer> tmpIndex = new ArrayList<>();
        Set<Integer> resultIndex = new HashSet<>();

        for (int i = 0; i < searchContent.length; i++) {
            if (i == 0 && invertedIndex.containsKey(searchContent[0])) {
                tmpIndex.addAll(invertedIndex.get(searchContent[0]));
            }
            List<Integer> checked = invertedIndex.get(searchContent[i]);
            for (Integer eachTmp : tmpIndex) {
                for (Integer eachCheked : checked) {
                    if (eachTmp.equals(eachCheked)) {
                        resultIndex.add(eachCheked);
                    }
                }
            }
        }

        return resultIndex;
    }
}
