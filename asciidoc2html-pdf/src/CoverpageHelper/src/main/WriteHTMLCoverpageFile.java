package main;

import java.io.*;
import java.io.IOException;
import java.util.*;

/**
 * 
 * @author dkirwan
 * @email dkirwan@tssg.org
 * Copyright 2012 Waterford Institute of Technolgy
 * Review LICENSE.txt for License
 */

public class WriteHTMLCoverpageFile {

	public static void writeCoverpage(String theDiskPath, Coverpage cover) {
		
		try
		{
			//System.out.println(cover.toString());
			
			BufferedWriter out = new BufferedWriter(new FileWriter(theDiskPath));
			
			out.write("image::tssg_logo.png[\"TSSG Logo\", scaledwidth=\"15%\", scaledheight=\"15%\", align=\"center\"]");
			out.write("\n\n");
			
			String tmp = "";
			
			out.write("[float]\n" + cover.getCovertitle() + "\n");
			for(int i = 0; i < cover.getCovertitle().length(); i++)
			{
				tmp += "-";
			}
			out.write(tmp + "\n\n");
			tmp = "";			
			
			out.write(".Research Group:\n");
			out.write(cover.getGroup() + "\n\n");
			
			out.write(".Address:\n");
			out.write(cover.getAddress() + "\n\n");
			
			out.write(".Author(s):\n");
			out.write(cover.getAuthorlist() + "\n\n");
			
			out.write(".Email List:\n");
			out.write(cover.getEmaillist() + "\n\n");
			
			out.write(".Rev Date:\n");
			out.write(cover.getRevdate() + "\n\n");
			
			out.write(".Rev Number:\n");
			out.write(cover.getRevnumber() + "\n\n");
			
			out.write(".Date:\n");
			out.write(cover.getDate() + "\n\n");
			
			out.write(".Copyright:\n");
			out.write(cover.getLegal() + "\n\n");
			
			out.write("[float]\n" + "Synopsis:\n^^^^^^^^^\n");
			out.write(cover.getSynopsis() + "\n\n");
			
			out.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		
		
	}
}
