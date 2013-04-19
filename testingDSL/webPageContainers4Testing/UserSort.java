package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.TextLabel;

public class UserSort extends BasePageContainer {

	private String searchTerm;

	public UserSort(String searchTerm) throws Exception {
		super();
		this.searchTerm = searchTerm;
	}

	public void selectSortElement(String sortElement) throws Exception {
		new DropDownBox(properties.get("sortByDropDownXPath"))
				.select(sortElement);
	}

	public boolean validateDisplayGroupByDescendingPublicationDate(
			String displayGroup) throws Exception {
		List<String> publicationDatesDisplayed = getPublicationDates(displayGroup);
		List<String> publicationdatesDisplayedInOcean = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), properties
						.get(displayGroup), properties.get("publicationDate"),
						properties.get("publicationDate"), searchTerm);
		return compareWithDB(publicationDatesDisplayed,
				publicationdatesDisplayedInOcean);
	}

	private List<String> getPublicationDates(String displayGroup)
			throws Exception {
		List<String> publicationDatesList = new ArrayList<String>();
		int noOfResults = new TextLabel(properties.get(displayGroup
				+ "_results")
				+ "/td[2]").getXpathCount();
		for (int i = 1; i <= noOfResults; i++) {
			publicationDatesList
					.add(new TextLabel(properties
							.get(displayGroup + "_results")
							+ "["
							+ i
							+ "]"
							+ properties.get(displayGroup
									+ "_results_publicationDate"))
							.getLabelText());
		}
		return publicationDatesList;
	}

	private boolean compareWithDB(List<String> displayedContent,
			List<String> displayedContentFromDB) {
		for (int i = 0; i < displayedContent.size(); i++) {
			if (!(displayedContent.get(i).equals(displayedContentFromDB.get(i)))) {
				return false;
			}
		}
		return true;
	}

	public boolean validateDisplayGroupByDescendingRelevance(String displayGroup)
			throws Exception {
		List<String> relevanceWeightDisplayed = getRevelanceWeights(displayGroup);
		List<String> relevanceWeightDisplayedInOcean = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), properties
						.get(displayGroup), properties.get("relevance"),
						properties.get("relevanceWeight"), searchTerm);
		return compareWithDB(relevanceWeightDisplayed,
				relevanceWeightDisplayedInOcean);
	}

	private List<String> getRevelanceWeights(String displayGroup)
			throws Exception {
		List<String> relevanceWeightDisplayedList = new ArrayList<String>();
		int noOfResults = new TextLabel(properties.get(displayGroup
				+ "_results")
				+ "/td[2]").getXpathCount();
		for (int i = 1; i <= noOfResults; i++) {
			relevanceWeightDisplayedList
					.add(new TextLabel(properties
							.get(displayGroup + "_results")
							+ "["
							+ i
							+ "]"
							+ properties.get(displayGroup
									+ "_results_publicationDate"))
							.getAttribute());
		}
		return relevanceWeightDisplayedList;
	}

	public boolean verifyResultsAfterSortingByDocumentTitle(String displayGroup)
			throws Exception {
		List<String> documentTitlesDisplayed = getDocumentTitles(displayGroup);
		List<String> documentTitlesDisplayedInOcean = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), properties
						.get(displayGroup), properties.get("documentTitle"),
						properties.get("documentTitle"), searchTerm);
		return compareWithDB(documentTitlesDisplayed,
				documentTitlesDisplayedInOcean);
	}

	private List<String> getDocumentTitles(String displayGroup)
			throws Exception {
		List<String> documentTitlesDisplayed = new ArrayList<String>();
		int noOfResults = new TextLabel(properties.get(displayGroup
				+ "_results")
				+ "/td[2]").getXpathCount();
		for (int i = 1; i <= noOfResults; i++) {
			documentTitlesDisplayed.add(new TextLabel(properties
					.get(displayGroup + "_results")
					+ "[" + i + "]" + "/h2/a").getLabelText());
		}
		return documentTitlesDisplayed;
	}

}
