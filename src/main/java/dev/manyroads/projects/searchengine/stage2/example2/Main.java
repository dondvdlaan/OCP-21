package dev.manyroads.projects.searchengine.stage2.example2;


public class Main {
    public static void main(String[] args) {
        DataSet dataSet = new DataSet();
        Menu menu = new Menu();
        SearchController controller = new SearchController(dataSet, menu);
        controller.start();
    }
}