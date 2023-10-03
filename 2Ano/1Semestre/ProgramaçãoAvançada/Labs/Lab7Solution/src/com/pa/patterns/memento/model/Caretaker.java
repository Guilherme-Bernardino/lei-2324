/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.patterns.memento.model;

import java.util.*;

public class Caretaker {

    //Nível 5
    //private Stack<Memento> objMementos;

    private Memento memento;

    private Originator originator;

    public Caretaker(Originator originator) {
        this.originator = originator;
        //Nível 5
        //objMementos = new Stack();
    }

    public void saveState() {
        this.memento= originator.createMemento();
        /*Nível 5
        originator.createMemento();
        objMementos.add(originator.createMemento());*/

    }

    public void restoreState() throws NoMementoException {
        /*Nível 5
        if (objMementos.empty()) {
            throw new NoMementoException();
        }
        originator.setMemento(objMementos.pop());*/
        originator.setMemento(this.memento);
    }

}
