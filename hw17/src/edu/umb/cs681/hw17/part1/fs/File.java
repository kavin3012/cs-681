package edu.umb.cs681.hw17.part1.fs;

import java.time.LocalDateTime;

public class File extends FSElement {

    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    public boolean isDirectory() {
        return false;
    }

    public void accept(FSVisitor v) {
        rLock.lock();
        try {
            v.visit(this);
        } finally {
            rLock.unlock();
        }
    }

}
