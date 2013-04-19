package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.TextLabel;

public class DefaultSortForEachDisplayGroup extends BasePageContainer {

	private String searchTerm;

	public DefaultSortForEachDisplayGroup(String searchTerm) throws Exception {
		super();
		this.searchTerm = searchTerm;
	}

	public boolean validateDefaultSortByRelevance(String displayGroup)
			throws Exception {
		List<String> relevanceWeightDisplayed = getRevelanceWeights(displayGroup);
		List<String> relevanceWeightDisplayedInOcean = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), properties
						.get(displayGroup), properties.get("relevance"),
						properties.get("relevance_wts"), searchTerm);
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

	private boolean compareWithDB(List<String> displayedContent,
			List<String> displayedContentFromDB) {
		for (int i = 0; i < displayedContent.size(); i++) {
			if (!(displayedContent.get(i).equals(displayedContentFromDB.get(i)))) {
				return false;
			}
		}
		return true;
	}

	public boolean validateDefaultSortByPublicationDate(String displayGroup)
			throws Exception {
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
}
