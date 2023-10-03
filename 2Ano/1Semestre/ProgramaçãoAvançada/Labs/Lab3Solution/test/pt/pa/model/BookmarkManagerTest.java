package pt.pa.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkManagerTest {
    /* take comments when BookmarkManager is implemented */
    private BookmarkManager manager;
    @BeforeEach
    void setUp() {
        manager= new BookmarkManager();
    }

    @Test
    void getTotalEntries_shouldReturnCorrectCounter() {
        assertEquals(0,manager.getTotalEntries());
        manager.addBookmarkFolder("bookmarks", "Redes Sociais");
        manager.addBookmarkFolder("bookmarks", "Diversos");
        assertEquals(2,manager.getTotalEntries());
        manager.addBookmarkEntry("diversos", "Gmail", "http://www.gmail.com");
        manager.addBookmarkEntry("diversos", "StackOverflow", "http://www.stackoverflow.com");
        assertEquals(4,manager.getTotalEntries());
    }

    @Test
    void addBookmarkEntry_shouldThrowException_whenFolderKeyIsInvalid() {
        assertThrows(BookmarkInvalidOperation.class, () -> manager.addBookmarkEntry("NoParentAtAll","Google","www.Google.com"));
        assertThrows(BookmarkInvalidOperation.class, () -> manager.addBookmarkEntry("","Google","www.Google.com"));
        assertThrows(BookmarkInvalidOperation.class, () -> manager.addBookmarkEntry(".","Google","www.Google.com"));
    }

    @Test
    void moveEntryToFolder_shouldThrowException_whenDestinationIsNotFolder() {
        assertThrows(BookmarkInvalidOperation.class, () -> manager.moveEntryToFolder("Redes sociais", "IPS"));
        assertThrows(BookmarkInvalidOperation.class, () -> manager.moveEntryToFolder("Redes sociais", "Record"));
        assertThrows(BookmarkInvalidOperation.class, () -> manager.moveEntryToFolder("Redes sociais", "A Bola"));
    }
}