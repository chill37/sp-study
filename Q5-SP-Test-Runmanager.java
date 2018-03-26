package com.lgcns.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class NewExecutor implements Runnable {
	Log log;
	public NewExecutor(Log log) {
		this.log=log;
	}
	@Override
	public void run() {
		try {
			printTypeLog(log);
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void printTypeLog(Log log) throws IOException {
		
		File file = new File("./", "TYPELOG_4_"+log.getType()+".TXT");
		
		FileWriter fw= new FileWriter(file, true);
		fw.append(log.getTime()+"#"+log.getType()+"#"+cmdExecute(".\\CODECONV", log.getMsg())+"\n");
		fw.close();
		
	}
	
	private static String cmdExecute(String arg1, String arg2) {
		
		String s;
		String str = null;
		
		try {
			
			//Process oProcess = new ProcessBuilder("cmd", "/c", "dir", "/?").start();
			Process oProcess = new ProcessBuilder("cmd", "/c", arg1, arg2).start();

			// 외부 프로그램 출력 읽기
			BufferedReader stdOut   = new BufferedReader(new InputStreamReader(oProcess.getInputStream()));
						
			while ((s =   stdOut.readLine()) != null) {
				//System.out.println(s);
				str=s;
			}

		} catch (IOException e) { // 에러 처리
			e.printStackTrace();
		}
		return str;
	}
	
}

public class RunManager {
	

	public static void main(String[] args) throws IOException, InterruptedException {
		

		
		ArrayList<Log> al = new ArrayList<Log>();
		al=ReadFile("LOGFILE_C.TXT");
		
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		printReport(al);
				
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		ExecutorService executorService = Executors.newFixedThreadPool(99);
		
		for(Log log: al) {
			
			NewExecutor ex=new NewExecutor(log);
			executorService.submit(ex);

		}
		executorService.shutdown();
				

	}
	
	private static void printReport(ArrayList<Log> al) throws IOException {
		
		File file = new File("./", "REPORT_4.TXT");
		FileWriter fw= new FileWriter(file);
		
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		
		for (Log log: al) {
			if(map.containsKey(log.getType())) {
				map.replace(log.getType(), map.get(log.getType())+1);
//				printTypeLog(log, true);
			}else {
				map.put(log.getType(), 1);
//				printTypeLog(log, false);
			}
		}
		
		for(String key: map.keySet()) {
			fw.write(key+"#"+map.get(key)+"\n");
		}
		
		fw.close();
		
	}

	static ArrayList<Log> ReadFile(String fileName) throws IOException {
		File file = new File("./", fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String s=null;
		Log log;
		ArrayList<Log> al =new ArrayList<Log>();
		while ((s=br.readLine())!=null) {
			//System.out.println(s);
			String[] str=s.split("#");
			log=new Log(str[0],str[1], str[2]);
			al.add(log);
		}
		return al;
	}
	


}

class Report{
	String type;
	int cnt;
	public Report(String type, int cnt) {
		super();
		this.type = type;
		this.cnt = cnt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public void addCnt() {
		this.cnt++;
	}
	@Override
	public String toString() {
		return type+"#"+cnt;
	}
}

class Log{
	String time;
	String type;
	String msg;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Log(String time, String type, String msg) {
		super();
		this.time = time;
		this.type = type;
		this.msg = msg;
	}
	
	
}