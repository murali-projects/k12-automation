package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllMagazinesOnSearchResultsPage;

public class ViewAllMagazinesOnSearchResultsPageTest extends BaseWebPageTest {

	private ViewAllMagazinesOnSearchResultsPage magDocSearch;
	public String searchTerm;
	public String magazines;

	@Parameters( { "searchTerm", "magazines" })
	@BeforeTest
	public void setUp(String searchTerm, String magazines) throws Exception {
		magDocSearch = new ViewAllMagazinesOnSearchResultsPage();
		this.searchTerm = searchTerm;
		this.magazines = magazines;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	 @Test(dependsOnMethods = {"basicSearch"})
	public void viewAllLinkForMagazinesIsDispalyed() throws Exception {
		Assert.assertTrue(magDocSearch
				.checkViewAllLinkIsPresent(magazines));
	}
	
	 @Test(dependsOnMethods = {"viewAllLinkForMagazinesIsDispalyed"})
	public void viewAllLinkForMagazinesIsWorking() throws Exception {
		Assert.assertTrue(magDocSearch
				.checkViewAllLinkIsWorking(magazines));
	}

	 @Test(dependsOnMethods = {"viewAllLinkForMagazinesIsWorking"})
	public void viewAllNavigationForMagazines()
			throws Exception {
		Assert.assertTrue(magDocSearch
				.checkViewAllIsNavigatedCorrectly(magazines));
	}

	 @Test(dependsOnMethods = {"viewAllNavigationForMagazines"})
	public void verifyDocumentDisplayPageResultsForMagazines() throws Exception {
	   // doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(magDocSearch
				.verifyDocumentFullListPage(magazines));
	}
	
	 @Test(dependsOnMethods = {"verifyDocumentDisplayPageResultsForMagazines"})
	public void verifyNextLinkForMagazines() throws Exception {
		Assert.assertTrue(magDocSearch.verifyNextLink());
	}

	 @Test(dependsOnMethods = {"verifyNextLinkForMagazines"})
	public void verifyNextLinkIsworkingForMagazines() throws Exception {
		Assert.assertTrue(magDocSearch.verifyNextLinkIsworking());
	}

	 @Test(dependsOnMethods = {"verifyNextLinkIsworkingForMagazines"})
	public void verifyPreviousLinkForMagazines() throws Exception {
		Assert.assertTrue(magDocSearch.verifyPreviousLink());
	}

	 @Test(dependsOnMethods = {"verifyPreviousLinkForMagazines"})
	public void verifyPreviousLinkIsworkingForMagazines()
			throws Exception {
		Assert.assertTrue(magDocSearch.verifyPreviousLinkIsworking());
	}

	@Test(dependsOnMethods = {"verifyPreviousLinkIsworkingForMagazines"})
	public void validateDocumentTitlesWithDB()
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(magDocSearch.validateWithDatabase(magazines, searchTerm));
	} 

}
