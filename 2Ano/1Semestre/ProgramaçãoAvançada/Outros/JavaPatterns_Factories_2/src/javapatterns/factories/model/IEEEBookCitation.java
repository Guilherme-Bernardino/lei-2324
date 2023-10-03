package javapatterns.factories.model;

/**
 * IEEE bibliography style for a Book:
 *
 *  - #AUTHOR#, #TITLE#, #PLACE#: #PUBLISHER#, #YEAR#.
 *
 * @author brunomnsilva
 */
public class IEEEBookCitation implements Citation {

    protected String author;
    protected String title;
    private String place;
    private String publisher;
    private String year;

    public IEEEBookCitation(String author, String title, String place, String publisher, String year) {
        this.author = author;
        this.title = title;
        this.place = place;
        this.publisher = publisher;
        this.year = year;
    }

    @Override
    public String toStringFormatted() {
        return String.format("%s, %s, %s: %s, %s.", author, title, place,
                publisher, year);
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
