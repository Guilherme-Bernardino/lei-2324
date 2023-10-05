import java.util.Comparator;

public class OrderableComparator implements Comparator<Orderable> {
    @Override
    public int compare(Orderable o1, Orderable o2) {
        if (o1.getPriority() < o2.getPriority()) {
            return -1;
        } else if (o1.getPriority() > o2.getPriority()) {
            return 1;
        } else {
            return o1.getDate().compareTo(o2.getDate());
        }
    }
}
