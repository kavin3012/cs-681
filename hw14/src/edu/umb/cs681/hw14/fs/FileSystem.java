package edu.umb.cs681.hw14.fs;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileSystem {

    private LinkedList<Directory> dirs;
    private static AtomicReference<FileSystem> fileSystem = new AtomicReference<>(null);
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.WriteLock wLock = lock.writeLock();
    private ReentrantReadWriteLock.ReadLock rLock = lock.readLock();

    private FileSystem() {
        dirs = new LinkedList<>();
    }

    public static FileSystem getFileSystem() {
        
        if (fileSystem.get() == null) {
            fileSystem.set(new FileSystem());
        }
        return fileSystem.get();
        
    }
    
    public LinkedList<Directory> getRootDirs() {
        rLock.lock();
        try {
            return new LinkedList<>(dirs);
        } finally {
            rLock.unlock();
        }
    }
    
    public void appendRootDir(Directory root) {
        wLock.lock();
        try {
            dirs.add(root);
        } finally {
            wLock.unlock();
        }
    }

    public void clearRootDir() {
        wLock.lock();
        try {
            dirs.clear();
        } finally {
            wLock.unlock();
        }
    }
}
