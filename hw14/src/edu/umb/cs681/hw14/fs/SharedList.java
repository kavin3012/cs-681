package edu.umb.cs681.hw14.fs;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class SharedList {

    private List<File> files = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();

    public void add(File file) {
        lock.lock();
        try {
            files.add(file);
        } finally {
            lock.unlock();
        }
    }
    
    public List<File> getFiles() {
        lock.lock();
        try {
            return files;
        } finally {
            lock.unlock();
        }
    }
}
