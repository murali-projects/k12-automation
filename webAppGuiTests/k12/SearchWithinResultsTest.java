package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.SearchWithinResults;


public class SearchWithinResultsTest extends BaseWebPageTest {
	 public SearchWithinResults searchwithin;
		
	   	@Parameters({"searchTerm"})
		@BeforeTest
		public void setUp(String searchTerm) throws Exception {
	   		doBasicSearchUsingSearchTerm(searchTerm);
	   		searchwithin= new SearchWithinResults();
	    }
	@Parameters({"searchTerm"})
		@Test
		public void verifySearchBoxInSidebarInViewAllPage(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(searchwithin.verifySearchBoxInSidebarInViewAllPage());
					
		}
		@Parameters({"searchTerm"})
		@Test(dependsOnMethods="verifySearchBoxInSidebarInViewAllPage")
		public void canUserEnterDataInSearchBox(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(searchwithin.canUserEnterDataInSearchBox());
		}
		@Parameters({"searchTerm"})
		@Test(dependsOnMethods="canUserEnterDataInSearchBox")
		public void verifyFullTextOnSearchTermsDisplayed(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(searchwithin.verifyFullTextOnSearchTermsDisplayed());
		}
		@Parameters({"searchTerm"})
		@Test(dependsOnMethods="verifyFullTextOnSearchTermsDisplayed")
		public void verifyUserInViewAllAfterResultsSearch(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(searchwithin.verifyUserInViewAllAfterResultsSearch());
		}
		@Parameters({"searchTerm"})
		@Test(dependsOnMethods="verifyUserInViewAllAfterResultsSearch")
		public void verifySearchOptionForOtherTabs(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(searchwithin.verifySearchOptionForOtherTabs());
		}
	
		@Parameters({"searchTerm"})
		@Test(dependsOnMethods="verifySearchOptionForOtherTabs")
		public void verifyHitcountAfterLimitingResults(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(searchwithin.verifyHitcountAfterLimitingResults());
		}
		@Parameters({"searchTerm"})
		@Test(dependsOnMethods="verifyHitcountAfterLimitingResults")
		public void verifySearchLimitedAcrossOtherTabs(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(searchwithin.verifySearchLimitedAcrossOtherTabs());
		}
		@Parameters({"searchTerm"})
		@Test(dependsOnMethods="verifyHitcountAfterLimitingResults")
		public void verifyLimiterModifiedByWithinSearch(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(searchwithin.verifyLimiterModifiedByWithinSearch());
		}

}
