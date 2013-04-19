package k12.dev.searchresults;


import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;

public class PortalPagesSearchResultsTest extends BaseDevWebPageTest {

	private static String SEARCH_TERM_PORTAL_PAGE_PORTLET = "adoption";
	
	private static String SEARCH_TERM_FOR_NO_PORTAL_PAGE_PORTLET = "war";
	
	private ListViewPortlet viewPortalPagesDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewPortalPagesDisplayGroup = new ListViewPortlet("Portals");
	}

	
	
	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm(SEARCH_TERM_PORTAL_PAGE_PORTLET);		
	}
	
	// uncomment when the cat content is loaded to ocean

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		
		Assert.assertTrue(viewPortalPagesDisplayGroup.checkDisplayGroupIsPresent());
		
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void isPortalPageTitlePresent() throws Exception {
		
		Assert.assertTrue(viewPortalPagesDisplayGroup.checkForDivision("Portals_first_portal_title"));
	}
	
	@Test(dependsOnMethods = { "isPortalPageTitlePresent" })
	public void searchValidationMessageWhenNoPortalPagePortletIsPresent() throws Exception {
		
		doBasicSearchUsingSearchTerm(SEARCH_TERM_FOR_NO_PORTAL_PAGE_PORTLET);		
	}
	
	@Test(dependsOnMethods = { "searchValidationMessageWhenNoPortalPagePortletIsPresent" })
	public void isPortalPagesPortletPresent() throws Exception {
		
		Assert.assertFalse(viewPortalPagesDisplayGroup.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isPortalPagesPortletPresent" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewPortalPagesDisplayGroup.doesContentExistInClass(viewPortalPagesDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
}	


