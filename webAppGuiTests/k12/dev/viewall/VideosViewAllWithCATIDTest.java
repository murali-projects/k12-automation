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

public class VideosViewAllWithCATIDTest extends BaseDevWebPageTest {

	private VideosFullPortlet videoFullPortlet;
	private VideosPortlet videoPortlet;

	private PropertyReader props;
	private final int noOfResultsPerPage;

	public VideosViewAllWithCATIDTest() {
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

//	@Test
//	public void searchValidationMessageIsDisplayed() throws Exception {
//		// this should return search results containing more than one page
//		doBasicSearchUsingSearchTerm("war OR Nation OR President");
//		videoPortlet.navigateToViewAll();
//		checkForFirstPage();
//
//	}

	@Test
	public void openViewAllPage() throws Exception {
		videoFullPortlet.openPage("video.catid.portalpage.url");
	}
	
	@Test(dependsOnMethods = { "openViewAllPage" })
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
	public void checkExplicitCATEntryShowup() throws Exception{
		Assert.assertTrue(videoFullPortlet.doesDocumentIdShowupInOrder("video_viewAll_docId_table_xpath", "", "A187087346", 1));
		Assert.assertTrue(videoFullPortlet.doesDocumentIdShowupInOrder("video_viewAll_docId_table_xpath", "", "A186587029", 2));
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void firstPageIsDisplayed() throws Exception {

		assertEquals(2, videoFullPortlet.getCurrentResultsEndingDocNumber());
		
	}

	@Test(dependsOnMethods = { "firstPageIsDisplayed" })
	public void checkNoOfDocsDisplayedInPage() throws Exception {
		assertEquals(2, videoFullPortlet.getDocThumbnailLink().getLinkCount());
	}

	
	@Test(dependsOnMethods = { "checkNoOfDocsDisplayedInPage" })
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
	
	// TODO: Enable this test when the MLSG Ocean index has been created
	// Subject Limiters	(For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" }, enabled=false)
	public void verifySubjectLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_subject_label_expected");
		Assert.assertTrue(videoFullPortlet.checkForBrowseIndexLabel(expectedLabel, "browse_index_subject_label"));
	}	

	@Test(dependsOnMethods = { "verifySubjectLabelIsPresent" }, enabled=false)
	public void verifySubjectLinksCount() throws Exception {
		Assert.assertTrue(videoFullPortlet.checkForBrowseIndexLinksExist("browse_index_subject_links"));
	}	
	
}
