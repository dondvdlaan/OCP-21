package dev.manyroads.projects.searchengine.stage5;

/**
 * Inverted Index
 Now your program can successfully search for all matching lines, and the search is case- and space-insensitive.
 There is one problem though: you need to check each line to find out whether it contains the query string.

 To optimize your program, you can use a data structure called an Inverted Index. It maps each word to all
 positions/lines/documents in which the word occurs. As a result, when we receive a query, we can immediately
 find the answer without any comparisons.

 Objectives
 In this stage, build an inverted index at the start of the program and then use the index for searching operations.
 You can implement it using the Map class. It connects an item with a list (or set) of indexes belonging to the
 lines that contain the item.

 Suppose you have the following list of lines:
 0: Katie Jacobs
 1: Erick Harrington harrington@gmail.com
 2: Myrtle Medina
 3: Erick Burgess

 For these lines, the inverted index will look like this:
 Katie -> [0]
 Jacobs -> [0]
 Erick -> [1, 3]
 Harrington -> [1]
 harrington@gmail.com -> [1]
 Myrtle -> [2]
 Medina -> [2]
 Burgess -> [3]

 Example
 === Menu ===
 1. Find a person
 2. Print all people
 0. Exit
 > 1

 Enter a name or email to search all suitable people.
 > ERICK
 2 persons found:
 Erick Harrington harrington@gmail.com
 Erick Burgess

 === Menu ===
 1. Find a person
 2. Print all people
 0. Exit
 > 1

 Enter a name or email to search all suitable people.
 > ROY@gmail.com
 1 persons found:
 Richard    Roy    roy@gmail.com

 === Menu ===
 1. Find a person
 2. Print all people
 0. Exit
 > 1

 Enter a name or email to search all suitable people.
 > john
 No matching people found.

 === Menu ===
 1. Find a person
 2. Print all people
 0. Exit
 > 0

 Bye!
 */
public class Main {
    public static void main(String[] args) {
        // 2 files available people.txt & names.txt
        if("--data".equals(args[0]))
        new SearchEngine(new Repository<>(),new SearchEngineUI()).run(args[1]);
    }
}


