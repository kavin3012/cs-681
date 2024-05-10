package edu.umb.cs681.hw13;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class OneDrivePPTWithoutRaceConditions {

    private ReentrantLock lock;
    private String content;
    private static AtomicReference<OneDrivePPTWithoutRaceConditions> instance = new AtomicReference<>();

    public OneDrivePPTWithoutRaceConditions() {
        this.content = "";
        this.lock = new ReentrantLock();
    }

    public static OneDrivePPTWithoutRaceConditions getInstance() {
        instance.compareAndSet(null, new OneDrivePPTWithoutRaceConditions());
        return instance.get();
    }

    public String readFile() {
        lock.lock();
        try {
            return content;
        } finally {
            lock.unlock();
        }
    }

    public void writeToFile(String s) {
        lock.lock();
        try {
            this.content = this.content + s;
        } finally {
            lock.unlock();
        }
    }
    
}