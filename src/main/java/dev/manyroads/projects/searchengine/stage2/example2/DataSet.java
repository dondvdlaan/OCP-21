package dev.manyroads.projects.searchengine.stage2.example2;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
    private final List<String> lines;

    public DataSet() {
        lines = new ArrayList<>();
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public List<String> search(String query) {
        List<String> results = new ArrayList<>();
        String trimmedQuery = query.toLowerCase().trim();
        for (String line : lines) {
            if (line.toLowerCase().contains(trimmedQuery)) {
                results.add(line);
            }
        }
        return results;
    }
}
