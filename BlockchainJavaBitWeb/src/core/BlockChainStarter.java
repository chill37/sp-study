package core;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import util.EC;

public class BlockChainStarter {

	public static void main(String[] args) throws Exception {
		
//		Block block1 = new Block(1, null, 0, new ArrayList<Transaction>());
//		block1.mine();
//		block1.showInformation();
//		
//		Block block2 = new Block(2, block1.getBlockHash(), 0, new ArrayList());
//		block2.addTransaction(new Transaction("나동빈", "박한울", 1.5));
//		block2.addTransaction(new Transaction("이태일", "박한울", 0.7));
//		block2.mine();
//		block2.showInformation();
//		
//		Block block3 = new Block(3, block2.getBlockHash(), 0, new ArrayList<Transaction>());
//		block3.addTransaction(new Transaction("강종구", "이상욱", 8.2));
//		block3.addTransaction(new Transaction("박한울", "나동빈", 0.4));
//		block3.mine();
//		block3.showInformation();
//
//		Block block4 = new Block(4, block3.getBlockHash(), 0, new ArrayList<Transaction>());
//		block4.addTransaction(new Transaction("이상욱", "강종구", 0.1));
//		block4.mine();
//		block4.showInformation();
		
//		//키 생성 객체 정의 (개인+공개키)
//		KeyPairGenerator kpg;
//		kpg = KeyPairGenerator.getInstance("EC", "SunEC");
//		
//		//서명 알고리즘 객체 생성 
//		ECGenParameterSpec ecsp;
//		ecsp = new ECGenParameterSpec("sect163k1");
//		kpg.initialize(ecsp, new SecureRandom());
//		
//		//개인키, 공개키 한쌍 생성 
//		KeyPair kp = kpg.genKeyPair();
//		PrivateKey privKey = kp.getPrivate();
//		PublicKey pubKey = kp.getPublic();
//		
//		//서명 객체 생성해 개인키 설정 
//		Signature ecdsa;
//		ecdsa = Signature.getInstance("SHA1withECDSA", "SunEC");
//		ecdsa.initSign(privKey);
//		
//		//임의 문장 
//		String text = "동빈이가 한율이에게 100코인 전송";
//		String text2 = "동빈이가 한율이에게 1000코인 전송";
//		System.out.println("원래 문장: " + text);
//		
//		//문장에 암호화 수행해 서명 값(암호문)을 얻어냄 
//		ecdsa.update(text.getBytes("UTF-8"));
//		byte[] signatureByte = ecdsa.sign();
//		System.out.println("암호문 : 0x" + (new BigInteger(1, signatureByte).toString(16)).toUpperCase());
//		
//		//서명 객체를 생성해 공개키를 이용해 복호화할 수 있도록 설정  
//		Signature signature;
//		signature = Signature.getInstance("SHA1withECDSA", "SunEC");
//		signature.initVerify(pubKey);
//		
//		//원래 문장을 공개키로 복호화해 검증 
//		signature.update(text.getBytes("UTF-8")); //
//		System.out.println("원래 문장 검증 : "+signature.verify(signatureByte));
//		
//		signature.update(text2.getBytes("UTF-8"));
//		System.out.println("변경 문장 검증 : "+signature.verify(signatureByte));
		
		
		
//		Security.addProvider(new BouncyCastleProvider());
//		EC ec = new EC();
//		ec.generate("private1.pem", "public1.pem");
//		ec.generate("private2.pem", "public2.pem");
//		
//		PrivateKey privateKey1 = ec.readPrivateKeyFromPemFile("private1.pem");
//		PublicKey publicKey1 = ec.readPublicKeyFromPemFile("public1.pem");
//		PrivateKey privateKey2 = ec.readPrivateKeyFromPemFile("private2.pem");
//		PublicKey publicKey2 = ec.readPublicKeyFromPemFile("public2.pem");
//		
//		Signature ecdsa;
//		ecdsa = Signature.getInstance("SHA1withECDSA");
//		ecdsa.initSign(privateKey1);
//		
//		String text = "평문입니다.";
//		System.out.println("평문 정보 : " + text);
//		byte[] baText = text.getBytes("UTF-8");
//		
//		//평문 데이터를 암호화하여 서명한 데이터를 출력합니다. 
//		ecdsa.update(baText);
//		byte[] baSignature = ecdsa.sign();
//		System.out.println("서명된 값 : 0x"+ (new BigInteger(1,baSignature).toString(16)).toUpperCase());
//		
//		Signature signature;
//		signature = Signature.getInstance("SHA1withECDSA");
//		
//		//공개키2를 이용해 복호화 try
//		signature.initVerify(publicKey2);
//		signature.update(baText);
//		boolean result = signature.verify(baSignature);
//		System.out.println("신원 검증 : " + result);
//		
//		//공개키1를 이용해 복호화 try
//		signature.initVerify(publicKey1);
//		signature.update(baText);
//		result = signature.verify(baSignature);
//		System.out.println("신원 검증 : " + result);
		
		

		Security.addProvider(new BouncyCastleProvider());
		// public/private pem file 3개 생성 
//		EC ec = new EC();
//		ec.generate("private1.pem", "public1.pem");
//		ec.generate("private2.pem", "public2.pem");
//		ec.generate("private3.pem", "public3.pem");
		
		Wallet wallet1 = new Wallet();
		wallet1.setFromFile("private1.pem", "public1.pem");
		Wallet wallet2 = new Wallet();
		wallet2.setFromFile("private2.pem", "public2.pem");
		Wallet wallet3 = new Wallet();
		wallet3.setFromFile("private3.pem", "public3.pem");
		
		Block block1 = new Block(1, null, 0, new ArrayList());
		block1.mine();
		block1.showInformation();
		
		Block block2 = new Block(2, block1.getBlockHash(), 0, new ArrayList());
		Transaction transaction1 = new Transaction(wallet1, wallet2.getPublicKey(), 1.5, "2018-05-03 23:05:19.5");
		block2.addTransaction(transaction1);
		Transaction transaction2 = new Transaction(wallet2, wallet3.getPublicKey(), 3.7, "2018-05-04 14:12:09.5");
		block2.addTransaction(transaction2);
		block2.mine();
		block2.showInformation();
		 
		Block block3 = new Block(3, block2.getBlockHash(), 0, new ArrayList());
		// 지갑1에서 지갑3으로 코인을 전송했다는 의미를 가진 트랜잭션을 생성합니다.
		Transaction transaction3 = new Transaction(wallet1, wallet3.getPublicKey(), 2.3, "2018-05-06 17:09:21.5");
		block3.addTransaction(transaction3);
		// 지갑2에서 지갑3으로 코인을 전송했다는 의미를 가진 트랜잭션을 생성합니다.
		Transaction transaction4 = new Transaction(wallet2, wallet3.getPublicKey(), 1.4, "2018-05-07 02:11:19.5");
		block3.addTransaction(transaction4);
		block3.mine();
		block3.showInformation();
		
	}

}
