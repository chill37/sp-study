//Reading/Writing Binary File

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class BinaryFileReadWrite {

	public static void main(String[] args) {
		CopyFile("test.txt","test2.txt");
	}
	
	static void CopyFile(String inputFileName, String outputFileName) {
		final int BUFFER_SIZE = 4096;
		int readLen;
		
		try {
			File inputFile = new File("./TEXT", inputFileName);
			File outputFile = new File("./TEXT", outputFileName);
			InputStream inputStream = new FileInputStream(inputFile);
			OutputStream outputStream = new FileOutputStream(outputFile);
			
			byte[] buffer = new byte[BUFFER_SIZE];
			
			while((readLen=inputStream.read(buffer))!=-1) {
				outputStream.write(buffer, 0, readLen);
				
			}
			inputStream.close();
			outputStream.close();
			System.out.println("Done");
		}catch(Exception e) {e.printStackTrace();}
	}

}
