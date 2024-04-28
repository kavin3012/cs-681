package edu.umb.cs681.hw07.fs.util;

import edu.umb.cs681.hw07.fs.*;

import java.util.ArrayList;
import java.util.List;

public class FileSearchVisitor implements FSVisitor {

    private List<File> files;

    private String fileName;

    public FileSearchVisitor(String fileName) {
        this.fileName = fileName;
        files = new ArrayList<>();
    }

    public void visit(Link link) {
        return;
    }

    public void visit(Directory dir) {
        return;
    }

    public void visit(File file) {
        if (fileName.equals(file.getName())){
            files.add(file);
        }
    }

    public List<File> getFiles() {
        return files;
    }

}
