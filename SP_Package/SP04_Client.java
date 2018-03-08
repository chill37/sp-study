package SP_Package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SP04_Client {

	public static void main(String[] args) throws IOException {
		Socket s = new Socket("127.0.0.1", 9000);
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String answer=br.readLine();
		System.out.println(answer);
		

	}

}
