package mocktest1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Gi_chul_2017 {

	public static void main(String[] args) throws IOException {
		ArrayList<String> al = new ArrayList<String>();
		al=readfile("data2.txt");
		//writeTypeCount(al);
		//writeManyTypeCount(al);
		writeManyTypeCountAndLog(al);
		writeManyTypeThreadCount(al);
		
	}
	
	private static void writeManyTypeThreadCount(ArrayList<String> al) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i=0; i<al.size(); i++) {
			if(al.get(i).equals("")) continue;
			Runnable worker = new WorkerThread(al.get(i));
            executor.execute(worker);
		}
		executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
			
	}


	private static void writeManyTypeCountAndLog(ArrayList<String> al) throws IOException {
		HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
		
		for(int i=0; i<al.size(); i++) {
			if(al.get(i).equals("")) continue;
			String[] spl = al.get(i).split("#");
			String type=spl[1];
			if(hm.containsKey(type)) {
				ArrayList <String> newal=hm.get(type);
				String temp=spl[0]+"#"+spl[1]+"#"+spl[2]+"#"+"AAA";
				newal.add(temp);
				hm.put(type, newal);
				
			}else if(!hm.containsKey(type)) {
				ArrayList <String> newal=new ArrayList<String>();
				String temp=spl[0]+"#"+spl[1]+"#"+spl[2]+"#"+"AAA";
				newal.add(temp);
				hm.put(type, newal);
			}
		}
		
		for(String key: hm.keySet()) {
			String typeFileName="type_3_"+key+".txt";
			File file = new File("./TEXT/", typeFileName);
			PrintWriter pw = new PrintWriter(new FileWriter(file, true));
			for(String temp:hm.get(key)) {
				pw.println(temp);
			}
			
			pw.close();
		}
		
		
	}

	
	private static void writeManyTypeCount(ArrayList<String> al) throws FileNotFoundException {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		for(int i=0; i<al.size(); i++) {
			if(al.get(i).equals("")) continue;
			String[] spl = al.get(i).split("#");
			String type=spl[1];
			if(hm.containsKey(type)) {
				int temp= hm.get(type);
				temp++;
				hm.put(type,temp);
			}else if(!hm.containsKey(type)) {
				hm.put(type, 1);
			}
		}
		
		String outputFileName="REPORT_2.txt";
		File file = new File("./TEXT/", outputFileName);
		PrintWriter pw = new PrintWriter(file);
		
		for(String key: hm.keySet()) {
			pw.println(key+"#"+hm.get(key));
		}
		
		pw.close();
		
	}

	private static void writeTypeCount(ArrayList<String> al) {
		int ee=0;
		int ww=0;
		int wx=0;
		for(int i=0; i<al.size(); i++) {
			if(al.get(i).equals("")) continue;
			String[] spl = al.get(i).split("#");
			if(spl[1].equals("EE")) {
				ee++;
			}else if(spl[1].equals("WW")) {
				ww++;
			}else if(spl[1].equals("WX")) {
				wx++;
			}
		}
		
		String outputFileName="REPORT_1.txt";
		File file = new File("./TEXT/", outputFileName);
		try {
			PrintWriter pw = new PrintWriter(file);
	        pw.println("EE#"+ee);
	        pw.println("WW#"+ww);
	        pw.println("WX#"+wx);
	        pw.close();
		}catch(Exception e) {}
		
	}

	private static ArrayList<String> readfile(String fileName) {
		String line = null;
		ArrayList<String> al = new ArrayList<String>();
		try {
			File file = new File("./TEXT/", fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				al.add(line);
				
			}
			bufferedReader.close();
		}catch(Exception e) {e.printStackTrace();}
		return al;
		
	}

}

class WorkerThread implements Runnable {
	  
    private String command;
    
    public WorkerThread(String s){
        this.command=s;
    }

	@Override
	public void run() {
		//System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
		System.out.println(Thread.currentThread().getName());
        try {
			processCommand(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println(Thread.currentThread().getName()+" End.");
		
	}

	private void processCommand(String str) throws IOException {
//		try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
		
		String[] spl = str.split("#");
		String type = spl[1];
		String filename = "type_4_"+type+".txt";
		File file = new File("./TEXT/", filename);
		PrintWriter pw = new PrintWriter(new FileWriter(file, true));
		pw.println(str);
		pw.close();
		
	}
}
