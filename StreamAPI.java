package test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {

	public static void main(String[] args) {
		
		String []words= {"ab", "bc", "cd", "ef"};
		int []numbers= {3,2,4,7,6};
		List<String> al=Arrays.asList(words);
		String str="helXZlo";
		
		// 1-String reverse sort
		// 1-
		char[] arr=str.toCharArray();
		Arrays.sort(arr);
		String ans1=new StringBuilder(new String(arr)).reverse().toString();

		// 2-
		String ans2=Stream.of(str.split(""))
				          .sorted(Comparator.reverseOrder())
				          .collect(Collectors.joining());
		
		// String count
		long count = Stream.of(words).count();
		long count2 = Arrays.asList(words).stream().count();
		
		// max, min
		Optional<String> max = Stream.of(words).max(String::compareToIgnoreCase);
		System.out.println("max: "+max.get());

		// filter
		List<String> filter1 = al.stream().filter(f -> f.equals("ab")).collect(Collectors.toList());
		String []filter2 = al.stream().filter(f -> f.contains("b")).toArray(String[]::new);
		for(String a: filter1) System.out.println("filter: "+a);
		
		// unique value
		Object[]unique =  al.stream().distinct().toArray();
		System.out.println("unique");
		for(Object a: unique) System.out.print(a+",");
		System.out.println();
		
		// join
		String join1= al.stream().collect(Collectors.joining("#"));
		System.out.println("j: "+join1);
		

		
		
		
	}

}
