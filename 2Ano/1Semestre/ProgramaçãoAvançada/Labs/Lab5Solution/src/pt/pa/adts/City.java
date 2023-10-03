package pt.pa.adts;

public class City<E> {
    private E element;

    public City(E element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return (String) element;
    }

    public E element() {
        return element;
    }
}
