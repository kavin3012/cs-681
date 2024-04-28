package edu.umb.cs681.hw07.fs;

public class RunnableFileSystem implements Runnable {

    private FileSystem fileSystem;

    public RunnableFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public void run() {
        System.out.println("RunnableFileSystem running...");
        FileSystem fileSystem2 = FileSystem.getFileSystem();
        System.out.println(fileSystem2.hashCode() == fileSystem.hashCode()); 
    }

    public static void main(String[] args) {
        FileSystem fileSystem = FileSystem.getFileSystem();
        
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new RunnableFileSystem(fileSystem));
            t.start();
        }

    }

}
