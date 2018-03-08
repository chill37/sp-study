
class ThreadClass extends Thread{
	public void run() {
		System.out.println("Thread is running..");
	}
}

public class ThreadSample {

	public static void main(String[] args) {

		ThreadClass tc1=new ThreadClass();
		
		tc1.start();
		
		try {
			tc1.join();
		}catch(Exception e) {e.printStackTrace();}

	}

}
