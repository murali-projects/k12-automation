package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class ContentVerificationOfDisplayGroups extends BasePageContainer {

	public ContentVerificationOfDisplayGroups() throws Exception {
		super();
	}
	
	public boolean checkDisplayGroupIsPresent(String displayGroup) throws Exception {
		if (new TextLabel(properties.get(displayGroup+"_label")).isPresent())
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsPresent(String displayGroup) throws Exception {
		if (new Link(properties.get(displayGroup+"_viewAll")).isPresent())
			return true;
		return false;
	}

	public boolean verifyCountIsDisplayed(String displayGroup) throws Exception{
		if(checkViewAllLinkIsPresent(displayGroup)){
			if(! new Link(properties.get(displayGroup+"_viewAll")).getLinkText().split(" ")[2].isEmpty())
				return true;
		}
		return false;
	}
	
	private int getCount(String displayGroup) throws Exception {
		if(checkViewAllLinkIsPresent(displayGroup)){
			String viewAllLinkText = new Link(properties.get(displayGroup+"_viewAll")).getLinkText();
			return Integer.parseInt(viewAllLinkText.split(" ")[2].trim().replace(",", ""));
		}
		return 0;
	}

	public boolean validateCount(String displayGroup, String searchTerm)
			throws Exception {
		int count = getCount(displayGroup);
		if(displayGroup.equals("reference"))
			displayGroup = properties.get("K12-Reference");
		if(displayGroup.equals("primarysources"))
			displayGroup = properties.get("K12-PrimarySources");
		List<String> list = OceanDatabaseReadFile.readResultsCountFromFile(properties.get("all"), properties.get(displayGroup), properties.get("relevance"), searchTerm);
		String countFromDatabase = (list!=null && !list.isEmpty()) ? list.get(0) : "0";
		return (count == Integer.parseInt(countFromDatabase));
	}

	public boolean verifyLinkCountInFirstPage(String displayGroup) throws Exception {
		int count = getCount(displayGroup);
		if(count >= 3){
			return (new Link(properties.get(displayGroup+"_searchResults_xpath")).getLinkCount() == 3);
		}else
			return (new Link(properties.get(displayGroup+"_searchResults_xpath")).getLinkCount() == count);
	}

	public boolean verifyDefaultSortOrderIsByRelevance(String displayGroup, String searchTerm) throws Exception{
		List<String> links = new ArrayList<String>();
		int linksCount = new Link(properties.get(displayGroup+"_searchResults_xpath")).getLinkCount();
		for(int i = 1; i <= linksCount; i++){
			links.add(new Link(properties.get(displayGroup+"_searchResults_xpath"+ "[" +i+ "]")).getLinkText());
		}
		return verifySortOrderInDatabase(displayGroup, searchTerm, links);
	}

	private boolean verifySortOrderInDatabase(String displayGroup, String searchTerm, List<String> linkTexts) throws IOException{
		List<String> list = OceanDatabaseReadFile.readResultsCountFromFile(properties.get("all"), properties.get(displayGroup), properties.get("relevance"), searchTerm);
		
		for(int i = 0; i < linkTexts.size(); i++){
			if(! linkTexts.get(i).equals(list.get(i)))
				return false;
		}
		return true;
	}
	
	public boolean verifyPublicationDateIsDisplayed(String displayGroup) throws Exception{
		int linksCount = new Link(properties.get(displayGroup+"_searchResults_xpath")).getLinkCount();
		for(int i = 1; i <= linksCount; i++){
			if(!new TextLabel(properties.get(displayGroup+"_searchResults_xpath")+ "[" +i+ "]" +properties.get(displayGroup+"_pubDate")).isPresent())
				return false;
		}
		return true;
	}
	
	public boolean verifyPublicationNameIsDisplayed(String displayGroup) throws Exception{
		int linksCount = new Link(properties.get(displayGroup+"_searchResults_xpath")).getLinkCount();
		for(int i = 1; i <= linksCount; i++){
			if(!new TextLabel(properties.get(displayGroup+"_searchResults_xpath")+"["+i+"]"+ properties.get(displayGroup+"_pubTitle")).isPresent())
				return false;
		}
		return true;
	}
	
	public boolean validatePublicationDates(String displayGroup, String sortBy, String searchTerm) throws Exception{
		int linksCount = new Link(properties.get(displayGroup+"_searchResults_xpath")).getLinkCount();
		List<String> publicationDates = new ArrayList<String>();
		for(int i = 1; i <= linksCount; i++){
			publicationDates.add(new TextLabel(properties.get(displayGroup+"_searchResults_xpath")+ "[" +i+ "]"+properties.get(displayGroup+"_pubDate")).getLabelText());
		}
		return verifyPublicationDatesWithDatabase(displayGroup, sortBy, searchTerm, publicationDates);
	}
	
	private boolean verifyPublicationDatesWithDatabase(String displayGroup, String sortBy, String searchTerm, List<String> publicationDates) throws IOException{
		if(displayGroup.equals("reference"))
			displayGroup = properties.get("K12-Reference");
		if(displayGroup.equals("primarysources"))
			displayGroup = properties.get("K12-PrimarySources");
		List<String> list = OceanDatabaseReadFile.readResultsFromFile(properties.get("all"), properties.get(displayGroup), properties.get(sortBy), properties.get("publicationDate"), searchTerm);
		for(int i = 0; i < publicationDates.size(); i++){
			if(! publicationDates.get(i).equals(list.get(i)))
				return false;
		}
		return true;
	}
	
	public boolean validatePublicationNames(String displayGroup, String sortBy, String searchTerm) throws Exception{
		int linksCount = new Link(properties.get(displayGroup+"_searchResults_xpath")).getLinkCount();
		List<String> publicationNames = new ArrayList<String>();
		String publicationTitle = "";
		for(int i = 1; i <= linksCount; i++){
			publicationTitle = new TextLabel(properties.get(displayGroup+"_searchResults_xpath")+ "[" +i+ "]"+properties.get(displayGroup+"_pubTitle")).getLabelText();
			
			//Since a special character is displayed at the end, we are excluding that
			publicationNames.add(publicationTitle.substring(0, publicationTitle.length()-1));
		}
		return verifyPublicationNamesWithDatabase(displayGroup, sortBy, searchTerm, publicationNames);
	}
	
	private boolean verifyPublicationNamesWithDatabase(String displayGroup, String sortBy, String searchTerm, List<String> publicationNames) throws IOException{
		if(displayGroup.equals("reference"))
			displayGroup = properties.get("K12-Reference");
		if(displayGroup.equals("primarysources"))
			displayGroup = properties.get("K12-PrimarySources");
		List<String> list = OceanDatabaseReadFile.readResultsFromFile(properties.get("all"), properties.get(displayGroup), properties.get(sortBy), properties.get("publicationTitle"), searchTerm);
		for(int i = 0; i < publicationNames.size(); i++){
			if(! publicationNames.get(i).equals(list.get(i)))
				return false;
		}
		return true;
	}
}


