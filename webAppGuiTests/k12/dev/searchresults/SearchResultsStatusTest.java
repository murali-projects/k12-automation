package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.GoogleAnalyticsElement;
import webPageElements4Testing.TextLabel;

public class SearchResultsStatusTest extends BaseDevWebPageTest {

	private static final String YOUR_SEARCH_DID_NOT_RETURN_ANY_RESULTS = "No Results matching your search term(s) were found.Please check your spelling or try other search term(s).";
	
	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@Test
	public void searchValidTerm() throws Exception {

		String searchTerm = "war";
		doBasicSearchUsingSearchTerm(searchTerm);
		TextLabel searchStatus = new TextLabel(properties.get("searchStatus_xpath"));
		
		Assert.assertTrue(searchStatus.getLabelText().contains(searchTerm));
		
		GoogleAnalyticsElement gae = new GoogleAnalyticsElement();
		Assert.assertTrue(gae.isPresent());
		Assert.assertEquals(gae.getJavaScriptBlockCount(),3);
	}

	@Test
	public void searchInvalidTerm() throws Exception {


		String searchTerm = "asdadhjkhaksj";
		doBasicSearchUsingSearchTerm(searchTerm);
		TextLabel searchStatus = new TextLabel(properties.get("searchStatus_xpath"));
		TextLabel invalidSearchStatus = new TextLabel(properties.get("invalidTerm_searchStatus_xpath"));

		Assert.assertTrue(searchStatus.getLabelText().contains(searchTerm));
		Assert.assertEquals((invalidSearchStatus.getLabelText()).replace("\n", ""), YOUR_SEARCH_DID_NOT_RETURN_ANY_RESULTS);
	}

	 @Test
	 public void searchStopWord() throws Exception {

		 
		 String searchTerm = "of";
		doBasicSearchUsingSearchTerm(searchTerm);
		TextLabel invalidSearchStatus = new TextLabel(properties.get("invalidTerm_searchStatus_xpath"));
		Assert.assertEquals((invalidSearchStatus.getLabelText()).replace("\n", ""), YOUR_SEARCH_DID_NOT_RETURN_ANY_RESULTS);
	 }

	 @Test
	 public void searchOceanOperators() throws Exception {

 
		String searchTerm = "())";
		doBasicSearchUsingSearchTerm(searchTerm);
		TextLabel searchStatus = new TextLabel(properties.get("searchStatus_xpath"));
		TextLabel invalidSearchStatus = new TextLabel(properties.get("invalidTerm_searchStatus_xpath"));

		Assert.assertTrue(searchStatus.getLabelText().contains("( ) )"));
		Assert.assertEquals((invalidSearchStatus.getLabelText()).replace("\n", ""), YOUR_SEARCH_DID_NOT_RETURN_ANY_RESULTS);
	 }

}
