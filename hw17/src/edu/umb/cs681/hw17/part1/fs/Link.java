package edu.umb.cs681.hw17.part1.fs;

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

    public void setTarget(FSElement target) {
        wLock.lock();
        try {
            this.target = target;
        } finally {
            wLock.unlock();
        }
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