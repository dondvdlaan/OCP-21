package dev.manyroads.projects.searchengine.stage6;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class FindNoneStrategy extends FindStrategy {

    Repository<Person> repo;

    public FindNoneStrategy(Repository<Person> repo) {
        this.repo = repo;
    }

    @Override
    void find() {
        List<Integer> noneLines = new ArrayList<>();
        for (int i = 0; i < query.length; i++) {
            noneLines.addAll(Optional.ofNullable(repo.getInvertedIndex().get(query[i])).orElse(List.of()));
        }
        list = IntStream.range(0, repo.getList().size())
                .filter(i -> !noneLines.contains(i))
                .mapToObj(i -> repo.getList().get(i))
                .toList();
    }
}
