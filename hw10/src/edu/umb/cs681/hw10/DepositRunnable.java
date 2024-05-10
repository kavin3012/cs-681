package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicBoolean;

public class DepositRunnable implements Runnable {

    private ThreadSafeBankAccountOptimistic bankAccount;
    private AtomicBoolean done = new AtomicBoolean(false);

    public DepositRunnable(ThreadSafeBankAccountOptimistic bankAccount) {
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

            bankAccount.deposit(100);

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                continue;
            }
        }
    }
    
}