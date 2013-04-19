package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;

public class ReferenceSearchResultsTest extends BaseDevWebPageTest {

	private ListViewPortlet viewReferenceDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewReferenceDisplayGroup = new ListViewPortlet("reference");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewReferenceDisplayGroup
				.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void doesPortletHaveResults() throws Exception {
		Assert.assertTrue(viewReferenceDisplayGroup.checkArticleLinkCount());
	}

	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert
				.assertTrue(viewReferenceDisplayGroup
						.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void articleDisplayWorking() throws Exception {
		Assert.assertTrue(viewReferenceDisplayGroup
				.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert
				.assertTrue(viewReferenceDisplayGroup
						.checkViewAllLinkIsWorking());
	}
	
	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void documentShortDescriptionIsPresent() throws Exception {
		Assert
				.assertTrue(viewReferenceDisplayGroup
						.doesContentExistInId("reference", "short_description_xpath"));
	}

	@Test(dependsOnMethods = { "documentShortDescriptionIsPresent" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewReferenceDisplayGroup.doesContentExistInClass(viewReferenceDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
}
