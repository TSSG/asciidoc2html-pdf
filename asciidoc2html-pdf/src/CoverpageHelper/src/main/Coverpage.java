package main;

/**
 * 
 * @author David Kirwan
 * @email dkirwan@tssg.org
 * @description Storage class for holding the coverpage xml contents
 *
 */

/*
<coverpage>
<minititle>Technical Report TSSG-2012</minititle>
<mininame>BDD Testing in Java</mininame>
<covertitle>BDD Testing in Java</covertitle>
<group>Telecommunications Software and Systems Group (TSSG)</group>
<address>Waterford Institute of Technology, West Campus, Carriganore, Waterford, Ireland</address>
<authorlist>David Kirwan</authorlist>
<emaillist>dkirwan@tssg.org</emaillist>
<revdate>February 20, 2012</revdate>
<revnumber>n/a</revnumber>
<date>February 20, 2012</date>
<legal>(C) Waterford Institute of Technology</legal>
<synopsis>Report detailing various technologies availible for testing code and evaluating code test coverage in Java</synopsis>
<encoding>iso-8859-1</encoding>
<toc>true</toc>
</coverpage>

should map to template below

:reporttype:    Deliverable Mobile Cloud Alliance CF 2011 1303
:reporttitle:   D1.1 Market Awareness and Engagement Report
:author:        Christine O’Meara, Miguel Ponce de Leon
:email:         comeara@tssg.org, miguelpdl@tssg.org
:group:         Telecommunications Software and Systems Group (TSSG)
:address:       Waterford Institute of Technology, West Campus, Carriganore, Waterford, Ireland
:revdate:       April 3, 2012
:revnumber:     https://redmine.tssg.org/issues/4096[Issue 4096]
:docdate:       March 31, 2012
:description:   This interim report reviews the market landscape and competitive proposition facing the MCA team as it seeks to deliver a product that will$
:legal:         (C) Waterford Institute of Technology
:encoding:      iso-8859-1
:toc:
 */


public class Coverpage 
{
	private String minititle;
	private String covertitle;
	private String group;
	private String address;
	private String authorlist;
	private String emaillist;
	private String revdate;
	private String revnumber;
	private String date;
	private String legal;
	private String synopsis;
	private String encoding;
	private boolean toc;
	
	public Coverpage(){		
		this.minititle = "";
		this.covertitle = "";
		this.group = "";
		this.address = "";
		this.authorlist = "";
		this.emaillist = "";
		this.revdate = "";
		this.revnumber = "";
		this.date = "";
		this.legal = "";
		this.synopsis = "";
		this.encoding = "";
		this.toc = false;
	}
	
	public String toString(){		
		return "Minititle: " + this.minititle + 
				"\nMininame: " + this.covertitle +
				"\nCovertitle: " + this.covertitle + 
				"\nGroup: " + this.group + 
				"\nAddress: " + this.address + 
				"\nAuthorlist " + this.authorlist +
				"\nEmail List: " + this.emaillist +
				"\nRev Date: " + this.revdate +
				"\nRev Number: " + this.revnumber +
				"\nDate: " + this.date + 
				"\nLegal: " + this.legal + 
				"\nSynopsis: " + this.synopsis +
				"\nEncoding: " + this.encoding +
				"\nTOC: " + this.toc;
	}
	
	public void setMinititle(String minititle) {
		this.minititle = minititle;
	}
	public String getMinititle() {
		return minititle;
	}
	public void setMininame(String mininame) {
		this.covertitle = mininame;
	}
	public String getMininame() {
		return covertitle;
	}
	public void setCovertitle(String covertitle) {
		this.covertitle = covertitle;
	}
	public String getCovertitle() {
		return covertitle;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getGroup() {
		return group;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setAuthorlist(String authorlist) {
		this.authorlist = authorlist;
	}
	public String getAuthorlist() {
		return authorlist;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}
	public String getLegal() {
		return legal;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getSynopsis() {
		return synopsis;
	}

	public String getEmaillist() {
		return emaillist;
	}

	public void setEmaillist(String emaillist) {
		this.emaillist = emaillist;
	}

	public String getRevdate() {
		return revdate;
	}

	public void setRevdate(String revdate) {
		this.revdate = revdate;
	}

	public String getRevnumber() {
		return revnumber;
	}

	public void setRevnumber(String revnumber) {
		this.revnumber = revnumber;
	}

	public boolean isToc() {
		return toc;
	}

	public void setToc(boolean toc) {
		this.toc = toc;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}	
}
