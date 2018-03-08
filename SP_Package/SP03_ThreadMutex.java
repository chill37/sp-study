package SP_Package;

import java.util.concurrent.locks.ReentrantLock;

class ThreadM implements Runnable{
	String name;
	static ReentrantLock lock = new ReentrantLock();
	public ThreadM(String name) {
		this.name=name;
	}
	public void run() {
		
		lock.lock();
		try {
			PrintNum(name);
		}finally {
			
			lock.unlock();
		}
	}
	static void PrintNum(String name2) {
		System.out.println(name2);
		for(int i=0; i<20; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
}

public class SP03_ThreadMutex {

	public static void main(String[] args) {
		
		Thread t1=new Thread(new ThreadM("Thread1: "));
		Thread t2=new Thread(new ThreadM("Thread2: "));
		
		t1.start();
		t2.start();
		ThreadM.lock.lock();
		try {
			ThreadM.PrintNum("Main:");
		}finally {
			ThreadM.lock.unlock();
		}
		
	}
}
