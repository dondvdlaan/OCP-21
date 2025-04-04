package dev.manyroads.projects.searchengine.stage6;

import java.util.ArrayList;
import java.util.List;

public class FindAnyStrategy extends FindStrategy {

    Repository<Person> repo;

    public FindAnyStrategy(Repository<Person> repo) {
        this.repo = repo;
    }

    @Override
    void find() {
        for (int i = 0; i < query.length; i++) {
            list.addAll(SearchEngine.searchListIndexed2(repo, query[i]));
        }
    }
}
