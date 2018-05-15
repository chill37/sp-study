package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ComparatorExample {

	public static void main(String[] args) {
		int[][] map= {
				{5,8,9,1,0},
				{11,1,0,2,87},
				{21,7,231,65,77},
				{1,6,8,1,5},
				{34,1,31,3,2},
		};
		
		int[][] twoDim = { {1, 2}, {3, 7}, {8, 9}, {4, 2}, {5, 3} };
		
		int[] arr= {6,3,5,1,3};
		
		List<int[]> lst=Arrays.asList(arr);
		
		
		
		simpleArraySort(arr);
		Integer[] arr2=intToInteger(arr);
		Integer[] arr3=intToIntegerJava8(arr);
		List<Integer> list= intToListJava8(arr);
		reverseSort(arr2);
		
		comparatorSort(arr2);
		
		twoDimSort(map);

	}

	

	private static void twoDimSort(int[][] map) {
		Arrays.sort(map, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
		});

		
	}



	private static void comparatorSort(Integer[] arr2) {
		Arrays.sort(arr2, new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0-arg1;
			}
			
		});
		
	}



	private static List<Integer> intToListJava8(int[] arr) {
		List<Integer> you  = Arrays.stream( arr ).boxed().collect( Collectors.toList() );
		List<Integer> like = IntStream.of( arr ).boxed().collect( Collectors.toList() );
		return you;
	}

	private static Integer[] intToIntegerJava8(int[] arr) {
		Integer[] what = Arrays.stream( arr ).boxed().toArray( Integer[]::new );
		Integer[] ever = IntStream.of( arr ).boxed().toArray( Integer[]::new );
		return ever;
	}

	private static Integer[] intToInteger(int[] intArray) {
		Integer[] result = new Integer[intArray.length];
		for (int i = 0; i < intArray.length; i++) {
			result[i] = Integer.valueOf(intArray[i]);
		}
		return result;
		
	}

	

	private static void reverseSort(Integer[] arr) {
		Arrays.sort(arr, Collections.reverseOrder());
	}

	private static void simpleArraySort(int[] arr) {
		Arrays.sort(arr);
		
	}

}
