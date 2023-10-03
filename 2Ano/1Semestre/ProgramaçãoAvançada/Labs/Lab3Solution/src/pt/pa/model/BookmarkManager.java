package pt.pa.model;

import pt.pa.adts.Position;
import pt.pa.adts.TreeLinked;

public class BookmarkManager {
    private TreeLinked<BookmarkEntry> bookmarks;

    public BookmarkManager() {
        bookmarks = new TreeLinked<>(new BookmarkEntry("Bookmarks"));
    }

    /**
     * Devolve a posição na árvore do elemento com a chave especificada ou null, caso contrário.
     * @param key a procurar
     * @return a posição na árvore do elemento com a chave especificada, null caso contrário.
     */
    private Position<BookmarkEntry> find(String key) {
        key = key.trim();
        Iterable<Position<BookmarkEntry>> positions = bookmarks.positions();
        for(Position<BookmarkEntry> position: positions)
            if(position.element().getKey().equalsIgnoreCase(key))
                return position;
        return null;
    }

    /**
     *
     * @param key a procurar
     * @return valor lógico que indica se existe algum elemento com a chave especificada
     */
    private boolean exists(String key) {
        return (find(key) != null);
    }

    /**
     * Recebe a chave da pasta ascendente (keyParent) e da nova (keyFolder), adicionando a última como descendente.
     * Caso a pasta ascendente não exista, lança a exceção com uma mensagem descritiva;
     * Caso a chave keyFolder já exista, também lança exceção com mensagem apropriada.
     *
     * @param keyParent chave da pasta ascendente
     * @param keyFolder chave da nova pasta
     * @throws BookmarkInvalidOperation
     */
    public void addBookmarkFolder(String keyParent, String keyFolder) throws BookmarkInvalidOperation {
        if (!exists(keyParent))
            throw new BookmarkInvalidOperation("No Parent with key " + keyParent);

        if (exists(keyFolder))
            throw new BookmarkInvalidOperation("Folder " + keyFolder + " already exists.");

        Position<BookmarkEntry> parentPosition = find(keyParent);

        // create the new Position
        BookmarkEntry newNode = new BookmarkEntry(keyFolder);
        bookmarks.insert(parentPosition, newNode);
     }

    /**
     * Adiciona um novo bookmark efetivo (não é uma pasta)
     * @param keyParent chave da pasta ascendente
     * @param keyEntry chave da nova pasta
     * @param url URL de destino
     * @throws BookmarkInvalidOperation
     */
    public void addBookmarkEntry(String keyParent, String keyEntry, String url) throws BookmarkInvalidOperation {
        if (!exists(keyParent))
            throw new BookmarkInvalidOperation("No Parent with key " + keyParent);

        if (exists(keyEntry))
            throw new BookmarkInvalidOperation("Bookmark " + keyEntry + " already exists.");

        Position<BookmarkEntry> parentPosition = find(keyParent);
        BookmarkEntry newNode = new BookmarkEntry(keyEntry, url);
        bookmarks.insert(parentPosition, newNode);
    }

    /**
     *
     * @return o número total de pastas. (Bookmarks não entra na contagem)
     */
	public int getTotalFolders() {
        int res = 0;
        for (BookmarkEntry bookmark : bookmarks.elements())
            if (bookmark.isFolder())
                res++;
        return res-1;
    }

    /**
     *
     * @return o número total de links.
     */
    public int getTotalLinks(){
        int res = 0;
        for (BookmarkEntry bookmark : bookmarks.elements())
            if (!bookmark.isFolder())
                res++;
        return res;
    }

    /**
     *
     * @return o número total de folders+links.
     */
    public int getTotalEntries(){
        //Bastaria o seguinte:
        //return getTotalFolders()+getTotalLinks();

        //No entanto, seria menos eficiente por termos de passar pela árvore duas vezes.
        int res = 0;
        for (BookmarkEntry bookmark : bookmarks.elements())
            res++;
        
        return res-1;
    }

    /**
     *
     * @param keyEntry do link a procurar
     * @return a key do Folder ascendente do bookmark do tipo link identificado como keyEntry
     * @throws BookmarkInvalidOperation se a keyEntry não existir ou se não é um link (é folder).
     */
   public String getParentFolder(String keyEntry) throws BookmarkInvalidOperation {
    Position<BookmarkEntry> pos = find(keyEntry);
    if (pos == null)
        throw new BookmarkInvalidOperation("No such key " + keyEntry);
    if (pos.element().isFolder())
        throw new BookmarkInvalidOperation("Entry " + keyEntry + " is not a link!");

    return bookmarks.parent(pos).element().getKey();
   }

    /**
     * Move um nó da árvore e coloca-o como descendente de outro nó
     * @param entry nó a mover
     * @param folder destino
     * @throws BookmarkInvalidOperation
     */
   public void moveEntryToFolder(String entry, String folder) throws BookmarkInvalidOperation {
        if(!exists(entry)) // Origem tem de existir
            throw new BookmarkInvalidOperation("Entry " + entry + " does not exist.");

        if(!exists(folder)) // Destino tem de existir
            throw new BookmarkInvalidOperation("Folder " + folder + " does not exist.");

        Position<BookmarkEntry> positionEntry = find(entry);
        Position<BookmarkEntry> positionDestinationFolder = find(folder);
        if (!positionDestinationFolder.element().isFolder()) // Destino tem de ser um Folder, não um link
            throw new BookmarkInvalidOperation("Destination " + folder + " should be a folder not a bookmark link.");

        if (bookmarks.isAncestor(positionDestinationFolder,positionEntry))
           throw new BookmarkInvalidOperation("Destination " + folder + " cannot be a descendant bookmark.");

       bookmarks.move(positionEntry, positionDestinationFolder);
   }

    @Override
    public String toString() {
        return "BookmarkManager " +
                "size= " + bookmarks.size() + " " +
                "{\n" + bookmarks + "}";
    }
}
