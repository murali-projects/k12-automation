package k12.dev.viewall;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.GoogleAnalyticsElement;
import webPageContainers4Testing.dev.ViewFullListPortlet;

public class AcademicJournalsViewAllTest extends BaseDevWebPageTest {

	private ViewFullListPortlet viewAllJournals;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewAllJournals = new ViewFullListPortlet("academic_journals");

	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("culture and war");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewAllJournals.doesContentExistInClass(viewAllJournals.getProperty("footerDiv"),
				"footerGaleLogo"));
	}

	@Test(dependsOnMethods = { "isFooterPresent" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewAllJournals.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(viewAllJournals.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(viewAllJournals.checkViewAllLinkIsWorking());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void viewAllNavigation() throws Exception {
		Assert.assertTrue(viewAllJournals.checkViewAllIsNavigatedCorrectly());
		GoogleAnalyticsElement gae = new GoogleAnalyticsElement();
		Assert.assertTrue(gae.isPresent());
		Assert.assertEquals(gae.getJavaScriptBlockCount(), 3);
	}

	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void documentFavIconsAreDisplayed() throws Exception {
		Assert.assertTrue(viewAllJournals.verifyDocumentFavIconsAreDisplayed());
		Assert.assertTrue(viewAllJournals.verifyCorrectDocumentFavIconAltTextIsDisplayed());
	}

	@Test(dependsOnMethods = { "documentFavIconsAreDisplayed" })
	public void verifyDocumentDisplayPageResults() throws Exception {
		Assert.assertTrue(viewAllJournals.verifyDocumentFullListPage());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void documentShortDescriptionIsPresent() throws Exception {
		Assert.assertTrue(viewAllJournals.doesContentExistInId("content", "description_xpath"));
	}

	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResults" })
	public void verifyNextLink() throws Exception {
		Assert.assertTrue(viewAllJournals.verifyNextLink());
	}

	// TODO - uncomment once there are more results.
	// @Test(dependsOnMethods = { "verifyNextLink" })
	// public void verifyNextLinkIsworking() throws Exception {
	// Assert.assertTrue(viewAllJournals.verifyNextLinkIsworking());
	// }
	//	
	// @Test(dependsOnMethods = { "verifyNextLinkIsworking" })
	// public void verifyPreviousLink() throws Exception {
	// Assert.assertTrue(viewAllJournals.verifyPreviousLink());
	// }
	//
	// @Test(dependsOnMethods = { "verifyPreviousLink" })
	// public void verifyPreviousLinkIsworking()
	// throws Exception {
	// Assert.assertTrue(viewAllJournals.verifyPreviousLinkIsworking());
	// }
	//
	// @Test(dependsOnMethods = { "verifyPreviousLinkIsworking" })
	// public void verifyLastLink() throws Exception {
	// Assert.assertTrue(viewAllJournals.verifyLastLink());
	// }
	//
	// @Test(dependsOnMethods = { "verifyLastLink" })
	// public void verifyLastLinkIsworking() throws Exception {
	// Assert.assertTrue(viewAllJournals.verifyLastLinkIsworking());
	// }
	//
	// @Test(dependsOnMethods = { "verifyLastLinkIsworking" })
	// public void verifyFirstLink() throws Exception {
	// Assert.assertTrue(viewAllJournals.verifyFirstLink());
	// }
	//
	// @Test(dependsOnMethods = { "verifyFirstLink" })
	// public void verifyFirstLinkIsworking() throws Exception {
	// Assert.assertTrue(viewAllJournals.verifyFirstLinkIsworking());
	// }

	// @Test(dependsOnMethods = { "verifyFirstLinkIsworking" })
	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResults" })
	public void verifyArticleDisplayWorking() throws Exception {
		Assert.assertTrue(viewAllJournals.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyArticleDisplayWorking" })
	public void verifyDocInfoTypeDetailDisplayed() throws Exception {
		Assert.assertTrue(viewAllJournals.checkDocInfoTypeOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyDocInfoTypeDetailDisplayed" })
	public void verifySortOrderChange() throws Exception {
		Assert.assertTrue(viewAllJournals.checkSortChange());
	}

	@Test(dependsOnMethods = { "verifySortOrderChange" })
	public void verifyFirstPageAfterSortOrderChange() throws Exception {
		Assert.assertTrue(viewAllJournals.checkFirstPageDisplayText());
	}

	@Test(dependsOnMethods = { "verifyFirstPageAfterSortOrderChange" })
	public void verifyContentGroupTabsDisplay() throws Exception {
		Assert.assertTrue(viewAllJournals.checkForContentGroupDisplay());
	}

	@Test(dependsOnMethods = { "verifyContentGroupTabsDisplay" })
	public void verifyViewAllPageNameDisplay() throws Exception {
		Assert.assertTrue(viewAllJournals.checkForViewAllPageName("academic_journals_fullList_page_name"));
	}

	@Test(dependsOnMethods = { "verifyViewAllPageNameDisplay" })
	public void verifyWorkingOfLinksInContentGroupSelector() throws Exception {
		Assert
				.assertTrue(viewAllJournals
						.checkForWorkingOfContentGroupSelector("academic_journals_fullList_page_name"));
	}

	// Document Type Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifyDocumentTypeLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_doctype_label_expected");
		Assert.assertTrue(viewAllJournals.checkForBrowseIndexLabel(expectedLabel, "browse_index_doctype_label"));
	}

	@Test(dependsOnMethods = { "verifyDocumentTypeLabelIsPresent" })
	public void verifyDocumentTypeLinksCount() throws Exception {
		Assert.assertTrue(viewAllJournals.checkForBrowseIndexLinksExist("browse_index_doctype_links"));
	}

	// Subject Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifySubjectLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_subject_label_expected");
		Assert.assertTrue(viewAllJournals.checkForBrowseIndexLabel(expectedLabel, "browse_index_subject_label"));
	}

	@Test(dependsOnMethods = { "verifySubjectLabelIsPresent" })
	public void verifySubjectLinksCount() throws Exception {
		Assert.assertTrue(viewAllJournals.checkForBrowseIndexLinksExist("browse_index_subject_links"));
	}

	// (Publication) Title Limiters (Only for Magazines, News, and Academic
	// Journals display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifyTitleLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_title_label_expected");
		Assert.assertTrue(viewAllJournals.checkForBrowseIndexLabel(expectedLabel, "browse_index_title_label"));
	}

	@Test(dependsOnMethods = { "verifyTitleLabelIsPresent" })
	public void verifyTitleTypeLinksCount() throws Exception {
		Assert.assertTrue(viewAllJournals.checkForBrowseIndexLinksExist("browse_index_title_links"));
	}

}
