package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllImagesOnSearchResultsPage;

public class ViewAllImagesOnSearchResultsPageTest extends BaseWebPageTest{

	private ViewAllImagesOnSearchResultsPage imagesDisplayGroup;
	
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		imagesDisplayGroup = new ViewAllImagesOnSearchResultsPage(searchTerm);
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyContentResultsInImages() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyContentResultsInImages());
	}
	
	@Test(dependsOnMethods = { "verifyContentResultsInImages" })
	public void verifyViewAllIsDisplayed() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.viewAllIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyViewAllIsDisplayed" })
	public void verifyImagesCountIsDisplayed() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyImagesCountIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyImagesCountIsDisplayed" })
	public void verifyViewAllIsNotDisplayed() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyViewAllIsNotDisplayed());
	}

	@Test(dependsOnMethods = { "verifyViewAllIsNotDisplayed" })
	public void verifyImagesHeading() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyImagesHeadingIsPresent());
	}
	
	@Test(dependsOnMethods = { "verifyImagesHeading" })
	public void validateImagesWithOcean() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.validateImagesWithOcean());
	}

	@Test(dependsOnMethods = { "validateImagesWithOcean" })
	public void verifyMarkMeForImages() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.ensureMarkOptionIsAvailable());
	}
	
	@Test(dependsOnMethods = { "verifyMarkMeForImages" })
	public void verifyMouseOverOfImage() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyTooltipIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyMouseOverOfImage" })
	public void verifyDetailImageNavigation() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.detailImageNavigation());
	}
	
	@Test(dependsOnMethods = { "verifyDetailImageNavigation" })
	public void verifyNextLinkIsWorking() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyNextLinkIsworking());
	}
	
	@Test(dependsOnMethods = { "verifyNextLinkIsWorking" })
	public void verifyPreviousLinkIsWorking() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyPreviousLinkIsworking());
	}
	
	@Parameters( { "searchTermForNoImages" })
	@Test(dependsOnMethods = { "verifyPreviousLinkIsWorking" })
	public void verifyImageDisplayGroupIsNotDisplayed(String searchTermForNoImages) throws Exception {
		doBasicSearchUsingSearchTerm(searchTermForNoImages);
		Assert.assertTrue(imagesDisplayGroup.verifyImagesDisplayGroupNotDisplayed());
	}
	
	@Test(dependsOnMethods = { "validateImagesWithOcean" })
	public void verifyDefaultSortIsByPublicationDate() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyDefaultSortIsByPublicationDate());
	}
	
	@Test(dependsOnMethods = { "verifyImageDisplayGroupIsNotDisplayed" })
	public void verifyImageContent() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyImageContent());
	}

}
