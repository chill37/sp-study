package SP_Package;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SP01_FileList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileList();
	}
	
	static void FileList() {
		File folder = new File("./TEXT/");
		File[] files = folder.listFiles();
		
		for (File file: files) {
			if (file.isDirectory()) {
			}
			else {
				System.out.println(file.getName()+":  "+file.length()+" bytes");
				if (file.length() > 2048) {
					copyFile(file);
				}
			}
		}
	}

	private static void copyFile(File file) {
		final int BUFFER= 4096;
		int readLen;
		
		File destFolder = new File("./OUTPUT/");
		if (!destFolder.exists()) {
			destFolder.mkdir();
		}
		

		try {
			File inputFile=new File("./TEXT/", file.getName());
			File outputFile = new File(destFolder, file.getName());
			InputStream inputStream = new FileInputStream(inputFile);
			OutputStream outputStream = new FileOutputStream(outputFile);
			
			byte[] buffer=new byte[BUFFER];
			
			while((readLen= inputStream.read(buffer))!=-1) {
				outputStream.write(buffer, 0, readLen);
			}
			inputStream.close();
			outputStream.close();
			
		}catch(Exception e) {e.printStackTrace();}
		
	}

}
