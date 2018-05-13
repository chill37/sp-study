package test01;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchEngine {

	public static void main(String[] args) {
		String []documents= {"LG CNS", "Baseball Team", "LG Uplus", "Art Class", "LG Class"};
		
		HashMap<String, ArrayList> indexMap = makeIndexEngine(documents);
		System.out.println("####");
		Integer []lst=findIndex(indexMap, "LG Baseball");
		
		for(int aa:lst) {
			System.out.print(aa+",");
		}

	}

	private static Integer[] findIndex(HashMap<String, ArrayList> indexMap, String str) {
		String []words = str.split(" ");
		ArrayList<Integer> al= new ArrayList<Integer>();
		ArrayList<Integer> newal= new ArrayList<Integer>();
		
		
		for(int i=0; i<words.length; i++) {
			al=indexMap.get(words[i]);
			//System.out.println(al.get(0));
			for(int aa:al) {
				newal.add(aa);
			}
			
		}
		
		Integer[] ret=newal.stream().distinct().sorted().toArray(Integer[]::new);
		
		return ret;
		
	}

	private static HashMap<String, ArrayList> makeIndexEngine(String[] documents) {
		HashMap<String, ArrayList> indexMap = new HashMap<String, ArrayList>();
		
		for(int i=0; i<documents.length; i++) {
			String []words= documents[i].split(" ");
			
			for(int w=0; w<words.length; w++) {
				ArrayList<Integer> al;
				if (indexMap.get(words[w]) == null) 
					al=new ArrayList<Integer>();
				else
					al= indexMap.get(words[w]);
				al.add(i);
				indexMap.put(words[w], al);
			}
		}
		
		System.out.println(indexMap.get("LG"));
		System.out.println(indexMap.get("CNS"));
		System.out.println(indexMap.get("Baseball"));
		System.out.println(indexMap.get("Class"));
		
		return indexMap;
		
	}

}
