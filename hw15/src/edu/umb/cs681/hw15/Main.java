package edu.umb.cs681.hw15;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		
		List<Thread> threads = new ArrayList<Thread>();
		List<DepositRunnable> depositRunnables = new ArrayList<DepositRunnable>();
		List<WithdrawRunnable> withdrawRunnables = new ArrayList<WithdrawRunnable>();

		for(int i = 0; i < 10; i++){
			DepositRunnable depositRunnable = new DepositRunnable(bankAccount);
			depositRunnables.add(depositRunnable);
			threads.add(new Thread(depositRunnable));
		}

		for(int i = 0; i < 10; i++){
			WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bankAccount);
			withdrawRunnables.add(withdrawRunnable);
			threads.add(new Thread(withdrawRunnable));
		}

		threads.forEach(Thread::start);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		depositRunnables.forEach(DepositRunnable::setDone);
		withdrawRunnables.forEach(WithdrawRunnable::setDone);
		threads.forEach(Thread::interrupt);
        
	}
}
