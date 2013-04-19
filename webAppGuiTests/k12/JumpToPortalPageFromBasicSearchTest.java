package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.HitcountAndSortingDocumentRelatedSubjects;
import webPageContainers4Testing.JumpToPortalPageFromBasicSearch;

public class JumpToPortalPageFromBasicSearchTest extends BaseWebPageTest {

	public JumpToPortalPageFromBasicSearch jumptoportalpagefrombasicsearch;
   
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		jumptoportalpagefrombasicsearch = new JumpToPortalPageFromBasicSearch();
	}

	@Parameters( { "searchTerm" })
	@Test
	public void verifyNavigationByEnteringTopic(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(jumptoportalpagefrombasicsearch
				.verifyNavigationByEnteringTopic());

	}

	@Parameters( { "searchTerm2" })
	@Test(dependsOnMethods = "verifyNavigationByEnteringTopic")
	public void verifyNavigationByEnteringNonTopicSearchTerm(String searchTerm2)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm2);
		Assert.assertTrue(jumptoportalpagefrombasicsearch
				.verifyNavigationByEnteringNonTopicSearchTerm());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyNavigationByEnteringNonTopicSearchTerm")
	public void verifyTopicRelatedPage(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(jumptoportalpagefrombasicsearch
				.verifyTopicRelatedPage());

	}

}
