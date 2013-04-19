package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllVideosOnSearchResultsPage;

import k12.BaseWebPageTest;


public class ViewAllVideosOnSearchResultsPageTest extends BaseWebPageTest{

	private ViewAllVideosOnSearchResultsPage videosDisplayGroup;
	
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		videosDisplayGroup = new ViewAllVideosOnSearchResultsPage(searchTerm);
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyContentResultsInVideos() throws Exception {
		Assert.assertTrue(videosDisplayGroup.verifyContentResultsInVideos());
	}
	
	@Test(dependsOnMethods = { "verifyContentResultsInVideos" })
	public void verifyViewAllIsDisplayed() throws Exception {
		Assert.assertTrue(videosDisplayGroup.viewAllIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyViewAllIsDisplayed" })
	public void verifyVideosCountIsDisplayed() throws Exception {
		Assert.assertTrue(videosDisplayGroup.verifyVideosCountIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyVideosCountIsDisplayed" })
	public void verifyViewAllIsNotDisplayed() throws Exception {
		Assert.assertTrue(videosDisplayGroup.verifyViewAllIsNotDisplayed());
	}

	@Test(dependsOnMethods = { "verifyViewAllIsNotDisplayed" })
	public void verifyVideosHeading() throws Exception {
		Assert.assertTrue(videosDisplayGroup.verifyVideosHeadingIsPresent());
	}

	@Test(dependsOnMethods = { "verifyVideosHeading" })
	public void verifyMarkMeForVideos() throws Exception {
		Assert.assertTrue(videosDisplayGroup.ensureMarkOptionIsAvailable());
	}
	
	@Test(dependsOnMethods = { "verifyMarkMeForVideos" })
	public void verifyMouseOverOfVideo() throws Exception {
		Assert.assertTrue(videosDisplayGroup.verifyTooltipIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyMouseOverOfVideo" })
	public void verifyDetailVideoNavigation() throws Exception {
		Assert.assertTrue(videosDisplayGroup.detailVideoNavigation());
	}
	
	@Test(dependsOnMethods = { "verifyDetailVideoNavigation" })
	public void verifyNextLinkIsWorking() throws Exception {
		Assert.assertTrue(videosDisplayGroup.verifyNextLinkIsworking());
	}
	
	@Test(dependsOnMethods = { "verifyNextLinkIsWorking" })
	public void verifyPreviousLinkIsWorking() throws Exception {
		Assert.assertTrue(videosDisplayGroup.verifyPreviousLinkIsworking());
	}
	
	@Parameters( { "searchTermForNoVideos" })
	@Test(dependsOnMethods = { "verifyPreviousLinkIsWorking" })
	public void verifyVideoDisplayGroupIsNotDisplayed(String searchTermForNoVideos) throws Exception {
		doBasicSearchUsingSearchTerm(searchTermForNoVideos);
		Assert.assertTrue(videosDisplayGroup.verifyVideosDisplayGroupNotDisplayed());
	}
}
