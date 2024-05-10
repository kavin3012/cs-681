package edu.umb.cs681.hw17.part1.fs;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

public class FileSystem {

    private ConcurrentLinkedQueue<Directory> dirs;
    private static AtomicReference<FileSystem> fileSystem = new AtomicReference<>(null);

    private FileSystem() {
        dirs = new ConcurrentLinkedQueue<>();
    }

    public static FileSystem getFileSystem() {
        
        if (fileSystem.get() == null) {
            fileSystem.set(new FileSystem());
        }
        return fileSystem.get();
        
    }
    
    public LinkedList<Directory> getRootDirs() {
        return new LinkedList<>(dirs);
    }
    
    public void appendRootDir(Directory root) {
        dirs.add(root);
    }

    public void clearRootDir() {
        dirs.clear();
    }
}
