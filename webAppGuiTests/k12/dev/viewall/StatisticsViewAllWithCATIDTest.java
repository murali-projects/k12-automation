package k12.dev.viewall;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.GoogleAnalyticsElement;
import webPageContainers4Testing.dev.ViewFullListPortlet;

public class StatisticsViewAllWithCATIDTest extends BaseDevWebPageTest {

	private ViewFullListPortlet viewAllStatistics;
	
	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewAllStatistics = new ViewFullListPortlet("statistics"); 

	}

//	@Test
//	public void searchValidationMessageIsDisplayed() throws Exception {
//		viewAllStatistics.openPage("statistics.catid.portalpage.url");
//	}

	@Test
	public void openViewAllPage() throws Exception {
		viewAllStatistics.openPage("statistics.catid.portalpage.url");
	}
	
	@Test(dependsOnMethods = { "openViewAllPage" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewAllStatistics.doesContentExistInClass(viewAllStatistics.getProperty("footerDiv"),
				"footerGaleLogo"));
	}

	@Test(dependsOnMethods = { "isFooterPresent" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkViewAllLinkIsWorking());
		GoogleAnalyticsElement gae = new GoogleAnalyticsElement();
		Assert.assertTrue(gae.isPresent());
		Assert.assertEquals(gae.getJavaScriptBlockCount(),3);
	}
	
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void checkExplicitCATEntryShowup() throws Exception{
		Assert.assertTrue(viewAllStatistics.doesDocumentIdShowupInOrder("viewAll_docId_table_xpath", "viewAll_docId_link_xpath", "EJ2210023773", 1));
		Assert.assertTrue(viewAllStatistics.doesDocumentIdShowupInOrder("viewAll_docId_table_xpath", "viewAll_docId_link_xpath", "EJ2210066256", 2));
	}
	
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void viewAllNavigation() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkViewAllIsNavigatedCorrectly());
	}

	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void contentLevelIndicatorIsPresent() throws Exception {
		Assert.assertTrue(viewAllStatistics.verifyContentLevelIndicatorIsDisplayed());
	}

	@Test(dependsOnMethods = { "contentLevelIndicatorIsPresent" })
	public void verifyContentLevelIndAlternateTextIsDisplayed() throws Exception{
		Assert.assertTrue(viewAllStatistics.verifyContentLevelIndicatorHasAltText());
	}
	
	
	@Test(dependsOnMethods = { "verifyContentLevelIndAlternateTextIsDisplayed" })
	public void verifyDocumentDisplayPageResults()
			throws Exception {
		Assert.assertTrue(viewAllStatistics
				.verifyDocumentFullListPage());
	}

	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResults" })
	public void verifyNextLink() throws Exception {
		Assert.assertTrue(viewAllStatistics.verifyNextLink());
	}

	@Test(dependsOnMethods = { "verifyNextLink" })
	public void verifyNextLinkIsworking() throws Exception {
		Assert.assertTrue(viewAllStatistics.verifyNextLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyNextLinkIsworking" })
	public void verifyPreviousLink() throws Exception {
		Assert.assertTrue(viewAllStatistics.verifyPreviousLink());
	}

	@Test(dependsOnMethods = { "verifyPreviousLink" })
	public void verifyPreviousLinkIsworking()
			throws Exception {
		Assert.assertTrue(viewAllStatistics.verifyPreviousLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyPreviousLinkIsworking" })
	public void verifyLastLink() throws Exception {
		Assert.assertTrue(viewAllStatistics.verifyLastLink());
	}

	@Test(dependsOnMethods = { "verifyLastLink" })
	public void verifyLastLinkIsworking() throws Exception {
		Assert.assertTrue(viewAllStatistics.verifyLastLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyLastLinkIsworking" })
	public void verifyFirstLink() throws Exception {
		Assert.assertTrue(viewAllStatistics.verifyFirstLink());
	}

	@Test(dependsOnMethods = { "verifyFirstLink" })
	public void verifyFirstLinkIsworking() throws Exception {
		Assert.assertTrue(viewAllStatistics.verifyFirstLinkIsworking());
	}
	
	@Test(dependsOnMethods = { "verifyFirstLinkIsworking" })
	public void verifyArticleDisplayWorking() throws Exception {
		Assert.assertTrue(viewAllStatistics
				.checkDetailedViewOfFirstItemIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyArticleDisplayWorking" })
	public void verifyDocInfoTypeDetailDisplayed() throws Exception {
		Assert.assertTrue(viewAllStatistics
				.checkDocInfoTypeOfFirstItemIsDisplayed());
	}
	
	@Test(dependsOnMethods = {"verifyLastLinkIsworking"})
	public void verifySortOrderChange() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkSortChange());
	}
	 
	@Test(dependsOnMethods = {"verifySortOrderChange"})
	public void verifyFirstPageAfterSortOrderChange() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkFirstPageDisplayText());
	}
	

	@Test(dependsOnMethods = {"verifyFirstPageAfterSortOrderChange"})
	public void verifyContentGroupTabsDisplay() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkForContentGroupDisplay());
	}
	
	@Test(dependsOnMethods = {"verifyContentGroupTabsDisplay"})
	public void verifyViewAllPageNameDisplay() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkForViewAllPageName("statistics_fullList_page_name"));
	}
	
	@Test(dependsOnMethods = {"verifyViewAllPageNameDisplay"})
	public void verifyWorkingOfLinksInContentGroupSelector() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkForWorkingOfContentGroupSelector("statistics_fullList_page_name"));
	}
	
	// Document Type Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifyDocumentTypeLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_doctype_label_expected");
		Assert.assertTrue(viewAllStatistics.checkForBrowseIndexLabel(expectedLabel, "browse_index_doctype_label"));
	}	

	@Test(dependsOnMethods = { "verifyDocumentTypeLabelIsPresent" })
	public void verifyDocumentTypeLinksCount() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkForBrowseIndexLinksExist("browse_index_doctype_links"));
	}	
	
	// TODO: Enable this test when the MLSG Ocean index has been created
	// Subject Limiters	(For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" }, enabled=false)
	public void verifySubjectLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_subject_label_expected");
		Assert.assertTrue(viewAllStatistics.checkForBrowseIndexLabel(expectedLabel, "browse_index_subject_label"));
	}	

	@Test(dependsOnMethods = { "verifySubjectLabelIsPresent" }, enabled=false)
	public void verifySubjectLinksCount() throws Exception {
		Assert.assertTrue(viewAllStatistics.checkForBrowseIndexLinksExist("browse_index_subject_links"));
	}		
}
