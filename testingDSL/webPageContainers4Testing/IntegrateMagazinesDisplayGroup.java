package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class IntegrateMagazinesDisplayGroup extends BasePageContainer {

	public IntegrateMagazinesDisplayGroup() throws Exception {
		super();
	}

	public boolean verifyTopThreeDocumentsArePresent() throws Exception {
		if (getResultsCount() >= 3) {
			return (new TextLabel(properties
					.get("magazines_searchResults_xpath")).getXpathCount() == 3);
		}
		return new TextLabel(properties.get("magazines_searchResults_xpath"))
				.getXpathCount() == getResultsCount();
	}

	private int getResultsCount() throws Exception {
		if (new Link(properties.get("magazines_viewAll")).isPresent()) {
			String viewAllText = new Link(properties.get("magazines_viewAll"))
					.getLinkText();
			return Integer.parseInt(viewAllText.split(" ")[2].trim().replace(
					",", ""));
		}
		return 0;
	}

	public boolean verifyViewAllNotDisplayed() throws Exception {
		if (getResultsCount() <= 3) {
			if (new TextLabel(properties.get("magazines_viewAll")).isPresent())
				return false;

			return true;
		}
		return new TextLabel(properties.get("magazines_viewAll")).isPresent();
	}

	public boolean verifyViewAllIsDisplayed() throws Exception {
		if (new TextLabel(properties.get("magazines_viewAll")).isPresent())
			return true;

		return false;
	}

	public boolean verifyViewAllIsAccessible() throws Exception {
		int totalNoOfResults = getResultsCount();
		new Link(properties.get("magazines_viewAll")).click();
		if (new TextLabel(properties.get("magazines_viewAll_page")).isPresent()
				&& new TextLabel(properties.get("totalResults")).getLabelText()
						.replace(",", "").equals(
								String.valueOf(totalNoOfResults))) {
			return true;
		}
		return false;
	}

	public boolean verifyCountIsDisplayed() throws Exception {
		boolean validate = true;
		if (new Link(properties.get("magazines_viewAll")).isPresent()) {
			String viewAllText = new Link(properties.get("magazines_viewAll"))
					.getLinkText();
			return (!viewAllText.split(" ")[2].isEmpty());
		}
		return validate;
	}

	public boolean verifyGroupNameIsDisplayedInCaps() throws Exception {
		return (new TextLabel(properties.get("magazines_viewAll_page"))
				.getLabelText().equals("Magazines"));
	}

	public boolean verifyContentLinksAreSortedByPublicationDate(
			String searchTerm, String displayGroup, String sortBy)
			throws Exception {
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= new TextLabel(properties
				.get("magazines_searchResults_xpath")).getXpathCount(); i++) {
			if (!new TextLabel(properties.get("magazines_searchResults_xpath")
					+ "[" + i + "]/h3/a").isPresent()) {
				return false;
			}
			list.add(new Link(properties.get("magazines_searchResults_xpath")
					+ "[" + i + "]/h3/a").getLinkText());
		}
		return compareWithDB(list, getDataFromDB(searchTerm, displayGroup,
				sortBy, properties.get("publicationDate")));
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
				.get("magazines_searchResults_xpath")).getXpathCount(); i++) {
			if (!(new TextLabel(properties.get("magazines_searchResults_xpath")
					+ "[" + i + "]" + properties.get("magazines_pubDate"))
					.isPresent() && new TextLabel(properties
					.get("magazines_searchResults_xpath")
					+ "[" + i + "]" + properties.get("magazines_pubTitle"))
					.isPresent())) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyContentLinkIsWorking() throws InterruptedException,
			Exception {
		Link magazinesLink = new Link(properties
				.get("magazines_searchResults_xpath")
				+ "/h3/a");
		String magazinesTitle = magazinesLink.getLinkText();
		magazinesLink.click();
		return new TextLabel(properties.get("magazines_label_in_details"))
				.getLabelText().equals(magazinesTitle);
	}
	
	public boolean verifyMagazinesCanBeMarked() throws Exception{
		if(new Link(properties.get("magazines_viewAll")).isPresent()){
			new Link(properties.get("magazines_viewAll")).click();
			Link markLink = new Link("//td[@class='publication_logo']/a");
			markLink.clickWithoutWait();
			Thread.sleep(10000);
			return markLink.getLinkText().equals("Marked");
		}
		return true;
	}
}
