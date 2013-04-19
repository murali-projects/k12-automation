package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;

public class PrimarySourcesSearchResultsTest extends BaseDevWebPageTest {

	private ListViewPortlet viewPrimarySourcesDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewPrimarySourcesDisplayGroup = new ListViewPortlet("primarysources");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");		
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewPrimarySourcesDisplayGroup
				.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void doesPortletHaveResults() throws Exception {
		Assert.assertTrue(viewPrimarySourcesDisplayGroup.checkArticleLinkCount());
	}

	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert
				.assertTrue(viewPrimarySourcesDisplayGroup
						.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void articleDisplayWorking() throws Exception {
		Assert.assertTrue(viewPrimarySourcesDisplayGroup
				.checkDetailedViewOfFirstItemIsDisplayed());
	}


	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert
				.assertTrue(viewPrimarySourcesDisplayGroup
						.checkViewAllLinkIsWorking());
	}
	
	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void documentShortDescriptionIsPresent() throws Exception {
		Assert
				.assertTrue(viewPrimarySourcesDisplayGroup
						.doesContentExistInId("primary_sources", "short_description_xpath"));
	}
	
	@Test(dependsOnMethods = { "documentShortDescriptionIsPresent" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewPrimarySourcesDisplayGroup.doesContentExistInClass(viewPrimarySourcesDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
}
