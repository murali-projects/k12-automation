package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllNewsOnSearchResultsPage;

public class ViewAllNewsOnSearchResultsPageTest extends BaseWebPageTest {

	private ViewAllNewsOnSearchResultsPage newsDocSearch;
	public String searchTerm;
	public String news;

	@Parameters( { "searchTerm", "news" })
	@BeforeTest
	public void setUp(String searchTerm, String news) throws Exception {
		newsDocSearch = new ViewAllNewsOnSearchResultsPage();
		this.searchTerm = searchTerm;
		this.news = news;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

//	@Test
	 @Test(dependsOnMethods = {"basicSearch"})
	public void viewAllLinkForNewsIsDispalyed() throws Exception {
		Assert.assertTrue(newsDocSearch.checkViewAllLinkIsPresent(news));
	}

//	@Test
	 @Test(dependsOnMethods = {"viewAllLinkForNewsIsDispalyed"})
	public void viewAllLinkForNewsIsWorking() throws Exception {
		Assert.assertTrue(newsDocSearch.checkViewAllLinkIsWorking(news));
	}

//	@Test
	 @Test(dependsOnMethods = {"viewAllLinkForNewsIsWorking"})
	public void viewAllNavigationForNews() throws Exception {
		Assert.assertTrue(newsDocSearch.checkViewAllIsNavigatedCorrectly(news));
	}

//	 @Test
	@Test(dependsOnMethods = { "viewAllNavigationForNews" })
	public void verifyDocumentDisplayPageResultsForNews() throws Exception {
		// doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(newsDocSearch.verifyDocumentFullListPage(news));
	}

//	 @Test
	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResultsForNews" })
	public void verifyNextLinkForNews() throws Exception {
		Assert.assertTrue(newsDocSearch.verifyNextLink());
	}

//	 @Test
	@Test(dependsOnMethods = { "verifyNextLinkForNews" })
	public void verifyNextLinkIsworkingForNews() throws Exception {
		Assert.assertTrue(newsDocSearch.verifyNextLinkIsworking());
	}

//	 @Test
	@Test(dependsOnMethods = { "verifyNextLinkIsworkingForNews" })
	public void verifyPreviousLinkForNews() throws Exception {
		Assert.assertTrue(newsDocSearch.verifyPreviousLink());
	}

//	 @Test
	@Test(dependsOnMethods = { "verifyPreviousLinkForNews" })
	public void verifyPreviousLinkIsworkingForNews() throws Exception {
		Assert.assertTrue(newsDocSearch.verifyPreviousLinkIsworking());
	}

//	 @Test
	@Test(dependsOnMethods = { "verifyPreviousLinkIsworkingForNews" })
	public void validateDocumentTitlesWithDB() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(newsDocSearch.validateWithDatabase(news,
				searchTerm));
	}
}
