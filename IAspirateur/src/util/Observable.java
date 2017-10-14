package util;

import java.util.ArrayList;

/**
 * Observable
 * @author Quentin Dechaux & Aymeri Dumartheray
 */
public abstract class Observable extends Thread{

	private ArrayList<Observer> observers ;

    /**
     * Constructor
     */
    public Observable() {
        this.observers = new ArrayList<>() ;
    }

    /**
     * Add an observer
     * @param o
     */
    public void attach(Observer o) {
        observers.add(o) ;
    }

    /**
     * Remove an observer
     * @param o
     */
    public void detach(Observer o) {
        this.observers.remove(o) ;
    }

    /**
     * Notify all observers of a change
     */
    public void notifyAllObserver() {
    	for (Observer o : this.observers) {
            o.update() ;
        }
    }
}
