package k12.dev.viewall;

import static org.testng.Assert.*;
import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.AdvancedSearchPage;
import webPageContainers4Testing.dev.HomePage;
import webPageContainers4Testing.dev.ViewFullListPortlet;

public class BrowseIndexSearchResultsTest extends BaseDevWebPageTest {
	
	private ViewFullListPortlet viewAllReferences;
	private AdvancedSearchPage advancedSearchPage;
	private HomePage homePage;

	
	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewAllReferences = new ViewFullListPortlet("news");
		advancedSearchPage = new AdvancedSearchPage();
		homePage = new HomePage();
	} 

	@Test
	public void goToReferenceViewAll() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		viewAllReferences.checkViewAllLinkIsWorking();
		assertTrue(viewAllReferences.checkViewAllIsNavigatedCorrectly());		
	}
	
	@Test(dependsOnMethods = { "goToReferenceViewAll" })
	public void verifyTitleLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_title_label_expected");
		Assert.assertTrue(viewAllReferences.checkForBrowseIndexLabel(expectedLabel, "browse_index_title_label"));
	}
	
	@Test(dependsOnMethods = { "verifyTitleLabelIsPresent" })
	public void verifyLimiterAppliedForFirstPublicationTitleLink() throws Exception {
		assertTrue(viewAllReferences.verifyLimiterAppliedForFirstPublicationTitleLink());
	}
	
	@Test(dependsOnMethods = { "verifyLimiterAppliedForFirstPublicationTitleLink" })
	public void verifyLimiterAppliedForFirstDocumentTypeLink() throws Exception {
		assertTrue(viewAllReferences.verifyLimiterAppliedForFirstDocumentTypeLink());
	}
	
	@Test(dependsOnMethods = { "verifyLimiterAppliedForFirstPublicationTitleLink" })
	public void verifyLimiterAppliedForFirstPublicationTitleLinkFromAdvancedSearch() throws Exception {
		homePage.clickOnAdvancedSearch();
		advancedSearchPage.performAnAdvancedSearch("water");
		viewAllReferences.checkViewAllLinkIsWorking();
		assertTrue(viewAllReferences.verifyLimiterAppliedForFirstPublicationTitleLink());
	}
	
	@Test(dependsOnMethods = { "verifyLimiterAppliedForFirstPublicationTitleLinkFromAdvancedSearch" })
	public void verifyLimiterAppliedForFirstDocumentTypeLinkFromAdvancedSearch() throws Exception {
		assertTrue(viewAllReferences.verifyLimiterAppliedForFirstDocumentTypeLink());
	}
	
	

}
