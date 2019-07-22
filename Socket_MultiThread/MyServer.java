package socket_multithread2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Socket Server : MultiThread
 * Gets Input from multi-client and prints
 */
public class MyServer {

    private static MyServer server; 
    private ServerSocket serverSocket;

    private ExecutorService executorService = Executors.newFixedThreadPool(10);        

    public static void main(String[] args) throws IOException {
        server = new MyServer();
        server.runServer();
    }

    private void runServer() {        
        int serverPort = 8085;
        try {
            System.out.println("Starting Server");
            serverSocket = new ServerSocket(serverPort); 

            while(true) {
                System.out.println("Waiting for request");
                try {
                    Socket s = serverSocket.accept();
                    System.out.println("Processing request");
                    executorService.submit(new ServiceRequest(s));
                } catch(IOException ioe) {
                    System.out.println("Error accepting connection");
                    ioe.printStackTrace();
                }
            }
        }catch(IOException e) {
            System.out.println("Error starting Server on "+serverPort);
            e.printStackTrace();
        }
    }

    //Call the method when you want to stop your server
    private void stopServer() {
        //Stop the executor service.
        executorService.shutdownNow();
        try {
            //Stop accepting requests.
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error in server shutdown");
            e.printStackTrace();
        }
        System.exit(0);
    }

    class ServiceRequest implements Runnable {

        private Socket socket;

        public ServiceRequest(Socket connection) {
            this.socket = connection;
        }

        public void run() {

            //Do your logic here. You have the `socket` available to read/write data.
        	try {
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String answer= null;
				
				while((answer=input.readLine()) != null) {
					System.out.println("[Server] Recv: "+answer+" from Port: "+socket.getPort());
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	

            //Make sure to close
            try {
                socket.close();
            }catch(IOException ioe) {
                System.out.println("Error closing client connection");
            }
        }        
    }
}