package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateTextDocument;

public class IntegrateTextDocumentTest extends BaseWebPageTest{
	
	public IntegrateTextDocument integrateTextDocument;
	public String searchTerm;

	@Parameters({"searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		this.searchTerm = searchTerm;
		integrateTextDocument = new IntegrateTextDocument();
     }

	@Parameters({"referenceDisplayGroup"})
	@Test
	public void verifyDocumetIsDisplayedForReference(String displayGroup)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateTextDocument.verifyDocumentDisplay(displayGroup));
				
	}
	@Parameters({"referenceDisplayGroup"})
	@Test (dependsOnMethods={"verifyDocumetIsDisplayedForReference"})
	public void verifyGaleDocumentNumberForReference(String displayGroup)throws Exception{
		Assert.assertTrue(integrateTextDocument.verifyDocumentNoDisplay());
	}
	@Parameters({"magazinesDisplayGroup"})
	@Test
	public void verifyDocumetIsDisplayedForMagazines(String displayGroup)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateTextDocument.verifyDocumentDisplay(displayGroup));
		
	}
	@Parameters({"magazinesDisplayGroup"})
	@Test (dependsOnMethods={"verifyDocumetIsDisplayedForMagazines"})
	public void verifyGaleDocumentNumberForMagazines(String displayGroup)throws Exception{
		Assert.assertTrue(integrateTextDocument.verifyDocumentNoDisplay());
	}
	@Parameters({"viewpointsDisplayGroup"})
	@Test
	public void verifyDocumetIsDisplayedForViewPoints(String displayGroup)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateTextDocument.verifyDocumentDisplay(displayGroup));
		}
	@Parameters({"viewpointsDisplayGroup"})
	@Test (dependsOnMethods={"verifyDocumetIsDisplayedForViewPoints"})
	public void verifyGaleDocumentNumberForViewPoints(String displayGroup)throws Exception{
		Assert.assertTrue(integrateTextDocument.verifyDocumentNoDisplay());
	}
	@Parameters({"primarySourcesDisplayGroup"})
	@Test
	public void verifyDocumetIsDisplayedForPrimarySources(String displayGroup)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateTextDocument.verifyDocumentDisplay(displayGroup));
		}
	@Parameters({"primarySourcesDisplayGroup"})
	@Test (dependsOnMethods={"verifyDocumetIsDisplayedForPrimarySources"})
	public void verifyGaleDocumentNumberForPrimarySources(String displayGroup)throws Exception{
		Assert.assertTrue(integrateTextDocument.verifyDocumentNoDisplay());
	}
	@Parameters({"websitesDisplayGroup"})	
	@Test
	public void verifyDocumetIsDisplayedForWebsites(String displayGroup)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateTextDocument.verifyDocumentDisplay(displayGroup));
			}
	@Parameters({"websitesDisplayGroup"})
	@Test (dependsOnMethods={"verifyDocumetIsDisplayedForWebsites"})
	public void verifyGaleDocumentNumberForWebsites(String displayGroup)throws Exception{
		Assert.assertTrue(integrateTextDocument.verifyDocumentNoDisplay());
	}
}
