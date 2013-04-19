package k12.dev.viewall;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.GoogleAnalyticsElement;
import webPageContainers4Testing.dev.ViewFullListPortlet;

public class MagazinesViewAllTest extends BaseDevWebPageTest {

	private ViewFullListPortlet viewAllMagazines;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewAllMagazines = new ViewFullListPortlet("magazines");

	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewAllMagazines.doesContentExistInClass(viewAllMagazines.getProperty("footerDiv"),
				"footerGaleLogo"));
	}

	@Test(dependsOnMethods = { "isFooterPresent" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkViewAllLinkIsPresent());
		GoogleAnalyticsElement gae = new GoogleAnalyticsElement();
		Assert.assertTrue(gae.isPresent());
		Assert.assertEquals(gae.getJavaScriptBlockCount(), 3);
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkViewAllLinkIsWorking());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void viewAllNavigation() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkViewAllIsNavigatedCorrectly());
	}

	@Test(dependsOnMethods = { "viewAllNavigation" })
	public void documentFavIconsAreDisplayed() throws Exception {
		Assert.assertTrue(viewAllMagazines.verifyDocumentFavIconsAreDisplayed());
		Assert.assertTrue(viewAllMagazines.verifyCorrectDocumentFavIconAltTextIsDisplayed());
	}

	@Test(dependsOnMethods = { "documentFavIconsAreDisplayed" })
	public void verifyDocumentDisplayPageResults() throws Exception {
		Assert.assertTrue(viewAllMagazines.verifyDocumentFullListPage());
	}

	// TODO: enable this test when content issue is resolved
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" }, enabled = false)
	public void documentShortDescriptionIsPresent() throws Exception {
		Assert.assertTrue(viewAllMagazines.doesContentExistInId("content", "description_xpath"));
	}

	// TODO - uncomment once there are more results.

	// @Test(dependsOnMethods = { "verifyDocumentDisplayPageResults" })
	// public void verifyNextLink() throws Exception {
	// Assert.assertTrue(viewAllMagazines.verifyNextLink());
	// }
	//
	// @Test(dependsOnMethods = { "verifyNextLink" })
	// public void verifyNextLinkIsworking() throws Exception {
	// Assert.assertTrue(viewAllMagazines.verifyNextLinkIsworking());
	// }
	//
	// @Test(dependsOnMethods = { "verifyNextLinkIsworking" })
	// public void verifyPreviousLink() throws Exception {
	// Assert.assertTrue(viewAllMagazines.verifyPreviousLink());
	// }
	//
	// @Test(dependsOnMethods = { "verifyPreviousLink" })
	// public void verifyPreviousLinkIsworking()
	// throws Exception {
	// Assert.assertTrue(viewAllMagazines.verifyPreviousLinkIsworking());
	// }
	//
	// @Test(dependsOnMethods = { "verifyPreviousLinkIsworking" })
	// public void verifyLastLink() throws Exception {
	// Assert.assertTrue(viewAllMagazines.verifyLastLink());
	// }
	//
	// @Test(dependsOnMethods = { "verifyLastLink" })
	// public void verifyLastLinkIsworking() throws Exception {
	// Assert.assertTrue(viewAllMagazines.verifyLastLinkIsworking());
	// }
	//
	// @Test(dependsOnMethods = { "verifyLastLinkIsworking" })
	// public void verifyFirstLink() throws Exception {
	// Assert.assertTrue(viewAllMagazines.verifyFirstLink());
	// }
	//
	// @Test(dependsOnMethods = { "verifyFirstLink" })
	// public void verifyFirstLinkIsworking() throws Exception {
	// Assert.assertTrue(viewAllMagazines.verifyFirstLinkIsworking());
	// }

	// @Test(dependsOnMethods = { "verifyFirstLinkIsworking" })
	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResults" })
	public void verifyArticleDisplayWorking() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyArticleDisplayWorking" })
	public void verifyDocInfoTypeDetailDisplayed() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkDocInfoTypeOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyDocInfoTypeDetailDisplayed" })
	public void verifySortOrderChange() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkSortChange());
	}

	@Test(dependsOnMethods = { "verifySortOrderChange" })
	public void verifyFirstPageAfterSortOrderChange() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkFirstPageDisplayText());
	}

	@Test(dependsOnMethods = { "verifyFirstPageAfterSortOrderChange" })
	public void verifyContentGroupTabsDisplay() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkForContentGroupDisplay());
	}

	@Test(dependsOnMethods = { "verifyContentGroupTabsDisplay" })
	public void verifyViewAllPageNameDisplay() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkForViewAllPageName("magazine_fullList_page_name"));
	}

	@Test(dependsOnMethods = { "verifyViewAllPageNameDisplay" })
	public void verifyWorkingOfLinksInContentGroupSelector() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkForWorkingOfContentGroupSelector("magazine_fullList_page_name"));
	}

	// Document Type Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifyDocumentTypeLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_doctype_label_expected");
		Assert.assertTrue(viewAllMagazines.checkForBrowseIndexLabel(expectedLabel, "browse_index_doctype_label"));
	}

	@Test(dependsOnMethods = { "verifyDocumentTypeLabelIsPresent" })
	public void verifyDocumentTypeLinksCount() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkForBrowseIndexLinksExist("browse_index_doctype_links"));
	}

	// Subject Limiters (For all display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifySubjectLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_subject_label_expected");
		Assert.assertTrue(viewAllMagazines.checkForBrowseIndexLabel(expectedLabel, "browse_index_subject_label"));
	}

	@Test(dependsOnMethods = { "verifySubjectLabelIsPresent" })
	public void verifySubjectLinksCount() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkForBrowseIndexLinksExist("browse_index_subject_links"));
	}

	// (Publication) Title Limiters (Only for Magazines, News, and Academic
	// Journals display groups)
	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void verifyTitleLabelIsPresent() throws Exception {
		String expectedLabel = properties.get("browse_index_title_label_expected");
		Assert.assertTrue(viewAllMagazines.checkForBrowseIndexLabel(expectedLabel, "browse_index_title_label"));
	}

	@Test(dependsOnMethods = { "verifyTitleLabelIsPresent" })
	public void verifyTitleTypeLinksCount() throws Exception {
		Assert.assertTrue(viewAllMagazines.checkForBrowseIndexLinksExist("browse_index_title_links"));
	}

}
