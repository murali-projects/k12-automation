package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class ViewAllPagination extends BasePageContainer {

	public int totalPages;
	public int totalCount;
	private List<String> databaseValuesList;
	private List<String> displayedValuesList;
	private String searchTerm;

	public ViewAllPagination(String searchTerm) throws Exception {
		super();
		displayedValuesList = new ArrayList<String>();
		this.searchTerm = searchTerm;
	}

	public void selectViewAllForDisplayGroup(String displayGroup)
			throws InterruptedException, Exception {
		new Link(properties.get(displayGroup + "_viewAll")).click();
	}

	public boolean numberOfPagesDisplayed() throws Exception {
		totalCount = Integer.parseInt(new TextLabel(properties
				.get("totalResults")).getLabelText().replace(",", ""));
		if (totalCount > 20) {
			totalPages = (totalCount / 20) + (totalCount % 20);
			for (int i = 1; i <= 5; i++) {
				if (!new TextLabel(properties.get("pages") + "[" + i + "]/a")
						.isPresent())
					return false;
			}
		}
		return true;
	}

	public boolean displayedPageNumbersAreLink() throws Exception {
		if (Integer.parseInt(new TextLabel(properties.get("totalResults"))
				.getLabelText().replace(",", "")) > 20)
			for (int i = 1; i <= 5; i++) {
				if (!new TextLabel(properties.get("pages") + "[" + i + "]/a")
						.isPresent())
					return false;
			}
		return true;
	}

	public boolean nextPreviousLinksVerification()
			throws NumberFormatException, Exception {
		boolean isPresent;
		if (Integer.parseInt(new TextLabel(properties.get("totalResults"))
				.getLabelText().replace(",", "")) > 20) {
			if (Integer
					.parseInt(new TextLabel(properties.get("currentResults"))
							.getLabelText().split("-")[0].trim()) < 20) {
				isPresent = new TextLabel(properties.get("next")).isPresent();
				new Link(properties.get("next")).click();
				isPresent = new TextLabel(properties.get("previous"))
						.isPresent();
				return isPresent;
			} else if (Integer
					.parseInt(new TextLabel(properties.get("currentResults"))
							.getLabelText().split("-")[0].trim()) > 20) {
				return new TextLabel(properties.get("next")).isPresent()
						&& new TextLabel(properties.get("previous"))
								.isPresent();
			}
		}
		return true;
	}

	public boolean verifyPreviousIsNotPresentInFirstPage()
			throws NumberFormatException, Exception {
		if (Integer.parseInt(new TextLabel(properties.get("totalResults"))
				.getLabelText().replace(",", "")) > 20) {
			if (Integer
					.parseInt(new TextLabel(properties.get("currentResults"))
							.getLabelText().split("-")[0].trim()) < 20) {
				return !new TextLabel(properties.get("previous")).isPresent();
			} else {
				new Link(properties.get("first")).click();
				return !new TextLabel(properties.get("previous")).isPresent();
			}
		}
		return true;
	}

	public boolean verifyNextIsNotPresentInLastPage()
			throws NumberFormatException, InterruptedException, Exception {
		if (Integer.parseInt(new TextLabel(properties.get("totalResults"))
				.getLabelText().replace(",", "")) > 20) {
			new Link(properties.get("lastPage")).click();
			return !new TextLabel(properties.get("next")).isPresent();
		}
		return true;
	}

	public boolean correctPageNavigation() throws InterruptedException,
			Exception {
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(4) + 1;
		int presentNumber = randomNumber;
		if (!new TextLabel(properties.get("pages") + "[" + 1 + "]"
				+ "/a[@class='current']").isPresent()) {
			randomNumber = randomNumber + 2;
		}
		if (randomNumber != 1)
			new Link(properties.get("pages") + "[" + randomNumber + "]/a")
					.click();
		if (Integer.parseInt(new TextLabel(properties.get("currentResults"))
				.getLabelText().split("-")[0].trim()) == ((presentNumber - 1) * 20) + 1) {
			return true;
		}
		return false;
	}

	public boolean currentPageVerification() throws InterruptedException,
			Exception {
		int currentPageNumber = (Integer.parseInt(new TextLabel(properties
				.get("currentResults")).getLabelText().split("-")[0].trim()) / 20 + 1);
		if (!new TextLabel(properties.get("currentpage") + "[contains(text(),"
				+ currentPageNumber + ")]").isPresent()) {
			return false;
		}
		return true;
	}

	public boolean validateRandomlyNavigatedContentFromDB(String displayGroup)
			throws InterruptedException, Exception {
		int currentPageNumber = (Integer.parseInt(new TextLabel(properties
				.get("currentResults")).getLabelText().split("-")[0].trim()
				.replace(",", "")) / 20 + 1);
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(4) + 1;
		clickNextOrPrevious(currentPageNumber, randomNumber);
		displayedValuesList = getDisplayedValues();
		databaseValuesList = getValuesFromDB(displayGroup);
		return compareValuesFromDB(databaseValuesList, displayedValuesList,
				randomNumber);
	}

	private List<String> getValuesFromDB(String displayGroup)
			throws IOException {
		List<String> databaseValuesList = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), "K12-Reference",
						properties.get("relevance"), properties
								.get("documentTitle"), searchTerm);
		return databaseValuesList;
	}

	private boolean compareValuesFromDB(List<String> databaseValues,
			List<String> displayedValues, int randomlyGeneratedValue) {
		return databaseValues.get(
				(randomlyGeneratedValue - 1) * 20 + randomlyGeneratedValue)
				.equals(displayedValues.get((randomlyGeneratedValue)));
	}

	private List<String> getDisplayedValues() throws Exception {
		for (int i = 1; i <= new TextLabel(properties
				.get("viewAll_searchResults")).getXpathCount(); i++) {
			displayedValuesList.add(new TextLabel(properties
					.get("viewAll_searchResults")
					+ "[" + i + "]/td[2]/h3/a").getLabelText());
		}
		return displayedValuesList;
	}

	private void clickNextOrPrevious(int currentPageNumber, int randomNumber)
			throws InterruptedException, Exception {
		if (currentPageNumber < randomNumber) {
			for (int i = 0; i < randomNumber - currentPageNumber; i++) {
				new Link(properties.get("next")).click();
			}
		} else {
			for (int i = 0; i < currentPageNumber - randomNumber; i++) {
				new Link(properties.get("previous")).click();
			}
		}

	}
}
