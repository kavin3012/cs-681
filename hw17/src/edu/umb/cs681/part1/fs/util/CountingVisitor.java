package edu.umb.cs681.part1.fs.util;

import edu.umb.cs681.part1.fs.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CountingVisitor implements FSVisitor {
    
    private int dirNum = 0;
    private int fileNum = 0;
    private int linkNum = 0;
    private AtomicBoolean done = new AtomicBoolean(false);

    public void setDone(){
        done.set(true);
    }

    public boolean isDone(){
        return done.get();
    }


    public void visit(Link link) {
        if (done.get()){return;}
        linkNum++;
    }

    public void visit(Directory dir ){
        if (done.get()){return;}
        dirNum++;
    }

    public void visit(File file) {
        if (done.get()){return;}
        fileNum++;
    }

    public int getFileNum() {
        return fileNum;
    }

    public int getLinkNum() {
        return linkNum;
    }

    public int getDirNum() {
        return dirNum;
    }
    
}
