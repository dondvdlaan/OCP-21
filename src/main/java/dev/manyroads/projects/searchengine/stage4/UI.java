package dev.manyroads.projects.searchengine.stage4;

public interface UI {
    void menu(Repository<Person> repo);
    void intakePeopleFromFile(Repository<Person> repo, String pathToFile);
}
