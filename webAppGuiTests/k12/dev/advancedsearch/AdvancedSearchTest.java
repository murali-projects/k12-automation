package k12.dev.advancedsearch;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import k12.dev.BaseDevWebPageTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.AdvancedSearchPage;
import webPageContainers4Testing.dev.HomePage;
import webPageContainers4Testing.dev.ListViewPortlet;
import webPageContainers4Testing.dev.VideosFullPortlet;
import webPageContainers4Testing.dev.VideosPortlet;
import webPageContainers4Testing.dev.ViewFullListPortlet;
import webPageContainers4Testing.dev.ViewSearchResultPortlet;
import webPageElements4Testing.Url;

public class AdvancedSearchTest extends BaseDevWebPageTest {

	private static final String REFERENCE_DISPLAY_GROUP = "reference";
	private HomePage homePage;
	private AdvancedSearchPage advancedSearchPage;
	private ViewSearchResultPortlet viewReferenceDisplayGroup;
	private ViewFullListPortlet viewAllReferences;
	private VideosPortlet videosPortlet;
	private VideosFullPortlet videosFullPortlet;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
		homePage = new HomePage();
		advancedSearchPage = new AdvancedSearchPage();
		viewReferenceDisplayGroup = new ListViewPortlet(REFERENCE_DISPLAY_GROUP);
		videosPortlet = new VideosPortlet();
		viewAllReferences = new ViewFullListPortlet(REFERENCE_DISPLAY_GROUP);
		videosFullPortlet = new VideosFullPortlet();
	}

	@Test
	public void gotoAdvancedSearchPage() throws Exception {
		homePage.clickOnAdvancedSearch();
		assertTrue(advancedSearchPage.checkifNavigatedToAdvancedSearchPage());

	}

	@Test(dependsOnMethods = { "gotoAdvancedSearchPage" })
	public void performAnAdvancedSearchForTextPortlets() throws Exception {
		advancedSearchPage.performAnAdvancedSearch("water");
		assertTrue(viewReferenceDisplayGroup.checkDisplayGroupIsPresent());
		validateTextPortletAdvanceSearchParameters();

	}

	@Test(dependsOnMethods = { "performAnAdvancedSearchForTextPortlets" })
	public void checkReferenceViewAll() throws Exception {
		int viewAllCountInList = viewReferenceDisplayGroup.getViewAllCount();
		viewReferenceDisplayGroup.goToViewAllPage();
		viewAllReferences.checkFirstPageDisplayText();
		int viewAllCountInFullList = viewAllReferences.getTotalSearchResultsCount();

		assertEquals(viewAllCountInFullList, viewAllCountInList);
		
		validateTextPortletAdvanceSearchParameters();

	}
	
	@Test (dependsOnMethods = { "checkReferenceViewAll" })
	public void checkReferenceNavigationTabs() throws Exception{
		assertTrue(viewAllReferences.verifyNavigationTabCount());
		validateTextPortletAdvanceSearchParameters();
		
	}

	@Test(dependsOnMethods = { "checkReferenceNavigationTabs" })
	public void performAdvancedSearchForMediaPortlets() throws Exception {
		gotoAdvancedSearchPage();
		advancedSearchPage.performAnAdvancedSearch("war");
		assertTrue(videosPortlet.getVideoPortletDiv().isPresent());
		validateMediaPorletURLAdvanceSearchParameters();

	}

	@Test(dependsOnMethods = { "performAdvancedSearchForMediaPortlets" })
	public void checkVideosViewAll() throws Exception {
		int viewAllCountInList = videosPortlet.getViewAllCount();
		videosPortlet.navigateToViewAll();
		int viewAllCountInFullList = viewAllReferences.getTotalSearchResultsCount();
		assertEquals(viewAllCountInFullList, viewAllCountInList);
		
		validateMediaPorletURLAdvanceSearchParameters();		
	}
	
	@Test (dependsOnMethods = { "checkVideosViewAll" })
	public void checkMediaNavigationTabs() throws Exception{
		int beforeNavigationTabCount = viewAllReferences.checkContentGroupLinkAndCount();
		viewAllReferences.clickFirstNavBarLink();
		int afterNavigationTabCount = viewAllReferences.checkContentGroupLinkAndCount();
		assertEquals(beforeNavigationTabCount, afterNavigationTabCount);
		
		validateMediaPorletURLAdvanceSearchParameters();
	}	

	
	private void validateTextPortletAdvanceSearchParameters() {
		assertTrue(Url.getCurrentUrl().contains("limiter=AC+y"));
		assertTrue(Url.getCurrentUrl().contains("query=KE+water"));
	}
		
	private void validateMediaPorletURLAdvanceSearchParameters() {
		assertTrue(Url.getCurrentUrl().contains("limiter=AC+y"));
		assertTrue(Url.getCurrentUrl().contains("query=KE+war"));
	}
	
}
