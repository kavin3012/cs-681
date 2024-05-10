package edu.umb.cs681.hw08.part1;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator{

    private volatile boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone(){
        done = true; 
    }

    @Override
    public void generatePrimes(){
		for (long n = from; n <= to; n++) {
            if (done) {break;}

            lock.lock();
            try {

                if( isPrime(n) ){ primes.add(n); }

            } finally {
                lock.unlock();
            }
			
        }
	}
    
}
