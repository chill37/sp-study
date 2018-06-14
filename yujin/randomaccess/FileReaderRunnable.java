package study.randomaccessfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileReaderRunnable implements Runnable {

	private String readPath = "./data.txt";
	private String writePath = "./target.txt";
	private long readPosition = 0;
	private long writePosition = 0;

	@Override
	public void run() {
		while(true) {
			try (RandomAccessFile readFile = new RandomAccessFile(readPath, "r")) {
				long length = readFile.length();
				if(length > readPosition) {
					//file이 더 늘어났으면 읽기 시작
					StringBuffer sb = new StringBuffer();
					//이전 위치로 우선 이동
					readFile.seek(readPosition);
					//현재 포인터가 끝에 도달하지 않으면 계속 반복하여 한줄씩 읽기
					while(readFile.getFilePointer() < length) {
						String readLine = readFile.readLine();
						sb.append(readLine).append(System.lineSeparator());
					}
					//다 읽었으면 파일에 쓰기
					//우선 콘솔로 테스트...
					System.out.println(sb.toString());
//					write(sb.toString());
					
					//현재 포인터 위치 저장하기
					readPosition = readFile.getFilePointer();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void write(String data) {
		try (RandomAccessFile writeFile = new RandomAccessFile(writePath, "rw")) {
			writeFile.seek(writePosition);
			writeFile.writeUTF(data);
			writePosition = writeFile.getFilePointer();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
