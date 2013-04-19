package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class IntegrateStatisticsDisplayGroup extends BasePageContainer {

	public IntegrateStatisticsDisplayGroup() throws Exception {
		super();
	}

	public boolean checkContentResultsArePresent() throws Exception {
		return (new TextLabel(properties
					.get("statistics_searchResults_xpath")).isPresent());
	}

	public boolean verifyThreeDocumentsArePresent() throws Exception {
		if (getResultsCount() >= 3) {
			return (new TextLabel(properties
					.get("statistics_searchResults_xpath")).getXpathCount() == 3);
		}
		return new TextLabel(properties.get("statistics_searchResults_xpath"))
				.getXpathCount() == getResultsCount();
	}

	private int getResultsCount() throws Exception {
		if (new Link(properties.get("statistics_viewAll")).isPresent()) {
			String viewAllText = new Link(properties.get("statistics_viewAll"))
					.getLinkText();
			return Integer.parseInt(viewAllText.split(" ")[2].trim().replace(
					",", ""));
		}
		return 0;
	}

	public boolean verifyViewAllIsDisplayedWithResultsCount() throws Exception {
		if (new TextLabel(properties.get("statistics_viewAll")).isPresent()) {
			String viewAllText = new Link(properties.get("statistics_viewAll"))
					.getLinkText();
			return (!viewAllText.split(" ")[2].isEmpty());
		}
		return true;
	}

	public boolean verifyViewAllIsAccessible() throws Exception {
		int totalNoOfResults = getResultsCount();
		new Link(properties.get("statistics_viewAll")).click();
		if (new TextLabel(properties.get("statistics_viewAll_page")).isPresent()
				&& new TextLabel(properties.get("totalResults")).getLabelText()
						.replace(",", "").equals(
								String.valueOf(totalNoOfResults))) {
			return true;
		}
		return false;
	}

	public boolean verifyCountIsDisplayed() throws Exception {
		boolean validate = true;
		if (new Link(properties.get("statistics_viewAll")).isPresent()) {
			String viewAllText = new Link(properties.get("statistics_viewAll"))
					.getLinkText();
			return (!viewAllText.split(" ")[2].isEmpty());
		}
		return validate;
	}

	public boolean verifyGroupNameIsDisplayedInViewAllPage() throws Exception {
		new Link(properties.get("statistics_viewAll")).click();
		return (new TextLabel(properties.get("statistics_viewAll_page")).isPresent());
	}

	public boolean verifySearchMessage(String searchTerm){
		return new TextLabel(properties.get("searchResults_msg_xpath")).getLabelText().contains(searchTerm);
	}
	
	public boolean verifyContentLevelIsPresent(){
		for(int i=1; i<new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++){
			if(! new TextLabel(properties.get("viewAll_searchResults")+ "["+ i +"]/td[@class='content_level']").isPresent())
				return false;
		}
		return true;
	}
	
	public boolean ensureDocumentsAreRelatedToStatistics(){
		List<String> documentTitles = getDocumentTitles();
		for(int i=1; i<new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++){
			if (!new TextLabel(properties.get("viewAll_searchResults") + "["
					+ i + "]/td[2]/h3/a").getLabelText().equals(
					documentTitles.get(i - 1)))
				return false;
		}
		return true;
	}

	public boolean isDetailedPageDisplayed() throws InterruptedException{
		new Link(properties.get("viewAll_searchResults")+"/td[2]/h3/a").click();
		return (new TextLabel(properties.get("statistics_title_in_details")).isPresent() && 
				new TextLabel(properties.get("copyright_label")).isPresent());
	}
	
	private List<String> getDocumentTitles(){
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= new TextLabel(properties
				.get("statistics_searchResults_xpath")).getXpathCount(); i++) {
			
			list.add(new Link(properties.get("statistics_searchResults_xpath")
					+ "[" + i + "]/h3/a").getLinkText());
		}
		return list;
	}
	
	public boolean verifyDocumentTitles(
			String searchTerm, String displayGroup)
			throws Exception {
		List<String> list = getDocumentTitles();
		List<String> documentTitlesFromDB = getDataFromDB(searchTerm, displayGroup,
				 properties.get("documentTitle"));
		for (int i = 0; i < list.size(); i++) {
				if (!(documentTitlesFromDB.get(i).contains(list.get(i).substring(0, list.get(i).length()-1)))) {
				return false;
			}
		}
		return true;
	}

	private List<String> getDataFromDB(String searchTerm, String displayGroup,
			String dataToRetrieve) throws IOException {
		List<String> list = OceanDatabaseReadFile.readResultsFromFile(
				properties.get("all"), displayGroup, "relevance", dataToRetrieve,
				searchTerm);
		return list;
	}

}

