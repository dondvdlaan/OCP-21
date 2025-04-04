package dev.manyroads.projects.searchengine.stage3;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

class SearchEngine implements Runnable {

    Supplier<String> userImport = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");
    Repository<String> repo;
    UI ui;

    public SearchEngine(Repository<String> repo, UI ui) {
        this.repo = repo;
        this.ui = ui;
    }

    static List<String> searchList(Repository<String> peopleRepo, String keyWord) {
        return peopleRepo.getList().stream().filter(line -> line.toLowerCase().contains(keyWord.toLowerCase())).toList();
    }

    @Override
    public void run() {
        ui.intakePeople(repo);
        ui.menu(repo);
    }
}
