package edu.umb.cs681.hw17.part1.fs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("HW17 Part 1");
        LocalDateTime date = LocalDateTime.now();
        SharedList sharedList = new SharedList();
        FileSystem fs = FileSystem.getFileSystem();
        for (int i = 0; i < 3; i++) {
            Directory dir = new Directory(null, "dir", 0, date);
            for (int j = 0; j < 1000; j++) {
                dir.appendChild(new File(dir, "file", 10, date));
            }
            System.out.println("Directory " + i + " has " + dir.getChildren().size() + " files");
            fs.appendRootDir(dir);
        }
        
        List<Thread> threads = new ArrayList<>();
        List<FileCrawlingThread> runnables = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            runnables.add(new FileCrawlingThread(fs.getRootDirs().get(i), sharedList));
        }

        runnables.forEach(r -> threads.add(new Thread(r)));
        threads.forEach(Thread::start);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            
        }

        runnables.forEach(FileCrawlingThread::stop);
        System.out.println("Total files collected: " + sharedList.getFiles().size());

    }

}
