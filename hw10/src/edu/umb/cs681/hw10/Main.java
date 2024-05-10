package edu.umb.cs681.hw10;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        ThreadSafeBankAccountOptimistic bankAccount = new ThreadSafeBankAccountOptimistic();
        
        List<Thread> threads = new ArrayList<>();
        List<DepositRunnable> dRunnables = new ArrayList<>();
        List<WithdrawRunnable> wRunnables = new ArrayList<>();
        List<ReadRunnable> rRunnables = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DepositRunnable dRunnable = new DepositRunnable(bankAccount);
            dRunnables.add(dRunnable);
            Thread dThread = new Thread(dRunnable);
            threads.add(dThread);

            WithdrawRunnable wRunnable = new WithdrawRunnable(bankAccount);
            wRunnables.add(wRunnable);
            Thread wThread = new Thread(wRunnable);
            threads.add(wThread);

            ReadRunnable rRunnable = new ReadRunnable(bankAccount);
            rRunnables.add(rRunnable);
            Thread rThread = new Thread(rRunnable);
            threads.add(rThread);
        }

        threads.forEach(t -> t.start());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dRunnables.forEach(d -> d.setDone(true));
        wRunnables.forEach(w -> w.setDone(true));
        rRunnables.forEach(r -> r.setDone(true));

        threads.forEach(t -> t.interrupt());

    }

}
