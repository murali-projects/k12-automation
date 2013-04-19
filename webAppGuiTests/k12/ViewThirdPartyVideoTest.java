package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewVideoDetails;

public class ViewThirdPartyVideoTest extends BaseWebPageTest{
	private ViewVideoDetails videoDetails;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		videoDetails = new ViewVideoDetails();
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void checkDetailedPageIsDisplayed() throws Exception {
		Assert.assertTrue(videoDetails.verifyDetailedViewOfVideoIsDisplayed());
	}
	
	 @Test(dependsOnMethods = { "checkDetailedPageIsDisplayed" })
	public void verifyVideoIsDispayed() throws Exception {
		Assert.assertTrue(videoDetails.verifyVideoIsDispayed());
	}
	
}
