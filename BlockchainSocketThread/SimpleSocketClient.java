package blockchainSocket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SimpleSocketClient {
	private static final int PORT = 8080;
	public static void main(String[] args) {
		try {
			// 소켓을 생성합니다.
			Socket socket = new Socket("localhost", PORT);
			// 스트림을 얻어옵니다.
			InputStream stream = socket.getInputStream();
			ObjectInputStream objectStream = new ObjectInputStream(new BufferedInputStream(stream));
//			// 스트림을 래핑합니다.
//			BufferedReader br = new BufferedReader(objectStream);
			Block block = (Block)objectStream.readObject();
			int val = block.getValue();
			System.out.println(val);
			
			// 결과를 읽습니다.
//			String response = br.readLine();
//			System.out.println(response); // 결과물 출력

			objectStream.close();
			socket.close(); // 소켓을 닫습니다.
			System.exit(0); // 프로그램 종료

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
