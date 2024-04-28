package edu.umb.cs681.part2.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Directory extends FSElement {

    private ConcurrentLinkedQueue<FSElement> children = new ConcurrentLinkedQueue<>();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime); 
    }

    public int countChildren() {
        return children.size(); 
    }


    public void appendChild(FSElement child) {
        children.add(child);

        wLock.lock();
        try {
            child.setParent(this);
        } finally {
            wLock.unlock();
        }
    }

    public LinkedList<FSElement> getChildren() {
        return new LinkedList<>(children);
    }

    
    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> dirs = new LinkedList<>();
        for (FSElement child : children) {
            if (child instanceof Directory) {
                dirs.add((Directory) child);
            }
        }
        return dirs;
    }

    public LinkedList<File> getFiles() {
        LinkedList<File> files = new LinkedList<>();
        for (FSElement child : children) {
            
            if (child instanceof File) {
                files.add((File) child);
            }
        }
        return files;
    }

    public int getTotalSize() {
        int size = 0;
        for (FSElement child : children) {
            
            if (child instanceof Directory) {
                size += ((Directory)child).getTotalSize();
            } else {
                size += child.getSize();
            }
        }
        return size;
    }

    public boolean isDirectory() {
        return true;
    }

    public void accept(FSVisitor v) {
        if (v.isDone()) {
            return;
        }
        wLock.lock();
        try {
            v.visit(this);
        } finally {
            wLock.unlock();
        }

        for (FSElement child : children) {
            if (v.isDone()) {
                return;
            }

            wLock.lock();
            try {
                child.accept(v);
            } finally {
                wLock.unlock();
            }
        }
    }

}
