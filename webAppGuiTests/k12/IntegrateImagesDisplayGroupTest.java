package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateImagesDisplayGroup;

public class IntegrateImagesDisplayGroupTest extends BaseWebPageTest {
	private IntegrateImagesDisplayGroup imagesDisplayGroup;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		imagesDisplayGroup = new IntegrateImagesDisplayGroup(searchTerm);
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyContentResultsInImages() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyContentResultsInImages());
	}

	@Test(dependsOnMethods = { "verifyContentResultsInImages" })
	public void verifyImagesCount() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyImagesCount());
	}

	@Test(dependsOnMethods = { "verifyImagesCount" })
	public void verifyMouseOverOfImage() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyTooltipIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyImagesCount" })
	public void verifyViewAllIsNotDisplayed() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.viewAllIsNotDisplayed());
	}

	@Test(dependsOnMethods = { "verifyViewAllIsNotDisplayed" })
	public void verifyViewwAllIsDisplayed() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.viewAllIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyViewwAllIsDisplayed" })
	public void verifyBoldImagesText() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyImageText());
	}

	@Test(dependsOnMethods = { "verifyBoldImagesText" })
	public void verifyNoImagesDisplayed() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyNoImagesDisplayed());
	}

	@Test(dependsOnMethods = { "verifyNoImagesDisplayed" })
	public void verifyMarkMeForImages() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.ensureMarkOptionIsAvailable());
	}
	
	@Test(dependsOnMethods = { "verifyMarkMeForImages" })
	public void verifyDetailImageNavigation() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.detailImageNavigation());
	}

	@Test(dependsOnMethods = { "verifyDetailImageNavigation" })
	public void verifyImageContent() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.verifyImageContent());
	}

	@Test(dependsOnMethods = { "verifyImageContent" })
	public void validateImagesWithOcean() throws Exception {
		Assert.assertTrue(imagesDisplayGroup.validateImagesWithOcean());
	}
}
