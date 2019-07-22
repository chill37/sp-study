//Thread with Lock

import java.util.concurrent.locks.ReentrantLock;

class ThreadClass1 implements Runnable{
	static ReentrantLock lock = new ReentrantLock();
	public void run() {
		lock.lock();
		try {
			//do something
		}finally {
			lock.unlock();
		}
	}
}
public class ThreadAndLock {

	public static void main(String[] args) {
		Thread t1=new Thread(new ThreadClass1());
		t1.start();

		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
