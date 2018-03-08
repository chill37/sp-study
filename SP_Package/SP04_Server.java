package SP_Package;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SP04_Server {

	public static void main(String[] args) throws IOException {
		SimpleDateFormat format=new SimpleDateFormat("yy-MM-dd-HH:mm:ss");
		ServerSocket listener = new ServerSocket(9000);
		try {
			Socket socket= listener.accept();
			try {
				PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
				out.println(format.format(new Date()));
			}finally {
				socket.close();
			}
			
		}finally {
			listener.close();
		}

	}

}
