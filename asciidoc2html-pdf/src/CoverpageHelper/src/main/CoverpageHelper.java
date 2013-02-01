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
public class CoverpageHelper {
	
	private static Coverpage cover;
	private static String diskPathInput;
	private static String diskPathOutput;
	
	public CoverpageHelper(){
		
	}
	
	public static void loadData(File coverpageFile)
	{		
		ReadAndPrintXMLFile.readCoverpageFromFile(coverpageFile);
		setCover(ReadAndPrintXMLFile.getCover());
		
		//System.out.println(getCover().toString());
	}
	
	public static void saveData(String theDiskPath)
	{
		try
		  {
			  WriteXMLFile.writeCoverpage(theDiskPath, getCover());
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
	}

	public static void setDiskPathInput(String newDiskPath) {
		diskPathInput = newDiskPath;
	}

	public static String getDiskPathInput() {
		return diskPathInput;
	}
	
	public static void setDiskPathOutput(String newDiskPath) {
		diskPathOutput = newDiskPath;
	}

	public static String getDiskPathOutput() {
		return diskPathOutput;
	}

	public static void setCover(Coverpage cover) {
		CoverpageHelper.cover = cover;
	}

	public static Coverpage getCover() {
		return cover;
	}
}
