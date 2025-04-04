package dev.manyroads.projects.searchengine.stage6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindAllStrategy extends FindStrategy {

    Repository<Person> repo;

    public FindAllStrategy(Repository<Person> repo) {
        this.repo = repo;
    }

    @Override
    void find() {
        if (query.length == 1) {
            list = SearchEngine.searchListIndexed2(repo, query[0]);
            return;
        }
        if (query.length == 2) {
            List<Person> res = new ArrayList<>();
            List<Integer> lines = Optional.ofNullable(repo.getInvertedIndex().get(query[0])).orElse(List.of());
            List<Integer> lines2 = Optional.ofNullable(repo.getInvertedIndex().get(query[1])).orElse(List.of());
            list = lines.stream().filter(lines2::contains).map(repo.getList()::get).toList();
            return;
        }
        if (query.length == 3) {
            List<Person> res = new ArrayList<>();
            List<Integer> temp = new ArrayList<>();
            List<Integer> lines = Optional.ofNullable(repo.getInvertedIndex().get(query[0])).orElse(List.of());
            List<Integer> lines2 = Optional.ofNullable(repo.getInvertedIndex().get(query[1])).orElse(List.of());
            List<Integer> lines3 = Optional.ofNullable(repo.getInvertedIndex().get(query[2])).orElse(List.of());
            list = lines.stream().filter(lines2::contains).filter(lines3::contains).map(repo.getList()::get).toList();
            return;
        }
    }
}
