package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewImageDetails;

public class ViewImageDetailsTest extends BaseWebPageTest{

	private ViewImageDetails imageDetails;
	private String searchTerm;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		this.searchTerm = searchTerm;
		imageDetails = new ViewImageDetails();
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void verifyGalleryViewIsDisplayed() throws Exception {
		Assert.assertTrue(imageDetails.verifyGalleryViewIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyGalleryViewIsDisplayed" })
	public void verifyDetailedViewOfImageIsDisplayed() throws Exception {
		Assert.assertTrue(imageDetails.verifyDetailedViewOfImageIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyDetailedViewOfImageIsDisplayed" })
	public void checkPictureIsDisplayedOnLeftSide() throws Exception {
		Assert.assertTrue(imageDetails.checkPictureIsDisplayedOnLeftSide());
	}
	
	@Test(dependsOnMethods = { "checkPictureIsDisplayedOnLeftSide" })
	public void checkSearchButtonIsPresent() throws Exception {
		Assert.assertTrue(imageDetails.checkSearchButtonIsPresent());
	}
	
	@Test(dependsOnMethods = { "checkSearchButtonIsPresent" })
	public void ensureMarkOptionIsAvailable() throws Exception {
		Assert.assertTrue(imageDetails.ensureMarkOptionIsAvailable());
	}

	@Test(dependsOnMethods = { "ensureMarkOptionIsAvailable" })
	public void verifyTooltipIsDisplayed() throws Exception {
		Assert.assertTrue(imageDetails.verifyTooltipIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyTooltipIsDisplayed" })
	public void verifyAfterMarkingImage() throws Exception {
		Assert.assertTrue(imageDetails.verifyAfterMarkingImage());
	}
	
	@Test(dependsOnMethods = { "verifyTooltipIsDisplayed" })
	public void verifyNextAndPreviousButtonsPresent() throws Exception {
		Assert.assertTrue(imageDetails.verifyNextAndPreviousButtonsPresent());
	}
	
	@Test(dependsOnMethods = { "verifyNextAndPreviousButtonsPresent" })
	public void verifyNavigationOfPreviousNextButtons() throws Exception {
		Assert.assertTrue(imageDetails.verifyNavigationOfPreviousNextButtons());
	}
	 
	 @Test(dependsOnMethods = { "verifyNavigationOfPreviousNextButtons" })
	public void verifyElementsAreDisplayedOnPage() throws Exception {
		Assert.assertTrue(imageDetails.verifyElementsAreDisplayedOnPage());
	}
	
	 @Test(dependsOnMethods = { "verifyElementsAreDisplayedOnPage" })
	public void verifyToolsArePresent() throws Exception {
		Assert.assertTrue(imageDetails.verifyToolsArePresent());
	}
	
	@Test(dependsOnMethods = { "verifyToolsArePresent" })
	public void verifyGlossaryDefnIsAvailable() throws Exception {
		Assert.assertTrue(imageDetails.verifyGlossaryDefnIsAvailable());
	}
	
	 @Test(dependsOnMethods = { "verifyTooltipIsDisplayed" })
	public void verifyPublicationDateIsDisplayed() throws Exception {
		Assert.assertTrue(imageDetails.verifyDateIsDisplayed());
	}
	
}
