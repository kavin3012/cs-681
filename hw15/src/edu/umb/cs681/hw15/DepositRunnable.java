package edu.umb.cs681.hw15;

import java.util.concurrent.atomic.AtomicBoolean;

public class DepositRunnable implements Runnable {

    private ThreadSafeBankAccount2 bankAccount;
    private AtomicBoolean done = new AtomicBoolean(false);

    public DepositRunnable(ThreadSafeBankAccount2 bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setDone(){
        done.set(true);
    }

    public void run() {
        while (true){
            if (done.get()){
                System.out.println("DepositRunnable done");
                break;
            }
            bankAccount.deposit(100);
        }
    }

}
