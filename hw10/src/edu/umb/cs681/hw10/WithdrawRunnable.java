package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicBoolean;

public class WithdrawRunnable implements Runnable {

    private ThreadSafeBankAccountOptimistic bankAccount;
    private AtomicBoolean done = new AtomicBoolean(false);

    public WithdrawRunnable(ThreadSafeBankAccountOptimistic bankAccount) {
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

            bankAccount.withdraw(100);

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                continue;
            }
        }
    }
    
}
