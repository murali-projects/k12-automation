package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.AdvancedSearch;

public class AdvancedSearchTest extends BaseWebPageTest{
	private AdvancedSearch advancedSearch;

	@BeforeTest
	public void setUp() throws Exception {
		advancedSearch = new AdvancedSearch();
	}
	
	@Test
	public void verifyAdvancedSearchPageIsDisplayed() throws Exception {
		Assert.assertTrue(advancedSearch.verifyAdvancedSearchPageIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyAdvancedSearchPageIsDisplayed" })
	public void verifyNewRowIsAdded() throws Exception {
		Assert.assertTrue(advancedSearch.verifyNewRowIsAdded());
	}	
	
	@Test(dependsOnMethods = { "verifyNewRowIsAdded" })
	public void verifyCheckboxesAvailableToLimitResults() throws Exception {
		Assert.assertTrue(advancedSearch.verifyCheckboxesAvailableToLimitResults());
	}
	
	@Test(dependsOnMethods = { "verifyCheckboxesAvailableToLimitResults" })
	public void isUserAbleToSelectDocumentType() throws Exception {
		Assert.assertTrue(advancedSearch.isUserAbleToSelectDocumentType());
	}
	
	@Test(dependsOnMethods = { "isUserAbleToSelectDocumentType" })
	public void isUserAbleToSelectContentLevel() throws Exception {
		Assert.assertTrue(advancedSearch.isUserAbleToSelectContentLevel());
	}
	
	@Test(dependsOnMethods = { "isUserAbleToSelectContentLevel" })
	public void isUserAbleToSelectMultipleContentLevels() throws Exception {
		Assert.assertTrue(advancedSearch.isUserAbleToSelectMultipleContentLevels());
	}
	
	@Test(dependsOnMethods = { "isUserAbleToSelectMultipleContentLevels" })
	public void verifyPublicationDatesDropdownBoxesPresent() throws Exception {
		Assert.assertTrue(advancedSearch.verifyPublicationDatesDropdownBoxesPresent());
	}
	
	@Test(dependsOnMethods = { "verifyPublicationDatesDropdownBoxesPresent" })
	public void isUserAbleToSelectMinMaxLexileScores() throws Exception {
		Assert.assertTrue(advancedSearch.isUserAbleToSelectMinMaxLexileScores());
	}
	
	@Parameters({ "docType" })
	@Test(dependsOnMethods = { "isUserAbleToSelectMinMaxLexileScores" })
	public void verifyResultsAfterSearchingByDoctype(String docType) throws Exception {
		Assert.assertTrue(advancedSearch.verifyResultsAfterSearchingByDoctype(docType));
	}
	
	@Parameters({ "news" })
	@Test(dependsOnMethods = { "verifyResultsAfterSearchingByDoctype" })
	public void verifyResultsAfterSearchingByContentType(String contentType) throws Exception {
		Assert.assertTrue(advancedSearch.verifyResultsAfterSearchingByContentType(new String[]{contentType}));
	}
	
	@Parameters({ "news", "reference" })
	@Test(dependsOnMethods = { "verifyResultsAfterSearchingByContentType" })
	public void verifyResultsAfterSearchingByMultipleContentTypes(String contentType1, String contentType2) throws Exception {
		Assert.assertTrue(advancedSearch.verifyResultsAfterSearchingByContentType(new String[]{contentType1, contentType2}));
	}
	
	@Test(dependsOnMethods = { "verifyPublicationDatesDropdownBoxesPresent" })
	public void verifyPublicationDatesRadioButtonsPresent() throws Exception {
		Assert.assertTrue(advancedSearch.verifyPublicationDatesRadioButtonsPresent());
	}
	
	@Parameters({ "fromDate" })
	@Test(dependsOnMethods = { "verifyPublicationDatesRadioButtonsPresent" })
	public void verifyResultsAfterSelectingAfterRadioButton(String date) throws Exception {
		Assert.assertTrue(advancedSearch.verifyResultsAfterSelectingDates(date, "after"));
	}
	
	@Parameters({ "fromDate" })
	@Test(dependsOnMethods = { "verifyResultsAfterSelectingAfterRadioButton" })
	public void verifyResultsAfterSelectingBeforeRadioButton(String date) throws Exception {
		Assert.assertTrue(advancedSearch.verifyResultsAfterSelectingDates(date, "before"));
	}
	
	@Parameters({ "fromDate" })
	@Test(dependsOnMethods = { "verifyResultsAfterSelectingBeforeRadioButton" })
	public void verifyResultsAfterSelectingOnRadioButton(String date) throws Exception {
		Assert.assertTrue(advancedSearch.verifyResultsAfterSelectingDates(date, "on"));
	}
	
	@Test(dependsOnMethods = { "verifyResultsAfterSelectingOnRadioButton" })
	public void verifyAfterClickingAllDatesRadioButton() throws Exception {
		Assert.assertTrue(advancedSearch.verifyAfterClickingAllDatesRadioButton());
	}
	
	@Test(dependsOnMethods = { "verifyAfterClickingAllDatesRadioButton" })
	public void verifyAfterClickingBetweenRadioButtons() throws Exception {
		Assert.assertTrue(advancedSearch.verifyAfterClickingBetweenRadioButton());
	}
	
	@Test(dependsOnMethods = { "verifyAfterClickingBetweenRadioButtons" })
	public void verifyAfterClickingOtherRadioButtons() throws Exception {
		Assert.assertTrue(advancedSearch.verifyAfterClickingRadioButtons());
	}
	
	@Parameters({ "fromDate", "toDate" })
	@Test(dependsOnMethods = { "verifyAfterClickingOtherRadioButtons" })
	public void verifyResultsAfterSelectingBetweenRadioButton(String fromDate, String toDate) throws Exception {
		Assert.assertTrue(advancedSearch.verifyResultsAfterSelectingBetweenRadioButton(fromDate, toDate));
	}
	
	@Parameters({ "lexileScore" })
	@Test(dependsOnMethods = { "verifyResultsAfterSelectingBetweenRadioButton" })
	public void verifyResultsAfterSearchingByLexileScore(String lexileScore) throws Exception {
		Assert.assertTrue(advancedSearch.verifyResultsAfterSearchingByLexileScore(lexileScore));
	}
	
}
