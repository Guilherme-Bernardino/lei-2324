Exercises Solutions
===

1. The method implementations are:

```java
public class APABookCitation implements Citation {
    //...
    @Override
    public String toStringFormatted() {
        return String.format("%s (%s). %s. %s.", author, year, title, publisher);
    }
}
```


```java
public class APABookChapterCitation implements Citation {
    //...
    @Override
    public String toStringFormatted() {
        return String.format("%s (%s). %s. In %s, (Eds), %s. (pp. %s), %s.", author, year,
                title, editor, book_title, page_numbers, year, publisher);
    }
}
```

```java
public class APAJournalCitation implements Citation {
    //...
    @Override
    public String toStringFormatted() {
        return String.format("%s (%s). \"%s.\" %s, %s, %s.",
                author, year, title, journal_title, volume_issue, page_numbers);
    }
}
```

2. The `IEEEBibliographyManager` class (*concrete product* for the abstract factory):

```java
public class IEEEBibliographyManager extends BibliographyManager {

    @Override
    public String output() {
        List<Citation> all = getAll();
        all.sort((c1, c2) -> c1.getAuthors().compareToIgnoreCase(c2.getAuthors()));

        StringBuilder sb = new StringBuilder();
        int number = 1;
        for(Citation c : all) {
            sb.append(String.format("[%d]", number++));
            sb.append("\t");
            sb.append(c.toStringFormatted());
            sb.append("\n");
        }
        return sb.toString();
    }
}
```

and return and instance of this class in the respective factory:

```java
public class IEEECitationStyleFactory implements CitationStyleFactory {
    
    //...

    @Override
    public BibliographyManager createManager() {
        return new IEEEBibliographyManager();
    }
}
```