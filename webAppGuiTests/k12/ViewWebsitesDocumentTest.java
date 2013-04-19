package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewWebsitesDocument;

public class ViewWebsitesDocumentTest extends BaseWebPageTest{

	private ViewWebsitesDocument websitesDocument;
	
	@BeforeTest
	@Parameters({"searchTerm"})
	public void setUp(String searchTerm) throws Exception{
		websitesDocument = new ViewWebsitesDocument();
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void verifyDocumentTitlePresent(){
		Assert.assertTrue(websitesDocument.verifyElementPresent("websites_document_title"));
	}
	
	@Test(dependsOnMethods = "verifyDocumentTitlePresent")
	public void verifyShortCitationPresent(){
		Assert.assertTrue(websitesDocument.verifyElementPresent("short_citation"));
	}
	
	@Test(dependsOnMethods = "verifyShortCitationPresent")
	public void verifyWebsiteAnnotationPresent(){
		Assert.assertTrue(websitesDocument.verifyElementPresent("annotation"));
	}
	
	@Test(dependsOnMethods = "verifyWebsiteAnnotationPresent")
	public void verifyLinkToWebsitePresent(){
		Assert.assertTrue(websitesDocument.verifyElementPresent("linkToWebsite"));
	}
	
	@Test(dependsOnMethods = "verifyLinkToWebsitePresent")
	public void verifySourceCitationPresent(){
		Assert.assertTrue(websitesDocument.verifyElementPresent("source_citation"));
	}
	
	@Test(dependsOnMethods = "verifySourceCitationPresent")
	public void verifyGaleDocumentNoPresent(){
		Assert.assertTrue(websitesDocument.verifyElementPresent("gale_document"));
	}
	
}
