package edu.umb.cs681.hw15;

import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().threadId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().threadId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().threadId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().threadId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().threadId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().threadId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() { 
		lock.lock();
		try {
			return this.balance;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		
		List<Thread> threads = new ArrayList<Thread>();
		List<DepositRunnable> depositRunnables = new ArrayList<DepositRunnable>();
		List<WithdrawRunnable> withdrawRunnables = new ArrayList<WithdrawRunnable>();

		for(int i = 0; i < 5; i++){
			DepositRunnable depositRunnable = new DepositRunnable(bankAccount);
			depositRunnables.add(depositRunnable);
			threads.add(new Thread(depositRunnable));
		}

		for(int i = 0; i < 5; i++){
			WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bankAccount);
			withdrawRunnables.add(withdrawRunnable);
			threads.add(new Thread(withdrawRunnable));
		}

		threads.forEach((t) -> t.start());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}

		depositRunnables.forEach((dr) -> dr.setDone());
		withdrawRunnables.forEach((wr) -> wr.setDone());

	}
}
