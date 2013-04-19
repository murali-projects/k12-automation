package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllStatisticsOnSearchResultsPage;

public class ViewAllStatisticsOnSearchResultsPageTest extends BaseWebPageTest {

	private ViewAllStatisticsOnSearchResultsPage statisticsDocSearch;
	public String searchTerm;
	public String statistics;

	@Parameters( { "searchTerm", "statistics" })
	@BeforeTest
	public void setUp(String searchTerm, String statistics) throws Exception {
		statisticsDocSearch = new ViewAllStatisticsOnSearchResultsPage();
		this.searchTerm = searchTerm;
		this.statistics = statistics;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test(dependsOnMethods = {"basicSearch"})
	public void viewAllLinkForStatisticsIsDispalyed() throws Exception {
		Assert.assertTrue(statisticsDocSearch
				.checkViewAllLinkIsPresent(statistics));
	}
	
	 @Test(dependsOnMethods = {"viewAllLinkForStatisticsIsDispalyed"})
	public void viewAllLinkForStatisticsIsWorking() throws Exception {
		Assert.assertTrue(statisticsDocSearch
				.checkViewAllLinkIsWorking(statistics));
	}

	 @Test(dependsOnMethods = {"viewAllLinkForStatisticsIsWorking"})
	public void viewAllNavigationForStatistics()
			throws Exception {
		Assert.assertTrue(statisticsDocSearch
				.checkViewAllIsNavigatedCorrectly(statistics));
	}

	 @Test(dependsOnMethods = {"viewAllNavigationForStatistics"})
	public void verifyDocumentDisplayPageResultsForStatistics() throws Exception {
	   // doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(statisticsDocSearch
				.verifyDocumentFullListPage(statistics));
	}
	
	 @Test(dependsOnMethods = {"verifyDocumentDisplayPageResultsForStatistics"})
	public void verifyNextLinkForStatistics() throws Exception {
		Assert.assertTrue(statisticsDocSearch.verifyNextLink());
	}

	 @Test(dependsOnMethods = {"verifyNextLinkForStatistics"})
	public void verifyNextLinkIsworkingForStatistics() throws Exception {
		Assert.assertTrue(statisticsDocSearch.verifyNextLinkIsworking());
	}

	 @Test(dependsOnMethods = {"verifyNextLinkIsworkingForStatistics"})
	public void verifyPreviousLinkForStatistics() throws Exception {
		Assert.assertTrue(statisticsDocSearch.verifyPreviousLink());
	}

	 @Test(dependsOnMethods = {"verifyPreviousLinkForStatistics"})
	public void verifyPreviousLinkIsworkingForStatistics()
			throws Exception {
		Assert.assertTrue(statisticsDocSearch.verifyPreviousLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyPreviousLinkIsworkingForStatistics" })
	public void validateDocumentTitlesWithDB() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(statisticsDocSearch.validateWithDatabase(statistics,
				searchTerm));
	}
}
