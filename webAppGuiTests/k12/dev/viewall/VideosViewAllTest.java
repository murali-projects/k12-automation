package k12.dev.viewall;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import utils.PropertyReader;
import webPageContainers4Testing.dev.GoogleAnalyticsElement;
import webPageContainers4Testing.dev.VideosFullPortlet;
import webPageContainers4Testing.dev.VideosPortlet;
import webPageElements4Testing.Url;

public class VideosViewAllTest extends BaseDevWebPageTest {

	private VideosFullPortlet videoFullPortlet;
	private VideosPortlet videoPortlet;

	private PropertyReader props;
	private final int noOfResultsPerPage;

	public VideosViewAllTest() {
		try {
			videoPortlet = new VideosPortlet();
			videoFullPortlet = new VideosFullPortlet();
		} catch (Exception e) {

		}		
		props = new PropertyReader("properties/default.dev.search.properties");
		noOfResultsPerPage = Integer.parseInt(props.get("page_sr.portlet_videosFull.noofresultsperpage").trim());
	}

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		// this should return search results containing more than one page
		doBasicSearchUsingSearchTerm("war OR Nation OR President");
		videoPortlet.navigateToViewAll();
		checkForFirstPage();

	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(videoFullPortlet.doesContentExistInClass(videoFullPortlet.getProperty("footerDiv"),
				"footerGaleLogo"));
	}

	@Test(dependsOnMethods = { "isFooterPresent" })
	public void viewAllLinkIsWorking() throws Exception {
		assertTrue(Url.getCurrentUrl().contains(props.get("page_sr.portlet_videosFull.parturl")));
		checkForFirstPage();
		GoogleAnalyticsElement gae = new GoogleAnalyticsElement();
		Assert.assertTrue(gae.isPresent());
		Assert.assertEquals(gae.getJavaScriptBlockCount(),3);
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void firstPageIsDisplayed() throws Exception {

		assertEquals(noOfResultsPerPage, videoFullPortlet.getCurrentResultsEndingDocNumber());
		assertFalse(videoFullPortlet.getFirstLink().isPresent());
		assertFalse(videoFullPortlet.getPrevLink().isPresent());
	}

	@Test(dependsOnMethods = { "firstPageIsDisplayed" })
	public void checkNoOfDocsDisplayedInPage() throws Exception {
		assertEquals(noOfResultsPerPage, videoFullPortlet.getDocThumbnailLink().getLinkCount());
	}

	@Test(dependsOnMethods = { "checkNoOfDocsDisplayedInPage" })
	public void verifyNextLinkIsWorking() throws Exception {
		videoFullPortlet.navigateToNextPage();
		assertEquals(videoFullPortlet.getCurrentResultsStartingDocNumber(), noOfResultsPerPage + 1);
	}

	@Test(dependsOnMethods = { "verifyNextLinkIsWorking" })
	public void verifyLastLinkIsWorking() throws Exception {
		videoFullPortlet.navigateToLastPage();
		assertEquals(videoFullPortlet.getCurrentResultsEndingDocNumber(), videoFullPortlet.getTotalResultsCount());
		assertFalse(videoFullPortlet.getLastLink().isPresent());
		assertFalse(videoFullPortlet.getNextLink().isPresent());
	}

	@Test(dependsOnMethods = { "verifyLastLinkIsWorking" })
	public void verifyPrevLinkIsWorking() throws Exception {
		videoFullPortlet.navigateToPrevPage();
		int penulmitatePageStartDocNumber = videoFullPortlet.getTotalResultsCount() - noOfResultsPerPage
				- videoFullPortlet.getTotalResultsCount() % noOfResultsPerPage + 1;
		assertEquals(videoFullPortlet.getCurrentResultsStartingDocNumber(), penulmitatePageStartDocNumber);
	}

	@Test(dependsOnMethods = { "verifyPrevLinkIsWorking" })
	public void verifyFirstLinkIsWorking() throws Exception {
		videoFullPortlet.navigateToFirstPage();
		checkForFirstPage();
	}

	@Test(dependsOnMethods = { "verifyFirstLinkIsWorking" })
	public void verifychangeSort() throws Exception {
		videoFullPortlet.navigateToNextPage();
		for (int i = 0; i < videoFullPortlet.getSortDropDownSize(); i++) {
			int changedSortIndex = videoFullPortlet.changeToNextSortOption();
			checkForFirstPage();
			assertEquals(changedSortIndex, videoFullPortlet.getCurrentSortIndex());
			videoFullPortlet.navigateToNextPage();
			assertEquals(changedSortIndex, videoFullPortlet.getCurrentSortIndex());
		}
	}

	@Test(dependsOnMethods = { "verifychangeSort" })
	public void verifyContentGroupTabsDisplay() throws Exception {
		Assert.assertTrue(videoFullPortlet.checkForContentGroupDisplay());
	}

	@Test(dependsOnMethods = { "verifyContentGroupTabsDisplay" })
	public void verifyViewAllPageNameDisplay() throws Exception {
		Assert.assertTrue(videoFullPortlet.checkForViewAllPageName("video_fullList_page_name"));
	}

	@Test(dependsOnMethods = { "verifyViewAllPageNameDisplay" })
	public void verifyWorkingOfLinksInContentGroupSelector() throws Exception {
		Assert.assertTrue(videoFullPortlet.checkForWorkingOfContentGroupSelector("video_fullList_page_name"));
	}

	private void checkForFirstPage() {
		assertEquals(videoFullPortlet.getCurrentResultsStartingDocNumber(), 1);
	}

	// Document Type Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifyDocumentTypeLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_doctype_label_expected");
		Assert.assertTrue(videoFullPortlet.checkForBrowseIndexLabel(expectedLabel, "browse_index_doctype_label"));
	}	

	@Test(dependsOnMethods = { "verifyDocumentTypeLabelIsPresent" })
	public void verifyDocumentTypeLinksCount() throws Exception {
		Assert.assertTrue(videoFullPortlet.checkForBrowseIndexLinksExist("browse_index_doctype_links"));
	}		
	
	// Subject Limiters	(For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifySubjectLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_subject_label_expected");
		Assert.assertTrue(videoFullPortlet.checkForBrowseIndexLabel(expectedLabel, "browse_index_subject_label"));
	}	

	@Test(dependsOnMethods = { "verifySubjectLabelIsPresent" })
	public void verifySubjectLinksCount() throws Exception {
		Assert.assertTrue(videoFullPortlet.checkForBrowseIndexLinksExist("browse_index_subject_links"));
	}	
	
}
