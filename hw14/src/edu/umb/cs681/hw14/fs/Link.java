package edu.umb.cs681.hw14.fs;

import java.time.LocalDateTime;

public class Link extends FSElement {

    private FSElement target;

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
    }

    public FSElement getTarget() {
        rLock.lock();
        try {
            return target;
        } finally {
            rLock.unlock();
        }
    }

    public boolean isDirectory() {
        return false;
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
    }

}