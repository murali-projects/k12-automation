package k12;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.QuotesInSearch;

public class QuotesInSearchTest extends BaseWebPageTest{
	private QuotesInSearch quotesInSearch;
	
	@BeforeTest
	public void setUp() throws Exception{
		quotesInSearch = new QuotesInSearch();
	}
	
	@Test
	public void checkStopWordsNotFilteredInSearchResults()throws Exception {
		doBasicSearchUsingSearchTerm("\"in US\"");
		quotesInSearch.search("\"in US\"");
		Assert.assertTrue(quotesInSearch.checkResultsContainsSearchTerm());
	}
	
	@Test
	public void checkNaughtyWordsNotIncludedInSearchResults()throws Exception {
		doBasicSearchUsingSearchTerm("\"shit war\"");
		quotesInSearch.search("\"shit war\"");
		Assert.assertTrue(quotesInSearch.checkResultsNotHaveNaughtyWords());
	}
	
	@Test
	public void checkResultContainsQuotedText()throws Exception {
		doBasicSearchUsingSearchTerm("\"US President\"");
		quotesInSearch.search("\"US President\"");
		Assert.assertTrue(quotesInSearch.checkResultsContainsQuotedText());
	}
	
	@Test
	public void checkResutsHasOneOfTheQuotedText() throws Exception{
		doBasicSearchUsingSearchTerm("\"President\" in \"US\"");
		quotesInSearch.search("\"President\" in \"US\"");
		Assert.assertTrue(quotesInSearch.checkResultsContainsMultipleQuotedText());
	}
	
	@Test
	public void checkStopAndNaughtWordsAreNotDisplayedForSearchTermHavingUnMathedQuotes() throws Exception{
		doBasicSearchUsingSearchTerm("\"ggg etc test");
		quotesInSearch.search("\"ggg etc test");
		Assert.assertTrue(quotesInSearch.checkStopAndNaughtWordsAreNotDisplayedForSearchTermHavingUnMathedQuotes());
	}
	
	@Test
	public void checkSearchResultPageContainsEchoOfSearchTerm() throws Exception{
		doBasicSearchUsingSearchTerm("\"President\" in \"US\"");
		quotesInSearch.search("\"President\" in \"US\"");
		Assert.assertTrue(quotesInSearch.verifySearchSuccessMsgIsDisplayed());
	}
	
	@Test
	public void checkStopAndNaughtWordsAreNotDisplayedForSearchTermWithSingleQuotes() throws Exception{
		doBasicSearchUsingSearchTerm("\'test etc ggg\'");
		quotesInSearch.search("\'test etc ggg\'");
		Assert.assertTrue(quotesInSearch.checkStopAndNaughtWordsAreNotDisplayedForSearchTermWithSingleQuotes());
	}		

}
