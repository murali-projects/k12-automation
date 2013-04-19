package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class HitTermHighlighting extends BasePageContainer {

	public HitTermHighlighting() throws Exception {
		super();
	}

	public boolean verifyKeyWordIsNotHighLightedInSearchResults(
			String displayGroup) throws InterruptedException {
		boolean isValidate = true;
			for (int count = 1; count < new TextLabel(properties
					.get(displayGroup + "_searchResults_xpath"))
					.getXpathCount(); count++) {
				if (new TextLabel(properties.get(displayGroup + "_label"))
						.isPresent()) {
					isValidate = !new TextLabel(properties.get(displayGroup
							+ "_searchResults_xpath")
							+ "/span").isPresent()
							&& !new TextLabel(properties.get(displayGroup
									+ "_searchResults_xpath")
									+ "[" + count + "]/p/span").isPresent();
					if (!isValidate) {
						return false;
					}
			}
		}
		return isValidate;
	}

	public boolean verifyKeyWordIsNotHighlightedInViewAll(String displayGroup) throws InterruptedException {
		boolean isValidate = true;
			if (new TextLabel(properties.get(displayGroup) + "_viewAll")
					.isPresent()) {
				new Link(properties.get(displayGroup) + "_viewAll").click();
				for (int count = 1; count < new TextLabel(properties
						.get("viewAll_searchResults")).getXpathCount(); count++) {
					isValidate = !new TextLabel(properties.get(displayGroup
							+ "viewAll_searchResults")
							+ "/tr" + "[" + count + "]/td[2]/h3/a/span")
							.isPresent()
							&& !new TextLabel(properties.get(displayGroup
									+ "_searchResults_xpath")
									+ "/tr" + "[" + count + "]/td[2]/p/span")
									.isPresent();
					if (!isValidate) {
						return false;
				}
			}
		}
		return isValidate;
	}

	public boolean verifyHighlightedTermsInDocument(String displayGroup,String searchTerm)
			throws InterruptedException {
			for (int i = 1; i < new TextLabel(properties
					.get(displayGroup + "_searchResults_xpath"))
					.getXpathCount(); i++) {
				new Link(properties.get(displayGroup
						+ "_searchResults_xpath")
						+ "[" + i + "]/h3/a").click();
				for (int count = 1; count <= new TextLabel(
						"//div[@class='transcript']/p").getXpathCount(); count++) {
					for (int spanCount = 1; spanCount <= new TextLabel(
							"//div[@class='transcript']/p" + "[" + count
									+ "]/span").getXpathCount(); spanCount++) {
						if (!new TextLabel("//div[@class='transcript']/p" + "["
								+ count + "]/span" + "[" + spanCount + "]")
								.getLabelText().toLowerCase().equals(searchTerm)) {
							new Url().goBackToPreviousPage();
							return false;
						}
					}
			}
			new Url().goBackToPreviousPage();
		}
		return true;
	}

	public boolean verifyHighlightedTermsInDocumentFromViewAll(String searchTerm)
			throws InterruptedException {
		if (isViewAllLinkPresent()) {
			for (int i = 1; i < new TextLabel(properties
					.get("viewAll_searchResults")).getXpathCount(); i++) {
				new Link(properties.get("viewAll_searchResults") + "[" + i
						+ "]/td[2]/h3/a").click();
				for (int count = 1; count <= new TextLabel(
						"//div[@class='transcript']/p").getXpathCount(); count++) {
					for (int spanCount = 1; spanCount <= new TextLabel(
							"//div[@class='transcript']/p" + "[" + count
									+ "]/span").getXpathCount(); spanCount++) {
						if (!new TextLabel("//div[@class='transcript']/p" + "["
								+ count + "]/span" + "[" + spanCount + "]")
								.getLabelText().toLowerCase().equals(searchTerm)) {
							new Url().goBackToPreviousPage();
							return false;
						}
					}
				}
				new Url().goBackToPreviousPage();
			}
		}
		return true;
	}

	private boolean isViewAllLinkPresent() throws InterruptedException {
		for (int i = 1; i <= new TextLabel(properties
				.get("displayGroups_searchResultsPage")).getXpathCount(); i++) {
			if (new Link(properties.get("displayGroups_searchResultsPage")
					+ "[" + i + "]/a").isPresent()) {
				new Link(properties.get("displayGroups_searchResultsPage")
						+ "[" + i + "]/a").click();
				return true;
			}
		}
		return false;
	}

	public boolean verifyHighlightedWordInDocumentTileInDetailPage(
			String displayGroup) throws InterruptedException {
		boolean isPresent = false;
			for (int i = 1; i < new TextLabel(properties
					.get(displayGroup + "_searchResults_xpath"))
					.getXpathCount(); i++) {
				new Link(properties.get(displayGroup
						+ "_searchResults_xpath")
						+ "[" + i + "]/h3/a").click();
				isPresent =! new TextLabel(properties.get("viewAll_document")+"/span")
						.isPresent();
				new Url().goBackToPreviousPage();
		}
		return isPresent;
	}
}
