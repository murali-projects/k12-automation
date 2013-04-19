package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.SubmitLimitedSearch;

public class SubmitLimitedSearchTest extends BaseWebPageTest{
private SubmitLimitedSearch submitLimitedSearch;
	
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		submitLimitedSearch = new SubmitLimitedSearch();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyBasicSearchResultsAndViewAllDisplayed() throws Exception {
		Assert.assertTrue(submitLimitedSearch.verifyBasicSearchResultsAreDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyBasicSearchResultsAndViewAllDisplayed" })
	public void verifyLimitByDropdownPresent() throws Exception {
		Assert.assertTrue(submitLimitedSearch.verifyLimitByDropdownPresent());
	}
	
	@Test(dependsOnMethods = { "verifyLimitByDropdownPresent" })
	public void verifyResultsAfterApplyingLimitSearch() throws Exception {
		Assert.assertTrue(submitLimitedSearch.verifyResultsAfterApplyingLimitSearch());
	}
	
	@Test(dependsOnMethods = { "verifyResultsAfterApplyingLimitSearch" })
	public void verifyResultsCountAfterLimitBySearch() throws Exception {
		Assert.assertTrue(submitLimitedSearch.verifyResultsCountAfterLimitBySearch());
	}
	
	@Test(dependsOnMethods = { "verifyResultsCountAfterLimitBySearch" })
	public void verifyResultsCountAfterApplyingLimitSearchAgain() throws Exception {
		Assert.assertTrue(submitLimitedSearch.verifyResultsCountAfterApplyingLimitSearchAgain());
	}
	
	@Test(dependsOnMethods = { "verifyResultsCountAfterApplyingLimitSearchAgain" })
	public void verifyResultsAfterApplyingSameLimiterSearch() throws Exception {
		Assert.assertTrue(submitLimitedSearch.verifyResultsAfterApplyingLimitSearch());
	}
	
	@Test(dependsOnMethods = { "verifyResultsCountAfterApplyingLimitSearchAgain" })
	public void verifyAfterApplyingMultipleLimitersSearch() throws Exception {
		Assert.assertTrue(submitLimitedSearch.verifyAfterApplyingMultipleLimitersSearch());
	}
	
	@Test(dependsOnMethods = { "verifyResultsCountAfterApplyingLimitSearchAgain" })
	public void verifyLimitSearchIsNotAppliedForOtherDisplayGroups() throws Exception {
		Assert.assertTrue(submitLimitedSearch.verifyLimitSearchIsNotAppliedForOtherDisplayGroups());
	}
}
