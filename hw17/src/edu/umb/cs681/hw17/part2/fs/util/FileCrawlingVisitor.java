package edu.umb.cs681.hw17.part2.fs.util;

import edu.umb.cs681.hw17.part2.fs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class FileCrawlingVisitor implements FSVisitor {

    private List<File> files = new ArrayList<>();
    private AtomicBoolean done = new AtomicBoolean(false);

    public void setDone() {
        done.set(true);
    }

    public boolean done() {
        return done.get();
    }

    public void visit(Link link) {
        return;
    }

    public void visit(Directory dir ){
        return;
    }

    public void visit(File file) {
        if (done.get()) {
            return;
        }
        files.add(file);
    }

    public List<File> getFiles() {
        return files;
    }

    public Stream<File> files() {
        return files.stream();
    }
    
}
