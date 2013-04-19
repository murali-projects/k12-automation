package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewAllAcademicJournalsOnSearchResultsPage extends BasePageContainer {

	private String VIEW_ALL = "View All";

	public ViewAllAcademicJournalsOnSearchResultsPage() throws Exception {
	}

	public boolean checkViewAllIsNavigatedCorrectly(String displayName) throws Exception {
		return new TextLabel(properties.get(displayName+"_viewAll_page")).isPresent() ? true : false;
	}

	public boolean verifyDocumentFullListPage(String displayName) throws Exception {
		int totalResultsTextDisplayedInPage = Integer.parseInt(getCount(properties.get("totalSearchResults")));
		int countOfRecordsInPage = new TextLabel(properties.get("viewAll_searchResults")).getXpathCount();
		return (totalResultsTextDisplayedInPage > 20) ? (countOfRecordsInPage == 20) : (countOfRecordsInPage  == totalResultsTextDisplayedInPage);
	}

	private String getCount(String locator) throws Exception {
		String displayGroup = new TextLabel(locator).getLabelText();
		return displayGroup.replaceAll(",", "");
	}

	public boolean verifyNextLink() throws Exception {
		if (Integer.parseInt(getCount(properties.get("totalSearchResults"))) > 20) {
			return new TextLabel(properties.get("next")).isPresent();
		}
		return true;
	}

	public boolean verifyNextLinkIsworking() throws Exception {
		new Link(properties.get("next")).click();
		return Integer.parseInt(getCurrentResults()[0].trim()) > 20 ? true : false;
	}

	private String[] getCurrentResults() throws Exception {
		return new TextLabel(properties.get("currentResults")).getLabelText().split("-");
	}

	public boolean verifyPreviousLink() throws Exception {
		return new TextLabel(properties.get("previous")).isPresent();
	}

	public boolean verifyPreviousLinkIsworking() throws Exception {
		int count = Integer.parseInt(getCurrentResults()[0]);
		new Link(properties.get("previous")).click();
		return Integer.parseInt(getCurrentResults()[0].trim()) == count - 20 ? true
				: false;
	}

	public boolean checkViewAllLinkIsPresent(String displayName)
			throws Exception {

		if ((new Link(properties.get(displayName+"_viewAll")).isPresent())
				&& (new Link(properties.get(displayName+"_viewAll")).getLinkText().contains(VIEW_ALL)))
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsWorking(String displayName) throws Exception {
		new Link(properties.get(displayName+"_viewAll")).click();
		String newUrl = new Url().getUrl();
		return (newUrl
				.contains("AcademicJournalsFullListPage/AcademicJournalsFullListWindow"));
	}

	public boolean checkDisplayGroupIsPresent(String displayName)
			throws Exception {
		if (new Link(properties.get(displayName+"_label")).isPresent())
			return true;
		return false;
	}

	public boolean displayGroupRelatedLinks(String displayName) throws Exception {
		int displayLinksCount = new Link(properties.get(displayName+"_searchResults_xpath")).getLinkCount();
		if (Integer.parseInt(getCount(properties.get(displayName+"_label"))) <= 3
				&& displayLinksCount == Integer.parseInt(getCount(properties.get(displayName+"_label"))))
			return true;
		else {
			return (displayLinksCount == 3) ? true : false;
		}
	}
}
