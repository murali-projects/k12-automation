package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class SortByDropDownInViewAllPage extends BasePageContainer {

	private String searchTerm;
	private HashMap<String, String> docInfoTypeMap = new HashMap<String, String>();

	public SortByDropDownInViewAllPage(String searchTerm) throws Exception {
		super();
		this.searchTerm = searchTerm;
	}

	public void selectSortElement(String sortElement) throws Exception {
		new DropDownBox(properties.get("sort_by_xpath"))
				.select(sortElement);
	}

	public void selectViewAllForDisplayGroup(String displayGroup)
			throws InterruptedException, Exception {
		new Link(properties.get(displayGroup + "_viewAll")).click();
	}

	public boolean validateDefaultSort(String displayGroup) throws Exception {
		if (displayGroup.contains("reference")
				|| displayGroup.contains("statistics")
				|| displayGroup.contains("websites")
				|| displayGroup.contains("primarysources")) {
			return validateDisplayGroupByDescendingRelevance(displayGroup);
		} else {
			return validateDisplayGroupByDescendingPublicationDate(displayGroup);
		}
	}

	public boolean validateDefaultSortAfterViewAll(String displayGroup)
			throws Exception {
		if (displayGroup.contains("reference")
				|| displayGroup.contains("statistics")
				|| displayGroup.contains("websites")
				|| displayGroup.contains("primarysources")) {
			return validateDisplayGroupByDescendingRelevanceAfterViewAll(displayGroup);
		} else {
			return validateDisplayGroupByDescendingPublicationDateAfterViewAll(displayGroup);
		}
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

	public boolean validateDisplayGroupByDescendingPublicationDateAfterViewAll(
			String displayGroup) throws Exception {
		List<String> publicationDatesDisplayedAfterViewAll = getPublicationDatesAfterViewAll(displayGroup);
		List<String> publicationdatesDisplayedInOcean = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), properties
						.get(displayGroup), properties.get("publicationDate"),
						properties.get("publicationDate"), searchTerm);
		return compareWithDB(publicationDatesDisplayedAfterViewAll,
				publicationdatesDisplayedInOcean);
	}

	private List<String> getPublicationDatesAfterViewAll(String displayGroup)
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

	public boolean validateDisplayGroupByDescendingRelevanceAfterViewAll(
			String displayGroup) throws Exception {
		List<String> relevanceWeightDisplayedAfterViewAll = getRevelanceWeightsAfterViewAll(displayGroup);
		List<String> relevanceWeightDisplayedInOcean = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), properties
						.get(displayGroup), properties.get("relevance"),
						properties.get("relevanceWeight"), searchTerm);
		return compareWithDB(relevanceWeightDisplayedAfterViewAll,
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

	private List<String> getRevelanceWeightsAfterViewAll(String displayGroup)
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

	public boolean verifyResultsAfterSortingByDocumentTitleAfterViewAll(
			String displayGroup) throws Exception {
		List<String> documentTitlesDisplayedAfterViewAll = getDocumentTitlesAfterViewAll(displayGroup);
		List<String> documentTitlesDisplayedInOcean = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), properties
						.get(displayGroup), properties.get("documentTitle"),
						properties.get("documentTitle"), searchTerm);
		return compareWithDB(documentTitlesDisplayedAfterViewAll,
				documentTitlesDisplayedInOcean);
	}

	private List<String> getDocumentTitlesAfterViewAll(String displayGroup)
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

	public boolean ensureSortAfterClickingViewAll(String displayGroup)
			throws Exception {
		return validateDefaultSortAfterViewAll(displayGroup);
	}

	public boolean verifyCountAfterSorting(String sortElement) throws Exception {
		int displayCount = new TextLabel(properties
				.get("viewAll_searchResults")).getXpathCount();
		selectSortElement(sortElement);
		int afterSortDisplayCount = new TextLabel(properties
				.get("viewAll_searchResults")).getXpathCount();
		return displayCount == afterSortDisplayCount;
	}

	public boolean ensureSortDownContent(String[] requiredContent)
			throws Exception {
		boolean isValidate = false;
		int count = new DropDownBox(properties.get("sortByDropDownXPath"))
				.getSelectOptionsCount();
		for (int i = 0; i < count; i++) {
			for (int y = 0; y < requiredContent.length; y++) {
				isValidate = new DropDownBox(properties
						.get("sortByDropDownXPath")).getSelectedOptionValue()
						.equalsIgnoreCase(requiredContent[y]);
				if (isValidate == true)
					break;
			}
		}
		return true;
	}

	public boolean ensureSortDownCountAfterNext(String[] requiredContent)
			throws Exception {
		if (new Link(properties.get("next")).isPresent()) {
			return ensureSortDownContent(requiredContent);
		}
		return ensureSortDownContent(requiredContent);
	}

	public boolean ensureSortDownCountAfterPrevious(String[] requiredContent)
			throws Exception {
		if (new Link(properties.get("previous")).isPresent()) {
			return ensureSortDownContent(requiredContent);
		}
		return ensureSortDownContent(requiredContent);
	}

	public boolean selectMultipleItemsFromDropDown(String[] options)
			throws Exception {
		new DropDownBox(properties.get("sort_by_xpath")).select(options);
		return !(new DropDownBox(properties.get("sort_by_xpath"))
				.getSelectOptionsCount() >= 2);
	}

	public boolean verifyDocInfoTypeAfterSort(String sortElement)
			throws Exception {
		boolean isValidate = true;
		HashMap<String, String> docInfoTypeMap = addDisplayedDocInfoTypeToMap();
		selectSortElement(sortElement);
		HashMap<String, String> docInfoTypeMapAfterSort = addDisplayedDocInfoTypeToMap();
		;
		if (docInfoTypeMap.size() == docInfoTypeMapAfterSort.size()) {
			for (int i = 0; i < docInfoTypeMap.size(); i++) {
				isValidate = docInfoTypeMap.get(
						new TextLabel(properties.get("viewAll_searchResults")
								+ "[" + i + "]/td[2]/h3/a").getLabelText())
						.equals(
								docInfoTypeMapAfterSort.get(new TextLabel(
										properties.get("viewAll_searchResults")
												+ "[" + i + "]/td[2]/h3/a")
										.getLabelText()));
				if (isValidate = false) {
					break;
				}
			}
		}
		return isValidate;
	}

	private HashMap<String, String> addDisplayedDocInfoTypeToMap()
			throws Exception {
		for (int i = 0; i <= new TextLabel("").getXpathCount(); i++) {
			docInfoTypeMap.put(new TextLabel("").getLabelText(), new TextLabel(
					properties.get("docInfoType_xpath") + "[" + i + "]td[3]")
					.getLabelText());
		}
		return docInfoTypeMap;
	}
}
