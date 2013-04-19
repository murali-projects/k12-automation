package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.HitCount;
import webPageContainers4Testing.PortalPageSuggestionsOnSearchResultsPage;

public class PortalPageSuggestionsOnSearchResultsPageTest extends
		BaseWebPageTest {

	private PortalPageSuggestionsOnSearchResultsPage portalpagesuggestions;

	private String searchTerm;

	@BeforeTest
	public void setUp() throws Exception {
		portalpagesuggestions = new PortalPageSuggestionsOnSearchResultsPage();

	}
	@Parameters( { "searchTerm" })
	@Test
	public void verifyTopicsPortletAvailable(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(portalpagesuggestions.verifyTopicsPortletAvailable());
	}
	@Parameters( { "searchTerm2" })
	@Test(dependsOnMethods = { "verifyTopicsPortletAvailable" })
	public void verifyTopicsPortletForInvalidKeyword(String searchTerm2) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm2);
		Assert.assertTrue(portalpagesuggestions
				.verifyTopicsPortletForInvalidKeyword());
	}
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyTopicsPortletForInvalidKeyword" })
	public void verifyTopicPortalalongWithOtherBuckets(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(portalpagesuggestions
				.verifyTopicPortalalongWithOtherBuckets());
	}
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyTopicPortalalongWithOtherBuckets" })
	public void verifyTopicsAreHyperlinked(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(portalpagesuggestions.verifyTopicsAreHyperlinked());
	}
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyTopicsAreHyperlinked" })
	public void verifySearchResultsStatementIsDisplayed(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(portalpagesuggestions
				.verifySearchResultsStatementIsDisplayed());
	}

}
