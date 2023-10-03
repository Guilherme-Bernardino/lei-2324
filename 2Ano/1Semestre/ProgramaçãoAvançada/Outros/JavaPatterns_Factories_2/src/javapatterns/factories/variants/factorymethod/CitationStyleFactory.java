package javapatterns.factories.variants.factorymethod;

import javapatterns.factories.model.Citation;

/**
 * Citation factory.
 *
 * Implements the Factory Method pattern.
 *
 * The factory method declares a method that creates the type of object
 * required by the caller.
 *
 * The concrete product types are defined by the concrete implementations of this factory.
 *
 * @author brunomnsilva
 */
public interface CitationStyleFactory {

    /**
     * Allows the creation of a citation.
     * <p>
     * The supported types and required arguments are detailed bellow:
     * <ul>
     * <li> Book.
     *      <b>Type:</b> book
     *      <b>Args:</b> author, title, place, publisher, year
     * </li>
     * <li> Book Chapter.
     *      <b>Type:</b> bookchapter
     *      <b>Args:</b> author, title, book_title, editor, place, publisher, page_numbers, year
     * </li>
     * <li> Journal.
     *      <b>Type:</b> journal
     *      <b>Args:</b> author, title, journal_title, volume_issue, page_numbers, year
     * </li>
     * </ul>
     * </p>
     * @param type type of publication
     * @param args list of arguments according to publication type
     * @return a citation instance.
     */
    Citation create(String type, String ... args);

}
