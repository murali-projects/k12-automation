package k12;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewImageDocument;

public class ViewImageDocumentTest extends BaseWebPageTest {

	private ViewImageDocument imageDocument;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		imageDocument = new ViewImageDocument();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyDetailedViewOfImageIsDisplayed() throws Exception {
		Assert.assertTrue(imageDocument.verifyDetailedViewOfImageIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyDetailedViewOfImageIsDisplayed" })
	public void verifyDetailedViewOfImageIsDisplayedFromViewAll()
			throws Exception {
		Assert.assertTrue(imageDocument
				.verifyDetailedViewOfImageIsDisplayedFromViewAll());
	}

	@Test(dependsOnMethods = { "verifyDetailedViewOfImageIsDisplayedFromViewAll" })
	public void verifyImagesForNext() throws Exception {
		Assert.assertTrue(imageDocument.verifyImagesForNext());
	}

	@Test(dependsOnMethods = { "verifyImagesForNext" })
	public void verifyImagesForPrevious() throws Exception {
		Assert.assertTrue(imageDocument.verifyImagesForPrevious());
	}

	@Test(dependsOnMethods = { "verifyImagesForPrevious" })
	public void verifyFullSizeImageIsDisplayed() throws Exception {
		assertTrue(imageDocument.verifyFullSizeImageIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyImagesForPrevious" })
	public void verifyToolsArePresent() throws Exception {
		assertTrue(imageDocument.verifyToolsArePresent());
	}

	@Test(dependsOnMethods = { "verifyToolsArePresent" })
	public void verifyNextAndPreviousButtonsPresent() throws Exception {
		Assert.assertTrue(imageDocument.verifyNextAndPreviousButtonsPresent());
	}
	
	@Test(dependsOnMethods = { "verifyNextAndPreviousButtonsPresent" })
	public void checkSearchButtonIsPresent() throws Exception {
		Assert.assertTrue(imageDocument.checkSearchButtonIsPresent());
	}
	
	@Test(dependsOnMethods = { "checkSearchButtonIsPresent" })
	public void verifyElementsAreDisplayedOnPage() throws Exception {
		Assert.assertTrue(imageDocument.verifyElementsAreDisplayedOnPage());
	}
}
