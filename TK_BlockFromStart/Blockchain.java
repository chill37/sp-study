package blockFromStart;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	private static final int BLOCKSIZE_LIMIT = 300;
	
	private int currentBlockNo;
	private int currentBlockSize;
	
	private ArrayList<Block> blockList;
	
	

	public ArrayList<Block> getBlockList() {
		return blockList;
	}
	public void setBlockList(ArrayList<Block> blockList) {
		this.blockList = blockList;
	}
	
	public int getCurrentBlockNo() {
		return currentBlockNo;
	}
	public void setCurrentBlockNo(int currentBlockNo) {
		this.currentBlockNo = currentBlockNo;
	}
	
	public int getCurrentBlockSize() {
		return currentBlockSize;
	}
	public void setCurrentBlockSize(int currentBlockSize) {
		this.currentBlockSize = currentBlockSize;
	}
	
	public boolean isBlockSizeFull(Block block) {
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList = block.getTransactionList();
		int sum=0;
		for(int i = 0; i < transactionList.size(); i++) {
			sum+= Integer.parseInt(transactionList.get(i).getAmount());
		}
		if(sum>300) {
			return true;
		}else {
			return false;
		}
	}
	
	public void createNewBlock() {
		Block block = new Block(0, null, 0, new ArrayList<Transaction>());
		blockList.add(block);
		setCurrentBlockNo(0);
	}
	
	public void nextBlock() {
		int lastBlockNo = blockList.size()-1;
		
		Block lastBlock = blockList.get(lastBlockNo);
		String lastBlockHash = lastBlock.getBlockHash();
		
		Block block = new Block(lastBlockNo+1, lastBlockHash, 0, new ArrayList<Transaction>());
		blockList.add(block);
		setCurrentBlockNo(lastBlockNo+1);
		setCurrentBlockSize(0);
	}
	
	public void addTransaction(Transaction transaction) {
		Block currentBlock = blockList.get(getCurrentBlockNo());
		
		if( getCurrentBlockSize()+Integer.parseInt(transaction.getAmount()) > BLOCKSIZE_LIMIT || getCurrentBlockNo()==0) {
			nextBlock();
			addTransaction(transaction);
			
		}else {
			currentBlock.addTransaction(transaction);
			setCurrentBlockSize(currentBlockSize + Integer.parseInt(transaction.getAmount()));
		}
		
	}
	
	public Blockchain(int currentBlockNo, ArrayList<Block> blockList, int currentBlockSize) {
		super();
		this.currentBlockNo = currentBlockNo;
		this.blockList = blockList;
		this.currentBlockSize = currentBlockSize;
	}
	
	public void getInfo() {
		System.out.println("--------------------------");
		for(int i=0; i < blockList.size(); i++) {
			System.out.println("[Block]Block No : " + blockList.get(i).getBlockID() );
			System.out.println("[Block]Block Hash : " + blockList.get(i).getBlockHash() );
			System.out.println("[Block]Block prev Hash : " + blockList.get(i).getPreviousBlockHash() );
			System.out.println("[Block]Merkle Root : " + blockList.get(i).getMerkleRoot());
			for(int j=0; j < blockList.get(i).getTransactionList().size(); j++ ) {
				System.out.println("[Transaction]"+ 
						blockList.get(i).getTransactionList().get(j).getSender() +" -> "+
						blockList.get(i).getTransactionList().get(j).getReceiver() +" : "+
						blockList.get(i).getTransactionList().get(j).getAmount()
						);
//				System.out.println( blockList.get(i).getTransactionList().get(j).getReceiver() );
//				System.out.println( blockList.get(i).getTransactionList().get(j).getAmount() );
			}
			System.out.println("--------------------------");
		}
		
		System.out.println("--------------------------");
	}
	
	public boolean validateChain() {
		for(int i=0; i<blockList.size()-1; i++){
			String currentHash = blockList.get(i).getBlockHash();
			String nextHash = blockList.get(i+1).getPreviousBlockHash();
			if(!(currentHash.equals(nextHash))) {
				return false;
			}
		}
		return true;
	}
	
	

	
}
