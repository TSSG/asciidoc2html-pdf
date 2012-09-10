package main;

import java.io.File;

public class FileChecker {
	
	private static boolean exist;
	
	public FileChecker(){
		
	}
	
	public static File checkFile(String path){

			File template = new File(path);
			
			if(template.exists()){
				setExist(true);
				return template;
			}
			else
			{
				setExist(false);
				return null;
			}		
	}

	private static void setExist(boolean b) {
		exist = b;		
	}

	public static boolean isExist() {
		return exist;
	}
}
