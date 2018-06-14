package study.randomaccessfile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterRunnable implements Runnable {
	
	private String path = "./data.txt";

	@Override
	public void run() {
		try (FileWriter fw = new FileWriter(path);
				BufferedWriter bw = new BufferedWriter(fw);) {
			while(true) {
				bw.write(String.valueOf(System.currentTimeMillis()));
				bw.write(System.lineSeparator());
				bw.flush();
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
