package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ConstructAndSubmitLimitedSearch;

public class ConstructAndSubmitLimitedSearchTest extends BaseWebPageTest{
	private ConstructAndSubmitLimitedSearch constructAndSubmitLimitedSearch;
	private String searchTerm;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		constructAndSubmitLimitedSearch = new ConstructAndSubmitLimitedSearch(searchTerm);
		this.searchTerm = searchTerm;
	}
	
	@Test
	public void verifyLimitSearchByPresent() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(constructAndSubmitLimitedSearch.verifyLimitSearchByPresent());
	}
	
	@Test(dependsOnMethods = { "verifyLimitSearchByPresent" })
	public void verifyCountAfterLimitSearch() throws Exception {
		Assert.assertTrue(constructAndSubmitLimitedSearch.verifyCountAfterLimitSearch());
	}
	
	@Test(dependsOnMethods = { "verifyCountAfterLimitSearch" })
	public void verifyLimitByPublicationTitleWorking() throws Exception {
		Assert.assertTrue(constructAndSubmitLimitedSearch.verifyLimitByPublicationTitleWorking());
	}
	
	@Test(dependsOnMethods = { "verifyLimitByPublicationTitleWorking" })
	public void verifyResultsAreReducedWithMoreLimiters() throws Exception {
		Assert.assertTrue(constructAndSubmitLimitedSearch.verifyResultsAreReducedWithMoreLimiters());
	}
	
	@Parameters({"news", "newsPublicationTitle", "newsDocType", "newsSubject", "limitByQueryForNews"})
	@Test(dependsOnMethods = { "verifyResultsAreReducedWithMoreLimiters" })
	public void verifyLimitByResultsCountForNews(String news, String newsPublicationTitle, String newsDocType, String newsSubject, String limitByQueryForNews) throws Exception {
		Assert.assertTrue(constructAndSubmitLimitedSearch.verifyLimitByResultsCountWithDB(newsPublicationTitle, newsDocType, newsSubject, news, limitByQueryForNews));
	}
	
	@Parameters({"magazines", "magazinesPublicationTitle", "magazinesDocType", "magazinesSubject", "limitByQueryForMagazines", "images", "imagesPublicationTitle", "imagesDocType", "imagesSubject", "limitByQueryForImages"})
	@Test(dependsOnMethods = { "verifyLimitByResultsCountForNews" })
	public void verifyLimitByResultsCountForOtherDisplayGroups(String magazines, String magazinesPublicationTitle, String magazinesDocType, String magazinesSubject, String limitByQueryForMagazines,
			String images, String imagesPublicationTitle, String imagesDocType, String imagesSubject, String limitByQueryForImages) throws Exception {
		Assert.assertTrue(constructAndSubmitLimitedSearch.verifyLimitByResultsCountWithDB(magazinesPublicationTitle, magazinesDocType, magazinesSubject, magazines, limitByQueryForMagazines) &&
				constructAndSubmitLimitedSearch.verifyLimitByResultsCountWithDB(imagesPublicationTitle, imagesDocType, imagesSubject, images, limitByQueryForImages));
	}
}
