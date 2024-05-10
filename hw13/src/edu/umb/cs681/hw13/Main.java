package edu.umb.cs681.hw13;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<Thread> threads = new LinkedList<>();
        List<RunnableClass> runnables = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            runnables.add(new RunnableClass());
        }
        runnables.forEach(r -> threads.add(new Thread(r)));
        threads.forEach(t -> t.start());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        runnables.forEach(RunnableClass::setDone);
        threads.forEach(Thread::interrupt);

    }
}