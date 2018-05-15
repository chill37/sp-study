package test;

public class ArrayRotate {

	public static void main(String[] args) {
		int [][]map= {
				{1,2,3},
				{4,5,6},
				{7,8,9}
		};
		
		int [][]map2=leftRotation(map);
//		OUTPUT		
//		3,6,9,
//		2,5,8,
//		1,4,7,

	}

	static int[][] leftRotation(int map[][]) {
		 int i,j;
		 int map2[][]=new int[3][3];
		 int n=map2.length;

		 for(i=0;i<n;i++) {
			 for(j=0;j<n;j++) {
				 map2[n-j-1][i]=map[i][j]; // map2에 map을 회전 시켜 저장
			 }
		 }
		 for(i=0;i<n;i++){
			 for(j=0;j<n;j++){
				 System.out.print(map[i][j]+",");
			 }
			 System.out.println();
		 }
		 return map2;
	}
	


}
