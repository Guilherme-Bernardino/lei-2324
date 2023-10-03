package javapatterns.factories.model;

/**
 * Interface required for a bibliography citation.
 *
 * @author brunomnsilva
 */
public interface Citation {

    /**
     * Formats the citation according to the citation style.
     * @return the formatted citation
     */
    String toStringFormatted();

    /**
     * Returns the authors of the publication.
     * @return publication's authors.
     */
    String getAuthors();

    /**
     * Returns the year of the publication.
     * @return publication year.
     */
    String getYear();
}

