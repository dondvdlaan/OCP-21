package dev.manyroads.projects.searchengine.stage6.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Repository {

    public final String SEPARATOR = " ";

    private List<DataModel> storage = new ArrayList<>();
    private Map<String, List<Integer>> invertedIndex = new HashMap<>();

    public Repository() {
    }

    public List<DataModel> getStorage() {
        return storage;
    }

    public void setStorage(List<DataModel> storage) {
        this.storage = storage;
    }

    public Map<String, List<Integer>> getInvertedIndex() {
        return invertedIndex;
    }

    public void setInvertedIndex(Map<String, List<Integer>> invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    public void initRepository(String[] source) {
        String fileName = source[1];

        File file = new File(fileName);

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                DataModel dataModel = new DataModel();
                dataModel.setDataLine(fileScanner.nextLine());
                storage.add(dataModel);
                String[] content = dataModel.getDataLine()
                        .toLowerCase(Locale.ROOT)
                        .split(SEPARATOR);
                for (String eachContent : content) {
                    List<Integer> indexes = new ArrayList<>();
                    if (invertedIndex.containsKey(eachContent)) {
                        indexes = invertedIndex.get(eachContent);
                        indexes.add(storage.size() - 1);
                    } else {
                        indexes.add(storage.size() - 1);
                        invertedIndex.put(eachContent, indexes);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + fileName);
        }

        System.out.println();
    }

    public void printAllData() {
        System.out.println("\n=== List of people ===");
        for (DataModel each : storage) {
            System.out.println(each.getDataLine());
        }
    }
}
