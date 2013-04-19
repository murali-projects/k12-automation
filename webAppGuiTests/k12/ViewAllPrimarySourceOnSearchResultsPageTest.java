package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllPrimarySourceOnSearchResultsPage;

public class ViewAllPrimarySourceOnSearchResultsPageTest extends BaseWebPageTest {

	private ViewAllPrimarySourceOnSearchResultsPage primarySourceDocSearch;
	public String searchTerm;
	public String primarysources;

	@Parameters( { "searchTerm", "primarysources" })
	@BeforeTest
	public void setUp(String searchTerm, String primarysources) throws Exception {
		primarySourceDocSearch = new ViewAllPrimarySourceOnSearchResultsPage();
		this.searchTerm = searchTerm;
		this.primarysources = primarysources;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	 @Test(dependsOnMethods = {"basicSearch"})
	public void viewAllLinkForPrimarySourceIsDispalyed() throws Exception {
		Assert.assertTrue(primarySourceDocSearch
				.checkViewAllLinkIsPresent(primarysources));
	}
	
	 @Test(dependsOnMethods = {"viewAllLinkForPrimarySourceIsDispalyed"})
	public void viewAllLinkForPrimarySourceIsWorking() throws Exception {
		Assert.assertTrue(primarySourceDocSearch
				.checkViewAllLinkIsWorking(primarysources));
	}

	 @Test(dependsOnMethods = {"viewAllLinkForPrimarySourceIsWorking"})
	public void viewAllNavigationForPrimarySource()
			throws Exception {
		Assert.assertTrue(primarySourceDocSearch
				.checkViewAllIsNavigatedCorrectly(primarysources));
	}

	 @Test(dependsOnMethods = {"viewAllNavigationForPrimarySource"})
	public void verifyDocumentDisplayPageResultsForPrimarySource() throws Exception {
	   // doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(primarySourceDocSearch
				.verifyDocumentFullListPage(primarysources));
	}
	
	 @Test(dependsOnMethods = {"verifyDocumentDisplayPageResultsForPrimarySource"})
	public void verifyNextLinkForPrimarySource() throws Exception {
		Assert.assertTrue(primarySourceDocSearch.verifyNextLink());
	}

	 @Test(dependsOnMethods = {"verifyNextLinkForPrimarySource"})
	public void verifyNextLinkIsworkingForPrimarySource() throws Exception {
		Assert.assertTrue(primarySourceDocSearch.verifyNextLinkIsworking());
	}

	 @Test(dependsOnMethods = {"verifyNextLinkIsworkingForPrimarySource"})
	public void verifyPreviousLinkForPrimarySource() throws Exception {
		Assert.assertTrue(primarySourceDocSearch.verifyPreviousLink());
	}

	 @Test(dependsOnMethods = {"verifyPreviousLinkForPrimarySource"})
	public void verifyPreviousLinkIsworkingForPrimarySource()
			throws Exception {
		Assert.assertTrue(primarySourceDocSearch.verifyPreviousLinkIsworking());
	}

	@Test(dependsOnMethods = {"verifyPreviousLinkIsworkingForPrimarySource"})
	public void validateDocumentTitlesWithDB()
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(primarySourceDocSearch.validateWithDatabase(primarysources, searchTerm));
	} 

}
