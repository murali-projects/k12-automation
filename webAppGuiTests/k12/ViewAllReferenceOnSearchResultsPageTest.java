package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllReferenceOnSearchResultsPage;

public class ViewAllReferenceOnSearchResultsPageTest extends BaseWebPageTest {

	private ViewAllReferenceOnSearchResultsPage refDocSearch;
	public String searchTerm;

	public String referenceArticle;

	@Parameters( { "searchTerm", "referenceArticle" })
	@BeforeTest
	public void setUp(String searchTerm, String referenceArticle)
			throws Exception {
		refDocSearch = new ViewAllReferenceOnSearchResultsPage();
		this.searchTerm = searchTerm;
		this.referenceArticle = referenceArticle;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test(dependsOnMethods = { "basicSearch" })
	public void viewAllLinkForReferenceArticleIsDispalyed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsPresent(referenceArticle));
	}

	@Test(dependsOnMethods = { "viewAllLinkForReferenceArticleIsDispalyed" })
	public void viewAllLinkForReferenceArticleIsWorking() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsWorking(referenceArticle));
	}

	@Test(dependsOnMethods = { "viewAllLinkForReferenceArticleIsWorking" })
	public void viewAllNavigationForReferenceArticle() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllIsNavigatedCorrectly(referenceArticle));
	}

	@Test(dependsOnMethods = { "viewAllNavigationForReferenceArticle" })
	public void verifyDocumentDisplayPageResultsForReferenceArticle()
			throws Exception {
		Assert.assertTrue(refDocSearch
				.verifyDocumentFullListPage(referenceArticle));
	}

	@Test(dependsOnMethods = { "verifyDocumentDisplayPageResultsForReferenceArticle" })
	public void verifyNextLinkForReferenceArticle() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLink());
	}

	@Test(dependsOnMethods = { "verifyNextLinkForReferenceArticle" })
	public void verifyNextLinkIsworkingForReferenceArticle() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyNextLinkIsworkingForReferenceArticle" })
	public void verifyPreviousLinkForReferenceArticle() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLink());
	}

	@Test(dependsOnMethods = { "verifyPreviousLinkForReferenceArticle" })
	public void verifyPreviousLinkIsworkingForReferenceArticle()
			throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyPreviousLinkIsworkingForReferenceArticle" })
	public void verifyLastLinkForReferenceArticle() throws Exception {
		Assert.assertTrue(refDocSearch.verifyLastLink());
	}

	@Test(dependsOnMethods = { "verifyLastLinkForReferenceArticle" })
	public void verifyLastLinkIsworkingForReferenceArticle() throws Exception {
		Assert.assertTrue(refDocSearch.verifyLastLinkIsworking());
	}

	@Test(dependsOnMethods = { "verifyLastLinkIsworkingForReferenceArticle" })
	public void verifyFirstLinkForReferenceArticle() throws Exception {
		Assert.assertTrue(refDocSearch.verifyFirstLink());
	}

	@Test(dependsOnMethods = { "verifyFirstLinkForReferenceArticle" })
	public void verifyFirstLinkIsworkingForReferenceArticle() throws Exception {
		Assert.assertTrue(refDocSearch.verifyFirstLinkIsworking());
	}

}
