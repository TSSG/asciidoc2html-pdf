package test;

import java.io.File;
import main.Coverpage;
import main.CoverpageHelper;
import junit.framework.TestCase;

/*
<coverpage>
	 <minititle>Technical Report TSSG-YYYY-Area-00001</minititle>
	 <mininame>Behavioural Driven Development</mininame>
	 <covertitle>Behavioural Driven Development Report</covertitle>
	 <subtitle>Technical Report Sub-Title</subtitle>
	 <group>Telecommunications Software and Systems Group (TSSG)</group>
	 <address>Waterford Institute of Technology, West Campus, Carriganore, Waterford, Ireland</address>
	 <authorlist>David Kirwan, Some otherguy, And Anotherguy</authorlist>
	 <date>9th January 2012</date>
	 <legal>(C) Waterford Institute of Technology</legal>
	<synopsis>Writing robust software requires constant Test/Behaviour driven development. Best practices suggest writing the tests first, 
	and then the implementation after.  This allows a software system to be continually tested, checked and verified to ensure new changes do
	not break other elements of the system unintentionally.
	</synopsis>
</coverpage>

*/


public class CoverpageHelperTest extends TestCase
{

	public CoverpageHelperTest(String nm)
	{
		super(nm);
	}


	public void testSetDiskPathInput()
	{
		CoverpageHelper.setDiskPathInput("template.xml");
		assertEquals(CoverpageHelper.getDiskPathInput(), "template.xml");
	}
	
	public void testSetDiskPathOutput()
	{
		CoverpageHelper.setDiskPathOutput("resources/test.xml");
		assertEquals(CoverpageHelper.getDiskPathOutput(), "resources/test.xml");
	}
	
	public void testLoadData()
	{
		Coverpage cover = new Coverpage();
		CoverpageHelper.setDiskPathInput("resources/template.xml");
		CoverpageHelper.loadData(new File("resources/template.xml"));
		cover = CoverpageHelper.getCover();
		assertEquals(cover.getMinititle(), "Technical Report TSSG-YYYY-Area-00001");
		assertEquals(cover.getMininame(), "Behavioural Driven Development");
		assertEquals(cover.getCovertitle(), "Behavioural Driven Development Report");
		assertEquals(cover.getGroup(), "Telecommunications Software and Systems Group (TSSG)");
		assertEquals(cover.getAddress(), "Waterford Institute of Technology, West Campus, Carriganore, Waterford, Ireland");
		assertEquals(cover.getAuthorlist(), "David Kirwan, Some otherguy, And Anotherguy");
		assertEquals(cover.getDate(), "9th January 2012");
		assertEquals(cover.getLegal(), "(C) Waterford Institute of Technology");
		assertEquals(cover.getSynopsis(), "Writing robust software requires constant Test/Behaviour driven development. Best practices suggest writing the tests first, and then the implementation after. This allows a software system to be continually tested, checked and verified to ensure new changes do not break other elements of the system unintentionally.");
	}
}