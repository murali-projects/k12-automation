package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class SearchUsingSearchAssist extends BasePageContainer {

	private List<String> displayedPortletTitles;
	private List<String> portletTitlesInDB;

	public SearchUsingSearchAssist() throws Exception {
		super();
		displayedPortletTitles = new ArrayList<String>();
	}

	public boolean isSearchFieldEmpty() throws Exception {
		return new TextBox(properties.get("searchBox")).getValue().equals(
				"Search K12");
	}

	public boolean verifySuggestionsList(String searchWord) throws Exception {
		new TextBox(properties.get("searchBox")).typeSearchText(searchWord);
		return new TextLabel("suggestion_list").isPresent();
	}

	public boolean verifyListLessThanThreeChar(
			String searchWordLessThanThreeChar) throws Exception {
		new TextBox(properties.get("searchBox"))
				.typeSearchText(searchWordLessThanThreeChar);
		return !new TextLabel("suggestion_list").isPresent();
	}

	public boolean verifyListMoreThanThreeChar(
			String searchWordMoreThanThreeChar) throws Exception {
		new TextBox(properties.get("searchBox"))
				.typeSearchText(searchWordMoreThanThreeChar);
		return new TextLabel("suggestion_list").isPresent();
	}

	public boolean verifyFourCharsForSuggestionList(
			String searchWordWithFourChar) throws Exception {
		new TextBox(properties.get("searchBox"))
				.typeSearchText(searchWordWithFourChar);
		new Link(properties.get("suggestion_list")).click();
		return new TextBox(properties.get("searchBox")).getValue().substring(0,
				4).contains(searchWordWithFourChar);
	}

	public boolean verifyAllBucketsForSearch(String searchWord,
			String displayGroup) throws InterruptedException {
		boolean isValidate = false;
		String[] displayGroups = displayGroup.split(",");
		for (int i = 0; i < displayGroups.length; i++) {
			if (new TextLabel(properties.get(displayGroups[i] + "_label"))
					.isPresent()) {
				for (int y = 1; y < new TextLabel(properties
						.get(displayGroups[i] + "_searchResults_xpath"))
						.getXpathCount(); y++) {
					new Link(properties.get(displayGroups[i]
							+ "_searchResults_xpath")
							+ "[" + y + "]").click();
					isValidate = new TextLabel(properties.get("detail_page"))
							.getLabelText().contains(searchWord);
					new Url().goBackToPreviousPage();
				}
				return isValidate;
			}
		}
		return true;
	}

	public boolean selectedSuggestionInSeachField(String searchTerm)
			throws Exception {
		boolean isValidate = false;
		new TextBox(properties.get("searchBox")).typeSearchText(searchTerm);
		isValidate = new TextBox(properties.get("searchBox")).getValue()
				.equals(searchTerm)
				&& !new TextLabel(properties.get("message")).isPresent();
		new Link(properties.get("find")).click();
		isValidate = new TextLabel(properties.get("message")).getLabelText()
				.equals("search results: KEYWORD=" + searchTerm);
		return isValidate;
	}

	public boolean verifySuggestionAvailForStopWord(String searchWithStopWord)
			throws Exception {
		new TextBox(properties.get("searchBox"))
				.typeSearchText(searchWithStopWord);
		return new TextLabel("suggestion_list").isPresent();
	}

	public boolean verifySearchSuggestionInViewAllPage(String displayGroup,
			String searchTermInViewAll) throws Exception {
		if (new Link(properties.get(displayGroup + "_viewAll")).isPresent()) {
			new Link(properties.get(displayGroup + "_viewAll")).click();
			new TextBox(properties.get("searchBox"))
					.typeSearchText(searchTermInViewAll);
			return new TextLabel("suggestion_list").isPresent();
		}
		return true;
	}

	public boolean verifySearchSuggestionFromViewAllPage(String displayGroup,
			String searchTermFromViewAll) throws Exception {
		if (new Link(properties.get(displayGroup + "viewAll_searchResults")
				+ "[1]/td[2]/h3").isPresent()) {
			new Link(properties.get(displayGroup + "viewAll_searchResults")
					+ "[1]/td[2]/h3").click();
			new TextBox(properties.get("searchBox"))
					.typeSearchText(searchTermFromViewAll);
			return new TextLabel("suggestion_list").isPresent();
		}
		return true;
	}

	public boolean verifyDocumentInDetailedPage(String displayGroup)
			throws InterruptedException {
		new Link(properties.get(displayGroup + "_searchResults_xpath")).click();
		return new TextLabel(properties.get("detail_page")).isPresent();
	}

	public boolean verifySuggestionForNewSearchWord(String newSearchWord)
			throws Exception {
		new TextBox(properties.get("searchBox")).typeSearchText(newSearchWord);
		return new TextLabel("suggestion_list").isPresent();
	}

	public boolean verifyPortletTitlesInSuggestionList(String searchWord,
			String sortBy, String displayGroup) throws Exception {
		new TextBox(properties.get("searchBox")).typeSearchText(searchWord);
		for (int i = 1; i < new TextLabel(properties.get("suggestion_list"))
				.getXpathCount(); i++) {
			displayedPortletTitles.add(new TextLabel(properties.get(""))
					.getLabelText());
		}
		return comparePortletValuesWithDB(displayedPortletTitles,
				getPortletTilesFromDataBase(sortBy, searchWord, displayGroup));
	}

	private List<String> getPortletTilesFromDataBase(String sortBy,
			String searchTerm, String displayGroup) throws IOException {
		String[] displayGroups = displayGroup.split(",");
		for (int i = 0; i < displayGroups.length; i++) {
			portletTitlesInDB = OceanDatabaseReadFile.readResultsCountFromFile(
					properties.get("all"), properties.get(displayGroups[i]),
					properties.get(sortBy), searchTerm);
		}
		return portletTitlesInDB;
	}

	private boolean comparePortletValuesWithDB(
			List<String> displayedPortletTitles, List<String> portletTitlesInDB) {
		boolean isValidate = false;
		for (int i = 0; i < portletTitlesInDB.size(); i++) {
			for (int y = 0; y < displayedPortletTitles.size(); y++) {
				isValidate = portletTitlesInDB.get(i).equalsIgnoreCase(
						displayedPortletTitles.get(y));
				if (isValidate == true)
					break;
			}
		}
		return isValidate;
	}

	public boolean isSelectedPortalPageIsDisplayed(String searchWord)
			throws Exception {
		new TextBox(properties.get("searchBox")).typeSearchText(searchWord);
		String displayedTilte = new TextLabel(properties.get("suggestion_list"))
				.getLabelText();
		return new TextLabel(properties.get("detail_page"))
				.equals(displayedTilte);
	}
}
