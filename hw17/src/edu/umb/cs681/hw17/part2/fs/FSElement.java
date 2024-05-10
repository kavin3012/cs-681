package edu.umb.cs681.hw17.part2.fs;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class FSElement {
    protected Directory parent;
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    protected ReentrantReadWriteLock.WriteLock wLock = lock.writeLock();
    protected ReentrantReadWriteLock.ReadLock rLock = lock.readLock();

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public String getName() {
        rLock.lock();
        try {
            return this.name;
        } finally {
            rLock.unlock();
        }
    }

    public void setName(String newName) {
        wLock.lock();
        try {
            this.name = newName;
        } finally {
            wLock.unlock();
        }
    }
    
    public int getSize() {
        rLock.lock();
        try {
            return this.size;
        } finally {
            rLock.unlock();
        }
    }

    public void setSize(int newSize) {
        wLock.lock();
        try {
            this.size = newSize;
        } finally {
            wLock.unlock();
        }
    }
    
    public LocalDateTime getCreationTime() {
        rLock.lock();
        try {
            return this.creationTime;
        } finally {
            rLock.unlock();
        }
    }

    public void setCreationTime(LocalDateTime newCreationTime) {
        wLock.lock();
        try {
            this.creationTime = newCreationTime;
        } finally {
            wLock.unlock();
        }
    }
    
    public Directory getParent() {
        rLock.lock();
        try {
            return this.parent;
        } finally {
            rLock.unlock();
        }
    }

    public void setParent(Directory newParent) {
        wLock.lock();
        try {
            this.parent = newParent;
        } finally {
            wLock.unlock();
        }
    }
    
    public abstract boolean isDirectory();

    public abstract void accept(FSVisitor v);

}
