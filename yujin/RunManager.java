package yujin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RunManager {
	
	private static Map<String, Integer> reportMap = new LinkedHashMap<>();
	
	private static final BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<String>();

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int threadNum = 7;
		ExecutorService service = Executors.newFixedThreadPool(threadNum);
		
		List<LogWriter> list = new ArrayList<>();
		for (int i = 0; i < threadNum; i++) {
			LogWriter consumer = new LogWriter(sharedQueue);
			list.add(consumer);
			service.execute(consumer);
		}
		
		String path = "LOGFILE_C.TXT";
		try (FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr)) {
			String line = null;
			while((line = br.readLine()) != null) {
				sharedQueue.put(line);
				
				String[] split = line.split("#");
				String type = split[1];
				if(reportMap.containsKey(type)) {
					reportMap.put(type, reportMap.get(type) + 1);
				} else {
					reportMap.put(type, 1);
				}

			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// report
		File reportFile = new File("REPORT_5.TXT");
		Iterator<String> iterator = reportMap.keySet().iterator();
		try(FileWriter fw = new FileWriter(reportFile);
				BufferedWriter bw = new BufferedWriter(fw)) {
			while(iterator.hasNext()) {
				String type = iterator.next();
				int count = reportMap.get(type);
				String log = type + "#" + count;
				bw.write(log);
				bw.write(System.lineSeparator());
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//queue가 빌 때까지 대기
//		while(true) {
//			if(sharedQueue.isEmpty()) {
//				break;
//			}
//		}
		
		// notify consumers to stop
		for (LogWriter consumer : list) {
			consumer.stop();
		}
		
		service.shutdown();
		
		boolean termination = false;
		try {
			termination = service.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (termination) {
			System.out.println("All tasks have completed execution.");
		} else {
			System.out.println("Timeout elapsed before termination.");
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("time(ms) : " + (end-start));
	}
	

}