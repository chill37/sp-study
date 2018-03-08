package SocketComm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(9090);
		try {
			Socket socket = listener.accept();
			try {
				PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
				out.println("test");
			}finally {
				socket.close();
			}
		}finally {
			listener.close();
		}

	}

}
