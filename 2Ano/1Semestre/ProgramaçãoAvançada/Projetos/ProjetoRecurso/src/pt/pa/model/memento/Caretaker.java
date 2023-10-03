package pt.pa.model.memento;

import java.util.Stack;

/**
 * Caretaker class delegates and keeps account of the Mementos of said object.
 * Saves a new instance of a saved state and sets to previous.
 *
 * @author Guilherme B. 202001870
 */
public class Caretaker {

    private Stack<Memento> mementos;

    private Originator originator;

    public Caretaker (Originator originator){
        this.originator = originator;
        this.mementos = new Stack<>();
    }

    /**
     * Save state, creates new memento and pushes it into the stack.
     */
    public void saveState(){
        Memento memento = this.originator.createMemento();
        this.mementos.push(memento);
    }

    /**
     * Restore state, pops the top memento and sets the originator to previous memento.
     * @throws NoMementoException
     */
    public void restoreState() throws NoMementoException {
        if(mementos.isEmpty()) throw new NoMementoException();
        Memento memento = mementos.pop();
        originator.setMemento(memento);
    }

    public Memento getMemento() {
        return mementos.get(0);
    }
}
