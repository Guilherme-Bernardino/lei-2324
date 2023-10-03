package javapatterns.factories.model;

public class APABookCitation implements Citation {

    protected String author;
    protected String title;
    private String place;
    private String publisher;
    private String year;

    public APABookCitation(String author, String title, String place, String publisher, String year) {
        this.author = author;
        this.title = title;
        this.place = place;
        this.publisher = publisher;
        this.year = year;
    }

    @Override
    public String toStringFormatted() {
       return String.format("%s (%s). %s. %s.",
               author,
               year,
               title,
               publisher);
    }

    @Override
    public String getAuthors() {
        return author;
    }

    @Override
    public String getYear() {
        return year;
    }
}
