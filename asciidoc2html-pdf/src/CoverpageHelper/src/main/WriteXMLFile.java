package main;


import java.io.*;
import java.util.ArrayList;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;  

/*
<coverpage>
	 <minititle>Technical Report TSSG-YYYY-Area-00001</minititle>
	 <mininame>Behavioural Driven Development</mininame>
	 <covertitle>Behavioural Driven Development Report</covertitle>
	 <group>Telecommunications Software and Systems Group (TSSG)</group>
	 <address>Waterford Institute of Technology, West Campus, Carriganore, Waterford, Ireland</address>
	 <authorlist>David Kirwan, Some otherguy, And Anotherguy</authorlist>
	 <emaillist>dkirwan@tssg.org, someotherguy@tssg.org, andanotherguy@tssg.org</emaillist>
	 <revdate></revdate>
	 <revnumber></revnumber>
	 <date>9th January 2012</date>
	 <legal>(C) Waterford Institute of Technology</legal>
	<synopsis>Writing robust software requires constant Test/Behaviour driven development. Best practices suggest writing the tests first, 
	and then the implementation after.  This allows a software system to be continually tested, checked and verified to ensure new changes do
	not break other elements of the system unintentionally.
	</synopsis>
</coverpage>

should map to template below

== What Open Source License should be used for a RED5 plugin item ==
:authorlist:  Miguel Ponce de Leon, Author2 Author2, Author3 Author 3
:emaillist:   miguelpdl@tssg.org, author2@tssg.org, author3@tssg.org
:revdate:     February 07, 2012
:revnumber:   https://redmine.tssg.org/issues/4190[Issue 4190]
:docdate:     February 10, 2012
:description: open source, licenses   //synopsis
:toc:

*/

/**
 * 
 * @author David Kirwan
 * @email dkirwan@tssg.org
 * @description Writes the XML Storage class Cover contents to a child of the 
 * root tag of the file specified by parameter filePath.
 *
 */ 
public class WriteXMLFile 
{
	public static void writeTemplate(String filePath, Coverpage cover)
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		Document doc;
		
		try {
			docBuilder = docFactory.newDocumentBuilder();
			//doc = docBuilder.parse(filePath);
			doc = docBuilder.newDocument();
						
			//Creates the <coverpage> </coverpage> tag
	        Node coverpage = doc.createElement("coverpage");
	        doc.appendChild(coverpage);
	        
	        //Creates the <minititle> </minititle> tag
	        Node minititle = writeMiniTitleTag(cover, doc);
	        
	        //Creates the <mininame> </mininame> tag
	        Node mininame = writeMiniNameTag(cover, doc);
	        
	        //Creates the <covertitle> </covertitle> tag
	        Node covertitle = writeCoverTitleTag(cover, doc);
	        
	        //Creates the <group> </group> tag
	        Node group = writeGroupTag(cover, doc);
	        
	        //Creates the <address> </address> tag
	        Node address = writeAddressTag(cover, doc);
	        
	        //Creates the <authorlist> </authorlist> tag
	        Node authorlist = writeAuthorListTag(cover, doc);
	        
	        //Creates the <emaillist> </emaillist> tag
	        Node emaillist = writeEmailListTag(cover, doc);
	        	        
	        //Creates the <revdate> </revdate> tag
	        Node revDate = writeRevDateTag(cover, doc);
	        
	        //Creates the <revnumber> </revnumber> tag
	        Node revNumber = writeRevNumberTag(cover, doc);
	        
	        
	        //Creates the <date> </date> tag
	        Node date = writeDateTag(cover, doc);
	        
	        //Creates the <legal> </legal> tag
	        Node legal = writeLegalTag(cover, doc);
	        
	        //Creates the <synopsis> </synopsis> tag
	        Node synopsis = writeSynopsisTag(cover, doc);
	        
	        //Creates the <encoding> </encoding> tag
	        Node encoding = writeEncodingTag(cover, doc);
	        
	        //Creates the <synopsis> </synopsis> tag
	        Node toc = writeTocTag(cover, doc);
	        
	        /**
	         * Appends all these tags then to the coverpage tag.
	         */
	        appendTagsToRoot(coverpage, minititle, mininame, covertitle,
					group, address, authorlist, emaillist, revDate, revNumber, date, legal, synopsis,
					encoding, toc);       
	    
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

	        //initialize StreamResult with File object to save to file
	        StreamResult result = new StreamResult(new StringWriter());
	        DOMSource source = new DOMSource(doc);
	        transformer.transform(source, result);

	        String xmlString = result.getWriter().toString();
	        //System.out.println(xmlString);
	        
	        File file = new File(filePath);
	        BufferedWriter bw = new BufferedWriter
	                      (new OutputStreamWriter(new FileOutputStream(file)));
	        bw.write(xmlString);
	        bw.flush();
	        bw.close();
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	
	private static Node writeEncodingTag(Coverpage cover, Document doc) {
		Node encoding = doc.createElement("encoding");
		Text etext = doc.createTextNode(String.valueOf(cover.getEncoding()));
		encoding.appendChild(etext);
		return encoding;
	}


	private static Node writeTocTag(Coverpage cover, Document doc) {
		Node toc = doc.createElement("toc");
		Text ttext = doc.createTextNode(String.valueOf(cover.isToc()));
		toc.appendChild(ttext);
		return toc;
	}


	public static void writeCoverpage(String filePath, Coverpage cover)
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		Document doc;
		
		try {
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(filePath);
			
			Node rootelment=doc.getDocumentElement();
	        //System.out.println(rootelment.getNodeName());
	        
			
			//Creates the <coverpage> </coverpage> tag
	        Node coverpage = doc.createElement("coverpage");
	        rootelment.appendChild(coverpage);
	        
	        //Creates the <minititle> </minititle> tag
	        Node minititle = writeMiniTitleTag(cover, doc);
	        
	        //Creates the <mininame> </mininame> tag
	        Node mininame = writeMiniNameTag(cover, doc);
	        
	        //Creates the <covertitle> </covertitle> tag
	        Node covertitle = writeCoverTitleTag(cover, doc);
	        
	        //Creates the <group> </group> tag
	        Node group = writeGroupTag(cover, doc);
	        
	        //Creates the <address> </address> tag
	        Node address = writeAddressTag(cover, doc);
	        
	        //Creates the <authorlist> </authorlist> tag
	        Node authorlist = writeAuthorListTag(cover, doc);
	        
	        //Creates the <emaillist> </emaillist> tag
	        Node emaillist = writeEmailListTag(cover, doc);
	        	        
	        //Creates the <revdate> </revdate> tag
	        Node revDate = writeRevDateTag(cover, doc);
	        
	        //Creates the <revnumber> </revnumber> tag
	        Node revNumber = writeRevNumberTag(cover, doc);
	        
	        
	        //Creates the <date> </date> tag
	        Node date = writeDateTag(cover, doc);
	        
	        //Creates the <legal> </legal> tag
	        Node legal = writeLegalTag(cover, doc);
	        
	        //Creates the <synopsis> </synopsis> tag
	        Node synopsis = writeSynopsisTag(cover, doc);
	        
	        //Creates the <encoding> </encoding> tag
	        Node encoding = writeEncodingTag(cover, doc);
	        
	        //Creates the <synopsis> </synopsis> tag
	        Node toc = writeTocTag(cover, doc);
	        
	        /**
	         * Appends all these tags then to the coverpage tag.
	         */
	        appendTagsToRoot(coverpage, minititle, mininame, covertitle,
					group, address, authorlist, emaillist, revDate, revNumber, date, legal, synopsis,
					encoding, toc);       
	    
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

	        //initialize StreamResult with File object to save to file
	        StreamResult result = new StreamResult(new StringWriter());
	        DOMSource source = new DOMSource(doc);
	        transformer.transform(source, result);

	        String xmlString = result.getWriter().toString();
	        //System.out.println(xmlString);
	        
	        File file = new File(filePath);
	        BufferedWriter bw = new BufferedWriter
	                      (new OutputStreamWriter(new FileOutputStream(file)));
	        bw.write(xmlString);
	        bw.flush();
	        bw.close();
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private static Node writeRevNumberTag(Coverpage cover, Document doc) {
		Node revnumber = doc.createElement("revnumber");
		Text rtext = doc.createTextNode(cover.getRevnumber());
		revnumber.appendChild(rtext);
		return revnumber;
	}

	private static Node writeRevDateTag(Coverpage cover, Document doc) {
		Node revdate = doc.createElement("revdate");
		Text rtext = doc.createTextNode(cover.getRevdate());
		revdate.appendChild(rtext);
		return revdate;
	}

	private static Node writeEmailListTag(Coverpage cover, Document doc) {
		Node emailList = doc.createElement("emaillist");
		Text etext = doc.createTextNode(cover.getEmaillist());
		emailList.appendChild(etext);
		return emailList;
	}

	private static void appendTagsToRoot(Node coverpage, Node minititle,
			Node mininame, Node covertitle, Node group,
			Node address, Node authorlist, Node emaillist, Node revdate,
			Node revnumber, Node date, Node legal, Node synopsis, Node encoding, Node toc) {
		coverpage.appendChild(minititle);
		coverpage.appendChild(mininame);
		coverpage.appendChild(covertitle);
		coverpage.appendChild(group);
		coverpage.appendChild(address);
		coverpage.appendChild(authorlist);
		coverpage.appendChild(emaillist);
		coverpage.appendChild(revdate);
		coverpage.appendChild(revnumber);
		coverpage.appendChild(date);
		coverpage.appendChild(legal);
		coverpage.appendChild(synopsis);
		coverpage.appendChild(encoding);
		coverpage.appendChild(toc);
	}

	private static Node writeSynopsisTag(Coverpage cover, Document doc) {
		Node synopsis = doc.createElement("synopsis");
		Text stext = doc.createTextNode(cover.getSynopsis());
		synopsis.appendChild(stext);
		return synopsis;
	}

	private static Node writeLegalTag(Coverpage cover, Document doc) {
		Node legal = doc.createElement("legal");
		Text ltext = doc.createTextNode(cover.getLegal());
		legal.appendChild(ltext);
		return legal;
	}

	private static Node writeDateTag(Coverpage cover, Document doc) {
		Node date = doc.createElement("date");
		Text dtext = doc.createTextNode(cover.getDate());
		date.appendChild(dtext);
		return date;
	}

	private static Node writeAuthorListTag(Coverpage cover, Document doc) {
		Node authorlist = doc.createElement("authorlist");
		Text altext = doc.createTextNode(cover.getAuthorlist());
		authorlist.appendChild(altext);
		return authorlist;
	}

	private static Node writeAddressTag(Coverpage cover, Document doc) {
		Node address = doc.createElement("address");
		Text atext = doc.createTextNode(cover.getAddress());
		address.appendChild(atext);
		return address;
	}

	private static Node writeGroupTag(Coverpage cover, Document doc) {
		Node group = doc.createElement("group");
		Text gtext = doc.createTextNode(cover.getGroup());
		group.appendChild(gtext);
		return group;
	}

	private static Node writeCoverTitleTag(Coverpage cover, Document doc) {
		Node covertitle = doc.createElement("covertitle");
		Text cttext = doc.createTextNode(cover.getCovertitle());
		covertitle.appendChild(cttext);
		return covertitle;
	}

	private static Node writeMiniNameTag(Coverpage cover, Document doc) {
		Node mininame = doc.createElement("mininame");
		Text mntext = doc.createTextNode(cover.getMininame());
		mininame.appendChild(mntext);
		return mininame;
	}

	private static Node writeMiniTitleTag(Coverpage cover, Document doc) {
		Node minititle = doc.createElement("minititle");
		Text mttext = doc.createTextNode(cover.getMinititle());
		minititle.appendChild(mttext);
		return minititle;
	}
}

