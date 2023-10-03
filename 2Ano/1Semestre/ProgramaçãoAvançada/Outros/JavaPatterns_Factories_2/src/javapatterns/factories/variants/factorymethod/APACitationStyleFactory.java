package javapatterns.factories.variants.factorymethod;

import javapatterns.factories.model.APABookChapterCitation;
import javapatterns.factories.model.APAJournalCitation;
import javapatterns.factories.model.APABookCitation;
import javapatterns.factories.model.Citation;

/**
 * Concrete implementation of the APA Citation factory.
 *
 * Concrete products returned by this factory are all from the APA family.
 *
 * @author brunomnsilva
 */
public class APACitationStyleFactory implements CitationStyleFactory {
    @Override
    public Citation create(String type, String... args) {
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
}
