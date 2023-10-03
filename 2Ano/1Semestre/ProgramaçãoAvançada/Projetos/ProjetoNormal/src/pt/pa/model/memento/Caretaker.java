package pt.pa.model.memento;

import pt.pa.model.UndoStateType;

import java.util.Stack;

public class Caretaker {


    private Stack<Memento> mementos;

    private Originator originator;

    public Caretaker (Originator originator){
        this.originator = originator;
        this.mementos = new Stack<>();
    }

    public void saveState(){
        Memento memento = this.originator.createMemento();
        this.mementos.push(memento);
    }

    public void restoreState() throws NoMementoException {
        if(mementos.isEmpty()) throw new NoMementoException();
        Memento memento = mementos.pop();
        originator.setMemento(memento);
    }

}
