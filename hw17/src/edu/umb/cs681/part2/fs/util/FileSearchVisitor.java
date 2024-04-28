package edu.umb.cs681.part2.fs.util;

import edu.umb.cs681.part2.fs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileSearchVisitor implements FSVisitor {

    private ConcurrentLinkedQueue<File> files;
    private String fileName;
    private AtomicBoolean done = new AtomicBoolean(false);

    public FileSearchVisitor(String fileName) {
        this.fileName = fileName;
        files = new ConcurrentLinkedQueue<>();
    }

    public void setDone(){
        done.set(true);
    }

    public boolean isDone(){
        return done.get();
    }

    public void visit(Link link) {
        return;
    }

    public void visit(Directory dir) {
        return;
    }

    public void visit(File file) {
        if (done.get()){return;}
        
        if (fileName.equals(file.getName())){
            files.add(file);
        }
    }

    public List<File> getFiles() {
        return new ArrayList<>(files);
    }

}
