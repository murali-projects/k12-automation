package k12.dev.viewall;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.GoogleAnalyticsElement;
import webPageContainers4Testing.dev.ViewFullListPortlet;

public class ViewpointViewAllTest extends BaseDevWebPageTest {
	private ViewFullListPortlet viewAllViewpoints;

	@BeforeTest
	public void setUp() throws Exception {

		viewAllViewpoints = new ViewFullListPortlet( "viewpoints"); 
	}

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");		
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewAllViewpoints.doesContentExistInClass(viewAllViewpoints.getProperty("footerDiv"),
				"footerGaleLogo"));
	}

	@Test(dependsOnMethods = { "isFooterPresent" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkViewAllLinkIsWorking());
		GoogleAnalyticsElement gae = new GoogleAnalyticsElement();
		Assert.assertTrue(gae.isPresent());
		Assert.assertEquals(gae.getJavaScriptBlockCount(),3);
	}
	
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void viewAllNavigation() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkViewAllIsNavigatedCorrectly());
	}

	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void documentFavIconsAreDisplayed() throws Exception {
		Assert.assertTrue(viewAllViewpoints.verifyDocumentFavIconsAreDisplayed());
		Assert.assertTrue(viewAllViewpoints.verifyCorrectDocumentFavIconAltTextIsDisplayed());
	}
	
	
	@Test(dependsOnMethods = { "documentFavIconsAreDisplayed" })
	public void verifyDocumentDisplayPageResults()
			throws Exception {
		Assert.assertTrue(viewAllViewpoints
				.verifyDocumentFullListPage());
	}

	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResults" })
	public void verifyNextLink() throws Exception {
		Assert.assertTrue(viewAllViewpoints.verifyNextLink());
	}

	@Test(dependsOnMethods = { "verifyNextLink" })
	public void verifyNextLinkIsworking() throws Exception {
		Assert.assertTrue(viewAllViewpoints.verifyNextLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyNextLinkIsworking" })
	public void verifyPreviousLink() throws Exception {
		Assert.assertTrue(viewAllViewpoints.verifyPreviousLink());
	}

	@Test(dependsOnMethods = { "verifyPreviousLink" })
	public void verifyPreviousLinkIsworking()
			throws Exception {
		Assert.assertTrue(viewAllViewpoints.verifyPreviousLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyPreviousLinkIsworking" })
	public void verifyLastLink() throws Exception {
		Assert.assertTrue(viewAllViewpoints.verifyLastLink());
	}

	@Test(dependsOnMethods = { "verifyLastLink" })
	public void verifyLastLinkIsworking() throws Exception {
		Assert.assertTrue(viewAllViewpoints.verifyLastLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyLastLinkIsworking" })
	public void verifyFirstLink() throws Exception {
		Assert.assertTrue(viewAllViewpoints.verifyFirstLink());
	}

	@Test(dependsOnMethods = { "verifyFirstLink" })
	public void verifyFirstLinkIsworking() throws Exception {
		Assert.assertTrue(viewAllViewpoints.verifyFirstLinkIsworking());
	}
	
	@Test(dependsOnMethods = { "verifyFirstLinkIsworking" })
	public void verifyArticleDisplayWorking() throws Exception {
		Assert.assertTrue(viewAllViewpoints
				.checkDetailedViewOfFirstItemIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyArticleDisplayWorking" })
	public void verifyDocInfoTypeDetailDisplayed() throws Exception {
		Assert.assertTrue(viewAllViewpoints
				.checkDocInfoTypeOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = {"verifyLastLinkIsworking"})
	public void verifySortOrderChange() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkSortChange());
	}
	 
	@Test(dependsOnMethods = {"verifySortOrderChange"})
	public void verifyFirstPageAfterSortOrderChange() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkFirstPageDisplayText());
	}
	
	@Test(dependsOnMethods = {"verifyFirstPageAfterSortOrderChange"})
	public void verifyContentGroupTabsDisplay() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkForContentGroupDisplay());
	}
	
	@Test(dependsOnMethods = {"verifyContentGroupTabsDisplay"})
	public void verifyViewAllPageNameDisplay() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkForViewAllPageName("viewpoint_fullList_page_name"));
	}
	
	@Test(dependsOnMethods = {"verifyViewAllPageNameDisplay"})
	public void verifyWorkingOfLinksInContentGroupSelector() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkForWorkingOfContentGroupSelector("viewpoint_fullList_page_name"));
	}
	
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void documentShortDescriptionIsPresent() throws Exception {
		Assert
				.assertTrue(viewAllViewpoints
						.doesContentExistInId("content", "description_xpath"));
	}
	
	// Document Type Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifyDocumentTypeLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_doctype_label_expected");
		Assert.assertTrue(viewAllViewpoints.checkForBrowseIndexLabel(expectedLabel, "browse_index_doctype_label"));
	}	

	@Test(dependsOnMethods = { "verifyDocumentTypeLabelIsPresent" })
	public void verifyDocumentTypeLinksCount() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkForBrowseIndexLinksExist("browse_index_doctype_links"));
	}	
	
	// Subject Limiters	(For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifySubjectLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_subject_label_expected");
		Assert.assertTrue(viewAllViewpoints.checkForBrowseIndexLabel(expectedLabel, "browse_index_subject_label"));
	}	

	@Test(dependsOnMethods = { "verifySubjectLabelIsPresent" })
	public void verifySubjectLinksCount() throws Exception {
		Assert.assertTrue(viewAllViewpoints.checkForBrowseIndexLinksExist("browse_index_subject_links"));
	}		
}
