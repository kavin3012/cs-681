package edu.umb.cs681.hw12;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        Aircraft aircraft = new Aircraft(new Position(0, 0, 0));
        
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> System.out.println("Aircraft position: " + aircraft.getPosition().coordinate())));
            threads.add(new Thread(() -> aircraft.setPosition(new Position(Math.random(), Math.random(), Math.random()))));
        }

        threads.forEach(Thread::start);

    }

}
