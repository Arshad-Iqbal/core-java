package com.arshad.java.tutorials.multithreading;

public class DeadlockTest {

	public static void main(String[] args) throws InterruptedException {
		
		DeadLock dl = new DeadLock();
		
		Runnable r1 = () -> dl.a();
		
		Runnable r2 = () -> dl.b();
		
		Thread t1 = new Thread(r1);
		t1.start();
		
		Thread t2 = new Thread(r2);
		t2.start();
		
		t1.join();
		t2.join();
		
	}

}
