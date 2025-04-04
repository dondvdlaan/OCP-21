package dev.manyroads.projects.searchengine.stage6;

/**
 Now let's Improve your search engine to make it support complex queries containing word sequences and use
 several strategies that determine how to match data.

 Objectives
 In this stage, your program should be able to use such searching strategies as ALL, ANY, and NONE.

 Take, for example, these six sample lines:
 Dwight Joseph djo@gmail.com
 Rene Webb webb@gmail.com
 Katie Jacobs
 Erick Harrington harrington@gmail.com
 Myrtle Medina
 Erick Burgess

 If the strategy is ALL, the program should print lines containing all the words from the query.
 Query:
 Harrington Erick
 Result:
 Erick Harrington harrington@gmail.com

 If the strategy is ANY, the program should print the lines containing at least one word from the query.
 Query:
 Erick Dwight webb@gmail.com
 Result:
 Erick Harrington harrington@gmail.com
 Erick Burgess
 Dwight Joseph djo@gmail.com
 Rene Webb webb@gmail.com

 If the strategy is NONE, the program should print lines that do not contain words from the query at all:
 Query:
 djo@gmail.com ERICK
 Result:
 Katie Jacobs
 Myrtle Medina
 Rene Webb webb@gmail.com

 All listed operations are implemented in the inverted index. The results should not contain duplicates.
Example:
 === Menu ===
 1. Find a person
 2. Print all persons
 0. Exit
 > 1

 Select a matching strategy: ALL, ANY, NONE
 > ANY

 Enter a name or email to search all suitable people.
 > Katie Erick QQQ

 3 persons found:
 Katie Jacobs
 Erick Harrington harrington@gmail.com
 Erick Burgess
 */
public class Main {
    public static void main(String[] args) {
        // 2 files available people.txt & names.txt
        if("--data".equals(args[0]))
        new SearchEngine(new Repository<>(),new SearchEngineUI()).run(args[1]);
    }
}


