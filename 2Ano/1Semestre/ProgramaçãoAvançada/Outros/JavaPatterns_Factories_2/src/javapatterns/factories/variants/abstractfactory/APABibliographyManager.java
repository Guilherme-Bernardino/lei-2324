package javapatterns.factories.variants.abstractfactory;

import javapatterns.factories.model.Citation;
import java.util.List;

public class APABibliographyManager extends BibliographyManager {

    @Override
    public String output() {
        List<Citation> all = getAll();
        all.sort((c1, c2) -> c1.getAuthors().compareToIgnoreCase(c2.getAuthors()));

        StringBuilder sb = new StringBuilder();
        for(Citation c : all) {
            sb.append(inTextCitation(c));
            sb.append("\t");
            sb.append(c.toStringFormatted());
            sb.append("\n");
        }
        return sb.toString();
    }

    private String inTextCitation(Citation c) {
        String authors = c.getAuthors();
        String[] list = authors.split(",");
        String auth = list[0];

        if(list.length > 1) auth += " et al.";

        return String.format("(%s, %s)", auth, c.getYear());
    }

}
