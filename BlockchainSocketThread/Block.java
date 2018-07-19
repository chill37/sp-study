package blockchainSocket;

public class Block {
	
	int blockNo;
	int value;
	public int getBlockNo() {
		return blockNo;
	}
	public void setBlockNo(int blockNo) {
		this.blockNo = blockNo;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Block(int blockNo, int value) {
		super();
		this.blockNo = blockNo;
		this.value = value;
	}
	
	

}
