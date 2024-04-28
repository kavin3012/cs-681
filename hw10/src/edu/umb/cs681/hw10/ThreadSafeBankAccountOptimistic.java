package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeBankAccountOptimistic implements BankAccount{
	private double balance = 0;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock rLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock wLock = lock.writeLock();

	public void deposit(double amount){
		wLock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (d): " + balance);
			balance = balance + amount;
			System.out.println(", New balance (d): " + balance);
		}finally{
			wLock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		wLock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (w): " + balance);
			balance = balance - amount;
			System.out.println(", New balance (w): " + balance);
		}finally{
			wLock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() { 
        rLock.lock();
        try {
            return this.balance;
        } finally {
            rLock.unlock();
        } 
    }
	
}
