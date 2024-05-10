package edu.umb.cs681.hw07.fs;

public class Main {
    public static void main(String[] args) {
        FileSystem fileSystem = FileSystem.getFileSystem();
        
        for (int i = 0; i < 15; i++) {
            Thread t = new Thread(() -> {
                FileSystem fileSystem2 = FileSystem.getFileSystem();
                System.out.println("In Thread " + String.valueOf(Thread.currentThread().getId()) + "  both same filesystem instance are :" + (fileSystem2.hashCode() == fileSystem.hashCode() ? "same" : "different"));
            });
            t.start();
        }

    }
}
