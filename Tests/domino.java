package test01;

import java.util.Arrays;

public class domino {

	public static void main(String[] args) {
		
		String domino="367153718";
		
		firstError(domino);
		changeDomino(domino);
		

	}

	private static void changeDomino(String domino) {
		String []ds=domino.split("");
		int []doms=new int[ds.length];
		
		for(int i=0; i<doms.length; i++) {
			doms[i]=Integer.parseInt(ds[i]);
		}
		
		for(int i=0; i<doms.length-1; i++) {
			if(doms[i]*2<doms[i+1]) {
				//System.out.println(doms[i+1]/2);
				doms[i]=(int)Math.ceil(doms[i+1]/2.0);
			}
		}
		
		for(int i=0; i<doms.length; i++) {
			System.out.print(doms[i]+",");
		}
		
	}

	private static int firstError(String domino) {
		String []ds=domino.split("");
		int []doms=new int[ds.length];
		
		for(int i=0; i<doms.length; i++) {
			doms[i]=Integer.parseInt(ds[i]);
		}
		
		int answer=-1;
		for(int i=0; i<doms.length-1; i++) {
			if(doms[i]*2<doms[i+1]) {
				answer=i;
				break;
			}
		}
		System.out.println(answer);
		return answer;
	}

}
