/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import model.State;

import java.util.*;

/**
 * Class for observable wrappers of states.
 * Originally created by Steve Vickers, modified by Stanley Hitchcock
 */
public class StateModel extends Observable {
    private State current;
    private int qbits;
    
    public StateModel(State s){
        current = s;
        qbits = s.getQbits();
    }

    
    public void reset() {
        set(new State(getQbits(),current.getSemiprime(),current.getMessage()));
    }

    
    public void set(State s){
        current = s;
        qbits = s.getQbits();
        setChanged();
        notifyObservers(null);

    }
    
    public State getState(){
        return current;
    }
    
    public int getQbits(){
        return qbits;
    }

    public void setQbits(int n){
//        if (n > 0 && n <= 11) {
            set(new State(n,current.getSemiprime(),current.getMessage()));
        setChanged();
        notifyObservers(null);
//        }
    }

}
