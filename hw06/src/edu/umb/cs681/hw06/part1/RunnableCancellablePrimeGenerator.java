package edu.umb.cs681.hw06.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator{

    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone(){
        lock.lock();
        try {
            this.done = true;
        } finally {
            lock.unlock();
        } 
    }

    @Override
    public void generatePrimes(){
        for (long n = from; n <= to; n++) {
            lock.lock();
            try {
                if (this.done) {
                    System.out.println("Stopped generating prime numbers.");
                    break;
                }

                if( isPrime(n) ){ primes.add(n); }

            } finally {
                lock.unlock();
            }
        }
	}


    
}
