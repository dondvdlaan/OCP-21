package dev.manyroads.projects.searchengine.stage2.example2;

import java.util.List;

public class SearchController {
    private final DataSet dataSet;
    private final Menu menu;

    public SearchController(DataSet dataSet, Menu menu) {
        this.dataSet = dataSet;
        this.menu = menu;
    }

    public void start() {
        int numberOfLines = menu.getNumberOfLines();
        System.out.println("Enter all people:");
        for (int i = 0; i < numberOfLines; i++) {
            dataSet.addLine(menu.getInputLine());
        }

        int numberOfQueries = menu.getNumberOfQueries();
        for (int i = 0; i < numberOfQueries; i++) {
            System.out.println("Enter data to search people:");
            String query = menu.getInputLine();
            List<String> results = dataSet.search(query);
            menu.displayResults(results);
        }
    }
}
