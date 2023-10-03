package javapatterns.factories.model;

/**
 * IEEE bibliography style for a Book Chapter:
 *
 *  - #AUTHOR#, "#TITLE#" in #BOOK_TITLE#, #EDITOR#, Ed. #PLACE#: #PUBLISHER#, #YEAR#, pp. #PAGE_NUMBERS#.
 *
 * @author brunomnsilva
 */
public class IEEEBookChapterCitation implements Citation {

    protected String author;
    protected String title;
    private String book_title;
    private String editor;
    private String place;
    private String publisher;
    private String page_numbers;
    private String year;

    public IEEEBookChapterCitation(String author, String title, String book_title, String editor,
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
        return String.format("%s, \"%s\" in %s, %s, Ed. %s: %s, %s, pp. %s.", author, title,
                book_title, editor, place, publisher, year, page_numbers);
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
