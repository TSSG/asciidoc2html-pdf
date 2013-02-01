package main;

import java.io.File;

/**
 * 
 * @author David Kirwan
 * @email dkirwan@tssg.org
 * @description Main class
 * Copyright 2012 Waterford Institute of Technolgy
 * Review LICENSE.txt for License
 */

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
