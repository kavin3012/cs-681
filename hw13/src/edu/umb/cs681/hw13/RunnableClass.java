package edu.umb.cs681.hw13;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableClass implements Runnable {

    private AtomicBoolean done = new AtomicBoolean(false);

    public void setDone() {
        done.set(true);
    }

    public void run() {
        while ( true ) {
            if (done.get()) {
                break;
            }

            OneDrivePPTWithoutRaceConditions ppt = OneDrivePPTWithoutRaceConditions.getInstance();
            System.out.println(ppt.readFile());
            ppt.writeToFile("random ");
            System.out.println(ppt.readFile());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                continue;
            }
            
        }
        

    }
}