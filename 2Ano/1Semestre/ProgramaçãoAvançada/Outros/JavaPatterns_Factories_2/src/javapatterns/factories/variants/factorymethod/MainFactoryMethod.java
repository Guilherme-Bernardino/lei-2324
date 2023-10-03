package javapatterns.factories.variants.factorymethod;

import javapatterns.factories.model.Citation;

import java.util.ArrayList;
import java.util.List;

/**
 * Ilustrates an application of the Factory Method pattern.
 *
 *
 * The client works with the interface of the created products.
 *
 * This pattern allows to change the concrete implementation of the factory. The client only works with
 * the interface of the factory.
 *
 * @author brunomnsilva
 */
public class MainFactoryMethod {

    public static void main(String[] args) {

        CitationStyleFactory factory = new IEEECitationStyleFactory();
        //CitationStyleFactory factory = new APACitationStyleFactory();

        List<Citation> bibliography = new ArrayList<>();

        Citation citation1 = factory.create("book", "D. Sarunyagate", "Lasers",
                "New York", "McGraw-Hill", "1996");

        Citation citation2 = factory.create("bookchapter", "G. O. Young",
                "Synthetic structure of industrial plastics", "Plastics, 2nd ed",
                "J. Peters", "New York", "McGraw-Hill", "15-64", "1996");

        Citation citation3 = factory.create("journal", "G. Liu, K. Y. Lee, and H. F. Jordan",
                "TDM and TWDM de Bruijn networks and shufflenets for optical communications",
                "EEE Trans. Comp.", "46", "695-701", "1997");

        bibliography.add( citation1 );
        bibliography.add( citation2 );
        bibliography.add( citation3 );

        for(Citation c : bibliography) {
            System.out.println(c.toStringFormatted());
        }
    }
}
