import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListOfElements<T extends Orderable> extends ArrayList<T> {
    public static final String HEADER= "\n |--------------------------------------------------------|"
                                     + "\n | Priority | Task name                      | Due date   |"
                                     + "\n |----------|--------------------------------|------------|";

    public ListOfElements(String HEADER) {
        super();
    }

    @Override
    public void sort(Comparator<? super T> c) {
        ArrayList<T> tempList = new ArrayList<>(this);
        Collections.sort(tempList, c);
        this.clear();
        this.addAll(tempList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER);
        for (Orderable o : this) {
            sb.append(o.toString());
        }
        return sb.toString();
    }

    public String showSortedByNaturalOrder(){
        sort(null);
        return this.toString();
    }

    public void sortByOrderableComparator(){
        OrderableComparator orderableComparator = new OrderableComparator();
        sort(orderableComparator);
    }

    public String showSortedByOrderableComparator(){
        sortByOrderableComparator();
        return this.toString();
    }
}
