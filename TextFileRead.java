import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TextFileRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintFile("test.txt");

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
