package javapatterns.factories.variants.abstractfactory;

import javapatterns.factories.model.APABookChapterCitation;
import javapatterns.factories.model.APABookCitation;
import javapatterns.factories.model.APAJournalCitation;
import javapatterns.factories.model.Citation;

/**
 * APA Citation Style Abstract Factory
 * @author brunomnsilva
 */
public class APACitationStyleFactory implements CitationStyleFactory {
    @Override
    public Citation createCitation(String type, String... args) {
        switch(type.toLowerCase()) {
            case "book":
                return new APABookCitation(args[0], args[1], args[2], args[3], args[4]);
            case "bookchapter":
                return new APABookChapterCitation(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
            case "journal":
                return new APAJournalCitation(args[0], args[1], args[2], args[3], args[4], args[5]);
            default:
                throw new UnsupportedOperationException("Type not supported: " + type);
        }
    }

    @Override
    public BibliographyManager createManager() {
        return new APABibliographyManager();
    }
}
