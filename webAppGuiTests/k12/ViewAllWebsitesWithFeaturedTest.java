package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.HitcountAndSortingDocumentRelatedSubjects;
import webPageContainers4Testing.ViewAllWebsitesWithFeatured;

public class ViewAllWebsitesWithFeaturedTest extends
BaseWebPageTest {
	
	
	public ViewAllWebsitesWithFeatured veiwallwebsiteswithfeatured;
	
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		veiwallwebsiteswithfeatured = new ViewAllWebsitesWithFeatured();
	}

	@Parameters( { "searchTerm" })
	@Test
	public void verifyDefaultsortRelevanceInViewall(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(veiwallwebsiteswithfeatured
				.verifyDefaultsortRelevanceInViewall());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyDefaultsortRelevanceInViewall" })
	public void verifyFeaturedwebsitesAppearOntheTopOfList(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(veiwallwebsiteswithfeatured
				.verifyFeaturedwebsitesAppearOntheTopOfList());

	}
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyDefaultsortRelevanceInViewall" })
	public void verifyFeaturedItemsAfterChangingSort(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(veiwallwebsiteswithfeatured
				.verifyFeaturedItemsAfterChangingSort());

	}
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyFeaturedItemsAfterChangingSort" })
	public void verifyClickingfeatureItemnavigatesToFeaturedPage(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(veiwallwebsiteswithfeatured
				.verifyClickingfeatureItemnavigatesToDocumentPage());

	}
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyClickingfeatureItemnavigatesToFeaturedPage" })
	public void verifyFeaturedWebsitesNotpresentInSearchResultsPageBucket(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(veiwallwebsiteswithfeatured
				.verifyFeaturedWebsitesNotpresentInSearchResultsPageBucket());

	}
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyFeaturedWebsitesNotpresentInSearchResultsPageBucket" })
	public void verifyEditorpickBucketItemwithFeaturedBucketItem(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(veiwallwebsiteswithfeatured
				.verifyEditorpickBucketItemwithFeaturedBucketItem());

	}
	

}
