package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewAllReferenceOnSearchResultsPage extends BasePageContainer {

	private String VIEW_ALL = "View All";

	public ViewAllReferenceOnSearchResultsPage() throws Exception {
	}

	public boolean checkViewAllIsNavigatedCorrectly(String displayName)
			throws Exception {
		return new TextLabel(properties.get(displayName + "_viewAll_page"))
				.isPresent() ? true : false;
	}

	public boolean verifyDocumentFullListPage(String displayName)
			throws Exception {
		int totalResultsTextDisplayedInPage = getCount(properties
				.get("totalSearchResults"));
		int countOfRecordsInPage = new TextLabel(properties
				.get("viewAll_searchResults")).getXpathCount();
		return (totalResultsTextDisplayedInPage > 20) ? (countOfRecordsInPage == 20)
				: (countOfRecordsInPage == totalResultsTextDisplayedInPage);
	}

	private int getCount(String locator) throws Exception {

		String displayGroup = new TextLabel(locator).getLabelText().trim();
		return Integer.parseInt(displayGroup.replaceAll(",", ""));
	}

	public boolean verifyNextLink() throws Exception {
		if (getCount(properties.get("totalSearchResults")) > 20) {
			return new TextLabel(properties.get("next")).isPresent();
		}
		return true;
	}

	public boolean verifyNextLinkIsworking() throws Exception {
		if (getCount(properties.get("totalSearchResults")) > 20) {
			new Link(properties.get("next")).click();
			return Integer.parseInt(getCurrentResults()[0].trim()) > 20;
		}
		return true;
	}

	private String[] getCurrentResults() throws Exception {
		return new TextLabel(properties.get("currentResults")).getLabelText()
				.split("-");
	}

	public boolean verifyPreviousLink() throws Exception {
		if (Integer.parseInt(getCurrentResults()[0].trim()) > 20)
			return new TextLabel(properties.get("previous")).isPresent();
		return true;
	}

	public boolean verifyPreviousLinkIsworking() throws Exception {
		if (new TextLabel(properties.get("previous")).isPresent()) {
			int count = Integer.parseInt(getCurrentResults()[0].trim());
			new Link(properties.get("previous")).click();
			return Integer.parseInt(getCurrentResults()[0].trim()) == count - 20;
		}
		return true;
	}

	public boolean verifyLastLink() throws Exception {
		return new TextLabel(properties.get("last")).isPresent();
	}

	public boolean verifyLastLinkIsworking() throws Exception {
		int count = getCount("totalSearchResults");
		new Link(properties.get("last")).click();
		return Integer.parseInt(getCurrentResults()[1].trim().replace(",", "")) == count ? true
				: false;
	}

	public boolean verifyFirstLink() throws Exception {
		return new TextLabel(properties.get("first")).isPresent();
	}

	public boolean verifyFirstLinkIsworking() throws Exception {
		new Link(properties.get("first")).click();
		int totalResultsTextDisplayedInPage = getCount("totalSearchResults");
		String[] currentResults = getCurrentResults();
		int firstResultOnPage = Integer.parseInt(currentResults[0].replace(",",
				"").trim());
		int lastResultOnPage = Integer.parseInt(currentResults[1].replace(",",
				"").trim());

		if (firstResultOnPage != 1) {
			return false;
		}

		if (totalResultsTextDisplayedInPage > 20) {
			return lastResultOnPage == 20;
		} else {
			return lastResultOnPage == totalResultsTextDisplayedInPage;
		}

	}

	public boolean checkViewAllLinkIsPresent(String displayName)
			throws Exception {

		if ((new Link(properties.get(displayName + "_viewAll")).isPresent())
				&& (new Link(properties.get(displayName + "_viewAll"))
						.getLinkText().contains(VIEW_ALL)))
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsWorking(String displayName)
			throws Exception {
		new Link(properties.get(displayName + "_viewAll")).click();

		String newUrl = new Url().getUrl();
		return (newUrl
				.contains("ReferenceFullListPage/ReferenceFullListWindow"));
	}

	public boolean checkDisplayGroupIsPresent(String displayName)
			throws Exception {
		if (new Link(properties.get(displayName + "_label")).isPresent())
			return true;
		return false;
	}

	public boolean displayGroupRelatedLinks(String displayName)
			throws Exception {
		int displayLinksCount = new Link(properties.get(displayName
				+ "_searchResults_xpath")).getLinkCount();
		if (getCount(displayName + "_label") <= 3
				&& displayLinksCount == getCount(properties.get(displayName
						+ "_label")))
			return true;
		else {
			return (displayLinksCount == 3) ? true : false;
		}
	}
}
