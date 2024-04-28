package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableFileSystem implements Runnable {

    private FileSystem fileSystem;
    private AtomicBoolean done = new AtomicBoolean(false);
    
    public void setDone() {
        done.set(true);
    }

    public RunnableFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public void run() {
        while (true) {
            if (done.get()) {
                break;
            }

            System.out.println(fileSystem.getRootDirs().getFirst().getTotalSize());

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
                continue;
            }
        }
    }

    public static void main(String[] args) {
        // repo directory
        Directory repo = new Directory(null, "repo", 0, LocalDateTime.now());

        // create repo/src directory
        Directory repoSrc = new Directory(repo, "src", 0, LocalDateTime.now());
        // create repo/src/A.java file
        File repoSrcA = new File(repoSrc, "A.java", 100, LocalDateTime.now());
        // create repo/src/B.java file
        File repoSrcB = new File(repoSrc, "B.java", 200, LocalDateTime.now());

        // create repo/bin directory
        Directory repoBin = new Directory(repo, "bin", 0, LocalDateTime.now());
        // create repo/bin/A.class file
        File repoBinA = new File(repoBin, "A.class", 300, LocalDateTime.now());
        // create repo/bin/B.class file
        File repoBinB = new File(repoBin, "B.class", 400, LocalDateTime.now());

        // create repo/test directory
        Directory repoTest = new Directory(repo, "test", 0, LocalDateTime.now());

        // create repo/test/src directory
        Directory repoTestSrc = new Directory(repoTest, "src", 0, LocalDateTime.now());
        // create repo/test/src/A.java file
        File repoTestSrcA = new File(repoTestSrc, "A.java", 100, LocalDateTime.now());
        // create repo/test/src/B.java file
        File repoTestSrcB = new File(repoTestSrc, "B.java", 200, LocalDateTime.now());
        // create repo/test/bin directory
        Directory repoTestBin = new Directory(repoTest, "bin", 0, LocalDateTime.now());
        // create repo/test/bin/A.class file
        File repoTestBinA = new File(repoTestBin, "A.class", 300, LocalDateTime.now());
        // create repo/test/bin/B.class file
        File repoTestBinB = new File(repoTestBin, "B.class", 400, LocalDateTime.now());

        // create repo/readme.md file
        File repoReadme = new File(repo, "readme.md", 500, LocalDateTime.now());

        FileSystem fileSystem = FileSystem.getFileSystem();


        fileSystem.appendRootDir(repo);
        List<RunnableFileSystem> runnables = new LinkedList<>();
        List<Thread> threads = new LinkedList<>();
        
        for (int i = 0; i < 10; i++) {
            RunnableFileSystem rfs = new RunnableFileSystem(fileSystem);
            runnables.add(rfs);
            Thread t = new Thread(rfs);
            threads.add(t);
            t.start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        runnables.forEach(rfs -> rfs.setDone());
        threads.forEach(t -> t.interrupt());

    }

}
