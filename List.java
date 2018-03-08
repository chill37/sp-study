import java.util.ArrayList;

public class List {

	public static void main(String[] args) {
		ArrayList<String> al=new ArrayList<String>();
		
		al.add("AAA");
		al.add("bbb");
		al.add("CCC");
		System.out.println(al);
		al.remove(1);
		System.out.println(al);
		al.remove("AAA");
		System.out.println(al);
		System.out.println(al.get(0));
		
		
		
	}

}
