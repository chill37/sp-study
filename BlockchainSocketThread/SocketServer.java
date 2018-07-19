package blockchainSocket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
	
	private static final int PORT = 8080;
	private static final int THREAD_CNT = 5;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_CNT);

	public static void main(String[] args) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			while(true) {
				Socket socket = serverSocket.accept();
				try {
					threadPool.execute(new ConnectionWrap(socket));
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}

class ConnectionWrap implements Runnable {
	private Socket socket = null;
	
	public ConnectionWrap(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			OutputStream stream = socket.getOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(stream);
			
			//stream.write(new Date().toString().getBytes());
			Block block = new Block(1, 100);
			objectStream.writeObject(block);
			objectStream.flush();
			objectStream.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				
				socket.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}

