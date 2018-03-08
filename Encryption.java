import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SHA256("hi");
		
	}

	private static void SHA256(String string) throws NoSuchAlgorithmException {
		MessageDigest mDigest=MessageDigest.getInstance("SHA-256");
		
		byte[] result=mDigest.digest(string.getBytes());
		
		StringBuffer sb=new StringBuffer();
		
		for(int i=0; i<result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xFF)+0x100, 16).substring(1));
		}
		System.out.println(sb.toString());
		
	}

}
