package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;

public class WebsitesSearchResultsTest extends BaseDevWebPageTest {

	private ListViewPortlet viewWebsitesDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewWebsitesDisplayGroup = new ListViewPortlet("websites");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewWebsitesDisplayGroup.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void doesPortletHaveResults() throws Exception {
		Assert.assertTrue(viewWebsitesDisplayGroup.checkArticleLinkCount());
	}

	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(viewWebsitesDisplayGroup.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void articleDisplayWorking() throws Exception {
		Assert.assertTrue(viewWebsitesDisplayGroup.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(viewWebsitesDisplayGroup.checkViewAllLinkIsWorking());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewWebsitesDisplayGroup.doesContentExistInClass(viewWebsitesDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
}
