package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class IntegrateAcademicJournalsDisplayGroup extends BasePageContainer {

	public IntegrateAcademicJournalsDisplayGroup() throws Exception {
		super();
	}

	public boolean verifyTopThreeDocumentsArePresent() throws Exception {
		if (getResultsCount() >= 3) {
			return (new TextLabel(properties
					.get("journals_searchResults_xpath")).getXpathCount() == 3);
		}
		return new TextLabel(properties.get("journals_searchResults_xpath"))
				.getXpathCount() == getResultsCount();
	}

	private int getResultsCount() throws Exception {
		if (new Link(properties.get("academicjournals_viewAll")).isPresent()) {
			String viewAllText = new Link(properties
					.get("academicjournals_viewAll")).getLinkText();
			return Integer.parseInt(viewAllText.split(" ")[2].trim().replace(
					",", ""));
		}
		return 0;
	}

	public boolean verifyViewAllNotDisplayed() throws Exception {
		if (getResultsCount() <= 3) {
			if (new TextLabel(properties.get("academicjournals_viewAll"))
					.isPresent())
				return false;
			return true;
		}
		return new TextLabel(properties.get("academicjournals_viewAll"))
				.isPresent();
	}

	public boolean verifyViewAllIsDisplayed() throws Exception {
		if (new TextLabel(properties.get("academicjournals_viewAll"))
				.isPresent())
			return true;
		return false;
	}

	public boolean verifyAcademicDisplayGroupIsDisplayed() throws Exception {
		if (new TextLabel(properties.get("journals_searchResults_xpath"))
				.isPresent())
			return true;
		return false;
	}

	public boolean verifyViewAllIsAccessible() throws Exception {
		int totalNoOfResults = getResultsCount();
		new Link(properties.get("academicjournals_viewAll")).click();
		if (new TextLabel(properties.get("academicJournals_viewAll_page"))
				.isPresent()
				&& new TextLabel(properties.get("journalsCount_in_viewAllPage"))
						.getLabelText().replace(",", "").equals(
								String.valueOf(totalNoOfResults))) {
			return true;
		}
		return false;
	}

	public boolean verifyCountIsDisplayed() throws Exception {
		boolean validate = true;
		if (new Link(properties.get("academicjournals_viewAll")).isPresent()) {
			String viewAllText = new Link(properties
					.get("academicjournals_viewAll")).getLinkText();
			return (!viewAllText.split(" ")[2].isEmpty());
		}
		return validate;
	}

	public boolean verifyGroupNameIsDisplayedInCaps() throws Exception {
		return (new TextLabel(properties.get("academicJournals_viewAll_page"))
				.getLabelText().equals("Academic Journals"));
	}

	public boolean verifyContentLinksAreSortedByPublicationDate(
			String searchTerm, String displayGroup, String sortBy)
			throws Exception {
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= new TextLabel(properties
				.get("journals_searchResults_xpath")).getXpathCount(); i++) {
			if (!new TextLabel(properties.get("journals_searchResults_xpath")
					+ "[" + i + "]/h3/a").isPresent()) {
				return false;
			}
			list.add(new Link(properties.get("journals_searchResults_xpath")
					+ "[" + i + "]/h3/a").getLinkText());
		}
		return compareWithDB(list, getDataFromDB(searchTerm, displayGroup,
				sortBy, properties.get("documentTitle")));
	}

	private List<String> getDataFromDB(String searchTerm, String displayGroup,
			String sortBy, String dataToRetrieve) throws IOException {
		List<String> list = OceanDatabaseReadFile.readResultsFromFile(
				properties.get("all"), displayGroup, sortBy, dataToRetrieve,
				searchTerm);
		return list;
	}

	private boolean compareWithDB(List<String> dataInAppl, List<String> dataInDB) {
		for (int i = 0; i < dataInAppl.size(); i++) {
			if (!(dataInAppl.get(i).equals(dataInDB.get(i)))) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyPublicationDetailsPresent() throws Exception {
		for (int i = 1; i <= new TextLabel(properties
				.get("journals_searchResults_xpath")).getXpathCount(); i++) {
			if (!(new TextLabel(properties.get("journals_searchResults_xpath")
					+ "[" + i + "]" + properties.get("journals_pubDate"))
					.isPresent() && new TextLabel(properties
					.get("journals_searchResults_xpath")
					+ "[" + i + "]" + properties.get("journals_pubTitle"))
					.isPresent())) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyContentLinkIsWorking() throws InterruptedException,
			Exception {
		Link journalLink = new Link(properties
				.get("journals_searchResults_xpath")
				+ "/h3/a");
		String journalTitle = journalLink.getLinkText();
		journalLink.click();
		return new TextLabel(properties.get("journals_label_in_details"))
				.getLabelText().contains(journalTitle.substring(0,journalTitle.length()-1));
	}

	public boolean verifyAcademicJournalCanBeMarked() throws Exception {
		if (new Link(properties.get("academicjournals_viewAll")).isPresent()) {
			new Link(properties.get("academicjournals_viewAll")).click();
			Link markLink = new Link(properties.get("mark_me"));
			markLink.clickWithoutWait();
			return markLink.getLinkText().equals("Marked");
		}
		return true;
	}

	public boolean verifyAfterClickingAcademicJournalsLink(String tabName)
			throws Exception {
		navigateTab(tabName);
		return new TextLabel(properties.get("academicJournals_viewAll_page"))
				.getLabelText().equals("Academic Journals");
	}

	public void navigateTab(String tabName) throws Exception {
		new Link(properties.get(tabName + "_tab")).click();
		new Link(properties.get("academicJournals_tab")).click();
	}
}
