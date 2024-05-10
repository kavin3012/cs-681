package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();
        FileSystem fs = FileSystem.getFileSystem();
        Directory root = new Directory(null, "root", 0, date);
        for (int i = 0; i < 10; i++) {
            root.appendChild(new File(root, "file", 10, date));
        }
        fs.appendRootDir(root);
        
        List<Thread> threads = new ArrayList<>();
        List<TotalSizeRunnable> runnables = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            runnables.add(new TotalSizeRunnable(root));
        }

        runnables.forEach(r -> threads.add(new Thread(r)));
        threads.forEach(Thread::start);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runnables.forEach(TotalSizeRunnable::setDone);
        threads.forEach(Thread::interrupt);

    }

}
