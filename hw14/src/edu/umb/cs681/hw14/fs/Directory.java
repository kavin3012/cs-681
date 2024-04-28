package edu.umb.cs681.hw14.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;


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
            child.setParent(this);
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
        LinkedList<Directory> dirs = new LinkedList<>();
        for (FSElement child : children) {
            rLock.lock();
            try {
                if (child instanceof Directory) {
                    dirs.add((Directory) child);
                }
            } finally {
                rLock.unlock();
            }
        }
        return dirs;
    }

    public LinkedList<File> getFiles() {
        LinkedList<File> files = new LinkedList<>();
        for (FSElement child : children) {
            rLock.lock();
            try {
                if (child instanceof File) {
                    files.add((File) child);
                }
            } finally {
                rLock.unlock();
            }
        }
        return files;
    }

    public int getTotalSize() {
        int size = 0;
        for (FSElement child : children) {
            rLock.lock();
            try {
                if (child instanceof Directory) {
                    size += ((Directory)child).getTotalSize();
                } else {
                    size += child.getSize();
                }
            } finally {
                rLock.unlock();
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
