package runner;


import java.io.File;

import main.CoverpageHTMLHelper;
import main.CoverpageHelper;
import main.FileChecker;
import main.ReadAsciidocDocinfo;
import main.WriteXMLFile;

/**
 * Launcher for the CoverpageHelper tool.
 * @author dkirwan
 * @email dkirwan@tssg.org
 * @date 17th February 2012
 *
 */
public class CoverRunner {
	
	public static void main(String args[]){
		
		if(args.length == 0){
			System.out.println("\nInvoke the tool in the following way for PDF" +
					" coversheet inclusion: \n\n" +
					"\"java -jar coverpagehelper.jar -p /path/to/coverpagetemplate.xml " +
					"/path/to/asciidocgenerated.xml\"");
			
			System.out.println("\nInvoke the tool in the following way for HTML" +
					" coversheet inclusion: \n\n" +
					"\"java -jar coverpagehelper.jar -h /path/to/coverpagetemplate.xml " +
					"/path/to/asciidocreport.txt\"");
		}
		else if(args[0].equals("-p") && args.length == 3)
		{
			File template = FileChecker.checkFile(args[1]);
			if(FileChecker.isExist())
				CoverpageHelper.setDiskPathInput(args[1]);
			else
			{
				System.out.println("Error! Template file not found!");
				System.exit(-1);
			}
			
			FileChecker.checkFile(args[2]);
			if(FileChecker.isExist())
				CoverpageHelper.setDiskPathOutput(args[2]);
			else
			{
				System.out.println("Error! Asciidoc file not found!");
				System.exit(-1);
			}
			
			System.out.println("Reading in the coverpage XML file located at: " +
						args[1] + "\nand copying it to the file located at: " +
						args[2]);
			CoverpageHelper.loadData(template);
			CoverpageHelper.saveData(CoverpageHelper.getDiskPathOutput());
			System.out.println("\nCompleted!");
		}
		else if(args[0].equals("-h") && args.length == 3)
		{
			File template = FileChecker.checkFile(args[1]);
			if(FileChecker.isExist())
				CoverpageHTMLHelper.setDiskPathInput(args[1]);
			else
			{
				System.out.println("Error! Template file not found!");
				System.exit(-1);
			}
			
			FileChecker.checkFile(args[2]);
			if(FileChecker.isExist())
				CoverpageHelper.setDiskPathOutput(args[2]);
			else
			{
				System.out.println("Error! Asciidoc file not found!");
				System.exit(-1);
			}
			
			System.out.println("Reading in the coverpage XML file located at: " +
						args[1] + "\nand copying it to the file located at: " +
						args[2]);
			CoverpageHTMLHelper.loadData(template);
			CoverpageHTMLHelper.saveData(CoverpageHelper.getDiskPathOutput());
			System.out.println("\nCompleted!");
		}
		else if(args[0].equals("-c") && args.length == 3)
		{
			File infile = FileChecker.checkFile(args[1]);
			if(FileChecker.isExist())
			{
				ReadAsciidocDocinfo.setDiskPathInput(args[1]);
				ReadAsciidocDocinfo.setDiskPathOutput(args[2]);
			}
			else
			{
				System.out.println("Error! Asciidoc file not found!");
				System.exit(-1);
			}
			
			ReadAsciidocDocinfo.parseDocinfoFromFile(ReadAsciidocDocinfo.getDiskPathInput());
			WriteXMLFile.writeTemplate(ReadAsciidocDocinfo.getDiskPathOutput(),
					ReadAsciidocDocinfo.getCover());
			
			System.out.println("Reading in the Asciidoc file located at: " +
						args[1] + "\nCoverpage information extracted successfully!\nOutputting to: " +
						args[2]);
			//
			System.out.println("\nCompleted!");
		}
		else
		{
			System.out.println("\nInvoke the tool in the following way for PDF" +
					" coversheet inclusion: \n\n" +
					"\"java -jar coverpagehelper.jar -p /path/to/coverpagetemplate.xml " +
					"/path/to/asciidocgenerated.xml\"");
			
			System.out.println("\nInvoke the tool in the following way for HTML" +
					" coversheet inclusion: \n\n" +
					"\"java -jar coverpagehelper.jar -h /path/to/coverpagetemplate.xml " +
					"/path/to/asciidocreport.txt\"");
		}
	}

}
