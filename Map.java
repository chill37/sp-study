import java.util.HashMap;

public class Map {

	public static void main(String[] args) {
		HashMap<String,String> map=new HashMap<String,String>();
		
		map.put("111", "AAA");
		map.put("222", "BBB");
		map.put("333", "CCC");
		
		for(String key: map.keySet()) {
			System.out.println(key+":"+map.get(key));
		}
		map.remove("222");
		map.replace("111", "aaa");
		

	}

}
