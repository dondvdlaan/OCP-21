package dev.manyroads.overridden;

/**
 * You need to override the methods getType() and getDetails() in classes inherited from the class Publication.
 * getType() should show the kind of publication, like a newspaper, and getDetails() should show the class
 * attribute, such as the source.
 * <p>
 * Then you need to implement getInfo() in the class Publication using getType() and getDetails().
 * The method should return a String with a type of publication in the first place,
 * then details in round brackets and the title after a colon.
 */
class Publication {

    private String title;

    public Publication(String title) {
        this.title = title;
    }

    public final String getInfo() {
        return getType() + getDetails() + ": " + title;
    }

    public String getType() {
        return "Publication";
    }

    public String getDetails() {
        return "";
    }

}

class Newspaper extends Publication {

    private String source;

    public Newspaper(String title, String source) {
        super(title);
        this.source = source;
    }

    @Override
    public String getType() {
        return "Newspaper";
    }

    @Override
    public String getDetails() {
        return " (source - " + this.source + ")";
    }

}

class Article extends Publication {

    private String author;

    public Article(String title, String author) {
        super(title);
        this.author = author;
    }

    @Override
    public String getType() {
        return "Article";
    }

    @Override
    public String getDetails() {
        return " (author - " + this.author + ")";
    }

}

class Announcement extends Publication {

    private int daysToExpire;

    public Announcement(String title, int daysToExpire) {
        super(title);
        this.daysToExpire = daysToExpire;
    }

    public String getType() {
        return "Announcement";
    }

    public String getDetails() {
        return " (days to expire - " + this.daysToExpire + ")";
    }
}

class TestPublication {
    public static void main(String[] args) {
        Publication publication = new Publication("The new era");
        System.out.println(publication.getInfo());
        Publication newspaper = new Newspaper("Football results","Sport news");
        System.out.println(newspaper.getInfo());
        Publication article = new Article("Why the Sun is hot","Dr James Smith");
        System.out.println(article.getInfo());
        Publication announcement = new Announcement("Will sell a house",30);
        System.out.println(announcement.getInfo());

    }
}
