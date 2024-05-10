package edu.umb.cs681.hw06.part1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(1, 1000);
		RunnableCancellablePrimeGenerator gen2 = new RunnableCancellablePrimeGenerator(1001, 2000);
        RunnableCancellablePrimeGenerator gen3 = new RunnableCancellablePrimeGenerator(2001, 3000);
        RunnableCancellablePrimeGenerator gen4 = new RunnableCancellablePrimeGenerator(3001, 4000);
        RunnableCancellablePrimeGenerator gen5 = new RunnableCancellablePrimeGenerator(4001, 5000);

		Thread t1 = new Thread(gen1);
		Thread t2 = new Thread(gen2);
        Thread t3 = new Thread(gen3);
        Thread t4 = new Thread(gen4);
        Thread t5 = new Thread(gen5);
		t1.start();
		t2.start();
        t3.start();
        t4.start();
        t5.start();
        // wait for 2 second before cancelling
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            
        }
        gen1.setDone();
        gen2.setDone();
        gen3.setDone();
        gen4.setDone();
        gen5.setDone();
        
		List<Long> primes = new ArrayList<>();
        primes.addAll(gen1.getPrimes());
        primes.addAll(gen2.getPrimes());
        primes.addAll(gen3.getPrimes());
        primes.addAll(gen4.getPrimes());
        primes.addAll(gen5.getPrimes());
        
        System.out.println("Primes found are: " + primes.size());
    }
    
}
