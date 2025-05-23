package dev.manyroads.comparable.article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * implement comparing articles by their size, and if their sizes are equal,
 * compare them by title (according to the lexicographical order).
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Article> articles = new ArrayList<>();
            String title = "How to bake an awesome cake?";
            System.out.println(title);
            int size = scanner.nextInt();
            articles.add(new Article(title, size));
            title = "How to bake an awesome cake?";
            System.out.println(title);
            size = scanner.nextInt();
            articles.add(new Article(title, size));
            title = "Alice likes pancakes...But who doesn't?";
            System.out.println(title);
            size = scanner.nextInt();
            articles.add(new Article(title, size));
            title = "Germany wants to win EURO 2020!";
            System.out.println(title);
            size = scanner.nextInt();
            articles.add(new Article(title, size));

            //articles.sort(Collections.reverseOrder());
            Collections.sort(articles);
            articles.forEach(System.out::println);
        }
    }
}

class Article implements Comparable<Article> {
    private String title;
    private int size;

    public Article(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

//    @Override
//    public int compareTo(Article otherArticle) {
//        // add your code here!
//        int compare = Integer.compare(this.getSize(), otherArticle.getSize());
//        if(compare == 0 ){
//            compare = this.getTitle().compareTo(otherArticle.getTitle());
//        }
//        return compare;
//    }
    @Override
    public int compareTo(Article article) {
        int result = Integer.compare(this.size, article.size);
        if(result==0 ){
            result = this.title.compareTo(article.title);
        }

        //return result;
        return result == -1 ? 1 : result == 1 ? -1 : result;
    }

//    @Override
//    public int compareTo(Article otherArticle) {
//        int result = Integer.compare(size, otherArticle.size);
//        if (result == 0) return title.compareTo(otherArticle.title);
//        return result;
//    }

//    @Override
//    public int compareTo(Article otherArticle) {
//        return Comparator.comparing(Article::getSize)
//                .thenComparing(Article::getTitle)
//                .reversed()
//                .compare(this, otherArticle);
//    }

    @Override
    public String toString() {
        return title + " " + size;
    }


}
