package edu.umb.cs681.hw01.part2.fs;

import java.time.LocalDateTime;

public class Link extends FSElement {

    private FSElement target;

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
    }

    public FSElement getTarget() {
        return target;
    }

    public boolean isDirectory() {
        return false;
    }

    public void accept(FSVisitor v) {
        v.visit(this);
    }

}