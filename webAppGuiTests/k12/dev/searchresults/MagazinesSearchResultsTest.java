package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;

public class MagazinesSearchResultsTest extends BaseDevWebPageTest {

	private ListViewPortlet viewMagazinesDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewMagazinesDisplayGroup = new ListViewPortlet("magazines");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");		
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewMagazinesDisplayGroup
				.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void doesPortletHaveResults() throws Exception {
		Assert.assertTrue(viewMagazinesDisplayGroup.checkArticleLinkCount());
	}

	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert
				.assertTrue(viewMagazinesDisplayGroup
						.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void articleDisplayWorking() throws Exception {
		Assert.assertTrue(viewMagazinesDisplayGroup
				.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert
				.assertTrue(viewMagazinesDisplayGroup
						.checkViewAllLinkIsWorking());
	}

	//TODO: enable this test when content issue is resolved
	@Test(dependsOnMethods = { "articleDisplayWorking" }, enabled=false)
	public void documentShortDescriptionIsPresent() throws Exception {
		Assert
				.assertTrue(viewMagazinesDisplayGroup
						.doesContentExistInId("magazines", "short_description_xpath"));
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewMagazinesDisplayGroup.doesContentExistInClass(viewMagazinesDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
}
