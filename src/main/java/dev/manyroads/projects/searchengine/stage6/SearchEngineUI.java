package dev.manyroads.projects.searchengine.stage6;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.lang.System.exit;

public class SearchEngineUI implements UI {
    Supplier<String> userImport = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");

    public void menu(Repository<Person> repo) {
        while (true) {
            System.out.println(Messages.MAIN_MENU.description);
            String choice = userImport.get();
            switch (choice) {
                case "1" -> findPeople(repo);
                case "2" -> printPeople(repo);
                case "0" -> {
                    System.out.println(Messages.BYE_NOW.description);
                    exit(0);
                }
                default -> System.out.println(Messages.WRONG_OPTION.description);
            }
        }
    }

    void intakePeopleManual(Repository<Person> repo) {
        System.out.println(Messages.NR_PEOPLE.description);
        int nrOfPeople = Integer.parseInt(userImport.get());

        System.out.println(Messages.ENTER_PEOPLE.description);
        for (int i = 1; i <= nrOfPeople; i++) {
            String[] data = userImport.get().trim().split("\\s+");
            switch (data.length) {
                case 1 -> {
                    repo.add(new Person(data[0]));
                }
                case 2 -> {
                    repo.add(new Person(data[0], data[1]));
                }
                case 3 -> {
                    repo.add(new Person(data[0], data[1], data[2]));
                }
            }
        }
    }

    public void intakePeopleFromFile(Repository<Person> repo, String pathToFile) {
        List<String> list = readFromFile(pathToFile);
        int i = 0;
        for (String line : list) {
            String[] data = line.trim().split("\\s+");
            switch (data.length) {
                case 1 -> {
                    addToIndex(repo, i, data);
                    repo.add(new Person(data[0]));
                }
                case 2 -> {
                    addToIndex(repo, i, data);
                    repo.add(new Person(data[0], data[1]));
                }
                case 3 -> {
                    addToIndex(repo, i, data);
                    repo.add(new Person(data[0], data[1], data[2]));
                }
            }
            i++;
        }
    }

    List<String> readFromFile(String pathToFile) {
        File file = new File(pathToFile);
        if (!(file.exists() && file.isFile())) {
            System.out.println("File not found!");
            return List.of();
        }
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
        } catch (InputMismatchException ex) {
            System.out.println("FoutjeI: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        }
        return list;
    }

    void addToIndex(Repository<Person> repo, int lineNr, String... datas) {
        for (String data : datas) {
            if (repo.getInvertedIndex().containsKey(data)) {
                repo.getInvertedIndex().get(data).add(lineNr);
            } else {
                repo.getInvertedIndex().put(data, new ArrayList<>(List.of(lineNr)));
            }
        }
    }

    void findPeople(Repository<Person> repo) {
        List<Person> res = null;
        System.out.println(Messages.MATCH_STRAT.description);
        String strat = userImport.get();
//        System.out.println(Messages.SEARCH_QUERY.description);
       //String[] query = userImport.get().split("\\s+");
        switch (strat) {
        //res = switch (strat) {
            case "ALL" ->{
                FindStrategy findAllStrat = new FindAllStrategy(repo);
                findAllStrat.findPeople();
                //findAll(repo, query);
            }
            //case "ALL" -> findAll(repo, query);
            case "ANY" -> {
                FindStrategy findAnyStrat = new FindAnyStrategy(repo);
                findAnyStrat.findPeople();
                //findAny(repo, query);
            }
            //case "ANY" -> findAny(repo, query);
            case "NONE" -> {
                FindStrategy findNoneStrat= new FindNoneStrategy(repo);
                findNoneStrat.findPeople();
                //findNone(repo, query);
            }
            default -> {
                System.out.println(Messages.WRONG_OPTION.description);
                //yield null;
            }
        };
        if (res != null) displayPeople(res);
    }

    List<Person> findAll(Repository<Person> repo, String[] query) {
        if (query.length == 1) return SearchEngine.searchListIndexed2(repo, query[0]);
        if (query.length == 2) {
            List<Person> res = new ArrayList<>();
            List<Integer> lines = Optional.ofNullable(repo.getInvertedIndex().get(query[0])).orElse(List.of());
            List<Integer> lines2 = Optional.ofNullable(repo.getInvertedIndex().get(query[1])).orElse(List.of());
            // alternative
//            for (Integer e : lines) {
//                if (lines2.contains(e)) res.add(repo.getList().get(e));
//            }
//            return res;
            return lines.stream().filter(lines2::contains).map(repo.getList()::get).toList();
        }
        if (query.length == 3) {
            List<Person> res = new ArrayList<>();
            List<Integer> temp = new ArrayList<>();
            List<Integer> lines = Optional.ofNullable(repo.getInvertedIndex().get(query[0])).orElse(List.of());
            List<Integer> lines2 = Optional.ofNullable(repo.getInvertedIndex().get(query[1])).orElse(List.of());
            List<Integer> lines3 = Optional.ofNullable(repo.getInvertedIndex().get(query[2])).orElse(List.of());
            // alternative
//            for (Integer e : lines) {
//                if (lines2.contains(e)) temp.add(e);
//            }
//            for (Integer e : temp) {
//                if (lines3.contains(e)) res.add(repo.getList().get(e));
//            }
            //return res;
            return lines.stream().filter(lines2::contains).filter(lines3::contains).map(repo.getList()::get).toList();
        }
        return null;
    }

    List<Person> findAny(Repository<Person> repo, String[] query) {
        List<Person> res = new ArrayList<>();
        for (int i = 0; i < query.length; i++) {
            res.addAll(SearchEngine.searchListIndexed2(repo, query[i]));
        }
        return res;
    }

    List<Person> findNone(Repository<Person> repo, String[] query) {
        List<Integer> noneLines = new ArrayList<>();
        List<Person> res = new ArrayList<>();
        for (int i = 0; i < query.length; i++) {
            noneLines.addAll(Optional.ofNullable(repo.getInvertedIndex().get(query[i])).orElse(List.of()));
        }
//        for (int i = 0; i < repo.getList().size(); i++) {
//            if (!noneLines.contains(i)) res.add(repo.getList().get(i));
//        }
//        return res;
        return IntStream.range(0,repo.getList().size()).filter(i->!noneLines.contains(i)).mapToObj(i->repo.getList().get(i)).toList();
    }

    void displayPeople(List<Person> list) {
        if (list.isEmpty()) {
            System.out.println(Messages.NO_MATCH_PEOPLE.description);
            return;
        }
        System.out.println(Messages.FOUND_PEOPLE.description);
        list.forEach(System.out::println);
    }

    void printPeople(Repository<Person> repo) {
        System.out.println(Messages.LIST_PEOPLE.description);
        repo.getList().forEach(System.out::println);
    }
}
