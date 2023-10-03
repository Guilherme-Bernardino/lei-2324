Factory Design Patterns | Java
===

This repository illustrates a real-world application of the *factory design patterns*, namely:

- Simple Factory;
- Factory Method, and;
- Abstract Factory.

Each of one can be regarded an evolution of the prior.

## The Problem

Scientific publications contain a *bibliography* section/chapter. This refers to
other works that support the research or thesis. Each entry is called a **citation**.

There are different types of publications, e.g., books, chapters of books, journal articles. etc.

There are also different *citation styles*, e.g., IEEE, APA, Chicago, etc. This relates
to how the citations are formatted in bibliography. Publishers require
different citation styles. For example, publications on engineering, computer science and information technology may use 
the IEEE citation style. Natural sciences may use the APA style.

Below are examples of the IEEE citation style for three different types of publications:

Type | In-text citation | Formatted Citation
--- | --- | ---
Book | [1] | D. Sarunyagate, Ed., Lasers. New York: McGraw-Hill, 1996.
Chapter in book | [2]  | G. O. Young, "Synthetic structure of industrial plastics," in Plastics, 2nd ed., vol. 3, J. Peters, Ed. New York: McGraw-Hill, 1964, pp. 15-64.
Journal article | [4] | G. Liu, K. Y. Lee, and H. F. Jordan, "TDM and TWDM de Bruijn networks and shufflenets for optical communications," IEEE Trans. Comp., vol. 46, pp. 695-701, 1997.

... and for the APA style:

Type | In-text citation | Formatted Citation
--- | --- | ---
Book | (Sapolsky, 2017) | Sapolsky, R. M. (2017). Behave: The biology of humans at our best and worst. Penguin Books.
Chapter in book | (Dillard, 2020)  | Dillard, J. P. (2020). Currents in the study of persuasion. In M. B. Oliver, A. A. Raney, & J. Bryant (Eds.), Media effects: Advances in theory and research (4th ed., pp. 115–129). Routledge.
Journal article | (Weinstein, 2009) | Weinstein, J. (2009). “The market in Plato’s Republic.” Classical Philology, 104(4), 439-458. 

You can find more information in:

- https://pitt.libguides.com/citationhelp
- https://pitt.libguides.com/citationhelp/ieee
- https://pitt.libguides.com/citationhelp/apa7

## Citation style classes

See `javapatterns.factories.model` package. 


The package contains:

- `Citation` interface, supertype for all concrete citation styles.

- **IEEE** citation styles: 

    - `IEEEBookCitation`, `IEEEBookChapterCitation` and `IEEEJournalCitation`.

- **APA** citation styles: 

    - `APABookCitation`, `APABookChapterCitation` and `APAJournalCitation`.

Each implementation of `Citation` is responsible for producing the correct format of the citation style.

## Simple Factory

See `javapatterns.factories.variants.simplefactory` package.

The **simple factory** pattern describes a static method with a conditional
statement that creates the required concrete product by the caller:

```java
public class IEEECitationStyleFactory {
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
    public static Citation create(String type, String... args) {
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
}
```

Documentation is of utmost importance for the caller to be aware on how to use the factory.

The client then uses the provided static method to create the required products, e.g.,:

```java
public class MainSimpleFactory {

    public static void main(String[] args) {

        List<Citation> bibliography = new ArrayList<>();

        Citation citation1 = IEEECitationStyleFactory.create("book", "D. Sarunyagate", "Lasers",
                "New York", "McGraw-Hill", "1996");

        Citation citation2 = IEEECitationStyleFactory.create("bookchapter", "G. O. Young",
                "Synthetic structure of industrial plastics", "Plastics, 2nd ed",
                "J. Peters", "New York", "McGraw-Hill", "15-64", "1996");

        Citation citation3 = IEEECitationStyleFactory.create("journal", "G. Liu, K. Y. Lee, and H. F. Jordan",
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
```

Note that this code is bound to the `IEEECitationStyleFactory` and only allows for this family of citations. Hence, this pattern can be used when you only have one family of *concrete products*.

The output for this program is the following:

```
D. Sarunyagate, Lasers, New York: McGraw-Hill, 1996.
G. O. Young, "Synthetic structure of industrial plastics" in Plastics, 2nd ed, J. Peters, Ed. New York: McGraw-Hill, 1996, pp. 15-64.
G. Liu, K. Y. Lee, and H. F. Jordan, "TDM and TWDM de Bruijn networks and shufflenets for optical communications", EEE Trans. Comp., vol. 46, pp. 695-701, 1997.

```

## Factory Method

See `javapatterns.factories.variants.factorymethod` package.

This pattern is an evolution of the previous; it allows to "change"
the family of concrete products, e.g., between **IEEE** and **APA**.

This is achieved by making the *client* use a factory interface and provide different concrete implementations of the factory (one for each family): 

```java
public interface CitationStyleFactory {
    /* javadoc ommited (the same presented earlier) */
    Citation create(String type, String ... args);
}
```

Whithin this pattern, the factory for the **IEEE** citation style factory is an implementation of the `CitationStyleFactory` *interface*:

```java
public class IEEECitationStyleFactory implements CitationStyleFactory {
    @Override
    public Citation create(String type, String... args) {
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
}
```

Finally, the client only uses the interface of the factory and products; if the factory changes, the client code stays the same:

```java
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
```

This also allows to easily extend the existing code with aditional citation styles.

## Abstract Factory

The abstract factory is yet another evolution of the previous pattern. In this pattern the factory can produce "related" products. 

This is again achieved by the client using only *interfaces* (note that an abstract class can also be considered an "interface"). 

In this application we introduce the `BibiographyManager` abstract class, from which `IEEEBibliographyManager` and `APABibliographyManager` extend. Each of the later are responsible for the output of a set of formatted citations according to the **in-text citation** style, e.g., for IEEE:

```
[1] D. Sarunyagate, Lasers, New York: McGraw-Hill, 1996.
```

Hence, the **abstract factory** interface is changed to the following:

```java
public interface CitationStyleFactory {

    /* javadoc ommited (the same presented earlier) */
    Citation createCitation(String type, String ... args);

    BibliographyManager createManager();
}
```

Only the method names changed to make it clear what each method produces.

The usage of the factory can, hereby, be the following:

```java
public class MainAbstractFactory {

    public static void main(String[] args) {

        CitationStyleFactory factory = new IEEECitationStyleFactory();
        //CitationStyleFactory factory = new APACitationStyleFactory();

        Citation citation1 = factory.createCitation("book", "D. Sarunyagate", "Lasers",
                "New York", "McGraw-Hill", "1996");

        Citation citation2 = factory.createCitation("bookchapter", "G. O. Young",
                "Synthetic structure of industrial plastics", "Plastics, 2nd ed",
                "J. Peters", "New York", "McGraw-Hill", "15-64", "1996");

        Citation citation3 = factory.createCitation("journal", "G. Liu, K. Y. Lee, and H. F. Jordan",
                "TDM and TWDM de Bruijn networks and shufflenets for optical communications",
                "EEE Trans. Comp.", "46", "695-701", "1997");

        BibliographyManager bibliography = factory.createManager();

        bibliography.add( citation1 );
        bibliography.add( citation2 );
        bibliography.add( citation3 );

        System.out.println(bibliography.output());
    }
}
```

## Exercises

There is some missing code, that you should produce, namely:

1. The implementation of `toStringFormatted()` in `APABookCitation`, `APABookChapterCitation` and `APAJournalCitation`.Use the following citation formatting:

    - **Book**: `[author] ([year]). [title]. [publisher].`

    - **Book chapter**: `[author] ([year]). [title]. In [editor] (Eds.), [book_title]. (pp. [pages]). [publisher].`

    - **Journal**: `[author] ([year]). "[title]." [journal_title], [volume_issue], [pages].`

2. The implementation of `IEEECitationStyleFactory.getManager()` and the corresponding *concrete product*, i.e., `IEEEBibliographyManager`.

3. Run the examples by exchanging the instantiated factories.

Solutions are provided [here](SOLUTION.md).
