package mocktest1;

import java.util.ArrayList;

public class river {

	public static void main(String[] args) {
		int [][]rarr= {
				{ 1, 1, 0, 0, 0, 0, 0, 1, 1},
				{ 0, 1, 1, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 1, 0, 0, 0, 0, 0, 0},
				{20,20,20,20,20,20,20,20,20},
				{ 1, 0, 0, 0, 1, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0},
				{ 1, 1, 1, 1, 1, 0, 0, 0, 0},
		};
		
		
		int [][]ret=intersectPoint(rarr);
		System.out.println();
		listAllRiver(rarr, ret);

	}
	
	

	private static int[][] intersectPoint(int[][] rarr) {
		ArrayList<Integer> intersect=new ArrayList<Integer>();
		int big=0;
		int len=rarr[0].length;
		for(int i=0; i<rarr.length; i++) {
			if(rarr[i][0]>1) {
				big=i;
			}
		}
		
		for(int i=0; i<len; i++) {
			if(rarr[big-1][i]==1 || rarr[big+1][i]==1) {
				intersect.add(i);
			}
		}
		
		for(int i:intersect) {
			System.out.print(i+",");
		}
		
		int [][]ret=new int[intersect.size()][2];
		for(int i=0; i<intersect.size(); i++) {
			ret[i][0]=big;
			ret[i][1]=intersect.get(i);
		}
		return ret;
		
	}

	private static void listAllRiver(int[][] rarr, int[][] ret) {
		
		int rowLimit = rarr.length;
		int colLimit = rarr[0].length;
		
		//ArrayList<river> rivers=new ArrayList<river>();
		ArrayList<ArrayList> rivers=new ArrayList<ArrayList>();
		
		for(int r=0; r<rowLimit; r++) {
			for(int c=0; c<colLimit; c++) {
				if(rarr[r][c]==0 || rarr[r][c]==20) continue;
				
				// {1,2},{1,3}
				
				if(rarr[r][c]==1) {
					ArrayList<int[]> river=new ArrayList<int[]>();
					river.add(new int[] {r,c});
					rarr[r][c]=0;
					int row=r;
					int col=c;
					boolean riverexist=true;
					while(riverexist) {
						if ( (col+1 < colLimit) && (rarr[row][col+1] == 1)) {
							int []tmp= {row,col+1};
							river.add(tmp);
							rarr[row][col+1]=0;
							col++;
						}else if( (row+1 < rowLimit) && (rarr[row+1][col] == 1 )) {
							int []tmp= {row+1,col};
							river.add(tmp);
							rarr[row+1][col]=0;
							row++;
						}else if( (col>0) && (rarr[row][col-1] ==1) ) {
							int []tmp= {row,col-1};
							river.add(tmp);
							rarr[row][col-1]=0;
							col--;
						}else {
							riverexist=false;
						}
					}
					rivers.add(river);
				}
				
				
			}
		}
		
		
		for(int i=0; i<rivers.size(); i++) {
			for(int j=0; j<rivers.get(i).size(); j++) {
				ArrayList<int[]>temp=rivers.get(i);
				int a=temp.get(j)[0];
				int b=temp.get(j)[1];
				System.out.print(a+","+b+" - ");
				
			}
			System.out.println();
		}
		
		
	}

	

}
