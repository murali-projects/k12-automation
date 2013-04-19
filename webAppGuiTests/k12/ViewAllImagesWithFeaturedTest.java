package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllImagesWithFeatured;

public class ViewAllImagesWithFeaturedTest extends BaseWebPageTest {

	ViewAllImagesWithFeatured viewAllImagesWithFeatured;

	@BeforeTest
	public void setUP() throws Exception {
		viewAllImagesWithFeatured = new ViewAllImagesWithFeatured();
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearchAndPortalNav(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		viewAllImagesWithFeatured.portalNavigation();
	}

	@Test(dependsOnMethods = { "basicSearchAndPortalNav" })
	public void verifyDefaultSortForImages() throws Exception {
		Assert.assertTrue(viewAllImagesWithFeatured
				.verifyDefaultSortForImages());
	}

	@Parameters( { "documentTitleOption" })
	@Test(dependsOnMethods = { "verifyDefaultSortForImages" })
	public void verifyChangeInSortForImages(String documentTitleOption) throws Exception {
		Assert.assertTrue(viewAllImagesWithFeatured
				.verifyChangeInSortForImages(documentTitleOption));
	}

	@Parameters( { "relevanceOption" })
	@Test(dependsOnMethods = { "verifyChangeInSortForImages" })
	public void verifyRelevanceSortForImages(String relevanceOption) throws Exception {
		Assert.assertTrue(viewAllImagesWithFeatured
				.verifyRelevanceSortForImages(relevanceOption));
	}

	@Test(dependsOnMethods = { "verifyRelevanceSortForImages" })
	public void verifyWhenTabsAreChanged() throws InterruptedException {
		Assert.assertTrue(viewAllImagesWithFeatured.verifyWhenTabsAreChanged());
	}

	@Test(dependsOnMethods = { "verifyWhenTabsAreChanged" })
	public void verifyPaginationOfHandPickedItems() throws InterruptedException {
		Assert.assertTrue(viewAllImagesWithFeatured
				.verifyPaginationOfHandPickedItems());
	}

	@Test(dependsOnMethods = { "verifyPaginationOfHandPickedItems" })
	public void verifyDetailedPageOfHandPickedItem() throws InterruptedException {
		Assert.assertTrue(viewAllImagesWithFeatured.verifyDetailedPageOfHandPickedItem());
	}

	@Test(dependsOnMethods = { "verifyDetailedPageOfHandPickedItem" })
	public void verifyPaginationWithRelavence() throws InterruptedException {
		Assert.assertTrue(viewAllImagesWithFeatured.verifyPaginationWithRelavence());
	}

	@Test(dependsOnMethods = { "verifyPaginationWithRelavence" })
	public void verifyPaginationWithRelavenceFromOtherSort() throws InterruptedException {
		Assert.assertTrue(viewAllImagesWithFeatured
				.verifyPaginationWithRelavenceFromOtherSort());
	}
	
	@Test(dependsOnMethods = { "verifyPaginationWithRelavenceFromOtherSort" })
	public void verifyHandPickedItemsOnSearchPage() throws InterruptedException {
		Assert
				.assertTrue(viewAllImagesWithFeatured
						.verifyHandPickedItemsOnSearchPage());
	}
}
