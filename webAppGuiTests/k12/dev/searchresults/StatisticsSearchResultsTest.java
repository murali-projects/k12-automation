package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;

public class StatisticsSearchResultsTest extends BaseDevWebPageTest {

	private ListViewPortlet viewStatisticsDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewStatisticsDisplayGroup = new ListViewPortlet("statistics");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");		
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewStatisticsDisplayGroup
				.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void doesPortletHaveResults() throws Exception {
		Assert.assertTrue(viewStatisticsDisplayGroup.checkArticleLinkCount());
	}

	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert
				.assertTrue(viewStatisticsDisplayGroup
						.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void articleDisplayWorking() throws Exception {
		Assert.assertTrue(viewStatisticsDisplayGroup
				.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert
				.assertTrue(viewStatisticsDisplayGroup
						.checkViewAllLinkIsWorking());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewStatisticsDisplayGroup.doesContentExistInClass(viewStatisticsDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
}
