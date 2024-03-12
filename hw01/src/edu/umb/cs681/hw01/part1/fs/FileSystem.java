package edu.umb.cs681.hw01.part1.fs;

import java.util.LinkedList;

public class FileSystem {

    private LinkedList<Directory> dirs;
    private static FileSystem fileSystem;

    private FileSystem() {
        dirs = new LinkedList<Directory>();
    }

    public static FileSystem getFileSystem() {
        if (fileSystem == null) {fileSystem = new FileSystem();}
        return fileSystem;
    }
    
    public LinkedList<Directory> getRootDirs() {
        return dirs;
    }
    
    public void appendRootDir(Directory root) {
        dirs.add(root);
    }

    public void clearRootDir() {
        dirs.clear();
    }
}
