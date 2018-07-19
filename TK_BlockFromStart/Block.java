package blockFromStart;

import java.util.ArrayList;
import java.util.List;

public class Block {
	
	private int blockID;
	private int nonce;
	private String previousBlockHash;
	private ArrayList<Transaction> transactionList;
	private String merkleRoot;
	
	public String getMerkleRoot() {
		return merkleRoot;
	}

	public void setMerkleRoot(String merkleRoot) {
		this.merkleRoot = merkleRoot;
	}
	
	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
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
	
	public Block(int blockID, String previousBlockHash, int nonce, ArrayList<Transaction> transactionList) {
		super();
		this.blockID = blockID;
		this.previousBlockHash = previousBlockHash;
		this.nonce = nonce;
		this.transactionList = transactionList;
		
	}
	
	public void addTransaction(Transaction transaction) {
		transactionList.add(transaction);
		computeMerkleRoot();
	}
	
	public void getInfo() {
		System.out.println("--------------------------");
		System.out.println("블록 번호 : "+ getBlockID());
		System.out.println("이전 해시 : "+ getPreviousBlockHash());
		System.out.println("채굴 변수 값: " + getNonce());
		System.out.println("Merkle Root: " + getMerkleRoot());
		for(int i = 0; i < transactionList.size(); i++) {
			System.out.println(transactionList.get(i).getInfo());
		}
	    System.out.println("블록 해시: " + getBlockHash());
		System.out.println("--------------------------");
	}
	
	public String getBlockHash() {
		String transactionInformations = "";
		for(int i = 0; i < transactionList.size(); i++) {
			transactionInformations += transactionList.get(i).getInfo();
		}
		return Util.getHash(nonce + transactionInformations + previousBlockHash);
	}
	
	public void mine() {
		while(true) {
			if(getBlockHash().substring(0, 4).equals("0000")) {
				System.out.println(blockID + "번째 블록의 채굴에 성공했습니다.");
				break;
			}
			nonce++;
		}
	}
	
//	//Increases nonce value until hash target is reached.
//	public void mineBlock(int difficulty) {
//		merkleRoot = StringUtil.getMerkleRoot(transactions);
//		String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0" 
//		while(!hash.substring( 0, difficulty).equals(target)) {
//			nonce ++;
//			hash = calculateHash();
//		}
//		System.out.println("Block Mined!!! : " + hash);
//	}
	
	public void computeMerkleRoot() {		
		List<String> treeList = merkleTree();
		// Last element is the merkle root hash if transactions
		setMerkleRoot(treeList.get(treeList.size()-1) );		
	}
	
	public List<String> merkleTree() {		
		ArrayList<String> tree = new ArrayList<>();
		// add all transactions as leaves of the tree.
		for (Transaction t : transactionList) {
			tree.add(t.getHash());
		}
		int levelOffset = 0; // first level
								
		// Iterate through each level, stopping when we reach the root (levelSize
		// == 1).
		for (int levelSize = transactionList.size(); levelSize > 1; levelSize = (levelSize + 1) / 2) {
			// For each pair of nodes on that level:
			for (int left = 0; left < levelSize; left += 2) {
				// The right hand node can be the same as the left hand, in the
				// case where we don't have enough
				// transactions.
				int right = Math.min(left + 1, levelSize - 1);
				String tleft = tree.get(levelOffset + left);
				String tright = tree.get(levelOffset + right);
				tree.add(Util.getHash(tleft + tright));
			}
			// Move to the next level.
			levelOffset += levelSize;
		}
		return tree;
	}
	
}
