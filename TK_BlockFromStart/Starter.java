package blockFromStart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Starter {
	
	private static String[] data = {
			"tom#mark#200#2018-05-18-12-01",
			"anna#tyler#100#2018-05-18-12-01",
			"rebecca#gautam#300#2019-05-18-12-01",
			"emile#michel#300#2019-05-18-12-01",
			"michael#peter#300#2020-05-18-12-01",
	};

	public static void main(String[] args) {
		
//		Scanner scan = new Scanner(System.in);
//		String input = "";
//		System.out.print("input: ");
//		while(scan.hasNextLine()) {
//			
//			input = scan.nextLine();
//			if(input.equals("Q")) {
//				System.exit(0);			
//			}
//			makeTransaction(input);
//			makeBlock(input);
//		}
		
//		for(int i=0; i<data.length; i++) {
//			makeTransaction(data[i]);
//			makeBlock(data[i]);
//		}
		//Block block1 = new Block(1, null, 0, new ArrayList<Transaction>());
		
		ArrayList<String> al = new ArrayList<String>();
		al=PrintFile("data.txt");
		
		Blockchain blockchain = new Blockchain(0, new ArrayList<Block>(), 0);
		blockchain.createNewBlock();
		for(int i=0; i<al.size(); i++) {
			String []spl = al.get(i).split("#");
			Transaction transaction = new Transaction(spl[0], spl[1], spl[2], spl[3]);
			blockchain.addTransaction(transaction);
		}
//		for(int i=0; i<data.length; i++) {
//			String[] spl = data[i].split("#");
//			Transaction transaction = new Transaction(spl[0], spl[1], spl[2], spl[3]);
//			blockchain.addTransaction(transaction);
//		}
		blockchain.getInfo();
		System.out.println("Chain Validation : " + blockchain.validateChain());
		
		
	}
	
	private static ArrayList<String> PrintFile(String fileName) {
		String line = null;
		ArrayList<String> al = new ArrayList<String>();
		
		try {
			File file = new File("./DATA/", fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				//System.out.println(line);
				al.add(line);
				
			}
			bufferedReader.close();
		}catch(Exception e) {e.printStackTrace();}
	
		return al;
	}
	
	
	private static void makeTransaction(String singleInput) {
		// sender, receiver, amount, time
		//input = "tom#mark#200#2018-05-18-12-01";
		//  tom#mark#200#2018-05-18-12-01
		//  anna#tyler#100#2018-05-18-12-01
		// rebecca#gautam#300#2019-05-18-12-01
		// emile#michel#300#2019-05-18-12-01
		// michael#peter#300#2020-05-18-12-01
		String[] spl = singleInput.split("#");
		Transaction transaction = new Transaction(spl[0], spl[1], spl[2], spl[3]);
		System.out.println(transaction.getInfo());
		
	}

	private static void makeBlock(String input) {
		
		
		Block block1 = new Block(1, null, 0, new ArrayList<Transaction>());
		block1.mine();
		block1.getInfo();
		Block block2 = new Block(2, block1.getBlockHash(), 0, new ArrayList<Transaction>());
		block2.addTransaction(new Transaction("나동빈", "박한울", "100", "2018"));
		block2.addTransaction(new Transaction("이태일", "박한울", "200", "2019"));
		block2.mine();
		block2.getInfo();
		
	}
	
	

}
