package edu.umb.cs681.hw14.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;

import edu.umb.cs681.hw14.fs.util.FileCrawlingVisitor;


public class Directory extends FSElement {

    private LinkedList<FSElement> children = new LinkedList<>();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime); 
    }

    public int countChildren() {
        rLock.lock();
        try {
            return children.size();
        } finally {
            rLock.unlock();
        }
    }


    public void appendChild(FSElement child) {
        wLock.lock();
        try {
            children.add(child);
        } finally {
            wLock.unlock();
        }
    }

    public LinkedList<FSElement> getChildren() {
        rLock.lock();
        try {
            return new LinkedList<>(children);
        } finally {
            rLock.unlock();
        }
    }

    
    public LinkedList<Directory> getSubDirectories() {
        rLock.lock();
        try {
            LinkedList<Directory> dirs = new LinkedList<>();
            for (FSElement child : children) {
                if (child instanceof Directory) {
                    dirs.add((Directory) child);
                }
            }
            return dirs;
        } finally {
            rLock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        try {
            rLock.lock();
            LinkedList<File> files = new LinkedList<>();
            for (FSElement child : children) {
                if (child instanceof File) {
                    files.add((File) child);
                }
            }
            return files;
        } finally {
            rLock.unlock();
        }
    }

    public int getTotalSize() {
        rLock.lock();
        try {
            int size = 0;
            for (FSElement child : children) {
                if (child instanceof Directory) {
                    size += ((Directory)child).getTotalSize();
                } else {
                    size += child.getSize();
                } 
            }
            return size;
        } finally {
            rLock.unlock();
        }
    }

    public boolean isDirectory() {
        return true;
    }

    public void accept(FSVisitor v) {
        rLock.lock();
        try {
            v.visit(this);
            for (FSElement child : children) {
                if (v instanceof FileCrawlingVisitor && ((FileCrawlingVisitor) v).done()) {
                    break;
                }
                child.accept(v);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    continue;
                }
            }
        } finally {
            rLock.unlock();
        }
    }

}
