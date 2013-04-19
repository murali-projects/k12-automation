package webPageContainers4Testing;

import java.util.List;
import java.util.Random;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewAllPrimarySourceOnSearchResultsPage extends BasePageContainer {

	private String VIEW_ALL = "View All";

	public ViewAllPrimarySourceOnSearchResultsPage() throws Exception {
	}

	public boolean checkViewAllIsNavigatedCorrectly(String displayName)
			throws Exception {
		return new TextLabel(properties.get(displayName + "_viewAll_page"))
				.isPresent() ? true : false;
	}

	public boolean verifyDocumentFullListPage(String displayName)
			throws Exception {
		int totalResultsTextDisplayedInPage = Integer
				.parseInt(getCount(properties.get("totalSearchResults")));
		int countOfRecordsInPage = new TextLabel(properties
				.get("viewAll_searchResults")).getXpathCount();
		return (totalResultsTextDisplayedInPage > 20) ? (countOfRecordsInPage == 20)
				: (countOfRecordsInPage == totalResultsTextDisplayedInPage);
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
		if (new TextLabel(properties.get("next")).isPresent()) {
			new Link(properties.get("next")).click();
			return Integer.parseInt(getCurrentResults()[0].trim()) > 20 ? true
					: false;
		}
		return true;
	}

	private String[] getCurrentResults() throws Exception {
		return new TextLabel(properties.get("currentResults")).getLabelText()
				.split("-");
	}

	public boolean verifyPreviousLink() throws Exception {
		if (Integer.parseInt(getCurrentResults()[0].trim()) > 20) {
			return new TextLabel(properties.get("previous")).isPresent();
		}
		return true;
	}

	public boolean verifyPreviousLinkIsworking() throws Exception {
		if (new TextLabel(properties.get("previous")).isPresent()) {
		int count = Integer.parseInt(getCurrentResults()[0].trim());
		new Link(properties.get("previous")).click();
		return Integer.parseInt(getCurrentResults()[0].trim()) == count - 20 ? true
				: false;
		}
		return true;
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
				.contains("PrimarySourcesFullListPage/PrimarySourcesFullListWindow"));
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
		if (Integer.parseInt(getCount(properties.get(displayName + "_label"))) <= 3
				&& displayLinksCount == Integer.parseInt(getCount(properties
						.get(displayName + "_label"))))
			return true;
		else {
			return (displayLinksCount == 3) ? true : false;
		}
	}

	public boolean validateWithDatabase(String displayGroup, String searchTerm)
			throws InterruptedException, Exception {
		new Link(properties.get(displayGroup + "_viewAll")).click();
		int totalCount = Integer.parseInt(getCount(properties
				.get("totalSearchResults")));
		List<String> contentFromDB = OceanDatabaseReadFile.readResultsFromFile(
				properties.get("all"), properties.get("K12-PrimarySources"),
				properties.get("documentTitle"), properties
						.get("documentTitle"), searchTerm);
		Random randomGenerator = new Random();
		boolean validate = true;
		new DropDownBox(properties.get("sort_by_xpath"))
				.select("Document Title");
		Thread.sleep(8000);
		for (int i = 0; i < 2; i++) {
			int randomNumber = randomGenerator.nextInt(40);
			if (randomNumber == 0 || totalCount < randomNumber)
				continue;
			Link pageNo = new Link(
					"//div[@id='paging-banner']/ul/li/descendant::a[@class='current']");
			if (totalCount > 20 && randomNumber > 20) {
				if (pageNo.isPresent()
						&& Integer.parseInt(pageNo.getLinkText()) != ((randomNumber / 20) + 1)) {
					new Link(
							"//div[@id='paging-banner']/ul/li/descendant::a[contains(text(),"
									+ ((randomNumber / 20) + 1) + ")]").click();
				}
				validate = new TextLabel(properties
						.get("viewAll_searchResults")
						+ "[" + (randomNumber % 20) + "]/td[2]/h3/a")
						.getLabelText().equals(
								contentFromDB.get(randomNumber - 1));
			} else {
				if (pageNo.isPresent()
						&& Integer.parseInt(pageNo.getLinkText()) != ((randomNumber / 20) + 1)) {
					new Link(
							"//div[@id='paging-banner']/ul/li/descendant::a[contains(text(),"
									+ ((randomNumber / 20) + 1) + ")]").click();
				}
				validate = new TextLabel(properties
						.get("viewAll_searchResults")
						+ "[" + (randomNumber) + "]/td[2]/h3/a").getLabelText()
						.equals(contentFromDB.get(randomNumber - 1));
			}
			if (!validate)
				break;
		}
		return validate;
	}
}
