package edu.umb.cs681.hw11.fs.util;

import edu.umb.cs681.hw11.fs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileCrawlingVisitor implements FSVisitor {

    private List<File> files = new ArrayList<>();

    public void visit(Link link) {
        return;
    }

    public void visit(Directory dir ){
        return;
    }

    public void visit(File file) {
        files.add(file);
    }

    public List<File> getFiles() {
        return files;
    }

    public Stream<File> files() {
        return files.stream();
    }
    
}
