package k12.dev.viewall;

import static org.testng.Assert.assertTrue;
import k12.dev.BaseDevWebPageTest;

import org.testng.annotations.Test;

import webPageContainers4Testing.dev.VideosFullPortlet;
import webPageContainers4Testing.dev.VideosPortlet;
import webPageContainers4Testing.dev.ViewFullListPortlet;

public class SearchWithinResultsTest extends BaseDevWebPageTest {

	private ViewFullListPortlet viewAllReferences;
	private VideosPortlet videosPortlet;
	private VideosFullPortlet videosFullPortlet;

	public SearchWithinResultsTest() throws Exception {
		viewAllReferences = new ViewFullListPortlet("reference");
		videosPortlet = new VideosPortlet();
		videosFullPortlet = new VideosFullPortlet();
	}

	@Test
	public void goToReferenceViewAll() throws Exception {
		doBasicSearchUsingSearchTerm("water");
		viewAllReferences.checkViewAllLinkIsWorking();
		assertTrue(viewAllReferences.checkViewAllIsNavigatedCorrectly());
	}

	@Test(dependsOnMethods={"goToReferenceViewAll"})
	public void checkSearchWithin() throws Exception {
		assertTrue(viewAllReferences.verifySearchWithinResultsCount("pollution"));
	}
	
	@Test(dependsOnMethods={"goToReferenceViewAll"})
	public void checkSearchWithinNoResults() throws Exception {
		assertTrue(viewAllReferences.verifySearchWithinResultsForNoResultsText("aaahhhhdsadssadd"));
	}
	
	@Test(dependsOnMethods={"checkSearchWithinNoResults"})
	public void goToVideosViewAll() throws Exception {
		doBasicSearchUsingSearchTerm("water");
		videosPortlet.navigateToViewAll();
	}
	

	
	@Test(dependsOnMethods={"goToVideosViewAll"})
	public void checkSearchMediaWithin() throws Exception {
		assertTrue(videosFullPortlet.verifySearchWithinResultsCount("island"));
	}
	
	@Test(dependsOnMethods={"checkSearchMediaWithin"})
	public void checkSearchWithinNoResultsForMedia() throws Exception {
		assertTrue(videosFullPortlet.verifySearchWithinResultsForNoResultsText("aaahhhhdsadssadd"));
	}
	
	@Test (dependsOnMethods = { "checkSearchWithinNoResultsForMedia" })
	public void checkReferenceNavigationTabs() throws Exception{
		goToReferenceViewAll();
		assertTrue(viewAllReferences.verifyNavigationTabCount());
		
	}
	
	@Test (dependsOnMethods = { "checkReferenceNavigationTabs" })
	public void checkAdditionalSearchWithin() throws Exception{
		goToReferenceViewAll();
		assertTrue(viewAllReferences.verifySearchWithinResultsCount("pollution"));
		assertTrue(viewAllReferences.verifySearchWithinResultsCount("resource"));
	}
	

}
