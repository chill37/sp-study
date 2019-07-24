package tct;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SP_Template {

	public static void main(String[] args) throws IOException {
		ArrayList<String> list = readFileToList("./TEXT/","INPUT_1.TXT");
		
		ArrayList<InputObject> objList = makeListOfObject(list);
		
		printObject(objList);
		
		Map<String, Integer> typeCountMap = categoryCount(list);
		
		printToFile("OUTPUT","RESULT_1.txt", typeCountMap);
		printToFile("OUTPUT","log1.txt");
	}

	private static void printObject(ArrayList<InputObject> objList) {
		System.out.println("<print obj>");
		for(int i=0; i<objList.size(); i++) {
			InputObject obj = objList.get(i);
			String type = obj.getType();
			LocalDate localDate = obj.getDateFormat();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			System.out.println(type+":"+localDate.format(formatter));
		}
		
	}

	private static ArrayList<InputObject> makeListOfObject(ArrayList<String> list) {
		ArrayList<InputObject> objList = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			String line = list.get(i);
			String[] split = line.split("#");
			String date = split[0];
			String file = split[1];
			String type = split[2];
			String msg = split[3];
			
			InputObject obj = new InputObject(date, file, type, msg);
			
			objList.add(obj);
		}
		
		return objList;
		
	}

	private static Map<String, Integer> categoryCount(ArrayList<String> list) {
		ArrayList<String> typeList = new ArrayList<>();
		Map<String, Integer> typeCountMap = new HashMap<>();
		
		for(int i=0; i<list.size(); i++) {
			String line = list.get(i);
			String[] split = line.split("#");
			String date = split[0];
			String file = split[1];
			String type = split[2];
			String msg = split[3];
			
			typeList.add(type);
		}
		
		Set<String> typeSet = new HashSet<>(typeList);
		for(String s: typeSet) {
			int cnt = Collections.frequency(typeList, s);
			typeCountMap.put(s, cnt);
		}
		
		System.out.println("<type count>");
		for(Map.Entry m : typeCountMap.entrySet()) {
			System.out.println(m.getKey()+":"+m.getValue());
		}
		
		return typeCountMap;
	}

	private static ArrayList<String> readFileToList(String folder, String fileName) {
		String line = null;
		ArrayList<String> al = new ArrayList<String>();
		try {
			File file = new File(folder, fileName);
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
	
	private static void printToFile(String folderName, String outputFileName, Map<String, Integer> typeCountMap) throws IOException {
		// create folder if not exist
				File folder = new File(folderName);
				if(!folder.exists()) {
					folder.mkdirs();
				}
				
				File file = new File(folder, outputFileName);
		        //PrintWriter pw = new PrintWriter(file); // if you want to overwrite existing file
		        PrintWriter pw = new PrintWriter(new FileWriter(file, true)); // if you want to append on existing file
		        
		        printContent(pw, typeCountMap);
		        
		        pw.close();
	}
	
	private static void printContent(PrintWriter pw, Map<String, Integer> typeCountMap) {
		
		for(Map.Entry m : typeCountMap.entrySet()) {
			pw.println("key:"+m.getKey()+"#val:"+m.getValue());
		}
		
	}

	private static void printToFile(String folderName, String outputFileName) throws IOException {
		
		// create folder if not exist
		File folder = new File(folderName);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		File file = new File(folder, outputFileName);
        //PrintWriter pw = new PrintWriter(file); // if you want to overwrite existing file
        PrintWriter pw = new PrintWriter(new FileWriter(file, true)); // if you want to append on existing file
        
        for(int i=11; i<21; i++) {
            String data = i+" 번째 줄입니다.";
            pw.println(data);
        }
        
        pw.close();
		
	}
	
	private static void execCommand() throws IOException {
		ProcessBuilder processBuilder = new ProcessBuilder();
	    // Windows
		
	    processBuilder.command("cmd.exe", "/c", "exe11.BAT");

	    processBuilder.directory(new File("."));
        Process process = processBuilder.start();
        
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
	        while ((line = reader.readLine()) != null) {
        	line = reader.readLine();
            System.out.println(line);
        }
	}

}

class InputObject {
	
	String date;
	String file;
	String type;
	String msg;
	LocalDate dateFormat;
	
	public InputObject(String date, String file, String type, String msg) {
		super();
		this.date = date;
		this.file = file;
		this.type = type;
		this.msg = msg;
		setDateFormat(date);
	}
	
	public void setDateFormat(String dateStr) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate localDate = LocalDate.parse(dateStr, format);
		this.dateFormat = localDate;
	}
	public LocalDate getDateFormat() {
		return dateFormat;
	}
	

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
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
	
}
