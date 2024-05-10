package edu.umb.cs681.hw17.part2.fs;

import java.util.concurrent.ConcurrentLinkedQueue;

import edu.umb.cs681.hw17.part2.fs.util.FileCrawlingVisitor;

public class FileCrawlingThread implements Runnable {
    private Directory root;
    private FileCrawlingVisitor visitor;
    private Thread thread;
    private ConcurrentLinkedQueue<File> sharedList;

    public FileCrawlingThread(Directory root, ConcurrentLinkedQueue<File> sharedList) {
        this.root = root;
        visitor = new FileCrawlingVisitor();
        this.sharedList = sharedList;
    }

    public void stop() {
        visitor.setDone();
        thread.interrupt();
        System.out.println("Files crawled :" + visitor.getFiles().size());
        visitor.getFiles().forEach(f -> sharedList.add(f));
    }

    @Override
    public void run() {
        thread = new Thread(() -> {
            root.accept(visitor);
        });
        thread.start();
    }

}
