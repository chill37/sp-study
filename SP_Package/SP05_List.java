package SP_Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class SP05_List {

	public static void main(String[] args) throws IOException {
		ArrayList<Student> al=new ArrayList<Student>();
		al=FileRead("List1.txt");
		
		
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String input = br.readLine();
			if (input.equals("PRINT")) {
				Comparator<Student> co =new Comparator<Student>() {
					@Override
					public int compare(Student a, Student b) {
						return (a.name.compareTo(b.name));
					}
				};
				Collections.sort(al, co);
			}else if (input.equals("KOREAN")) {
				Collections.sort(al, (g1,g2)->(g2.getLiterature()-g1.getLiterature()));
			}else if (input.equals("QUIT")) {
				break;
			}
			Iterator<Student> itr=al.iterator();
			while(itr.hasNext()) {
				Student val=itr.next();
				System.out.println(val.toString());
			}
		}
		
		
		
		

	}

	private static ArrayList<Student> FileRead(String fileName) throws IOException {
		File file=new File("./TEXT", fileName);
		
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		
		ArrayList<Student> al=new ArrayList<Student>();
		
		String list=null;
		while((list=br.readLine())!=null) {
			//System.out.println(list);
			String[] str=list.split(" ");
			Student stu=new Student(str[0], Integer.parseInt(str[1]),  Integer.parseInt(str[2]),  Integer.parseInt(str[3]));
			//System.out.println(stu.toString());
			al.add(stu);
			
		}
		br.close();
		System.out.println(al);
		return al;
		
	}
	
}

class Student{
	String name;
	int literature;
	int english;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLiterature() {
		return literature;
	}

	public void setLiterature(int literature) {
		this.literature = literature;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", literature=" + literature + ", english=" + english + ", math=" + math + "]";
	}

	int math;
	
	public Student(String name, int literature, int english, int math) {
		super();
		this.name = name;
		this.literature = literature;
		this.english = english;
		this.math = math;
	}
	
	
}
