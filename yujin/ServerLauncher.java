package yujin;

import java.util.Scanner;

public class ServerLauncher {

	//socket server 기동하고 console에서 Q 입력받으면 socket server 중지하고 프로그램 종료
	public static void main(String[] args) {
		SocketServer socketServer = new SocketServer();
		Thread thread = new Thread(socketServer);
		thread.start();
		
		Scanner scan = new Scanner(System.in);
		String input = "";
		while(!input.equals("Q")) {
			input = scan.nextLine();
		}
		
		socketServer.stopListener();
	}
}