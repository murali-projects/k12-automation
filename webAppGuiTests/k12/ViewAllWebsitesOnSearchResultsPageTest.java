package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllWebsitesOnSearchResultsPage;

public class ViewAllWebsitesOnSearchResultsPageTest extends BaseWebPageTest {

	private ViewAllWebsitesOnSearchResultsPage websitesDocSearch;
	public String searchTerm;
	public String websites;

	@Parameters( { "searchTerm", "websites" })
	@BeforeTest
	public void setUp(String searchTerm, String websites) throws Exception {
		websitesDocSearch = new ViewAllWebsitesOnSearchResultsPage();
		this.searchTerm = searchTerm;
		this.websites = websites;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	 @Test(dependsOnMethods = {"basicSearch"})
	public void viewAllLinkForWebsitesIsDispalyed() throws Exception {
		Assert.assertTrue(websitesDocSearch
				.checkViewAllLinkIsPresent(websites));
	}
	
	 @Test(dependsOnMethods = {"viewAllLinkForWebsitesIsDispalyed"})
	public void viewAllLinkForWebsitesIsWorking() throws Exception {
		Assert.assertTrue(websitesDocSearch
				.checkViewAllLinkIsWorking(websites));
	}

	 @Test(dependsOnMethods = {"viewAllLinkForWebsitesIsWorking"})
	public void viewAllNavigationForWebsites()
			throws Exception {
		Assert.assertTrue(websitesDocSearch
				.checkViewAllIsNavigatedCorrectly(websites));
	}

	 @Test(dependsOnMethods = {"viewAllNavigationForWebsites"})
	public void verifyDocumentDisplayPageResultsForWebsites() throws Exception {
	   // doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(websitesDocSearch
				.verifyDocumentFullListPage(websites));
	}
	
	 @Test(dependsOnMethods = {"verifyDocumentDisplayPageResultsForWebsites"})
	public void verifyNextLinkForWebsites() throws Exception {
		Assert.assertTrue(websitesDocSearch.verifyNextLink());
	}

	 @Test(dependsOnMethods = {"verifyNextLinkForWebsites"})
	public void verifyNextLinkIsworkingForWebsites() throws Exception {
		Assert.assertTrue(websitesDocSearch.verifyNextLinkIsworking());
	}

	 @Test(dependsOnMethods = {"verifyNextLinkIsworkingForWebsites"})
	public void verifyPreviousLinkForWebsites() throws Exception {
		Assert.assertTrue(websitesDocSearch.verifyPreviousLink());
	}

	 @Test(dependsOnMethods = {"verifyPreviousLinkForWebsites"})
	public void verifyPreviousLinkIsworkingForWebsites()
			throws Exception {
		Assert.assertTrue(websitesDocSearch.verifyPreviousLinkIsworking());
	}

	@Test(dependsOnMethods = {"verifyPreviousLinkIsworkingForWebsites"})
	public void validateDocumentTitlesWithDB()
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(websitesDocSearch.validateWithDatabase(websites, searchTerm));
	} 

}
