// changing duplicates

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Duplicates {

	public static void main(String[] args) {
		  List<String> zippedList = new ArrayList<>();
			zippedList=changeDuplicates(strList);
	}
	
	static List<String> changeDuplicates(List<String> list) {
		String prev="";
		int k=0;
		int cnt=1;
		List<String> newList = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			if(!prev.equals(list.get(i))) {
				cnt=1;
				newList.add(list.get(i));
				k++;
				prev = list.get(i);
			} else {
				cnt++;
				StringBuilder strBuf = new StringBuilder(list.get(i));
				strBuf.insert(0, cnt+"#");
				newList.remove(newList.size()-1);
				newList.add(strBuf.toString());
			}
		}
		return newList;
	}
	
	private static String getCompressedLine(String line) {
		StringBuilder sb = new StringBuilder();
		char prev = line.charAt(0);
		int count = 1;
		for(int i=1; i<line.length(); i++) {
			char ch = line.charAt(i);
			if(ch == prev) {
				count++;
			} else {
				if(count > 2) {
					//compress
					sb.append(count);
					sb.append(prev);
				} else {
					for(int j=0; j<count; j++) {
						sb.append(prev);
					}
				}
				//init
				prev = ch;
				count = 1;
			}
		}
		//last character
		if(count > 2) {
			//compress
			sb.append(count);
			sb.append(prev);
		} else {
			for(int j=0; j<count; j++) {
				sb.append(prev);
			}
		}
		return sb.toString();
	}

}
