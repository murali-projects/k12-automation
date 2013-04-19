package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.BiographyInfoInDocumentDisplay;

public class BiographyInfoInDocumentDisplayTest extends BaseWebPageTest{
	private BiographyInfoInDocumentDisplay documentDisplay;
	@Parameters( { "searchTerm","displayGroup","sortBy" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup, String sortBy) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		documentDisplay = new BiographyInfoInDocumentDisplay(displayGroup, sortBy, searchTerm);
	}

	@Test
	public void isNarrativeBiographyDocInfoTypePresent() throws Exception {
		Assert.assertTrue(documentDisplay.isNarrativeBiographyDocInfoTypePresent());
	}

	@Test(dependsOnMethods = { "isNarrativeBiographyDocInfoTypePresent" })
	public void verifyDocInfoTypeWithDB() throws Exception {
		Assert.assertTrue(documentDisplay.verifyDocInfoTypeWithDB());
	}
	
	@Test(dependsOnMethods = { "verifyDocInfoTypeWithDB" })
	public void isNarrativeBiographyDocInfoTypePresentInOtherPages() throws Exception {
		Assert.assertTrue(documentDisplay.isNarrativeBiographyDocInfoTypePresent());
	}
	
	@Test(dependsOnMethods = { "isNarrativeBiographyDocInfoTypePresentInOtherPages" })
	public void verifyDetailedDocumentIsDisplayed() throws Exception {
		Assert.assertTrue(documentDisplay.verifyDetailedDocumentIsDisplayed());
	}
	
}
