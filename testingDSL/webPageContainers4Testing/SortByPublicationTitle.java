package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.PropertyReader;
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class SortByPublicationTitle extends BasePageContainer {

	
	private PropertyReader propertyReader;
	private String searchTerm;
	private int initialCount;
	private int intialviewAllCount;
	private String displayGroupName;
	private List<String> publicationTitles;
	private List<String> publicationTitlesDisplayed;
	private List<String> displayedContent = new ArrayList<String>();
	private List<String> displayedContentFromDB = new ArrayList<String>();
	
	public SortByPublicationTitle(String searchTerm, String displayGroupName)
			throws Exception {
		super();
		this.searchTerm = searchTerm;
		this.displayGroupName=displayGroupName;
		propertyReader = new PropertyReader(
				"properties\\default.search.properties");
		publicationTitles = new ArrayList<String>();
		publicationTitlesDisplayed = new ArrayList<String>();
		
	}

	public void getIntialValues() throws Exception
	{
		initialCount = new TextLabel(propertyReader.get(displayGroupName
				.toLowerCase()
				+ "_content")).getXpathCount();
		intialviewAllCount = Integer.parseInt(new TextLabel(propertyReader.get(displayGroupName
				.toLowerCase()
				+ "_viewAll")).getLabelText()
				.split(" ")[2]);
	}
	
	public boolean ensureDefaultSort(String displayGroupName,
			String dataBaseElement) throws InterruptedException, Exception {
		displayedContent = getDisplayedContent(displayGroupName);
		List<String> displayedContentFromDB = getDataFromDB(displayGroupName,
				dataBaseElement);
		return compareWithDB(displayedContent, displayedContentFromDB);
	}

	private List<String> getDataFromDB(String displayGroupName,
			String dataBaseElement) throws IOException {
		displayedContentFromDB = OceanDatabaseReadFile
				.readResultsFromFile(propertyReader.get("all"),
						displayGroupName, propertyReader.get("relevance"),
						propertyReader.get(dataBaseElement), searchTerm);
		return displayedContentFromDB;
	}

	private List<String> getDisplayedContent(String displayGroupName)
			throws Exception {
		for (int i = 1; i <= new TextLabel(propertyReader.get(displayGroupName
				.toLowerCase()
				+ "_content")).getXpathCount(); i++) {
			displayedContent.add(new TextLabel(propertyReader.get(displayGroupName.toLowerCase()+"_link")
					+ "[" + i + "]" + propertyReader.get(displayGroupName.toLowerCase()+"_link_value"))
					.getLabelText());
		}
		return displayedContent;
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

	private boolean selectSortByAsPublicationTitle() throws Exception {
		boolean isSelected = false;

		if (isSelected = new DropDownBox(propertyReader.get("sort_by_xpath"))
				.isPresent()) {
			new DropDownBox(propertyReader.get("sort_by_xpath"))
					.select(propertyReader.get("sort_by_publicationTitle"));
			new Link(propertyReader.get("go")).click();
		}
		return isSelected;
	}

	public boolean validateSearchResultsByPublicationTitleWithDB(
			String displayGroupName, String dataBaseElment) throws Exception {
		selectSortByAsPublicationTitle();
		publicationTitles = getDataFromDB(displayGroupName, dataBaseElment);
		publicationTitlesDisplayed = getPublicationTitlesDisplayed();
		for (int i = 0; i < displayedContent.size(); i++) {
			if (!(publicationTitlesDisplayed.get(i).equals(publicationTitles
					.get(i)))) {
				return false;
			}
		}
		return true;
	}

	private List<String> getPublicationTitlesDisplayed() throws Exception {
		for (int i = 1; i <= new TextLabel(propertyReader.get(displayGroupName
				.toLowerCase()
				+ "_content")).getXpathCount(); i++) {
			publicationTitlesDisplayed.add( new TextLabel(propertyReader.get(displayGroupName
					.toLowerCase()
					+ "_content")).getLabelText());
		}
		return publicationTitlesDisplayed;
	}

	public boolean checkDisplayCountAfterSorting() throws Exception {
		int count = new TextLabel(propertyReader.get(displayGroupName
				.toLowerCase()
				+ "_content")).getXpathCount();
		return count == initialCount;
	}

	public boolean checkViewAllDisplayCountAfterSorting() throws Exception {
		int viewallcount = Integer.parseInt(new TextLabel(propertyReader.get(displayGroupName
				.toLowerCase()
				+ "_viewAll")).getLabelText()
				.split(" ")[2]);
		return viewallcount == intialviewAllCount;
	}

	public boolean verifySortOrderAfterClickingViewAll(String displayGroupName,
			String dataBaseElement) throws InterruptedException, Exception {
		new Link(propertyReader.get(displayGroupName
				.toLowerCase()
				+ "_viewAll")).click();
		for (int i = 1; i <= new TextLabel(propertyReader.get(displayGroupName
				.toLowerCase()
				+ "_content")).getXpathCount(); i++) {
			publicationTitlesDisplayed.add(new TextLabel(propertyReader.get(displayGroupName
					.toLowerCase()
					+ "_link_afterViewAll")).getLabelText());
		}
		return compareWithDB(publicationTitlesDisplayed, getDataFromDB(
				displayGroupName, dataBaseElement));
	}
	
	public boolean validateSortingAfterClickingNext(String displayGroupName,String dataBaseElement) throws Exception{
		if(new Link(propertyReader.get("next")).isPresent()){
			new Link(propertyReader.get("next")).click();
		}
		displayedContent = new ArrayList<String>();
		displayedContent = getPublicationTitlesDisplayed();
		return compareWithDBAfterNextOPrevious(displayedContent,getDataFromDB(displayGroupName,
				 dataBaseElement),20);
	}
	
	public boolean validateSortingAfterClickingPrevious(String displayGroupName,String dataBaseElement) throws InterruptedException, Exception{
		if(new Link(propertyReader.get("previous")).isPresent()){
			new Link(propertyReader.get("previous")).click();
		}
		displayedContent = new ArrayList<String>();
		displayedContent = getPublicationTitlesDisplayed();
		return compareWithDBAfterNextOPrevious(displayedContent,getDataFromDB(displayGroupName,
				 dataBaseElement),10);
	}
	
	private boolean compareWithDBAfterNextOPrevious(List<String> displayedContentForNextOrPrevious,
			List<String> displayedContentFromDB,int dataBaseIndex) {
		if(displayedContentFromDB.size()>dataBaseIndex){
		for (int i = 0; i < displayedContentForNextOrPrevious.size(); i++) {
			if (!(displayedContentForNextOrPrevious.get(i).equals(displayedContentFromDB.get(dataBaseIndex++)))) {
				return false;
			}
		}
		}
		return true;
	}
	
	public boolean validateSortingByUnSelectingPublicationTitle(String displayGroupName,String dataBaseElement) throws InterruptedException, Exception{
		new DropDownBox(propertyReader.get("sort_by_xpath")).select(propertyReader.get("sort_by_contentLevel"));
		new Link(propertyReader.get("go")).click();
		displayedContent = new ArrayList<String>();
		displayedContent = getPublicationTitlesDisplayed();
		return !compareWithDB(displayedContent,getDataFromDB(displayGroupName,
				 dataBaseElement));
	}
	
	public boolean validateSortingForBreadCrumbNavigation(String displayGroupName,String dataBaseElement) throws InterruptedException, Exception{
		new Link(propertyReader.get("audio_content_locator")).click();
		new Link(propertyReader.get("search_results")).click();
		displayedContent = new ArrayList<String>();
		displayedContent = getPublicationTitlesDisplayed();
		return compareWithDB(displayedContent,getDataFromDB(displayGroupName,
				 dataBaseElement));
	}
}
