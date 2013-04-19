package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.BiographyInfoInDocumentDisplay;

public class DynamicGenerationOfTocTest extends BaseWebPageTest{
	private BiographyInfoInDocumentDisplay documentDisplay;
	
	@Parameters( { "searchTerm","displayGroup","sortBy" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup, String sortBy) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		documentDisplay = new BiographyInfoInDocumentDisplay(displayGroup, sortBy, searchTerm);
	}

	@Test
	public void verifyTocIsDisplayed() throws Exception {
		Assert.assertTrue(documentDisplay.verifyTocIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyTocIsDisplayed" })
	public void verifyContentsInToc() throws Exception {
		Assert.assertTrue(documentDisplay.verifyContentsInToc());
	}
}
