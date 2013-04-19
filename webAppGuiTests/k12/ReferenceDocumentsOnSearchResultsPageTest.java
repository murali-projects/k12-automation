package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ReferenceDocumentsOnSearchResultsPage;
@Deprecated
// TODO - Remove this class. All logic is moved to ViewAllReferenceOnSearchResultsPageTest
public class ReferenceDocumentsOnSearchResultsPageTest extends BaseWebPageTest {

	private ReferenceDocumentsOnSearchResultsPage refDocSearch;
	public String searchTerm;
	public String newsDisplayName;
	public String magazinesDisplayName;
	public String primarySourceDisplayName;
	public String academicDisplayName;
	public String viewPointsDisplayName;
	public String statisticsDisplayName;
	public String referenceArticle;

	@Parameters( { "searchTerm", "newsDisplayName", "magazinesDisplayName",
			"primarySourceDisplayName", "academicDisplayName",
			"viewPointsDisplayName", "statisticsDisplayName","referenceArticle" })
	@BeforeTest
	public void setUp(String searchTerm, String newsDisplayName,
			 String magazinesDisplayName,String primarySourceDisplayName,
			String academicDisplayName, String viewPointsDisplayName,
			 String statisticsDisplayName,String referenceArticle) throws Exception {
		refDocSearch = new ReferenceDocumentsOnSearchResultsPage();
		this.searchTerm = searchTerm;
		this.newsDisplayName = newsDisplayName;
		this.magazinesDisplayName = magazinesDisplayName;
		this.primarySourceDisplayName = primarySourceDisplayName;
		this.academicDisplayName = academicDisplayName;
		this.viewPointsDisplayName = viewPointsDisplayName;
		this.statisticsDisplayName = statisticsDisplayName;
		this.referenceArticle = referenceArticle;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test(dependsOnMethods = { "basicSearch" })
	public void referenceArticleIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkDisplayGroupIsPresent(referenceArticle));
	}

	//@Test
	 @Test(dependsOnMethods = {"referenceArticleIsDisplayed"})
	public void viewAllLinkForReferenceArticleIsDispalyed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsPresent(referenceArticle));
	}


	//@Test
	 @Test(dependsOnMethods = {"viewAllLinkForReferenceArticleIsDispalyed"})
	public void viewAllLinkForReferenceArticleIsWorking() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsWorking(referenceArticle));
	}

	//@Test
	 @Test(dependsOnMethods = {"viewAllLinkForReferenceArticleIsWorking"})
	public void viewAllNavigationForReferenceArticle()
			throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllIsNavigatedCorrectly(referenceArticle));
	}

	//@Test
	 @Test(dependsOnMethods = {"viewAllNavigationForReferenceArticle"})
	public void verifyDocumentDisplayPageResultsForReferenceArticle() throws Exception {
	    doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(refDocSearch
				.verifyDocumentDisplayPage(referenceArticle));
	}
	
	//@Test
	 @Test(dependsOnMethods = {"verifyDocumentDisplayPageResultsForReferenceArticle"})
	public void verifyNextLinkForReferenceArticle() throws Exception {
		//Assert.assertTrue(refDocSearch.verifyNextLink(referenceArticle));
	}

	//@Test
	 @Test(dependsOnMethods = {"verifyNextLinkForReferenceArticle"})
	public void verifyNextLinkIsworkingForReferenceArticle() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLinkIsworking());
	}

	//@Test
	 @Test(dependsOnMethods = {"verifyNextLinkIsworkingForReferenceArticle"})
	public void verifyPreviousLinkForReferenceArticle() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLink());
	}

	//@Test
	 @Test(dependsOnMethods = {"verifyPreviousLinkForReferenceArticle"})
	public void verifyPreviousLinkIsworkingForReferenceArticle()
			throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLinkIsworking());
	}

	/*@Test
	// @Test(dependsOnMethods = {"basicSearch"})
	public void newsIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkDisplayGroupIsPresent(newsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"newsIsDisplayed"})
	public void viewAllLinkForNewsIsDispalyed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsPresent(newsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewAllLinkForNewsIsDispalyed"})
	public void viewAllLinkForNewsIsWorking() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsWorking(newsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewAllLinkForNewsIsWorking"})
	public void newsRelatedLinksIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.displayGroupRelatedLinks(newsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"newsRelatedLinksIsDisplayed"})
	public void viewAllNavigationForNews() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllIsNavigatedCorrectly(newsDisplayName));
	}
	
	//@Test
	 @Test(dependsOnMethods = {"viewAllNavigationForNews"})
	public void verifyDocumentDisplayPageResultsForNews() throws Exception {
	    doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(refDocSearch
				.verifyDocumentDisplayPage(newsDisplayName));
	}

	@Parameters( { "newsDisplayName" })
	@Test
	// @Test(dependsOnMethods = {"viewAllNavigationForNews"})
	public void verifyNextLinkForNews() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLink(newsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"verifyNextLinkForNews"})
	public void verifyNextLinkIsworkingForNews() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLinkIsworking());
	}

	@Test
	// @Test(dependsOnMethods = {"verifyNextLinkIsworkingForNews"})
	public void verifyPreviousLinkForNews() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLink());
	}

	@Test
	// @Test(dependsOnMethods = {"verifyPreviousLinkForNews"})
	public void verifyPreviousLinkIsworkingForNews() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLinkIsworking());
	}

	@Test
	// @Test(dependsOnMethods = {"basicSearch"})
	public void viewPointsIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkDisplayGroupIsPresent(viewPointsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewPointsIsDisplayed"})
	public void viewAllLinkForViewPointsIsDispalyed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsPresent(viewPointsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewAllLinkForViewPointsIsDispalyed"})
	public void viewAllLinkForViewPointsIsWorking() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsWorking(viewPointsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewAllLinkForViewPointsIsWorking"})
	public void viewPointsRelatedLinksIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.displayGroupRelatedLinks(viewPointsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewPointsRelatedLinksIsDisplayed"})
	public void viewAllNavigationForViewPoints() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllIsNavigatedCorrectly(viewPointsDisplayName));
	}
	
	//@Test
	 @Test(dependsOnMethods = {"viewAllNavigationForViewPoints"})
	public void verifyDocumentDisplayPageResultsForViewPoints() throws Exception {
	    doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(refDocSearch
				.verifyDocumentDisplayPage(viewPointsDisplayName));
	}
	

	@Test
	// @Test(dependsOnMethods = {"viewAllNavigationForNews"})
	public void verifyNextLinkForViewPoints() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLink(viewPointsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"verifyNextLinkForNews"})
	public void verifyNextLinkIsworkingForViewPoints() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLinkIsworking());
	}

	@Test
	// @Test(dependsOnMethods = {"verifyNextLinkIsworkingForNews"})
	public void verifyPreviousLinkForViewPoints() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLink());
	}

	@Test
	// @Test(dependsOnMethods = {"verifyPreviousLinkForNews"})
	public void verifyPreviousLinkIsworkingForViewPoints() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLinkIsworking());
	}*/

//	 @Test(dependsOnMethods = {"basicSearch"})
//	public void magazinesIsDisplayed() throws Exception {
//		doBasicSearchUsingSearchTerm(searchTerm);
//		Assert.assertTrue(refDocSearch
//				.checkDisplayGroupIsPresent(magazinesDisplayName));
//	}
//
//	 @Test(dependsOnMethods = {"magazinesIsDisplayed"})
//	public void viewAllLinkForMagazinesIsDispalyed() throws Exception {
//		Assert.assertTrue(refDocSearch
//				.checkViewAllLinkIsPresent(magazinesDisplayName));
//	}
//	
//	 @Test(dependsOnMethods = {"viewAllLinkForMagazinesIsDispalyed"})
//	public void magazinesRelatedLinksIsDisplayed() throws Exception {
//		Assert.assertTrue(refDocSearch
//				.displayGroupRelatedLinks(magazinesDisplayName));
//	}
//
//	 @Test(dependsOnMethods = {"magazinesRelatedLinksIsDisplayed"})
//	public void viewAllLinkForMagazinesIsWorking() throws Exception {
//		Assert.assertTrue(refDocSearch
//				.checkViewAllLinkIsWorking(magazinesDisplayName));
//	}
//
//	@Test(dependsOnMethods = {"viewAllLinkForMagazinesIsWorking"})
//	public void viewAllNavigationForMagazines() throws Exception {
//		Assert.assertTrue(refDocSearch
//				.checkViewAllIsNavigatedCorrectly(magazinesDisplayName));
//	}
//	
//	 @Test(dependsOnMethods = {"viewAllNavigationForMagazines"})
//	public void verifyDocumentDisplayPageResultsForMagazines() throws Exception {
//	    doBasicSearchUsingSearchTerm(searchTerm);
//		Assert.assertTrue(refDocSearch
//				.verifyDocumentDisplayPage(magazinesDisplayName));
//	}
//	 
//	@Test(dependsOnMethods = {"verifyDocumentDisplayPageResultsForMagazines"})
//	public void verifyNextLinkForMagazines() throws Exception {
//		//Assert.assertTrue(refDocSearch.verifyNextLink(magazinesDisplayName));
//	}
//
//	@Test(dependsOnMethods = {"verifyNextLinkForMagazines"})
//	public void verifyNextLinkIsworkingForMagazines() throws Exception {
//		Assert.assertTrue(refDocSearch.verifyNextLinkIsworking());
//	}
//
//	 @Test(dependsOnMethods = {"verifyNextLinkIsworkingForMagazines"})
//	public void verifyPreviousLinkForMagazines() throws Exception {
//		Assert.assertTrue(refDocSearch.verifyPreviousLink());
//	}
//
//	@Test(dependsOnMethods = {"verifyPreviousLinkForMagazines"})
//	public void verifyPreviousLinkIsworkingForMagazines() throws Exception {
//		Assert.assertTrue(refDocSearch.verifyPreviousLinkIsworking());
//	}

	/*@Test
	// @Test(dependsOnMethods = {"basicSearch"})
	public void academicJournalsIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkDisplayGroupIsPresent(academicDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"academicJournalsIsDisplayed"})
	public void viewAllLinkForAcademicJournalsIsDispalyed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsPresent(academicDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewAllLinkForAcademicJournalsIsDispalyed"})
	public void viewAllLinkForAcademicJournalsIsWorking() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsWorking(academicDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewAllLinkForAcademicJournalsIsWorking"})
	public void academicJournalsRelatedLinksIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.displayGroupRelatedLinks(academicDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"academicJournalsRelatedLinksIsDisplayed"})
	public void viewAllNavigationForAcademicJournals() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllIsNavigatedCorrectly(academicDisplayName));
	}
	
	//@Test
	 @Test(dependsOnMethods = {"viewAllNavigationForAcademicJournals"})
	public void verifyDocumentDisplayPageResultsForAcademicJournals() throws Exception {
	    doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(refDocSearch
				.verifyDocumentDisplayPage(academicDisplayName));
	}
	

	@Test
	// @Test(dependsOnMethods = {"viewAllNavigationForAcademicJournals"})
	public void verifyNextLinkForAcademicJournals() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLink(academicDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"verifyNextLinkForAcademicJournals"})
	public void verifyNextLinkIsworkingForAcademicJournals() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLinkIsworking());
	}

	@Test
	// @Test(dependsOnMethods = {"verifyNextLinkIsworkingForAcademicJournals"})
	public void verifyPreviousLinkForAcademicJournals() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLink());
	}

	@Test
	// @Test(dependsOnMethods = {"verifyPreviousLinkForAcademicJournals"})
	public void verifyPreviousLinkIsworkingForAcademicJournals()
			throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLinkIsworking());
	}

	@Test
	// @Test(dependsOnMethods = {"basicSearch"})
	public void primarySourceIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkDisplayGroupIsPresent(primarySourceDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"primarySourceIsDisplayed"})
	public void viewAllLinkForPrimarySourceIsDispalyed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsPresent(primarySourceDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewAllLinkForPrimarySourceIsDispalyed"})
	public void viewAllLinkForPrimarySourceIsWorking() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsWorking(primarySourceDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewAllLinkForPrimarySourceIsWorking"})
	public void primarySourceRelatedLinksIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.displayGroupRelatedLinks(primarySourceDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"primarySourceRelatedLinksIsDisplayed"})
	public void viewAllNavigationForPrimarySource() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllIsNavigatedCorrectly(primarySourceDisplayName));
	}

	//@Test
	 @Test(dependsOnMethods = {"viewAllNavigationForPrimarySource"})
	public void verifyDocumentDisplayPageResultsForPrimarySource() throws Exception {
	    doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(refDocSearch
				.verifyDocumentDisplayPage(primarySourceDisplayName));
	}
	 
	@Test
	// @Test(dependsOnMethods = {"viewAllNavigationForPrimarySource"})
	public void verifyNextLinkForPrimarySource() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLink(primarySourceDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"verifyNextLinkForPrimarySource"})
	public void verifyNextLinkIsworkingForPrimarySource() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLinkIsworking());
	}

	@Test
	// @Test(dependsOnMethods = {"verifyNextLinkIsworkingForPrimarySource"})
	public void verifyPreviousLinkForPrimarySource() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLink());
	}

	@Test
	// @Test(dependsOnMethods = {"verifyPreviousLinkForPrimarySource"})
	public void verifyPreviousLinkIsworkingForPrimarySource() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLinkIsworking());
	}

	@Test
	// @Test(dependsOnMethods = {"basicSearch"})
	public void statisticsIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkDisplayGroupIsPresent(statisticsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"statisticsIsDisplayed"})
	public void viewAllLinkForStatisticsIsDispalyed() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsPresent(statisticsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewAllLinkForStatisticsIsDispalyed"})
	public void viewAllLinkForStatisticsIsWorking() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllLinkIsWorking(statisticsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"viewAllLinkForStatisticsIsWorking"})
	public void statisticsRelatedLinksIsDisplayed() throws Exception {
		Assert.assertTrue(refDocSearch
				.displayGroupRelatedLinks(statisticsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"statisticsRelatedLinksIsDisplayed"})
	public void viewAllNavigationForStatistics() throws Exception {
		Assert.assertTrue(refDocSearch
				.checkViewAllIsNavigatedCorrectly(statisticsDisplayName));
	}

	//@Test
	 @Test(dependsOnMethods = {"viewAllNavigationForStatistics"})
	public void verifyDocumentDisplayPageResultsStatistics() throws Exception {
	    doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(refDocSearch
				.verifyDocumentDisplayPage(statisticsDisplayName));
	}
	@Test
	// @Test(dependsOnMethods = {"viewAllNavigationForStatistics"})
	public void verifyNextLinkForStatistics() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLink(statisticsDisplayName));
	}

	@Test
	// @Test(dependsOnMethods = {"verifyNextLinkForStatistics"})
	public void verifyNextLinkIsworkingForStatistics() throws Exception {
		Assert.assertTrue(refDocSearch.verifyNextLinkIsworking());
	}

	@Test
	// @Test(dependsOnMethods = {"verifyNextLinkIsworkingForStatistics"})
	public void verifyPreviousLinkForStatistics() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLink());
	}

	@Test
	// @Test(dependsOnMethods = {"verifyPreviousLinkForStatistics"})
	public void verifyPreviousLinkIsworkingForStatistics() throws Exception {
		Assert.assertTrue(refDocSearch.verifyPreviousLinkIsworking());
	}*/
}
