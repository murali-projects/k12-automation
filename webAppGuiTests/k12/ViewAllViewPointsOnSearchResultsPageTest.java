package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllViewPointsOnSearchResultsPage;

public class ViewAllViewPointsOnSearchResultsPageTest extends BaseWebPageTest {

	private ViewAllViewPointsOnSearchResultsPage viewPointDocSearch;
	public String searchTerm;
	public String viewpoints;

	@Parameters( { "searchTerm", "viewpoints" })
	@BeforeTest
	public void setUp(String searchTerm, String viewpoints) throws Exception {
		viewPointDocSearch = new ViewAllViewPointsOnSearchResultsPage();
		this.searchTerm = searchTerm;
		this.viewpoints = viewpoints;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test(dependsOnMethods = { "basicSearch" })
	public void viewAllLinkForViewPointsIsDispalyed() throws Exception {
		Assert.assertTrue(viewPointDocSearch
				.checkViewAllLinkIsPresent(viewpoints));
	}

	@Test(dependsOnMethods = { "viewAllLinkForViewPointsIsDispalyed" })
	public void viewAllLinkForViewPointsIsWorking() throws Exception {
		Assert.assertTrue(viewPointDocSearch
				.checkViewAllLinkIsWorking(viewpoints));
	}

	@Test(dependsOnMethods = { "viewAllLinkForViewPointsIsWorking" })
	public void viewAllNavigationForViewPoints() throws Exception {
		Assert.assertTrue(viewPointDocSearch
				.checkViewAllIsNavigatedCorrectly(viewpoints));
	}

	@Test(dependsOnMethods = { "viewAllNavigationForViewPoints" })
	public void verifyDocumentDisplayPageResultsForViewPoints()
			throws Exception {
		// doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewPointDocSearch
				.verifyDocumentFullListPage(viewpoints));
	}

	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResultsForViewPoints" })
	public void verifyNextLinkForViewPoints() throws Exception {
		Assert.assertTrue(viewPointDocSearch.verifyNextLink());
	}

	@Test(dependsOnMethods = { "verifyNextLinkForViewPoints" })
	public void verifyNextLinkIsworkingForViewPoints() throws Exception {
		Assert.assertTrue(viewPointDocSearch.verifyNextLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyNextLinkIsworkingForViewPoints" })
	public void verifyPreviousLinkForViewPoints() throws Exception {
		Assert.assertTrue(viewPointDocSearch.verifyPreviousLink());
	}

	@Test(dependsOnMethods = { "verifyPreviousLinkForViewPoints" })
	public void verifyPreviousLinkIsworkingForViewPoints() throws Exception {
		Assert.assertTrue(viewPointDocSearch.verifyPreviousLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyPreviousLinkIsworkingForViewPoints" })
	public void validateDocumentTitlesWithDB() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewPointDocSearch.validateWithDatabase(viewpoints,
				searchTerm));
	}

}
