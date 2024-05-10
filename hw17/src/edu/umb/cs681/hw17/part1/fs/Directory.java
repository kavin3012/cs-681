package edu.umb.cs681.hw17.part1.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.umb.cs681.hw17.part1.fs.util.FileCrawlingVisitor;


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
        rLock.lock();
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
        if (v instanceof FileCrawlingVisitor && ((FileCrawlingVisitor) v).done()) {
            return;
        }
        
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
    }

}
