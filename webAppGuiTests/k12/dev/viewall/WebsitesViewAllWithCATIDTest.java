package k12.dev.viewall;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.GoogleAnalyticsElement;
import webPageContainers4Testing.dev.ViewFullListPortlet;

public class WebsitesViewAllWithCATIDTest extends BaseDevWebPageTest {

	private ViewFullListPortlet viewAllNews;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewAllNews = new ViewFullListPortlet("websites");

	}

	@Test
	public void openViewAllPage() throws Exception {
		viewAllNews.openPage("websites.catid.portalpage.url");
	}

	@Test(dependsOnMethods = { "openViewAllPage" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewAllNews.doesContentExistInClass(viewAllNews.getProperty("footerDiv"), "footerGaleLogo"));
	}

	@Test(dependsOnMethods = { "isFooterPresent" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewAllNews.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(viewAllNews.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(viewAllNews.checkViewAllLinkIsWorking());
		GoogleAnalyticsElement gae = new GoogleAnalyticsElement();
		Assert.assertTrue(gae.isPresent());
		Assert.assertEquals(gae.getJavaScriptBlockCount(), 3);
	}


	
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void viewAllNavigation() throws Exception {
		Assert.assertTrue(viewAllNews.checkViewAllIsNavigatedCorrectly());
	}

	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void verifyDocumentDisplayPageResults() throws Exception {
		Assert.assertTrue(viewAllNews.verifyDocumentFullListPage());
	}

	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResults" })
	public void checkExplicitCATEntryShowup() throws Exception{
		Assert.assertTrue(viewAllNews.doesDocumentIdShowupInOrder("viewAll_docId_table_xpath", "viewAll_docId_link_xpath", "EJ3000100766", 1));
	}
	
	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void documentFavIconsAreDisplayed() throws Exception {
		Assert.assertTrue(viewAllNews.verifyDocumentFavIconsAreDisplayed());
		Assert.assertTrue(viewAllNews.verifyCorrectDocumentFavIconAltTextIsDisplayed());
	}



	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void verifyArticleDisplayWorking() throws Exception {
		Assert.assertTrue(viewAllNews.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void verifyContentGroupTabsDisplay() throws Exception {
		Assert.assertTrue(viewAllNews.checkForContentGroupDisplay());
	}

	@Test(dependsOnMethods = { "verifyContentGroupTabsDisplay" })
	public void verifyViewAllPageNameDisplay() throws Exception {
		Assert.assertTrue(viewAllNews.checkForViewAllPageName("website_fullList_page_name"));
	}

	@Test(dependsOnMethods = { "verifyViewAllPageNameDisplay" })
	public void verifyWorkingOfLinksInContentGroupSelector() throws Exception {
		Assert.assertTrue(viewAllNews.checkForWorkingOfContentGroupSelector("website_fullList_page_name"));
	}

	// Document Type Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifyDocumentTypeLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_doctype_label_expected");
		Assert.assertTrue(viewAllNews.checkForBrowseIndexLabel(expectedLabel, "browse_index_doctype_label"));
	}

	@Test(dependsOnMethods = { "verifyDocumentTypeLabelIsPresent" })
	public void verifyDocumentTypeLinksCount() throws Exception {
		Assert.assertTrue(viewAllNews.checkForBrowseIndexLinksExist("browse_index_doctype_links"));
	}

	// TODO: Enable this test when the MLSG Ocean index has been created
	// Subject Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" }, enabled = false)
	public void verifySubjectLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_subject_label_expected");
		Assert.assertTrue(viewAllNews.checkForBrowseIndexLabel(expectedLabel, "browse_index_subject_label"));
	}

	@Test(dependsOnMethods = { "verifySubjectLabelIsPresent" }, enabled = false)
	public void verifySubjectLinksCount() throws Exception {
		Assert.assertTrue(viewAllNews.checkForBrowseIndexLinksExist("browse_index_subject_links"));
	}

}
