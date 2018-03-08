package SP_Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SP06_Map {

	public static void main(String[] args) throws IOException {
		
		ExcelRead("Map.csv");
		

	}

	private static void ExcelRead(String fileName) throws IOException {
		File file = new File("./TEXT/", fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br= new BufferedReader(fr);
		
		String s= null;
		while((s=br.readLine())!=null) {
			System.out.println(s);
		}
	}

}
