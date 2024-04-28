package edu.umb.cs681.hw13;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            threads.add(new Thread(new RunnableClass()));
        }

        threads.forEach(t -> t.start());

    }
}