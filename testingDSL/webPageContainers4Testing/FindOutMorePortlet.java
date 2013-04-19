package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class FindOutMorePortlet extends BasePageContainer {

	public FindOutMorePortlet() throws Exception {
		super();
	}

	public boolean verifyViewPointRecordsCount() {
		return new TextLabel(properties.get("viewpoints_searchResults_xpath"))
				.getXpathCount() == 2;
	}

	public boolean verifyDetailedViewPageIsDisplayed()
			throws InterruptedException {
		String viewPointValue = new TextLabel(properties
				.get("viewpoints_searchResults_xpath")
				+ "/h3/a").getLabelText();
		new Link(properties.get("viewpoints_searchResults_xpath") + "/h3/a").click();
		return new TextLabel(properties.get("viewpoints_detail"))
				.getLabelText().equals(viewPointValue);
	}

	public boolean verifyFindOutMorePortletIsDisplayed() {
		return new TextLabel(properties.get("find_outMore")).isPresent();
	}

	public boolean verifyAssignmentTextIsDisplayed() {
		return new TextLabel(properties.get("find_outMore")+"/p").isPresent();
	}

	public boolean verifySubjectSearch() throws InterruptedException {
		new Link(properties.get("findportlet_link")).click();
		return new TextLabel(properties.get("subject_search")).isPresent();
	}

	public boolean verifySamePortletIsNotDisplayed()
			throws InterruptedException {
		List<String> searchResults = new ArrayList<String>();
		for (int i = 0; i < new TextLabel(properties.get("viewpoints_label"))
				.getXpathCount(); i++) {
			searchResults.add(new TextLabel(properties.get("viewpoints_label"))
					.getLabelText());
		}
		new Link(properties.get("")).click();
		List<String> searchResultsAfter = new ArrayList<String>();
		for (int i = 0; i < new TextLabel(properties
				.get("viewpoints_detailedPage")).getXpathCount(); i++) {
			searchResultsAfter.add(new TextLabel(properties
					.get("viewpoints_detailedPage")).getLabelText());
		}
		return compareSearhcResults(searchResults, searchResultsAfter);
	}

	private boolean compareSearhcResults(List<String> searchResults,
			List<String> searchResultsAfter) {
		boolean isValidate = true;
		for (int i = 0; i < searchResultsAfter.size(); i++) {
			if (searchResultsAfter.get(i)
					.equalsIgnoreCase(searchResults.get(i))) {
				isValidate = false;
			}
		}
		return isValidate;
	}
}
