package yujin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class LogWriter implements Runnable {

	private final BlockingQueue<String> sharedQueue;
	private AtomicBoolean running = new AtomicBoolean(true);
	
	public LogWriter(BlockingQueue<String> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}
	
	public void stop() {
		this.running = new AtomicBoolean(false);

	}

	@Override
	public void run() {
//		while(running.get()) {
		while(running.get() || !sharedQueue.isEmpty()) {
			try {
				String log = sharedQueue.take();
				
				String[] split = log.split("#");
				String type = split[1];
				String time = split[0];
				String message = split[2];
				
				//convert message
				String convertMessage = convertMessage(message);
				
				//log type message
				writeLog(type, convertMessage, time);				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static synchronized void writeLog(String type, String message, String time) {
		File file = new File("TYPELOG_5_" + type + ".TXT");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String log = time + "#" + type + "#" + message;
		
		try(FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write(log);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	private static String convertMessage(String message) {
		String msg = null;
		String[] cmd = { "CODECONV.EXE", message };
        Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			
			StringBuilder textBuilder = new StringBuilder();
			InputStream inputStream = p.getInputStream();
			int c = 0;
	        while ((c = inputStream.read()) != -1) {
	            textBuilder.append((char) c);
	        }
			msg = textBuilder.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return msg;
	}

}