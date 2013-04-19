package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.HomePageSpotlight;

public class HomePageSpotlightTest extends BaseWebPageTest{
	
	private HomePageSpotlight homePageSpotlight;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		homePageSpotlight = new HomePageSpotlight();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifySpotlightPortletPage() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifySpotlightPortletPage());
	}
	
	@Test(dependsOnMethods = { "verifySpotlightPortletPage" })
	public void verifyImageInPortlet() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifyImageInPortlet());
	}
	
	@Test(dependsOnMethods = { "verifyImageInPortlet" })
	public void verifyDocumentTitleOrPortalPageTile() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifyDocumentTitleOrPortalPageTile());
	}
	
	@Test(dependsOnMethods = { "verifyDocumentTitleOrPortalPageTile" })
	public void verifyPageLeadToDetailedViewPage() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifyPageLeadToDetailedViewPage());
	}
	
	@Test(dependsOnMethods = { "verifyPageLeadToDetailedViewPage" })
	public void verifySnippetForDocumentLink() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifySnippetForDocumentLink());
	}
	
	@Test(dependsOnMethods = { "verifySnippetForDocumentLink" })
	public void verifySnippetForPortalPageTilte() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifySnippetForPortalPageTilte());
	}
	
	@Test(dependsOnMethods = { "verifySnippetForPortalPageTilte" })
	public void verifyReadMoreLink() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifyReadMoreLink());
	}
	
	@Test(dependsOnMethods = { "verifyReadMoreLink" })
	public void readMoreLinkDetailPage() throws Exception {
		Assert.assertTrue(homePageSpotlight.readMoreLinkDetailPage());
	}
	
	@Test(dependsOnMethods = { "readMoreLinkDetailPage" })
	public void verifyPortalOrDocumentCountInSpotlight() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifyPortalOrDocumentCountInSpotlight());
	}
	
	@Test(dependsOnMethods = { "verifyPortalOrDocumentCountInSpotlight" })
	public void verifyEachItemIsAppearedAsNumber() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifyEachItemIsAppearedAsNumber());
	}
	
	@Test(dependsOnMethods = { "verifyEachItemIsAppearedAsNumber" })
	public void verifySpotlightUpdates() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifySpotlightUpdates());
	}
	
	@Test(dependsOnMethods = { "verifySpotlightUpdates" })
	public void verifySpotlightUpdatesForAllFive() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifySpotlightUpdatesForAllFive());
	}
	
	@Test(dependsOnMethods = { "verifySpotlightUpdatesForAllFive" })
	public void verifyGreyedOutForCurrentPage() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifyGreyedOutForCurrentPage());
	}
	
	@Test(dependsOnMethods = { "verifyGreyedOutForCurrentPage" })
	public void verifyPortletForViewAllPage(String displayGroup) throws Exception {
		Assert.assertTrue(homePageSpotlight.verifyPortletForViewAllPage(displayGroup));
	}
	
	@Test(dependsOnMethods = { "verifyPortletForViewAllPage" })
	public void verifySpotlightNavigation() throws Exception {
		Assert.assertTrue(homePageSpotlight.verifySpotlightNavigation());
	}
	
	@Test(dependsOnMethods = { "verifySpotlightNavigation" })
	public void veriyfLinkNavigationInSpotlight() throws Exception {
		Assert.assertTrue(homePageSpotlight.linkNavigationInSpotlight());
	}
}
