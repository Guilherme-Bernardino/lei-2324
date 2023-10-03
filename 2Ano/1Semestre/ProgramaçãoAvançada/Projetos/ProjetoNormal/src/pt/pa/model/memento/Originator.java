package pt.pa.model.memento;


public interface Originator {

    public Memento createMemento();

    public void setMemento(Memento savedState);

}
