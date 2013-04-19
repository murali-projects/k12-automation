package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;
@Deprecated
public class ReferenceDocumentsOnSearchResultsPage extends BasePageContainer {


	private String currentUrl;
	private String VIEW_ALL = "View All";

	public ReferenceDocumentsOnSearchResultsPage() throws Exception {
		super();
		currentUrl = new Url().getUrl();
	}

	public boolean checkViewAllIsNavigatedCorrectly(String displayName) throws Exception {
		return new TextLabel(properties.get(displayName+"_viewAll_page")).isPresent() ? true : false;
	}

	public boolean verifyDocumentDisplayPage(String displayName) throws Exception {
		int count = Integer.parseInt(getCount(properties.get(displayName+"_label")));
		new Link(properties.get(displayName+"_viewAll")).click();
		Integer count1 = new TextLabel(properties.get(displayName+"_viewAll_page")).getXpathCount();
		return (count > 20) ? count1.toString().equals(20) : count1
				.equals(count);
	}

	private String getCount(String locator) throws Exception {
		Thread.sleep(3000);
		String displayGroup = new TextLabel(locator).getLabelText();
		String displayGroupCountValue = getSubString(displayGroup, "(", ")");
		return displayGroupCountValue;
	}

	private String getSubString(String source, String startIndex,
			String endIndex) {
		return source.substring(source.indexOf(startIndex) + 1, source
				.indexOf(endIndex));
	}

	public boolean verifyNextLink() throws Exception {
		if (Integer.parseInt(new TextLabel(properties.get("totalResults")).getLabelText()) > 20) {
			return new TextLabel(properties.get("next")).isPresent();
		}
		return true;
	}

	public boolean verifyNextLinkIsworking() throws Exception {
		new Link(properties.get("next")).click();
		return Integer.parseInt(getCurrentResults()[0]) > 20 ? true : false;
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
		return Integer.parseInt(getCurrentResults()[0]) == count - 20 ? true
				: false;
	}

	public boolean checkViewAllLinkIsPresent(String displayName)
			throws Exception {

		if ((new Link(properties.get(displayName+"_viewAll")).isPresent())
				&& (new Link(properties.get(displayName+"_viewAll")).getLinkText().contains(VIEW_ALL)))
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsWorking(String displayName)
			throws Exception {
		new Link(properties.get(displayName+"_viewAll")).click();
		// return true if both the URL's contains "reference.jsp"
		return (currentUrl.contains("FullListPage") );
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
