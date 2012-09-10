package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
:reporttype:  Technical Report TSSG-2012
:reporttitle: BDD Testing in Java
:author:      David Kirwan
:email:       dkirwan@tssg.org
:group:	      Telecommunications Software and Systems Group (TSSG)
:address:     Waterford Institute of Technology, West Campus, Carriganore, Waterford, Ireland
:revdate:     February 20, 2012
:revnumber:   n/a
:docdate:     February 20, 2012
:description: Report detailing various technologies availible for testing code and evaluating code test coverage in Java
:legal:		  (C) Waterford Institute of Technology
:encoding:      iso-8859-1
:toc:
 */

/**
 * 
 * @author dkirwan
 * @email dkirwan@tssg.org
 */
public class ReadAsciidocDocinfo {
	
	private static Coverpage cover;
	private static String diskPathInput;
	private static String diskPathOutput;
	private static boolean gotMiniTitle;
	private static boolean gotTitle;
	private static boolean gotAuthors;
	private static boolean gotEmail;
	private static boolean gotGroup;
	private static boolean gotAddress;
	private static boolean gotRevDate;
	private static boolean gotRevNumber;
	private static boolean gotDocDate;
	private static boolean gotDescription;
	private static boolean gotLegal;
	private static boolean gotEncoding;
	private static boolean gotToc;
	
	public ReadAsciidocDocinfo(){
	}
	
	
	/**
	 * 
	 * 
	 * @param theDiskPath
	 */
	public static void parseDocinfoFromFile(String theDiskPath){
		
		BufferedReader in;
		try {
			cover = new Coverpage();
			in = new BufferedReader(new FileReader(theDiskPath));
						
			String str = "";
			while ((str = in.readLine()) != null) {
				if(!gotMiniTitle)
					checkForReportMinititle(str);
				else if(!gotTitle)
					checkForReportTitle(str);
				else if(!gotAuthors)
					checkForAuthors(str);
				else if(!gotEmail)
					checkForEmail(str);
				else if(!gotGroup)
					checkForGroup(str);
				else if(!gotAddress)
					checkForAddress(str);
				else if(!gotRevDate)
					checkForRevDate(str);
				else if(!gotRevNumber)
					checkForRevNumber(str);
				else if(!gotDocDate)
					checkForDocDate(str);
				else if(!gotDescription)
					checkForDescription(str);
				else if(!gotLegal)
					checkForLegal(str);
				else if(!gotEncoding)
					checkForEncoding(str);
				else if(!gotToc)
					checkForToc(str);
			}
			
			//System.out.println("\n" + cover + "\n");
			System.out.println("TOC is set to: " + gotToc);
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	// :encoding:      iso-8859-1
	private static void checkForEncoding(String str) {
		//System.out.println("Origional String: " + str);
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 10);
		//System.out.println(test1);
		
		if(test1.equals(":encoding:")){
			
			// Removes the :encoding: from the start of the string
			str = str.substring(12, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :encoding: and 
			// the actual encoding data
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setEncoding(str);
			gotEncoding = true;
		}
	}


	// :reporttype:  Technical Report TSSG-2012
	private static void checkForReportMinititle(String str) {

		//System.out.println("Origional String: " + str);
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 12);
		//System.out.println(test1);
		
		if(test1.equals(":reporttype:")){
			
			// Removes the :reporttype: from the start of the string
			str = str.substring(12, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :author: and 
			// the actual author(s) names
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setMinititle(str);
			gotMiniTitle = true;
		}
	}


	// :toc:
	private static void checkForToc(String str) {

		//System.out.println("Origional String: " + str);
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 5);
		//System.out.println(test1);
		
		if(test1.equals(":toc:")){
			cover.setToc(true);
			gotToc = true;
		}
	}


	// :legal:		  (C) Waterford Institute of Technology
	private static void checkForLegal(String str) {
		//System.out.println("Origional String: " + str);
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 7);
		//System.out.println(test1);
		
		if(test1.equals(":legal:")){
			
			// Removes the :legal: from the start of the string
			str = str.substring(7, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :author: and 
			// the actual author(s) names
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setLegal(str);
			gotLegal = true;
		}
	}


	// :address:	  Waterford Institute of Technology, West Campus, Carriganore, Waterford, Ireland
	private static void checkForAddress(String str) {
		//System.out.println("Origional String: " + str);
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 9);
		//System.out.println(test1);
		
		if(test1.equals(":address:")){
			
			// Removes the :address: from the start of the string
			str = str.substring(9, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :author: and 
			// the actual author(s) names
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setAddress(str);
			gotAddress = true;
		}
	}


	// :group:		  Telecommunications Software and Systems Group (TSSG)
	private static void checkForGroup(String str) {
		//System.out.println("Origional String: " + str);
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 7);
		//System.out.println(test1);
		
		if(test1.equals(":group:")){
			
			// Removes the :group: from the start of the string
			str = str.substring(7, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :author: and 
			// the actual author(s) names
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setGroup(str);
			gotGroup = true;
		}
	}


	// :description: open source, licenses
	private static void checkForDescription(String str) {
		//System.out.println("Origional String: " + str);
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 13);
		//System.out.println(test1);
		
		if(test1.equals(":description:")){
			
			// Removes the :description: from the start of the string
			str = str.substring(13, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :author: and 
			// the actual author(s) names
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setSynopsis(str);
			gotDescription = true;
		}
	}


	// :docdate:     February 10, 2012
	private static void checkForDocDate(String str) {
		//System.out.println("Origional String: " + str);
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 9);
		//System.out.println(test1);
		
		if(test1.equals(":docdate:")){
			
			// Removes the :docdate: from the start of the string
			str = str.substring(9, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :author: and 
			// the actual author(s) names
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setDate(str);
			gotDocDate = true;
		}
	}


	// :revnumber:   https://redmine.tssg.org/issues/4190[Issue 4190]
	private static void checkForRevNumber(String str) {
		//System.out.println("Origional String: " + str);
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 11);
		//System.out.println(test1);
		
		if(test1.equals(":revnumber:")){
			
			// Removes the :revnumber: from the start of the string
			str = str.substring(11, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :author: and 
			// the actual author(s) names
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setRevnumber(str);
			gotRevNumber = true;
		}
	}


	//:revdate:     February 07, 2012
	private static void checkForRevDate(String str) {
		//System.out.println("Origional String: " + str);
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 9);
		//System.out.println(test1);
		
		if(test1.equals(":revdate:")){
			
			// Removes the :revdate: from the start of the string
			str = str.substring(9, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :author: and 
			// the actual author(s) names
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setRevdate(str);
			gotRevDate = true;
		}	
	}


	// :email:       miguelpdl@tssg.org
	private static void checkForEmail(String str) {
		//System.out.println("Origional String: " + str);
		
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 7);
		//System.out.println(test1);
		
		if(test1.equals(":email:")){
			
			// Removes the :email: from the start of the string
			str = str.substring(7, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :author: and 
			// the actual author(s) names
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setEmaillist(str);
			gotEmail = true;
		}	
	}


	// :author:      Miguel Ponce de Leon
	private static void checkForAuthors(String str) {
		//System.out.println("Origional String: " + str);
		
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
		
		String test1 = str.substring(0, 8);
		//System.out.println(test1);
		
		if(test1.equals(":author:")){
			
			// Removes the :author: from the start of the string
			str = str.substring(8, str.length());
			
			// Gets rid of any tabs or any other white space out of there between :author: and 
			// the actual author(s) names
			str = trimLeft(str);
			//System.out.println(str);
			
			cover.setAuthorlist(str);
			gotAuthors = true;
		}		
	}

	// :reporttitle:       BDD Testing in Java
	public static void checkForReportTitle(String str){
		//System.out.println("Origional String: " + str);
	
		// Gets rid of any white characters before or after the string.
		str = trimLeft(str);
		//System.out.println(test);
		str = trimRight(str);
		//System.out.println(test);
	
		String test1 = str.substring(0, 13);
		//System.out.println(test1);
	
		if(test1.equals(":reporttitle:")){
		
			// Removes the :reporttitle: from the start of the string
			str = str.substring(13, str.length());
		
			// Gets rid of any tabs or any other white space out of there between :reporttitle: and 
			// the actual title
			str = trimLeft(str);
			//System.out.println(str);
		
			cover.setCovertitle(str);
			gotTitle = true;
		}
	}
	
	public static Coverpage getCover() {
		return cover;
	}

	public static void setCover(Coverpage newcover) {
		cover = newcover;
	}


	public static String getDiskPathInput() {
		return diskPathInput;
	}


	public static void setDiskPathInput(String diskPathInput) {
		ReadAsciidocDocinfo.diskPathInput = diskPathInput;
	}


	public static String getDiskPathOutput() {
		return diskPathOutput;
	}


	public static void setDiskPathOutput(String diskPathOutput) {
		ReadAsciidocDocinfo.diskPathOutput = diskPathOutput;
	}
	
    public static String trimLeft(String s) {
        return s.replaceAll("^\\s+", "");
    }
     
    public static String trimRight(String s) {
        return s.replaceAll("\\s+$", "");
    } 
}
