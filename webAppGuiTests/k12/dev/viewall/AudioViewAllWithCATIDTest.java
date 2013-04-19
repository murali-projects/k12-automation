package k12.dev.viewall;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.GoogleAnalyticsElement;
import webPageContainers4Testing.dev.ViewFullListPortlet;

public class AudioViewAllWithCATIDTest extends BaseDevWebPageTest {

	private ViewFullListPortlet viewAllAudio;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewAllAudio = new ViewFullListPortlet("audio");

	}

	@Test
	public void openViewAllPage() throws Exception {
		viewAllAudio.openPage("audio.catid.portalpage.url");
	}

	@Test(dependsOnMethods = { "openViewAllPage" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewAllAudio.doesContentExistInClass(viewAllAudio.getProperty("footerDiv"), "footerGaleLogo"));
	}


	@Test(dependsOnMethods = { "isFooterPresent" })
	public void googleAnalyticsIsWorking() throws Exception {
		
		GoogleAnalyticsElement gae = new GoogleAnalyticsElement();
		Assert.assertTrue(gae.isPresent());
		Assert.assertEquals(gae.getJavaScriptBlockCount(), 3);
	}

	@Test(dependsOnMethods = { "googleAnalyticsIsWorking" })
	public void checkExplicitCATEntryShowup() throws Exception{
		Assert.assertTrue(viewAllAudio.doesDocumentIdShowupInOrder("viewAll_docId_table_xpath", "viewAll_docId_link_xpath", "A190891871", 1));
	}
	
	@Test(dependsOnMethods = { "checkExplicitCATEntryShowup" })
	public void viewAllNavigation() throws Exception {
		Assert.assertTrue(viewAllAudio.checkViewAllIsNavigatedCorrectly());
	}

	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void documentFavIconsAreDisplayed() throws Exception {
		Assert.assertTrue(viewAllAudio.verifyDocumentFavIconsAreDisplayed());
		Assert.assertTrue(viewAllAudio.verifyCorrectDocumentFavIconAltTextIsDisplayed());
	}

	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void contentLevelIndicatorIsPresent() throws Exception {
		Assert.assertTrue(viewAllAudio.verifyContentLevelIndicatorIsDisplayed());
	}

	@Test(dependsOnMethods = { "contentLevelIndicatorIsPresent" })
	public void verifyContentLevelIndAlternateTextIsDisplayed() throws Exception {
		Assert.assertTrue(viewAllAudio.verifyContentLevelIndicatorHasAltText());
	}

	@Test(dependsOnMethods = { "verifyContentLevelIndAlternateTextIsDisplayed" })
	public void verifyDocumentDisplayPageResults() throws Exception {
		Assert.assertTrue(viewAllAudio.verifyDocumentFullListPage());
	}


	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResults" })
	public void verifyArticleDisplayWorking() throws Exception {
		Assert.assertTrue(viewAllAudio.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyArticleDisplayWorking" })
	public void verifyDocInfoTypeDetailDisplayed() throws Exception {
		Assert.assertTrue(viewAllAudio.checkDocInfoTypeOfFirstItemIsDisplayed());
	}


	@Test(dependsOnMethods = { "verifyDocInfoTypeDetailDisplayed" })
	public void verifyFirstPageAfterSortOrderChange() throws Exception {
		Assert.assertTrue(viewAllAudio.checkFirstPageDisplayText());
	}

	@Test(dependsOnMethods = { "verifyFirstPageAfterSortOrderChange" })
	public void verifyContentGroupTabsDisplay() throws Exception {
		Assert.assertTrue(viewAllAudio.checkForContentGroupDisplay());
	}

	@Test(dependsOnMethods = { "verifyContentGroupTabsDisplay" })
	public void verifyViewAllPageNameDisplay() throws Exception {
		Assert.assertTrue(viewAllAudio.checkForViewAllPageName("audio_fullList_page_name"));
	}

	@Test(dependsOnMethods = { "verifyViewAllPageNameDisplay" })
	public void verifyWorkingOfLinksInContentGroupSelector() throws Exception {
		Assert.assertTrue(viewAllAudio.checkForWorkingOfContentGroupSelector("audio_fullList_page_name"));
	}

	// TODO: enable this test when content issue is resolved
	@Test(dependsOnMethods = { "verifyWorkingOfLinksInContentGroupSelector" }, enabled = false)
	public void documentShortDescriptionIsPresent() throws Exception {
		Assert.assertTrue(viewAllAudio.doesContentExistInId("content", "description_xpath"));
	}

	// Document Type Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void verifyDocumentTypeLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_doctype_label_expected");
		Assert.assertTrue(viewAllAudio.checkForBrowseIndexLabel(expectedLabel, "browse_index_doctype_label"));
	}

	@Test(dependsOnMethods = { "verifyDocumentTypeLabelIsPresent" })
	public void verifyDocumentTypeLinksCount() throws Exception {
		Assert.assertTrue(viewAllAudio.checkForBrowseIndexLinksExist("browse_index_doctype_links"));
	}

	// TODO: Enable this test when the MLSG Ocean index has been created
	// Subject Limiters (For all display groups)
	@Test(dependsOnMethods = { "verifyDocumentTypeLinksCount" }, enabled = false)
	public void verifySubjectLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_subject_label_expected");
		Assert.assertTrue(viewAllAudio.checkForBrowseIndexLabel(expectedLabel, "browse_index_subject_label"));
	}

	@Test(dependsOnMethods = { "verifySubjectLabelIsPresent" }, enabled = false)
	public void verifySubjectLinksCount() throws Exception {
		Assert.assertTrue(viewAllAudio.checkForBrowseIndexLinksExist("browse_index_subject_links"));
	}

}
