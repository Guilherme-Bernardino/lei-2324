import java.util.List;

public interface Searchable {

    /**
     * Returns a list of Listable, with all the objects that contain a string to be searched in the available information.
     * @param searchText
     * @return listable object list
     */
    List<Listable> search(String searchText);

    /**
     * Returns a list of Listable, with all objects that occur in the searched location.
     * @param where
     * @return listable object list
     */
    List<Listable> searchWhere(String where);

    /**
     * Returns a list of Listable, with all objects that satisfy the condition of being free.
     * @return listable object list
     */
    List<Listable> searchFree();
}
