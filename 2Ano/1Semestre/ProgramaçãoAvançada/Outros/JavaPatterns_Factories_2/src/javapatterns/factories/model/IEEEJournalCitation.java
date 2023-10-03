package javapatterns.factories.model;

/**
 * IEEE bibliography style for a Journal:
 *
 *  - #AUTHOR#, "#TITLE#", #JOURNAL_TITLE#, vol. #VOLUME#, pp. #PAGE_NUMBERS#, #YEAR#.
 *
 * @author brunomnsilva
 */
public class IEEEJournalCitation implements Citation {

    protected String author;
    protected String title;
    private String journal_title;
    private String volume_issue;
    private String page_numbers;
    private String year;

    public IEEEJournalCitation(String author, String title, String journal_title, String volume_issue,
                               String page_numbers, String year) {
        this.author = author;
        this.title = title;
        this.journal_title = journal_title;
        this.volume_issue = volume_issue;
        this.page_numbers = page_numbers;
        this.year = year;
    }

    @Override
    public String toStringFormatted() {
        return String.format("%s, \"%s\", %s, vol. %s, pp. %s, %s.",
                author, title, journal_title, volume_issue, page_numbers, year);
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
