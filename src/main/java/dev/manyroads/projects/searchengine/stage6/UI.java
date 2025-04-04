package dev.manyroads.projects.searchengine.stage6;

public interface UI {
    void menu(Repository<Person> repo);
    void intakePeopleFromFile(Repository<Person> repo, String pathToFile);
}
