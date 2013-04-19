package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListMediaViewPortlet;

public class ImagesSearchResultsTest extends BaseDevWebPageTest {

	private ListMediaViewPortlet viewImagesDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewImagesDisplayGroup = new ListMediaViewPortlet("image");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");		
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewImagesDisplayGroup
				.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void doesPortletHaveResults() throws Exception {
		Assert.assertTrue(viewImagesDisplayGroup.checkImagesLinkCount());
	}
	
	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert
				.assertTrue(viewImagesDisplayGroup
						.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void imageDisplayWorking() throws Exception {
		Assert.assertTrue(viewImagesDisplayGroup
				.checkDetailedViewOfFirstImageIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "imageDisplayWorking" })
	public void viewAllLinkIsWorking() throws Exception {
		Assert
				.assertTrue(viewImagesDisplayGroup
						.checkViewAllLinkIsWorking());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewImagesDisplayGroup.doesContentExistInClass(viewImagesDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
}
