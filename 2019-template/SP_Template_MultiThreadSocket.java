package tct;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SP_Template_MultiThreadSocket {
	
	private static SP_Template_MultiThreadSocket template;
	private ServerSocket serverSocket;

    private ExecutorService executorService = Executors.newFixedThreadPool(10);      
			
	public static void main(String[] args) throws IOException {
		
		template = new SP_Template_MultiThreadSocket();
		template.runServer();
		
		
//		ArrayList<String> list = template.readFileToList("./TEXT/","INPUT_1.TXT");
//		
//		ArrayList<InputObject> objList = template.makeListOfObject(list);
//		
//		template.printObject(objList);
//		
//		Map<String, Integer> typeCountMap = template.categoryCount(list);
//		
//		template.printToFile("OUTPUT","RESULT_1.txt", typeCountMap);
//		template.printToFile("OUTPUT","log1.txt");
	}
	
	private void runServer() {        
        int serverPort = 8085;
        try {
            System.out.println("Starting Server");
            serverSocket = new ServerSocket(serverPort); 

            while(true) {
                System.out.println("Waiting for request");
                try {
                    Socket s = serverSocket.accept();
                    System.out.println("Processing request");
                    executorService.submit(new ServiceRequest(s));
                    
                } catch(IOException ioe) {
                    System.out.println("Error accepting connection");
                    ioe.printStackTrace();
                }
            }
        }catch(IOException e) {
            System.out.println("Error starting Server on "+serverPort);
            e.printStackTrace();
        }
    }

    //Call the method when you want to stop your server
    private void stopServer() {
        //Stop the executor service.
        executorService.shutdownNow();
        try {
            //Stop accepting requests.
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error in server shutdown");
            e.printStackTrace();
        }
        System.exit(0);
    }

    class ServiceRequest implements Runnable {

        private Socket socket;

        public ServiceRequest(Socket connection) {
            this.socket = connection;
        }

        public void run() {

            //Do your logic here. You have the `socket` available to read/write data.
        	try {
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String answer= null;
				ArrayList<String> list = new ArrayList<>();
				
				while((answer=input.readLine()) != null) {
					
					System.out.println("[Server] Recv: "+answer+" from Port: "+socket.getPort());
					list.add(answer);
					Map<String, Integer> typeCountMap = template.categoryCount(list);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	

            //Make sure to close
            try {
                socket.close();
            }catch(IOException ioe) {
                System.out.println("Error closing client connection");
            }
        }        
    }

	private void printObject(ArrayList<InputObject> objList) {
		System.out.println("<print obj>");
		for(int i=0; i<objList.size(); i++) {
			InputObject obj = objList.get(i);
			String type = obj.getType();
			LocalDate localDate = obj.getDateFormat();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			System.out.println(type+":"+localDate.format(formatter));
		}
		
	}

	private ArrayList<InputObject> makeListOfObject(ArrayList<String> list) {
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

	private Map<String, Integer> categoryCount(ArrayList<String> list) {
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

	private ArrayList<String> readFileToList(String folder, String fileName) {
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
	
	private void printToFile(String folderName, String outputFileName, Map<String, Integer> typeCountMap) throws IOException {
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
	
	private void printContent(PrintWriter pw, Map<String, Integer> typeCountMap) {
		
		for(Map.Entry m : typeCountMap.entrySet()) {
			pw.println("key:"+m.getKey()+"#val:"+m.getValue());
		}
		
	}

	private void printToFile(String folderName, String outputFileName) throws IOException {
		
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
	
	private void execCommand() throws IOException {
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
