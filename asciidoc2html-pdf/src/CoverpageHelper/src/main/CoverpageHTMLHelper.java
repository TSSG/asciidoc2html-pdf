package main;

import java.io.File;

public class CoverpageHTMLHelper {

	private static Coverpage cover;
	private static String diskPathInput;
	private static String diskPathOutput;
	
	public CoverpageHTMLHelper(){
		
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
			  WriteHTMLCoverpageFile.writeCoverpage(theDiskPath, getCover());
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
		CoverpageHTMLHelper.cover = cover;
	}

	public static Coverpage getCover() {
		return cover;
	}	
}
