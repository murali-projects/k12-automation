package k12.dev.viewall;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.GoogleAnalyticsElement;
import webPageContainers4Testing.dev.ViewFullListPortlet;

public class WebsitesViewAllTest extends BaseDevWebPageTest {

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
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
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

	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void documentFavIconsAreDisplayed() throws Exception {
		Assert.assertTrue(viewAllNews.verifyDocumentFavIconsAreDisplayed());
		Assert.assertTrue(viewAllNews.verifyCorrectDocumentFavIconAltTextIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResults" })
	public void verifyNextLink() throws Exception {
		Assert.assertTrue(viewAllNews.verifyNextLink());
	}

	@Test(dependsOnMethods = { "verifyNextLink" })
	public void verifyNextLinkIsworking() throws Exception {
		Assert.assertTrue(viewAllNews.verifyNextLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyNextLinkIsworking" })
	public void verifyPreviousLink() throws Exception {
		Assert.assertTrue(viewAllNews.verifyPreviousLink());
	}

	@Test(dependsOnMethods = { "verifyPreviousLink" })
	public void verifyPreviousLinkIsworking() throws Exception {
		Assert.assertTrue(viewAllNews.verifyPreviousLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyPreviousLinkIsworking" })
	public void verifyLastLink() throws Exception {
		Assert.assertTrue(viewAllNews.verifyLastLink());
	}

	@Test(dependsOnMethods = { "verifyLastLink" })
	public void verifyLastLinkIsworking() throws Exception {
		Assert.assertTrue(viewAllNews.verifyLastLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyLastLinkIsworking" })
	public void verifyFirstLink() throws Exception {
		Assert.assertTrue(viewAllNews.verifyFirstLink());
	}

	@Test(dependsOnMethods = { "verifyFirstLink" })
	public void verifyFirstLinkIsworking() throws Exception {
		Assert.assertTrue(viewAllNews.verifyFirstLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyFirstLinkIsworking" })
	public void verifyArticleDisplayWorking() throws Exception {
		Assert.assertTrue(viewAllNews.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyFirstLinkIsworking" })
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

	// Subject Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifySubjectLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_subject_label_expected");
		Assert.assertTrue(viewAllNews.checkForBrowseIndexLabel(expectedLabel, "browse_index_subject_label"));
	}

	@Test(dependsOnMethods = { "verifySubjectLabelIsPresent" })
	public void verifySubjectLinksCount() throws Exception {
		Assert.assertTrue(viewAllNews.checkForBrowseIndexLinksExist("browse_index_subject_links"));
	}

}
