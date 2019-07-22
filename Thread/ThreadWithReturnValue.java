package SP_Test_Thread;

import java.util.concurrent.TimeUnit;

public class ThreadWithReturnValue {

	public static void main(String argc[]) throws InterruptedException {
		System.out.println("Main thread starts here...");

		MyThreadTask task1 = new MyThreadTask("AB");
		MyThreadTask task2 = new MyThreadTask("CD");

		new Thread(task1,"firstThread").start();
		new Thread(task2,"secondThread").start();
		
		System.out.println("task1 result:" + task1.getRandomSum());
		System.out.println("task2 result:" + task2.getRandomSum());
		
		System.out.println("Main thread ends here...");
	}

}

class MyThreadTask implements Runnable {	
	private static int count = 0;
	private int id;
	private volatile boolean done = false;
	private int randomSum = 0;
	private String name;

	@Override
	public void run(){
		for(int i = 0; i<5; i++) {
			System.out.println("<" + id + ">TICK TICK " + i);
			randomSum += Math.random()*1000;
			try {
				TimeUnit.MICROSECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		/********** Unlock ************/
		done = true;
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + ": Notifying the result");	
			this.notifyAll();
		}
	}
	
	public String getRandomSum(){
		/*********** Lock ************/
		if(!done) {
			synchronized (this) { 
				try {
					System.out.println(Thread.currentThread().getName() + ": Waiting for the result");	
					this.wait(); 
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + ": Woken up");	
		}
		return randomSum+name;
	}

	public MyThreadTask(String name) {
		this.name = name;
		this.id = ++count;
	}
}