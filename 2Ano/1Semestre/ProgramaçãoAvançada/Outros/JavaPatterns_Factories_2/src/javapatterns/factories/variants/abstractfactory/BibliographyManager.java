package javapatterns.factories.variants.abstractfactory;

import javapatterns.factories.model.Citation;

import java.util.ArrayList;
import java.util.List;

public abstract class BibliographyManager {

    private final List<Citation> list;

    public BibliographyManager() {
        list = new ArrayList<>();
    }

    public void add(Citation citation) {
        list.add(citation);
    }

    public final List<Citation> getAll() {
        return new ArrayList<>( list );
    }

    public abstract String output();
}
