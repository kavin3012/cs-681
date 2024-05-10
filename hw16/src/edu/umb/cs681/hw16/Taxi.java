package edu.umb.cs681.hw16;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Taxi implements Runnable {

    private Dispatcher dispatcher;
    private Point location;
    private Point dest;
    private ReentrantLock lock = new ReentrantLock();
    private AtomicBoolean done = new AtomicBoolean(false);

    public Taxi(Dispatcher dispatcher, Point location, Point dest){
        this.dispatcher = dispatcher;
        this.location = location;
        this.dest = dest;
    }

    public void setDone(){
        done.set(true);
    }

    public void setLocation(Point location){
        lock.lock();
        try{
            this.location = location;
            if (!this.location.equals(dest)){
                return;
            }
        }  finally {
            lock.unlock();
        }
        dispatcher.notifyAvailableTaxi(this);
    }

    public void display(){
        System.out.println("Taxi location: " + location.x() + ", " + location.y());
    }

    public void run() {
        while(true) {
            if (done.get()){
                System.out.println("Taxi done");
                break;
            }

            display();
            setLocation(new Point((int)(Math.random()*100), (int)(Math.random()*100)));

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Taxi interrupted");
                break;
            }
        }
    }


        
    
}
