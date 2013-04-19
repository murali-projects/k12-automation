package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;

public class AcademicJournalsSearchResultsTest extends BaseDevWebPageTest {

	private ListViewPortlet viewJournalsDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewJournalsDisplayGroup = new ListViewPortlet("academic_journals");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("usda");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewJournalsDisplayGroup.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void doesPortletHaveResults() throws Exception {
		Assert.assertTrue(viewJournalsDisplayGroup.checkArticleLinkCount());
	}

	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(viewJournalsDisplayGroup.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void articleDisplayWorking() throws Exception {
		Assert.assertTrue(viewJournalsDisplayGroup.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(viewJournalsDisplayGroup.checkViewAllLinkIsWorking());
	}

	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void documentShortDescriptionIsPresent() throws Exception {
		Assert.assertTrue(viewJournalsDisplayGroup.doesContentExistInId("academic_journals",
				"short_description_xpath"));
	}

	@Test(dependsOnMethods = { "documentShortDescriptionIsPresent" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewJournalsDisplayGroup.doesContentExistInClass(viewJournalsDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
}
