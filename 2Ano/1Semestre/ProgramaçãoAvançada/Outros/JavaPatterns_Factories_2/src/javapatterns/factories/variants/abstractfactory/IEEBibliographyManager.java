package javapatterns.factories.variants.abstractfactory;

import javapatterns.factories.model.Citation;

public class IEEBibliographyManager extends BibliographyManager {
    @Override
    public String output() {
        String out = "";
        int id = 1;
        for (Citation citation : getAll()) {
            out += String.format("[%d] %s \n", id++, citation.toStringFormatted());
        }
        return out;
    }
}
