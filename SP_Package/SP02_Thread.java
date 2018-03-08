package SP_Package;
class ThreadC extends Thread{
	String name;
	public ThreadC(String name) {
		this.name=name;
	}
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println(name+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class SP02_Thread {

	public static void main(String[] args) throws InterruptedException {
		ThreadC tc1=new ThreadC("[Thread1] ");
		ThreadC tc2=new ThreadC("[Thread2] ");
		
		tc1.start();
		tc2.start();
		
		for(int i=0; i<10; i++) {
			System.out.println("[Main] "+i);
			Thread.sleep(100);
		}
		
		try {
			tc1.join();
			tc2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
	}

}
