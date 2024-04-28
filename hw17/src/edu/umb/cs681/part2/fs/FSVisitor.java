package edu.umb.cs681.part2.fs;

public interface FSVisitor {

    public abstract void visit(Link link);
    public abstract void visit(Directory dir);
    public abstract void visit(File file);
    public abstract boolean isDone();
    public void setDone();
}
