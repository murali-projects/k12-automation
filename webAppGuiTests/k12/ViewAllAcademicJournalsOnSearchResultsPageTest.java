package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllAcademicJournalsOnSearchResultsPage;

public class ViewAllAcademicJournalsOnSearchResultsPageTest extends BaseWebPageTest {

	private ViewAllAcademicJournalsOnSearchResultsPage viewAcademicJournals;
	public String searchTerm;
	public String academicJournals;

	@Parameters( { "searchTerm", "academicJournals" })
	@BeforeTest
	public void setUp(String searchTerm, String academicJournals) throws Exception {
		viewAcademicJournals = new ViewAllAcademicJournalsOnSearchResultsPage();
		this.searchTerm = searchTerm;
		this.academicJournals = academicJournals;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test(dependsOnMethods = {"basicSearch"})
	public void viewAllLinkForAcademicJournalsIsDispalyed() throws Exception {
		Assert.assertTrue(viewAcademicJournals
				.checkViewAllLinkIsPresent(academicJournals));
	}
	
	@Test(dependsOnMethods = {"viewAllLinkForAcademicJournalsIsDispalyed"})
	public void viewAllLinkForAcademicJournalsIsWorking() throws Exception {
		Assert.assertTrue(viewAcademicJournals
				.checkViewAllLinkIsWorking(academicJournals));
	}

	@Test(dependsOnMethods = {"viewAllLinkForAcademicJournalsIsWorking"})
	public void viewAllNavigationForAcademicJournals()
			throws Exception {
		Assert.assertTrue(viewAcademicJournals
				.checkViewAllIsNavigatedCorrectly(academicJournals));
	}

	@Test(dependsOnMethods = {"viewAllNavigationForAcademicJournals"})
	public void verifyDocumentDisplayPageResultsForAcademicJournals() throws Exception {
	   // doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewAcademicJournals
				.verifyDocumentFullListPage(academicJournals));
	}
	
	@Test(dependsOnMethods = {"verifyDocumentDisplayPageResultsForAcademicJournals"})
	public void verifyNextLinkForAcademicJournals() throws Exception {
		Assert.assertTrue(viewAcademicJournals.verifyNextLink());
	}

	@Test(dependsOnMethods = {"verifyNextLinkForAcademicJournals"})
	public void verifyNextLinkIsworkingForAcademicJournals() throws Exception {
		Assert.assertTrue(viewAcademicJournals.verifyNextLinkIsworking());
	}

	@Test(dependsOnMethods = {"verifyNextLinkIsworkingForAcademicJournals"})
	public void verifyPreviousLinkForAcademicJournals() throws Exception {
		Assert.assertTrue(viewAcademicJournals.verifyPreviousLink());
	}

	@Test(dependsOnMethods = {"verifyPreviousLinkForAcademicJournals"})
	public void verifyPreviousLinkIsworkingAcademicJournals()
			throws Exception {
		Assert.assertTrue(viewAcademicJournals.verifyPreviousLinkIsworking());
	}


}
