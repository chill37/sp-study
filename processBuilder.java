package test02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecCmd {

	public static void main(String[] args) throws IOException {
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

//        int exitCode = process.waitFor();
//        System.out.println("\nExited with error code : " + exitCode);

	}
}
