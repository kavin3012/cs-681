package edu.umb.cs681.part2.fs;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.umb.cs681.part2.fs.util.FileCrawlingVisitor;

public class RunnableFileSystem implements Runnable {

    private Directory root;
    private AtomicBoolean done = new AtomicBoolean(false);
    private FileCrawlingVisitor visitor = new FileCrawlingVisitor();
    private ConcurrentLinkedQueue<File> list;


    public void setDone() {
        done.set(true);
        visitor.setDone();
    }

    public RunnableFileSystem(Directory root, ConcurrentLinkedQueue<File> list) {
        this.root = root;
        this.list = list;
    }

    public void run() {
        while (true) {
            if (done.get()) {
                System.out.println("RunnableFileSystem done");
                System.out.println("Files visited: " + visitor.getFiles().size());

                visitor.getFiles().forEach(file -> list.add(file));

                return;
            }

            visitor = new FileCrawlingVisitor();
            System.out.println("Crawling.....");
            root.accept(visitor);
        }
    }

    public static void main(String[] args) {
        // repo directory
        Directory repo1 = new Directory(null, "repo", 0, LocalDateTime.now());
        Directory repo2 = new Directory(null, "repo", 0, LocalDateTime.now());
        Directory repo3 = new Directory(null, "repo", 0, LocalDateTime.now());

        for (int i = 0; i < 10000; i++) {
            repo1.appendChild(new File(repo1, "repo1 - file" + i, 0, LocalDateTime.now()));
            repo2.appendChild(new File(repo2, "repo2 - file" + i, 0, LocalDateTime.now()));
            repo3.appendChild(new File(repo3, "repo3 - file" + i, 0, LocalDateTime.now()));
        }

        FileSystem fileSystem = FileSystem.getFileSystem();

        ConcurrentLinkedQueue<File> list = new ConcurrentLinkedQueue<>();

        fileSystem.appendRootDir(repo1);
        fileSystem.appendRootDir(repo2);
        fileSystem.appendRootDir(repo3);

        RunnableFileSystem crawler1 = new RunnableFileSystem(fileSystem.getRootDirs().get(0) , list);
        RunnableFileSystem crawler2 = new RunnableFileSystem(fileSystem.getRootDirs().get(1) , list);
        RunnableFileSystem crawler3 = new RunnableFileSystem(fileSystem.getRootDirs().get(2) , list);

        Thread thread1 = new Thread(crawler1);
        Thread thread2 = new Thread(crawler2);
        Thread thread3 = new Thread(crawler3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        crawler1.setDone();
        crawler2.setDone();
        crawler3.setDone();

    }

}
