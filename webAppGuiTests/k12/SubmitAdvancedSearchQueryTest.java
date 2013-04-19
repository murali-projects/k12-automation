package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.SubmitAdvancedSearchQuery;

public class SubmitAdvancedSearchQueryTest extends BaseWebPageTest{
	private SubmitAdvancedSearchQuery advancedSearch;
	private String orOperator;
	private String andOperator;
	private String notOperator;
	private String keywordIndex;
	private String subjectIndex;
	private String publicationTitleIndex;
	private String documentTitleIndex;
	
	@Parameters({"orOperator", "andOperator", "notOperator" ,"keywordIndex", "subjectIndex", "publicationTitleIndex", "documentTitleIndex"})
	@BeforeTest
	public void setup(String orOperator, String andOperator,
			String notOperator, String keywordIndex, String subjectIndex,
			String publicationTitleIndex, String documentTitleIndex) throws Exception {
		advancedSearch = new SubmitAdvancedSearchQuery();
		this.orOperator = orOperator;
		this.andOperator = andOperator;
		this.notOperator = notOperator;
		this.keywordIndex = keywordIndex;
		this.subjectIndex = subjectIndex;
		this.publicationTitleIndex = publicationTitleIndex;
		this.documentTitleIndex = documentTitleIndex;
	}
	
	@Test
	public void verifyDefaultIndexIsKeyword() throws InterruptedException{
		Assert.assertTrue(advancedSearch.verifyDefaultIndexIsKeyword());
	}
	
	@Test(dependsOnMethods = "verifyDefaultIndexIsKeyword")
	public void verifyUserCanSearchMultipleTermsUsingConnectors() throws InterruptedException{
		Assert.assertTrue(advancedSearch.verifyUserCanSearchMultipleTermsUsingConnectors());
	}
	
	@Parameters({"war", "forest"})
	@Test(dependsOnMethods = "verifyUserCanSearchMultipleTermsUsingConnectors")
	public void verifyResultsWithCombinationOfAnd(String searchTermForKeyword, String searchTermForSubject) throws Exception{
		Assert.assertTrue(advancedSearch.verifyResultsWithCombinationOfBooleanOperators(keywordIndex,
						searchTermForKeyword, andOperator, subjectIndex,
						searchTermForSubject, "", "", ""));
	}
	
	@Parameters({"war", "forest"})
	@Test(dependsOnMethods = "verifyResultsWithCombinationOfAnd")
	public void verifyResultsWithCombinationOfOr(String searchTermForKeyword, String searchTermForSubject) throws Exception{
		Assert.assertTrue(advancedSearch.verifyResultsWithCombinationOfBooleanOperators(keywordIndex,
						searchTermForKeyword, orOperator, subjectIndex,
						searchTermForSubject, "", "", ""));
	}
	
	@Parameters({"war", "LosAngelesTimes"})
	@Test(dependsOnMethods = "verifyResultsWithCombinationOfOr")
	public void verifyResultsWithCombinationOfNot(String searchTermForSubject, String searchTermForPubTitle) throws Exception{
		Assert.assertTrue(advancedSearch.verifyResultsWithCombinationOfBooleanOperators(documentTitleIndex,
				searchTermForSubject, notOperator, subjectIndex,
						searchTermForSubject, notOperator, publicationTitleIndex, searchTermForPubTitle));
	}
	
	@Parameters({"worldWar", "war", "LosAngelesTimes"})
	@Test(dependsOnMethods = "verifyResultsWithCombinationOfNot")
	public void verifyResultsWithDifferentCombinationsOfIndexes(String searchTermForDocumentTitle, String searchTermForSubject, String searchTermForPubTitle) throws Exception{
		Assert.assertTrue(advancedSearch.verifyResultsWithCombinationOfBooleanOperators(documentTitleIndex,
				searchTermForDocumentTitle, orOperator, subjectIndex,
						searchTermForSubject, notOperator, publicationTitleIndex, searchTermForPubTitle));
	}
	
	@Test(dependsOnMethods = "verifyResultsWithDifferentCombinationsOfIndexes")
	public void verifyIndexesInDropdown() throws InterruptedException{
		Assert.assertTrue(advancedSearch.verifyIndexesInDropdown());
	}
	
	@Test(dependsOnMethods = "verifyIndexesInDropdown")
	public void verifyTwoSearchButtonsPresent() throws InterruptedException{
		Assert.assertTrue(advancedSearch.verifyTwoSearchButtonsPresent());
	}
	
	@Test(dependsOnMethods = "verifyTwoSearchButtonsPresent")
	public void verifyNewRowIsAdded() throws InterruptedException{
		Assert.assertTrue(advancedSearch.verifyNewRowIsAdded());
	}
	
	@Parameters({"N4SearchTerm"})
	@Test(dependsOnMethods = "verifyNewRowIsAdded")
	public void verifyProximityOperatorSearch(String N4SearchTerm) throws Exception{
		Assert.assertTrue(advancedSearch.verifyProximityOperatorSearch(N4SearchTerm));
	}

	@Test(dependsOnMethods = "verifyProximityOperatorSearch")
	public void verifyBreadCrumb() throws Exception{
		Assert.assertTrue(advancedSearch.verifyBreadCrumb());
	}
	
	@Test(dependsOnMethods = "verifyBreadCrumb")
	public void checkMsgIsDisplayedForMaxRows() throws Exception{
		Assert.assertTrue(advancedSearch.checkMsgIsDisplayedForMaxRows());
	}
	
	@Test(dependsOnMethods = "checkMsgIsDisplayedForMaxRows")
	public void verifySearchResultsPageDisplayed() throws Exception{
		Assert.assertTrue(advancedSearch.verifySearchResultsPageDisplayed());
	}

	@Parameters({"fullTextDocuments", "Article", "fromDate", "toDate", "news", "searchQuery", "limitQuery"})
	@Test(dependsOnMethods = "verifySearchResultsPageDisplayed")
	public void verifyResultsWithMultipleCombinations(String limitTo, String documentType, String fromDate, String toDate, String displayGroup, String searchQuery, String limitQuery) throws Exception{
		Assert.assertTrue(advancedSearch.verifyResultsWithMultipleCombinations(limitTo, new String[]{documentType}, "", "", displayGroup, searchQuery, limitQuery));
	}
	
	@Parameters({"fromDate", "toDate"})
	@Test(dependsOnMethods = "verifySearchResultsPageDisplayed")
	public void verifyDatesInResults(String fromDate, String toDate) throws Exception{
		Assert.assertTrue(advancedSearch.verifyDatesInResults(fromDate, toDate));
	}
	
	@Test(dependsOnMethods = "verifyDatesInResults")
	public void checkResultsDisplayedForSameToAndFromDate() throws Exception{
		Assert.assertTrue(advancedSearch.checkResultsDisplayedForSameToAndFromDate());
	}
	
	@Parameters({"docType"})
	@Test(dependsOnMethods = "checkResultsDisplayedForSameToAndFromDate")
	public void verifyDocTypesInResults(String docType) throws Exception{
		Assert.assertTrue(advancedSearch.verifyDocTypesInResults(docType));
	}
	
	@Parameters({"news"})
	@Test(dependsOnMethods = "verifyDocTypesInResults")
	public void checkOnlyOneDisplayGroupDisplayed(String news) throws Exception{
		Assert.assertTrue(advancedSearch.checkOnlyOneDisplayGroupDisplayed(news));
	}
	
	@Parameters({"audio"})
	@Test(dependsOnMethods = "checkOnlyOneDisplayGroupDisplayed")
	public void verifyResultsForContentGroup(String audio) throws Exception{
		Assert.assertTrue(advancedSearch.verifyResultsForContentGroup(new String[]{audio}));
	}
	
	@Parameters({"audio", "images"})
	@Test(dependsOnMethods = "verifyResultsForContentGroup")
	public void verifyResultsForMultipleContentGroups(String audio, String images) throws Exception{
		Assert.assertTrue(advancedSearch.verifyResultsForContentGroup(new String[]{audio, images}));
	}
	
	@Test(dependsOnMethods = "verifyResultsForMultipleContentGroups")
	public void verifyWarningMsgDisplayedForLexileScore() throws Exception{
		Assert.assertTrue(advancedSearch.verifyWarningMsgDisplayedForLexileScore());
	}
	
	@Test(dependsOnMethods = "verifyWarningMsgDisplayedForLexileScore")
	public void verifyWarningMsgDisplayedForNonIntegerLexileScore() throws Exception{
		Assert.assertTrue(advancedSearch.verifyWarningMsgDisplayedForNonIntegerLexileScore());
	}

}

