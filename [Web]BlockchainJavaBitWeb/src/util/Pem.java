package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.Key;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

public class Pem {
	private PemObject pemObject;
	
	//키 데이터와 키에 대한 설명 정보 PEM객체에 저장
	public Pem (Key key, String description) {
		this.pemObject = new PemObject(description, key.getEncoded());
	}
	
	//특정한 파일 이름으로 PEM 파일을 저장 
	public void write(String filename) throws IOException {
		PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
		
		try {
			pemWriter.writeObject(this.pemObject);
		}finally {
			pemWriter.close();
		}
	}
	
}
