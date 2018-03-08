import java.io.File;

public class FileFolderList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileFolderList();
	}
	
	static void FileFolderList() {
		File folder = new File(".");
		File[] fList = folder.listFiles();
		
		for (File file: fList) {
			if(file.isDirectory()) {
				System.out.println("["+file.getName()+"]");
			}
			else {
				System.out.println(file.getName());
			}
		}
	}

}
