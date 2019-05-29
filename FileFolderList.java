import java.io.File;

public class FileFolderList {

	public static void main(String[] args) {
		FileFolderList();
	}

	// only 1 layer folder
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
	
	// subfolder list
	static void listFileFolder(String directoryName, List<File> files) {
	    File directory = new File(directoryName);

	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if(fList != null) {
	    	for (File file : fList) {
	    		 if (file.isFile()) {
		            	System.out.println(file.getName());
		                files.add(file);
	    		 } else if (file.isDirectory()) {
		            	System.out.println("["+file.getName()+"]");
		            	listFileFolder(file.getAbsolutePath(), files);
		                
	    		 }
	    	}
	    }
	}
	
	//get filepath from subfolders
	static String getFileFromFolders(String directoryName, List<File> files, String fileToFind, String filePath) {
	    File directory = new File(directoryName);
	   
	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if(fList != null) {
	    	for (File file : fList) {
	    		if (file.isFile()) {
		            System.out.println(file.getName());
		            if(file.getName().equals(fileToFind)) {
		            	 return file.getPath();
		            }
		            files.add(file);
	    		 } else if (file.isDirectory()) {
		            System.out.println("["+file.getName()+"]");
		            filePath=getFileFromFolders(file.getPath(), files, fileToFind, filePath);
	    		 }
	    	}
	    }
	    return filePath;
	}

}
