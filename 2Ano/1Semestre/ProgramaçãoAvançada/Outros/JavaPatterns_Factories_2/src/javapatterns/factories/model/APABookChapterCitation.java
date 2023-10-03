package javapatterns.factories.model;

public class APABookChapterCitation implements Citation {

    protected String author;
    protected String title;
    private String book_title;
    private String editor;
    private String place;
    private String publisher;
    private String page_numbers;
    private String year;

    public APABookChapterCitation(String author, String title, String book_title, String editor,
                                  String place, String publisher, String page_numbers, String year) {
        this.author = author;
        this.title = title;
        this.book_title = book_title;
        this.editor = editor;
        this.place = place;
        this.publisher = publisher;
        this.page_numbers = page_numbers;
        this.year = year;
    }

    @Override
    public String toStringFormatted() {
        return String.format("%s (%s). %s. In %s (Eds.), %s. (pp. %s). %s.",
                author,
                year,
                title,
                editor,
                book_title,
                page_numbers,
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
