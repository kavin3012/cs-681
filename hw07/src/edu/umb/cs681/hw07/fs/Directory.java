package edu.umb.cs681.hw07.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;


public class Directory extends FSElement {

    private LinkedList<FSElement> children;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        children = new LinkedList<>();
    }

    public int countChildren() {
        return children.size();
    }


    public void appendChild(FSElement child) {
        this.children.add(child);
        child.setParent(this);
    }

    public LinkedList<FSElement> getChildren() {
        return children;
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
        v.visit(this);
        for (FSElement child : children) {
            child.accept(v);
        }
    }

}
