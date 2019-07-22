//Simple Thread with ThreadRunnable

class ThreadRunnableClass implements Runnable{
	public void run() {
		System.out.println("running...");
	}
}
public class ThreadRunnable {

	public static void main(String[] args) {
		ThreadRunnableClass tc1=new ThreadRunnableClass();
		Thread t1=new Thread(tc1);
		t1.start();

		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
