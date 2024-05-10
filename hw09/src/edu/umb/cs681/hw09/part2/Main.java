package edu.umb.cs681.hw09.part2;

import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer gen1 = new RunnableCancellablePrimeFactorizer(10000, 2, 100);
        RunnableCancellablePrimeFactorizer gen2 = new RunnableCancellablePrimeFactorizer(10000, 101, 200);
        RunnableCancellablePrimeFactorizer gen3 = new RunnableCancellablePrimeFactorizer(10000, 201, 300);
        RunnableCancellablePrimeFactorizer gen4 = new RunnableCancellablePrimeFactorizer(10000, 301, 400);
        RunnableCancellablePrimeFactorizer gen5 = new RunnableCancellablePrimeFactorizer(10000, 401, 500);

        Thread thread1 = new Thread(gen1);
        Thread thread2 = new Thread(gen2);
        Thread thread3 = new Thread(gen3);
        Thread thread4 = new Thread(gen4);
        Thread thread5 = new Thread(gen5);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        gen1.setDone();
        gen2.setDone();
        gen3.setDone();
        gen4.setDone();
        gen5.setDone();

        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
        thread4.interrupt();
        thread5.interrupt();

        List<Long> factors = gen1.getPrimeFactors();
        factors.addAll(gen2.getPrimeFactors());
        factors.addAll(gen3.getPrimeFactors());
        factors.addAll(gen4.getPrimeFactors());
        factors.addAll(gen5.getPrimeFactors());

        System.out.println("Prime factors found are: " + factors.size());
        
    }

}
