package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewVideoDetails;

public class ViewVideoDetailsTest extends BaseWebPageTest {

	private ViewVideoDetails videoDetails;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		videoDetails = new ViewVideoDetails();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyDetailedViewOfVideoIsDisplayed() throws Exception {
		Assert.assertTrue(videoDetails.verifyDetailedViewOfVideoIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyDetailedViewOfVideoIsDisplayed" })
	public void verifyElementsAreDisplayedOnPage() throws Exception {
		Assert.assertTrue(videoDetails.verifyElementsAreDisplayedOnPage());
	}

	@Test(dependsOnMethods = { "verifyDetailedViewOfVideoIsDisplayed" })
	public void verifyToolsArePresent() throws Exception {
		Assert.assertTrue(videoDetails.verifyToolsArePresent());
	}

	@Test(dependsOnMethods = { "verifyToolsArePresent" })
	public void checkSearchButtonIsPresent() throws Exception {
		Assert.assertTrue(videoDetails.checkSearchButtonIsPresent());
	}

	@Test(dependsOnMethods = { "checkSearchButtonIsPresent" })
	public void checkVideoIsDisplayedOnLeftSide() throws Exception {
		Assert.assertTrue(videoDetails.checkVideoIsDisplayedOnLeftSide());
	}

	@Test(dependsOnMethods = { "checkVideoIsDisplayedOnLeftSide" })
	public void verifyNextAndPreviousButtonsPresent() throws Exception {
		Assert.assertTrue(videoDetails.verifyNextAndPreviousButtonsPresent());
	}

	@Test(dependsOnMethods = { "verifyNextAndPreviousButtonsPresent" })
	public void verifyNavigationOfPreviousNextButtons() throws Exception {
		Assert.assertTrue(videoDetails.verifyNavigationOfPreviousNextButtons());
	}

	@Test(dependsOnMethods = { "verifyNavigationOfPreviousNextButtons" })
	public void verifyGalleryViewIsDisplayed() throws Exception {
		Assert.assertTrue(videoDetails.verifyGalleryViewIsDisplayed());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyNavigationOfPreviousNextButtons" })
	public void ensureMarkOptionIsAvailable(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(videoDetails.ensureMarkOptionIsAvailable());
	}

	@Test(dependsOnMethods = { "ensureMarkOptionIsAvailable" })
	public void verifyAfterMarkingVideo() throws Exception {
		Assert.assertTrue(videoDetails.verifyAfterMarkingVideo());
	}

	@Test(dependsOnMethods = { "verifyAfterMarkingVideo" })
	public void verifyPublicationDateIsDisplayed() throws Exception {
		Assert.assertTrue(videoDetails.verifyDateIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyAfterMarkingVideo" })
	public void verifyGlossaryDefnIsAvailable() throws Exception {
		Assert.assertTrue(videoDetails.verifyGlossaryDefnIsAvailable());
	}

	@Test(dependsOnMethods = { "verifyAfterMarkingVideo" })
	public void verifyTooltipIsDisplayed() throws Exception {
		Assert.assertTrue(videoDetails.verifyTooltipIsDisplayed());
	}

}
