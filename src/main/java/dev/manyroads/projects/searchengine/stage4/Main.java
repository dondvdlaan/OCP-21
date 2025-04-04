package dev.manyroads.projects.searchengine.stage4;

/**

 */
public class Main {
    public static void main(String[] args) {
        if("--data".equals(args[0]))
        new SearchEngine(new Repository<>(),new SearchEngineUI()).run(args[1]);
    }
}


