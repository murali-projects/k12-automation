package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllAudioOnSearchResultsPage;

public class ViewAllAudioOnSearchResultsPageTest extends BaseWebPageTest {

	private ViewAllAudioOnSearchResultsPage viewAllAudioOnSearchResultsPage;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		viewAllAudioOnSearchResultsPage = new ViewAllAudioOnSearchResultsPage(searchTerm);
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test(dependsOnMethods = { "basicSearch" })
	public void checkAudioTextIsDisplayed() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.verifyAudioTextIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "checkAudioTextIsDisplayed" })
	public void checkViewAllForAudio() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.checkViewAllForAudio());
	}
	
	@Test(dependsOnMethods = { "checkViewAllForAudio" })
	public void verifyInLineAudioCount() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.inLineValidationForDisplayCount());
	}
	
	@Test(dependsOnMethods = { "verifyInLineAudioCount" })
	public void verifyInLineAudioViewAll() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.inLineValidationForViewAll());
	}
	
	@Test(dependsOnMethods = { "verifyInLineAudioViewAll" })
	public void verifyAudioLinksDisplayed() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.verifyAudioContentLinksAreDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyAudioLinksDisplayed" })
	public void validateAudioLinksWithOcean() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.validateAudioDocumentTilesWithDB());
	}
	
	@Test(dependsOnMethods = { "validateAudioLinksWithOcean" })
	public void verifyViewAllIsLink() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.validateViewAllLink());
	}
	
	@Test(dependsOnMethods = { "verifyViewAllIsLink" })
	public void verifyViewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.checkViewAllLinkIsWorking());
	}
	
	//Audio Icon display hasn't been implemented
	/*@Test(dependsOnMethods = { "verifyViewAllLinkIsWorking" })
	public void verifyInLineAudioIcon() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.inLineValidationForAudioIcon());
	}*/

	//This test has been moved for Manual
	/*@Test(dependsOnMethods = { "verifyInLineAudioIcon" })
	public void verifyAudioContentNavigation() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.verifyAudioContentNavigation());
	}*/
	
	@Test(dependsOnMethods = { "verifyViewAllLinkIsWorking" })
	public void pageHidination() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.pageHidination());
	}
	
	@Test(dependsOnMethods = { "pageHidination" })
	public void pageNumberNavigation() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.pageNumberNavigation(2));
	}
	
	@Test(dependsOnMethods = { "pageNumberNavigation" })
	public void shortDescriptionOfEachLink() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.shortDescriptionOfEachLink());
	}
	
	@Test(dependsOnMethods = { "pageNumberNavigation" })
	public void lastPageVerification() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.lastPageVerification());
	}
	
	@Test(dependsOnMethods = { "lastPageVerification" })
	public void firstPageVerification() throws Exception {
		Assert.assertTrue(viewAllAudioOnSearchResultsPage.firstPageVerification());
	}
}
