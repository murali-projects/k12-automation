package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.DisplayQueryFormatting;

public class DisplayQueryFormattingTest extends BaseWebPageTest{
	private DisplayQueryFormatting queryFormatting;


	@Parameters( { "searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		queryFormatting = new DisplayQueryFormatting(searchTerm);
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyMsgForKeywordSearch() throws Exception {
		Assert.assertTrue(queryFormatting.verifyMsgForKeywordSearch());
	}

	@Parameters({"humour","fromMonth","fromDate","fromYear","toMonth","toDate","toYear"})
	@Test(dependsOnMethods = { "verifyMsgForKeywordSearch" })
	public void verifyMsgWithDateCombinationSearch(String keyword, String fromMonth, String fromDate, String fromYear, String toMonth, String toDate, String toYear) throws Exception {
		Assert.assertTrue(queryFormatting.verifyMsgWithDateCombinationSearch(keyword, fromMonth, fromDate, fromYear, toMonth, toDate, toYear));
	}
	
	@Parameters({"humour","fromMonth","fromDate","fromYear","toMonth","toDate","toYear"})
	@Test(dependsOnMethods = { "verifyMsgWithDateCombinationSearch" })
	public void checkDateFormatInMsg(String fromMonth, String fromDate, String fromYear, String toMonth, String toDate, String toYear) throws Exception {
		Assert.assertTrue(queryFormatting.checkDateFormatInMsg(fromMonth, fromDate, fromYear, toMonth, toDate, toYear));
	}
	
	@Parameters({"army","docType","basic","intermediate","lexileScore"})
	@Test(dependsOnMethods = { "checkDateFormatInMsg" })
	public void verifyMsgWithAllCombinationsOfInput(String keyword, String subject, String docType, String basic, String intermediate, String lexileScore) throws Exception {
		Assert.assertTrue(queryFormatting.verifyMsgWithAllCombinationsOfInput(keyword, subject, docType, new String[]{basic, intermediate}, lexileScore));
	}
	
	@Parameters({"searchTermWithStopword"})
	@Test(dependsOnMethods = { "verifyMsgWithAllCombinationsOfInput" })
	public void verifyMsgWithStopWordSearch(String searchTermWithStopword) throws Exception {
		Assert.assertTrue(queryFormatting.verifyMsgWithStopWordSearch(searchTermWithStopword));
	}
	
	@Parameters({"searchTermWithNaughtyword"})
	@Test(dependsOnMethods = { "verifyMsgWithStopWordSearch" })
	public void verifySearchMsgWithNaughtyWords(String searchTermWithNaughtyword) throws Exception {
		Assert.assertTrue(queryFormatting.verifySearchMsgWithStopAndNaughtyWords(searchTermWithNaughtyword));
	}
	
	@Parameters({"searchTermWithStopAndNaughtywords"})
	@Test(dependsOnMethods = { "verifySearchMsgWithNaughtyWords" })
	public void verifySearchMsgWithStopAndNaughtyWords(String verifySearchMsgWithNaughtyWords) throws Exception {
		Assert.assertTrue(queryFormatting.verifySearchMsgWithStopAndNaughtyWords(verifySearchMsgWithNaughtyWords));
	}
	
	@Parameters({"searchTermForPortalPage"})
	@Test(dependsOnMethods = { "verifySearchMsgWithStopAndNaughtyWords" })
	public void verifyMsgDisplayedAfterSearchFromPortalViewAll(String searchTermForPortalPage) throws Exception {
		doBasicSearchUsingSearchTerm(searchTermForPortalPage);
		Assert.assertTrue(queryFormatting.verifyMsgDisplayedAfterSearchFromPortalViewAll(searchTermForPortalPage));
	}
	
	@Parameters({"searchTermForPortalPage"})
	@Test(dependsOnMethods = { "verifyMsgDisplayedAfterSearchFromPortalViewAll" })
	public void verifyMsgExistsAfterPortalPageNavigation(String searchTermForPortalPage) throws Exception {
		doBasicSearchUsingSearchTerm(searchTermForPortalPage);
		Assert.assertTrue(queryFormatting.verifyMsgExistsAfterPortalPageNavigation());
	}
	
	@Parameters({"humour"})
	@Test(dependsOnMethods = { "verifyMsgDisplayedAfterSearchFromPortalViewAll" })
	public void verifyMsgExistsInViewAllNavigation(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(queryFormatting.verifyMsgExistsInViewAllNavigation());
	}
	
	@Test(dependsOnMethods = { "verifyMsgExistsInViewAllNavigation" })
	public void verifyMsgExistsAfterDetailedPageNavigation() throws Exception {
		Assert.assertTrue(queryFormatting.verifyMsgExistsInViewAllNavigation());
	}
	
	@Test(dependsOnMethods = { "verifyMsgExistsAfterDetailedPageNavigation" })
	public void verifyMsgChangesAfterSearchingByRelatedSubjects() throws Exception {
		Assert.assertTrue(queryFormatting.verifyMsgChangesAfterSearchingByRelatedSubjects());
	}
	
	@Test(dependsOnMethods = { "verifyMsgChangesAfterSearchingByRelatedSubjects" })
	public void verifyMsgAfterLimitBySearch() throws Exception {
		Assert.assertTrue(queryFormatting.verifyMsgAfterLimitBySearch());
	}
	
}
