package core;

import java.security.PublicKey;
import java.sql.Timestamp;

import util.Util;

public class Transaction {
	
	String signature;
	PublicKey sender;
	PublicKey receiver;
	double amount;
	Timestamp timestamp;
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public PublicKey getSender() {
		return sender;
	}
	public void setSender(PublicKey sender) {
		this.sender = sender;
	}
	public PublicKey getReceiver() {
		return receiver;
	}
	public void setReceiver(PublicKey receiver) {
		this.receiver = receiver;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	// 서명 값을 포함한 트랜잭션 정보 출력 
	public String getInformation() {
		return "<" + signature + ">" +
				new Util().getHash(sender.toString()) + " -> " +
				new Util().getHash(receiver.toString()) + " : " + 
				amount + "개 (" + timestamp + ")";
	}
	
	// 서명 값을제회한 단순 트랜잭션 정보 
	public String getData() {
		return new Util().getHash(sender.toString()) + " -> " +
				new Util().getHash(receiver.toString()) + " : " + 
				amount + "개 (" + timestamp + ")";
	}
	
	// 어떠한 지갑 주소로 얼마 보냈는지 정보를 기준으로 초기화 
	public Transaction(Wallet wallet, PublicKey receiver, double amount, String timestamp) throws Exception {
		super();
		
		this.sender = wallet.getPublicKey();
		this.receiver = receiver;
		this.amount = amount;
		this.timestamp = java.sql.Timestamp.valueOf(timestamp);
		this.signature = wallet.sign(getData());
	}
	
	
	
	
}
