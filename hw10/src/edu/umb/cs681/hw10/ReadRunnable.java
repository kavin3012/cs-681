package edu.umb.cs681.hw10;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReadRunnable implements Runnable {

    private ThreadSafeBankAccountOptimistic bankAccount;
    private AtomicBoolean done = new AtomicBoolean(false);

    public ReadRunnable(ThreadSafeBankAccountOptimistic bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setDone(boolean done) {
        this.done.set(done);
    }

    @Override
    public void run() {
        while (true) {
            if (done.get()) {
                break;
            }
            System.out.println("Balance (r): " + bankAccount.getBalance());
        }
    }

    public static void main(String[] args) {
        ThreadSafeBankAccountOptimistic bankAccount = new ThreadSafeBankAccountOptimistic();
        bankAccount.deposit(10000);

        List<Thread> threads = new ArrayList<>();
        List<ReadRunnable> runnables = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            ReadRunnable runnable = new ReadRunnable(bankAccount);
            runnables.add(runnable);
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (ReadRunnable runnable : runnables) {
            runnable.setDone(true);
        }

        for (Thread thread : threads) {
            thread.interrupt();
        }

        
    }
    
}
