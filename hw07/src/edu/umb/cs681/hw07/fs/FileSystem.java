package edu.umb.cs681.hw07.fs;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {

    private LinkedList<Directory> dirs;
    private static FileSystem fileSystem;
    private static ReentrantLock lock = new ReentrantLock();

    private FileSystem() {
        dirs = new LinkedList<>();
    }

    public static FileSystem getFileSystem() {
        lock.lock();
        try {
            if (fileSystem == null) {fileSystem = new FileSystem();}
            return fileSystem;
        } finally {
            lock.unlock();
        }
    }
    
    public LinkedList<Directory> getRootDirs() {
        return dirs;
    }
    
    public void appendRootDir(Directory root) {
        dirs.add(root);
    }

    public void clearRootDir() {
        dirs.clear();
    }
}
