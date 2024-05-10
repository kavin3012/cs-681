package edu.umb.cs681.hw11.fs;

import java.util.concurrent.atomic.AtomicBoolean;

public class TotalSizeRunnable implements Runnable {
    
    private Directory root;
    private AtomicBoolean done = new AtomicBoolean(false);
    
    public TotalSizeRunnable(Directory root) {
        this.root = root;
    }

    public void setDone() {
        done.set(true);
    }
    
    public void run() {
        while (true) {
            if (done.get()) {
                break;
            }

            System.out.println("Total size: " + root.getTotalSize());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                continue;
            }
        }
    }

}
