package com.arshad.java.tutorials.multithreading;

public class LongWrapper {

	private final Object key = new Object();

	private long l;

	public LongWrapper(long l) {
		this.l = l;
	}
	
	/**
	 * 
	 * Need to observe the getValue(GV) and incrementValue(IV) closely.
	 * This code is buggy, Though it runs correctly in dev environment 
	 * here, the write is synchronize GV but the read GV is neither synchronized nor volatile, So it does not 
	 *       offer the guarantee to return the last value written in the IV method.
	 * If we want to make this correct, We need to also synchronize the block of code in GV() like below,
	 * 
	 * synchronized(key){
	 * 		return l;
	 * }
	 * conclusion: The happens-before link was missing in the previous example.
	 *   
	 * @return
	 */
	public long getValue() {
		return l;
	}

	public void incrementValue() {
		synchronized (key) {
			l = l + 1;
		}
	}

	
	//Testing LongWrapper for race condition
	
	public static void main(String[] args) throws InterruptedException {
		LongWrapper longWrapper = new LongWrapper(0L);
		
		Runnable r = () -> {
			for(int i = 0; i < 1_000; i++) {
				longWrapper.incrementValue();
			}
		};
		
		Thread[] threads = new Thread[1_000]; 
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(r);
			threads[i].start();
		}
		/*Thread t = new Thread(r);
		t.start();
		
		t.join(); // Ensures the code after this line will be executed 
				  // after the Thread t execution finishes.
		*/
		
		for(int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		
		System.out.println("Value = "+longWrapper.getValue());
	}
}
