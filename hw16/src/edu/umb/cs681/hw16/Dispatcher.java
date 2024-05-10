package edu.umb.cs681.hw16;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

public class Dispatcher {
    
    private HashSet<Taxi> availableTaxis = new HashSet<Taxi>();
    private ReentrantLock lock = new ReentrantLock();

    public void notifyAvailableTaxi(Taxi taxi){
        lock.lock();
        try{
            availableTaxis.add(taxi);
        } finally {
            lock.unlock();
        }
    }

    public void displayAvailableTaxis(){
        HashSet<Taxi> availableTaxisLocal;
        
        lock.lock();
        try{
            availableTaxisLocal = new HashSet<Taxi>(availableTaxis);
        } finally {
            lock.unlock();
        }

        availableTaxisLocal.forEach((taxi) -> taxi.display());
    }

}
