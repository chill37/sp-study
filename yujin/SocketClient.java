package yujin;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	
	//input 디렉토리 하위의 파일을 socket 통신으로 전송
	public static void sendFiles() {
		File[] listFiles = new File("input").listFiles();
		try(Socket socket = new Socket("127.0.0.1", 9090);
				DataOutputStream d = new DataOutputStream(socket.getOutputStream())) {
			for(File file : listFiles) {
				String name = file.getName();
				long size = file.length();
				d.writeUTF(name);
				d.writeLong(size);
				d.flush();
				byte[] buffer = new byte[2014];
				try(FileInputStream fis = new FileInputStream(file)) {
					int read = 0;
					while((read = fis.read(buffer)) != -1) {
						d.write(buffer, 0, read);
					}
					d.flush();
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}