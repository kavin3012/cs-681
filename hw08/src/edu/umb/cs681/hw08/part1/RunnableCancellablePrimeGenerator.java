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

    public static void main(String[] args) {
		RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(1, 100);
		RunnableCancellablePrimeGenerator gen2 = new RunnableCancellablePrimeGenerator(101, 200);
		Thread t1 = new Thread(gen1);
		Thread t2 = new Thread(gen2);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {}

		gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		gen2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		
		long primeNum = gen1.getPrimes().size() + gen2.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
        

	}

    
}
