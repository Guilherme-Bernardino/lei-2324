package javapatterns.factories.variants.abstractfactory;

import javapatterns.factories.model.Citation;
import javapatterns.factories.model.IEEEBookChapterCitation;
import javapatterns.factories.model.IEEEBookCitation;
import javapatterns.factories.model.IEEEJournalCitation;

/**
 * IEEE Citation Style Abstract Factory
 * @author brunomnsilva
 */
public class IEEECitationStyleFactory implements CitationStyleFactory {
    @Override
    public Citation createCitation(String type, String... args) {
        switch(type.toLowerCase()) {
            case "book":
                return new IEEEBookCitation(args[0], args[1], args[2], args[3], args[4]);
            case "bookchapter":
                return new IEEEBookChapterCitation(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
            case "journal":
                return new IEEEJournalCitation(args[0], args[1], args[2], args[3], args[4], args[5]);
            default:
                throw new UnsupportedOperationException("Type not supported: " + type);
        }
    }

    @Override
    public BibliographyManager createManager() {
        return new IEEBibliographyManager();
    }
}
