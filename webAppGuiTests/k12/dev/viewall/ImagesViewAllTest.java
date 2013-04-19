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
import webPageContainers4Testing.dev.ImagesFullPortlet;
import webPageContainers4Testing.dev.ImagesPortlet;
import webPageElements4Testing.Url;

public class ImagesViewAllTest extends BaseDevWebPageTest {

	private ImagesFullPortlet imageFullPortlet;
	private ImagesPortlet imagePortlet = new ImagesPortlet();

	private PropertyReader props;
	private final int noOfResultsPerPage;

	public ImagesViewAllTest() {
		try {
			imageFullPortlet = new ImagesFullPortlet();
		} catch (Exception e) {

		}
		props = new PropertyReader("properties/default.dev.search.properties");
		noOfResultsPerPage = Integer.parseInt(props.get("page_sr.portlet_imagesFull.noofresultsperpage").trim());
	}

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		// this should return search results containing more than one page
		doBasicSearchUsingSearchTerm("war or peace or usda");
		imagePortlet.navigateToViewAll();
		checkForFirstPage();

	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(imageFullPortlet.doesContentExistInClass(imageFullPortlet.getProperty("footerDiv"),
				"footerGaleLogo"));
	}

	@Test(dependsOnMethods = { "isFooterPresent" })
	public void viewAllLinkIsWorking() throws Exception {
		assertTrue(Url.getCurrentUrl().contains(props.get("page_sr.portlet_imagesFull.parturl")));
		checkForFirstPage();
		GoogleAnalyticsElement gae = new GoogleAnalyticsElement();
		Assert.assertTrue(gae.isPresent());
		Assert.assertEquals(gae.getJavaScriptBlockCount(), 3);
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void firstPageIsDisplayed() throws Exception {

		assertEquals(noOfResultsPerPage, imageFullPortlet.getCurrentResultsEndingDocNumber());
		assertFalse(imageFullPortlet.getFirstLink().isPresent());
		assertFalse(imageFullPortlet.getPrevLink().isPresent());
	}

	@Test(dependsOnMethods = { "firstPageIsDisplayed" })
	public void checkNoOfDocsDisplayedInPage() throws Exception {
		assertEquals(noOfResultsPerPage, imageFullPortlet.getDocThumbnailLink().getLinkCount());
	}

	@Test(dependsOnMethods = { "checkNoOfDocsDisplayedInPage" })
	public void verifyNextLinkIsWorking() throws Exception {
		imageFullPortlet.navigateToNextPage();
		assertEquals(imageFullPortlet.getCurrentResultsStartingDocNumber(), noOfResultsPerPage + 1);
	}

	@Test(dependsOnMethods = { "verifyNextLinkIsWorking" })
	public void verifyLastLinkIsWorking() throws Exception {
		imageFullPortlet.navigateToLastPage();
		assertEquals(imageFullPortlet.getCurrentResultsEndingDocNumber(), imageFullPortlet.getTotalResultsCount());
		assertFalse(imageFullPortlet.getLastLink().isPresent());
		assertFalse(imageFullPortlet.getNextLink().isPresent());

	}

	@Test(dependsOnMethods = { "verifyLastLinkIsWorking" })
	public void verifyPrevLinkIsWorking() throws Exception {
		imageFullPortlet.navigateToPrevPage();
		int penulmitatePageStartDocNumber = imageFullPortlet.getTotalResultsCount() - noOfResultsPerPage
				- imageFullPortlet.getTotalResultsCount() % noOfResultsPerPage + 1;
		assertEquals(imageFullPortlet.getCurrentResultsStartingDocNumber(), penulmitatePageStartDocNumber);
	}

	@Test(dependsOnMethods = { "verifyPrevLinkIsWorking" })
	public void verifyFirstLinkIsWorking() throws Exception {
		imageFullPortlet.navigateToFirstPage();
		checkForFirstPage();
	}

	@Test(dependsOnMethods = { "verifyFirstLinkIsWorking" })
	public void verifychangeSort() throws Exception {
		imageFullPortlet.navigateToNextPage();
		for (int i = 0; i < imageFullPortlet.getSortDropDownSize(); i++) {
			int changedSortIndex = imageFullPortlet.changeToNextSortOption();
			checkForFirstPage();
			assertEquals(changedSortIndex, imageFullPortlet.getCurrentSortIndex());
			imageFullPortlet.navigateToNextPage();
			assertEquals(changedSortIndex, imageFullPortlet.getCurrentSortIndex());
		}
	}

	@Test(dependsOnMethods = { "verifychangeSort" })
	public void verifyContentGroupTabsDisplay() throws Exception {
		Assert.assertTrue(imageFullPortlet.checkForContentGroupDisplay());
	}

	@Test(dependsOnMethods = { "verifyContentGroupTabsDisplay" })
	public void verifyViewAllPageNameDisplay() throws Exception {
		Assert.assertTrue(imageFullPortlet.checkForViewAllPageName("image_fullList_page_name"));
	}

	@Test(dependsOnMethods = { "verifyViewAllPageNameDisplay" })
	public void verifyWorkingOfLinksInContentGroupSelector() throws Exception {
		Assert.assertTrue(imageFullPortlet.checkForWorkingOfContentGroupSelector("image_fullList_page_name"));
	}

	private void checkForFirstPage() {
		assertEquals(imageFullPortlet.getCurrentResultsStartingDocNumber(), 1);
	}

	// Document Type Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifyDocumentTypeLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_doctype_label_expected");
		Assert.assertTrue(imageFullPortlet.checkForBrowseIndexLabel(expectedLabel, "browse_index_doctype_label"));
	}

	@Test(dependsOnMethods = { "verifyDocumentTypeLabelIsPresent" })
	public void verifyDocumentTypeLinksCount() throws Exception {
		Assert.assertTrue(imageFullPortlet.checkForBrowseIndexLinksExist("browse_index_doctype_links"));
	}

	// Subject Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifySubjectLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_subject_label_expected");
		Assert.assertTrue(imageFullPortlet.checkForBrowseIndexLabel(expectedLabel, "browse_index_subject_label"));
	}

	@Test(dependsOnMethods = { "verifySubjectLabelIsPresent" })
	public void verifySubjectLinksCount() throws Exception {
		Assert.assertTrue(imageFullPortlet.checkForBrowseIndexLinksExist("browse_index_subject_links"));
	}

}
