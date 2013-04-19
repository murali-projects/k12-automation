package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;

public class ViewpointsSearchResultsTest extends BaseDevWebPageTest {

	private ListViewPortlet viewViewpointsDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewViewpointsDisplayGroup = new ListViewPortlet("viewpoints");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup
				.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void doesPortletHaveResults() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkArticleLinkCount());
	}

	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert
				.assertTrue(viewViewpointsDisplayGroup
						.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void articleDisplayWorking() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup
				.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert
				.assertTrue(viewViewpointsDisplayGroup
						.checkViewAllLinkIsWorking());
	}
	
	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void documentShortDescriptionIsPresent() throws Exception {
		Assert
				.assertTrue(viewViewpointsDisplayGroup
						.doesContentExistInId("viewpoints", "short_description_xpath"));
	}

	@Test(dependsOnMethods = { "documentShortDescriptionIsPresent" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.doesContentExistInClass(viewViewpointsDisplayGroup.getProperty("footerDiv"),
				"footerGaleLogo"));
	}
}
