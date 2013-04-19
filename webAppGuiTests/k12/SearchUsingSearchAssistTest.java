package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.SearchUsingSearchAssist;

public class SearchUsingSearchAssistTest {

	private SearchUsingSearchAssist searchUsingSearchAssist;
	private String searchTerm;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		searchUsingSearchAssist = new SearchUsingSearchAssist();
		this.searchTerm = searchTerm;
	}

	@Test
	public void verifyIsSearchFieldEmpty() throws Exception {
		Assert.assertTrue(searchUsingSearchAssist.isSearchFieldEmpty());
	}

	@Test
	public void verifySuggestionsList() throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.verifySuggestionsList(searchTerm));
	}

	@Parameters( { "searchWordLessThanThreeChar" })
	@Test
	public void verifyListLessThanThreeChar(String searchWordLessThanThreeChar)
			throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.verifyListLessThanThreeChar(searchWordLessThanThreeChar));
	}

	@Parameters( { "searchWordMoreThanThreeChar" })
	@Test
	public void verifyListMoreThanThreeChar(String searchWordMoreThanThreeChar)
			throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.verifyListMoreThanThreeChar(searchWordMoreThanThreeChar));
	}

	@Parameters( { "searchWordMoreThanThreeChar" })
	@Test
	public void verifyFourCharsForSuggestionList(
			String searchWordMoreThanThreeChar) throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.verifyFourCharsForSuggestionList(searchWordMoreThanThreeChar));
	}

	@Parameters( { "displayGroup" })
	@Test
	public void verifyAllBucketsForSearch(String displayGroupList)
			throws Exception {
		Assert.assertTrue(searchUsingSearchAssist.verifyAllBucketsForSearch(
				searchTerm, displayGroupList));
	}

	@Test
	public void selectedSuggestionInSeachField() throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.selectedSuggestionInSeachField(searchTerm));
	}

	@Parameters( { "searchWithStopWord" })
	@Test
	public void verifySuggestionAvailForStopWord(String searchWithStopWord)
			throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.verifySuggestionAvailForStopWord(searchWithStopWord));
	}

	@Parameters( { "displayGroup" })
	@Test
	public void verifySearchSuggestionInViewAllPage(String displayGroup)
			throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.verifySearchSuggestionInViewAllPage(displayGroup, searchTerm));
	}

	@Parameters( { "displayGroup" })
	@Test
	public void verifySearchSuggestionFromViewAllPage(String displayGroup)
			throws Exception {
		Assert
				.assertTrue(searchUsingSearchAssist
						.verifySearchSuggestionFromViewAllPage(displayGroup,
								searchTerm));
	}

	@Parameters( { "displayGroup" })
	@Test
	public void verifyDocumentInDetailedPage(String displayGroup)
			throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.verifyDocumentInDetailedPage(displayGroup));
	}

	@Parameters( { "newSearchWord" })
	@Test
	public void verifySuggestionForNewSearchWord(String newSearchWord)
			throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.verifySuggestionForNewSearchWord(newSearchWord));
	}

	@Parameters( { "searchWord", "sortBy", "displayGroupList" })
	@Test
	public void verifyPortletTitlesInSuggestionList(String searchWord,
			String sortBy, String displayGroupList) throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.verifyPortletTitlesInSuggestionList(searchWord, sortBy,
						displayGroupList));
	}

	@Parameters( { "searchWord" })
	@Test
	public void verifyIsSelectedPortalPageIsDisplayed(String searchWord)
			throws Exception {
		Assert.assertTrue(searchUsingSearchAssist
				.isSelectedPortalPageIsDisplayed(searchWord));
	}
}
