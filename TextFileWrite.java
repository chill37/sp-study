import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFileWrite {

	public static void main(String[] args) throws IOException {
		
		UsePrintWriter("textwrite.txt");
		UseFileWriter("textwrite1.txt");

	}

	private static void UseFileWriter(String outputFileName) throws IOException {
		File file = new File("./TEXT/", outputFileName);
		
		FileWriter fw = new FileWriter(file);
        for(int i=1; i<11; i++) {
            String data = i+" 번째 줄입니다.\r\n";
            fw.write(data);
        }
        fw.close();

        FileWriter fw2 = new FileWriter(file, true);
        for(int i=11; i<21; i++) {
            String data = i+" 번째 줄입니다.\r\n";
            fw2.write(data);
        }
        fw2.close();
		
	}

	private static void UsePrintWriter(String outputFileName) throws IOException {
		File file = new File("./TEXT/", outputFileName);
		
		PrintWriter pw = new PrintWriter(file);
        for(int i=1; i<11; i++) {
            String data = i+" 번째 줄입니다.";
            pw.println(data);
        }
        pw.close();

        PrintWriter pw2 = new PrintWriter(new FileWriter(file, true));
        for(int i=11; i<21; i++) {
            String data = i+" 번째 줄입니다2";
            pw2.println(data);
        }
        pw2.close();
		
	}

}
