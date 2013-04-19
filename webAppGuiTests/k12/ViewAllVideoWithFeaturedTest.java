package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllVideoWithFeatured;
import webPageContainers4Testing.ViewAllWebsitesWithFeatured;

public class ViewAllVideoWithFeaturedTest extends
BaseWebPageTest {
	
	
	
	public ViewAllVideoWithFeatured viewallvideowithfeatured;
	
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		viewallvideowithfeatured = new ViewAllVideoWithFeatured();
	}

	@Parameters( { "searchTerm" })
	@Test
	public void verifyViewallVideoSortOrder(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifyViewallVideoSortOrder());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewallVideoSortOrder" })
	public void verifyHandpickedItemsNotOntopOfList(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifyHandpickedItemsNotOntopOfList());

	}
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewallVideoSortOrder" })
	public void verifyHanpickedItemsByChangingSortOrder(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifyHanpickedItemsByChangingSortOrder());

	}
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewallVideoSortOrder" })
	public void verifyHandpickedForPubdate(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifyHandpickedForPubdate());

	}
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewallVideoSortOrder" })
	public void verifySortOrderForOtherOption(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifySortOrderForOtherOption());

	}
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewallVideoSortOrder" })
	public void verifySortOrderForDefaultOne(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifySortOrderForDefaultOne());

	}
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewallVideoSortOrder" })
	public void verifyFeaturedItemsBynavigatingtoOtherDisplayGroups(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifyFeaturedItemsBynavigatingtoOtherDisplayGroups());

	}
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewallVideoSortOrder" })
	public void verifyFeaturendItemsbyNavigationtoOtherPages(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifyFeaturendItemsbyNavigationtoOtherPages());

	}
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewallVideoSortOrder" })
	public void verifyFeaturedItemsbyDifferentPagesWithDifferentSortOrder(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifyFeaturedItemsbyDifferentPagesWithDifferentSortOrder());

	}
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewallVideoSortOrder" })
	public void verifyFeaturedItemsOnDetailedPage(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifyFeaturedItemsOnDetailedPage());

	}
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewallVideoSortOrder" })
	public void verifyFeaturedItemsOnSearchResultsPage(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewallvideowithfeatured
				.verifyFeaturedItemsOnSearchResultsPage());

	}
	
	
	
	
	

}
