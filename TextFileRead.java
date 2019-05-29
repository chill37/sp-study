//Reading File

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TextFileRead {

	public static void main(String[] args) {
		PrintFile("test.txt");
	}
	
	static List<String> readFileAsList(String fileName) {
		String line= null;
		List<String> list = new ArrayList<>();
		try {
			File file = new File("./", fileName);
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			
			while((line=br.readLine()) != null) {
//				System.out.println(line);
				list.add(line);
			}
			
		} catch (Exception e ) {}
		return list;
	}
	
	static void PrintFile(String fileName) {
		String line = null;
		
		try {
			File file = new File("./TEXT/", fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				
			}
			bufferedReader.close();
		}catch(Exception e) {e.printStackTrace();}
	}

}
