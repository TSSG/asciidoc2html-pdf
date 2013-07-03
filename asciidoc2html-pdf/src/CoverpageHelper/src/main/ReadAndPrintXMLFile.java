package main;

import java.io.File;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

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
:firstname:   Miguel
:lastname:    Ponce de Leon
:email:       miguelpdl@tssg.org
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
 * @description Reads in XML file.
 * Copyright 2012 Waterford Institute of Technolgy
 * Review LICENSE.txt for License
 */
 
public class ReadAndPrintXMLFile
{
	private static Coverpage cover;
	
    public static void readCoverpageFromFile(File coverpageFile){
    try {
    		cover = new Coverpage();
    		
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            docBuilder.setEntityResolver( new BlankingResolver() );
            Document doc = docBuilder.parse (coverpageFile);

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            //System.out.println ("Root element of the doc is " + 
            //     doc.getDocumentElement().getNodeName());

            //##############Read in the <coverpage> element################
            NodeList coverNode = doc.getElementsByTagName("coverpage");
            int totalNoOfCoverNodes = coverNode.getLength();
            //System.out.println("Total no of coverpage nodes : " + totalNoOfCoverNodes);
            
            Node firstCoverNode = coverNode.item(0);
            	
            if(firstCoverNode.getNodeType() == Node.ELEMENT_NODE){
                Element firstCoverElement = (Element)firstCoverNode;
                
                readMiniTitle(firstCoverElement);
                
                readMiniName(firstCoverElement);                

                readCoverTitle(firstCoverElement);
                
                readGroup(firstCoverElement);
                
                readAddress(firstCoverElement);                
                
                readAuthorList(firstCoverElement);
                
                readEmailList(firstCoverElement);
                
                readRevDate(firstCoverElement);
                
                readRevNumber(firstCoverElement);
                                
                readDate(firstCoverElement);
                
                readLegal(firstCoverElement);
                
                readSynopsis(firstCoverElement);
                
                readEncoding(firstCoverElement);
                
                readToc(firstCoverElement);

           }//end of if clause
                
           coverpageFile = null;
           
        }
    	catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
    }

	private static void readEncoding(Element firstCoverElement) {
		//##############Read in the <encoding> element################
				//------
				NodeList encodingList = firstCoverElement.getElementsByTagName("revnumber");
				Element encodingElement = (Element)encodingList.item(0);

				NodeList textENList = encodingElement.getChildNodes();
				//System.out.println("Rev Number: " + 
				//       ((Node)textRNList.item(0)).getNodeValue().trim());
								
				try{ 
					String encodingTag = (((Node)textENList.item(0)).getNodeValue().trim());
					cover.setEncoding(encodingTag);
				}catch(java.lang.NullPointerException e){
					//Check to make sure the tag is not empty
					cover.setEncoding(" ");
				}
				
	}

	private static void readToc(Element firstCoverElement) {//##############Read in the <revnumber> element################
		//------
		NodeList toc = firstCoverElement.getElementsByTagName("toc");
		Element tocElement = (Element)toc.item(0);

		NodeList textToc = tocElement.getChildNodes();
		//System.out.println("TOC: " + 
		//       ((Node)textToc.item(0)).getNodeValue().trim());
						
		try{ 
			String tocTag = (((Node)textToc.item(0)).getNodeValue().trim());
			cover.setToc(Boolean.valueOf(tocTag));
		}catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setToc(false);
		}
	}

	private static void readRevNumber(Element firstCoverElement) {
		//##############Read in the <revnumber> element################
		//------
		NodeList revnumberList = firstCoverElement.getElementsByTagName("revnumber");
		Element revnumberElement = (Element)revnumberList.item(0);

		NodeList textRNList = revnumberElement.getChildNodes();
		//System.out.println("Rev Number: " + 
		//       ((Node)textRNList.item(0)).getNodeValue().trim());
						
		try{ 
			String revnumberTag = (((Node)textRNList.item(0)).getNodeValue().trim());
			cover.setRevnumber(revnumberTag);
		}catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setRevnumber(" ");
		}
		
	}

	private static void readRevDate(Element firstCoverElement) {
		//##############Read in the <revdate> element################
		//------
		NodeList revdateList = firstCoverElement.getElementsByTagName("revdate");
		Element revdateElement = (Element)revdateList.item(0);

		NodeList textRDList = revdateElement.getChildNodes();
		//System.out.println("Rev Date: " + 
		//       ((Node)textRDList.item(0)).getNodeValue().trim());
						
		try{ 
			String revdateTag = (((Node)textRDList.item(0)).getNodeValue().trim());
			cover.setRevdate(revdateTag);
		}catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setRevdate(" ");
		}
	}

	private static void readEmailList(Element firstCoverElement) {
		//##############Read in the <emaillist> element################
		//------
		NodeList emaillistList = firstCoverElement.getElementsByTagName("emaillist");
		Element emaillistListElement = (Element)emaillistList.item(0);

		NodeList textELList = emaillistListElement.getChildNodes();
		//System.out.println("Emaillist : " + 
		//       ((Node)textELList.item(0)).getNodeValue().trim());
				
		try{ 
			String emaillistTag = (((Node)textELList.item(0)).getNodeValue().trim());
			cover.setEmaillist(emaillistTag);
		}catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setEmaillist(" ");
		}
	}

	private static void readSynopsis(Element firstCoverElement) {
		//##############Read in the <synopsis> element################
		//------
		NodeList synopsisList = firstCoverElement.getElementsByTagName("synopsis");
		Element synopsisElement = (Element)synopsisList.item(0);

		NodeList textSYList = synopsisElement.getChildNodes();
		//System.out.println("Synopsis : " + 
		//       ((Node)textSYList.item(0)).getNodeValue().trim());
		
		try{ 
			String synopsisTag = (((Node)textSYList.item(0)).getNodeValue().trim());
			cover.setSynopsis(synopsisTag);
		}catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setSynopsis(" ");
		}
	}

	private static void readLegal(Element firstCoverElement) {
		//##############Read in the <legal> element################
		//------
		NodeList legalList = firstCoverElement.getElementsByTagName("legal");
		Element legalElement = (Element)legalList.item(0);

		NodeList textLEList = legalElement.getChildNodes();
		//System.out.println("Legal : " + 
		//       ((Node)textLEList.item(0)).getNodeValue().trim());
		
		try{
			String legalTag = (((Node)textLEList.item(0)).getNodeValue().trim());
			cover.setLegal(legalTag);
		}catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setLegal(" ");
		}
	}

	private static void readDate(Element firstCoverElement) {
		//##############Read in the <date> element################
		//------
		NodeList dateList = firstCoverElement.getElementsByTagName("date");
		Element dateElement = (Element)dateList.item(0);

		NodeList textDAList = dateElement.getChildNodes();
		//System.out.println("Date : " + 
		//       ((Node)textDAList.item(0)).getNodeValue().trim());
		try{
			String dateTag = (((Node)textDAList.item(0)).getNodeValue().trim());
			cover.setDate(dateTag);
		}
		catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setDate(" ");
		}
	}

	private static void readAuthorList(Element firstCoverElement) {
		//##############Read in the <authorlist> element################
		//------
		NodeList authorlistList = firstCoverElement.getElementsByTagName("authorlist");
		Element authorlistElement = (Element)authorlistList.item(0);

		NodeList textALList = authorlistElement.getChildNodes();
		//System.out.println("Authorlist : " + 
		//       ((Node)textALList.item(0)).getNodeValue().trim());
		try{
			String authorList = (((Node)textALList.item(0)).getNodeValue().trim());
			cover.setAuthorlist(authorList);
		}
		catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setAuthorlist(" ");
		}
	}

	private static void readAddress(Element firstCoverElement) {
		//##############Read in the <address> element################
		//------
		NodeList addressList = firstCoverElement.getElementsByTagName("address");
		Element addressElement = (Element)addressList.item(0);

		NodeList textAList = addressElement.getChildNodes();
		//System.out.println("Address : " + 
		//       ((Node)textAList.item(0)).getNodeValue().trim());
		try{
			String addressTag = (((Node)textAList.item(0)).getNodeValue().trim());
			cover.setAddress(addressTag);
		}
		catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setAddress(" ");
		}
	}

	private static void readGroup(Element firstCoverElement) {
		//##############Read in the <group> element################
		//------
		NodeList groupList = firstCoverElement.getElementsByTagName("group");
		Element groupElement = (Element)groupList.item(0);

		NodeList textGList = groupElement.getChildNodes();
		//System.out.println("Group : " + 
		//       ((Node)textGList.item(0)).getNodeValue().trim());
		try{
			String groupTag = (((Node)textGList.item(0)).getNodeValue().trim());
			cover.setGroup(groupTag);
		}
		catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setGroup(" ");
		}
	}

	private static void readCoverTitle(Element firstCoverElement) {
		//##############Read in the <covertitle> element################
		//----
		NodeList covertitleList = firstCoverElement.getElementsByTagName("covertitle");
		Element covertitleElement = (Element)covertitleList.item(0);

		NodeList textCTList = covertitleElement.getChildNodes();
		//System.out.println("Covertitle : " + 
		//       ((Node)textCTList.item(0)).getNodeValue().trim());
		try{
			String coverTitleTag = ((Node)textCTList.item(0)).getNodeValue().trim();
			cover.setCovertitle(coverTitleTag);
		}
		catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setCovertitle(" ");
		}
	}

	private static void readMiniName(Element firstCoverElement) {
		//##############Read in the <mininame> element################
		//-------
		NodeList mininameList = firstCoverElement.getElementsByTagName("mininame");
		Element mininameElement = (Element)mininameList.item(0);

		NodeList textMNList = mininameElement.getChildNodes();
		//System.out.println("Mininame : " + 
		//       ((Node)textMNList.item(0)).getNodeValue().trim());
		try{
			String miniNameTag = ((Node)textMNList.item(0)).getNodeValue().trim();
			cover.setMininame(miniNameTag);
		}
		catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setMininame(" ");
		}
	}

	private static void readMiniTitle(Element firstCoverElement) {
		//##############Read in the <minititle> element################
		//-------
		NodeList minititleList = firstCoverElement.getElementsByTagName("minititle");
		Element firstNameElement = (Element)minititleList.item(0);

		NodeList textMTList = firstNameElement.getChildNodes();
		//System.out.println("Minititle : " + 
		//       ((Node)textMTList.item(0)).getNodeValue().trim());
		try{
			String miniTitleTag = (((Node)textMTList.item(0)).getNodeValue().trim());
			cover.setMinititle(miniTitleTag);  
		}
		catch(java.lang.NullPointerException e){
			//Check to make sure the tag is not empty
			cover.setMinititle(" ");
		}
	}

	public static void setCover(Coverpage cover) {
		ReadAndPrintXMLFile.cover = cover;
	}

	public static Coverpage getCover() {
		return cover;
	}

	
}
