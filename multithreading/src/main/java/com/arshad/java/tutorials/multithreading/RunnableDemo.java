package com.arshad.java.tutorials.multithreading;

public class RunnableDemo {

	public static void main(String[] args) {
		Runnable runnable = () -> {
			System.out.println("I am running in - "+ Thread.currentThread().getName());
		};
		
		Thread t = new Thread(runnable);
		t.setName("First Thread");
		t.start();
		
	}

}
