package core;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.util.ArrayList;
import util.*;

public class Block {
	
	private static final String ALGORITHM = "SHA1withECDSA";
	
	private int blockID;
	private String previousBlockHash;
	private int nonce;
	private ArrayList<Transaction> transactionList;
	//private String data;
	
	
	
	public String getPreviousBlockHash() {
		return previousBlockHash;
	}
	public void setPreviousBlockHash(String previousBlockHash) {
		this.previousBlockHash = previousBlockHash;
	}
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
//	public String getData() {
//		return data;
//	}
//	public void setData(String data) {
//		this.data = data;
//	}
	public Block(int blockID, String previousBlockHash, int nonce, ArrayList<Transaction> transactionList) {
		super();
		this.blockID = blockID;
		this.previousBlockHash = previousBlockHash;
		this.nonce = nonce;
		//this.data = data;
		this.transactionList = transactionList;
	}
	
	// 정상적인 트랜잭션인지 확인/검증 
	private boolean verifyTransaction(Transaction transaction) throws Exception {
		Signature signature;
		signature = Signature.getInstance(ALGORITHM);
		byte[] baText = transaction.getData().getBytes("UTF-8");
		signature.initVerify(transaction.getSender());
		signature.update(baText);
		return signature.verify(new BigInteger(transaction.getSignature(), 16).toByteArray());
	}
	
	public void addTransaction(Transaction transaction) throws Exception {
		if(verifyTransaction(transaction)) {
			System.out.println("정상적인 트랜잭션임.");
			transactionList.add(transaction);
		}else {
			System.out.println("비정상적인 트랜잭션. 잘못된 인증값");
		}
	}
	
	public void showInformation() {
		System.out.println("----------------------------");
		System.out.println("블록 번호 : " + getBlockID());
		System.out.println("이전 해시 : " + getPreviousBlockHash());
		System.out.println("채굴 변수 : " + getNonce());
		System.out.println("트랜잭션 개수 : " + transactionList.size());
		for(int i=0; i<transactionList.size(); i++) {
			System.out.println(transactionList.get(i).getInformation());
		}
		System.out.println("Block Hash : " + getBlockHash());
		System.out.println("----------------------------");
		
	}
	
	public String getBlockHash() {
		String transactionInformations = "";
		for(int i=0; i<transactionList.size(); i++) {
			transactionInformations += transactionList.get(i).getInformation();
		}
		return Util.getHash(nonce+transactionInformations+previousBlockHash);
	}
	
	public void mine() {
		while(true) {
			if(getBlockHash().substring(0, 4).equals("0000")) {
				System.out.println(blockID + "번째 블록의 채굴에 성공!");
				break;
			}
			nonce++;
		}
	}
	
}
