package blockFromStart;

public class Transaction {
	String sender;
	String receiver;
	String amount;
	String time;
	String hash;
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash= Util.getHash(sender+receiver+amount+time);
		this.hash = hash;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Transaction(String sender, String receiver, String amount, String time) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.time = time;
		this.hash= Util.getHash(sender+receiver+amount+time);
	}
	public String getInfo() {
		return ""+sender + "이(가) " + receiver + "에게 " + amount + "개의 코인을 보냈습니다.";
	}
	
}
