package socket_multithread2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Socket Client
 * Gets Input from User and sends to Server
 */
public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("127.0.0.1", 8085);
		
		PrintWriter out=new PrintWriter(s.getOutputStream(), true);
		out.println("test");
		Scanner sc = new Scanner(System.in);
		String str=null;
		while((str=sc.nextLine()) != null) {
			if(str.equals("q")) {
				break;
			}
			System.out.println("[Client] Send: "+str);
			System.out.println("[Client] Sckt: "+s);
			out.println(str);
		}
		

	}

}
