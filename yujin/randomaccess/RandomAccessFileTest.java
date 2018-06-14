package study.randomaccessfile;

public class RandomAccessFileTest {

	public static void main(String[] args) throws InterruptedException {
		Thread writerThread = new Thread(new FileWriterRunnable());
		Thread readerThread = new Thread(new FileReaderRunnable());
		
		readerThread.start();
		writerThread.start();
//		Thread.sleep(5000);
		
		
	}

}
