package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class ViewPublicationDates extends BasePageContainer{
	
	public ViewPublicationDates() throws Exception {
		super();
	}
	   
	public boolean verifyPublicationDatesArePresent(String displayGroup)
			throws Exception {
		int count = new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")).getXpathCount();
		for (int i = 1; i <= count; i++) {
			if (!new TextLabel(properties.get(displayGroup
					+ "_searchResults_xpath")
					+ "[" + i + "]" + properties.get(displayGroup + "_pubDate")).isPresent()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean validatePublicationDates(String displayGroup, String searchTerm) throws Exception{
		int linksCount = new Link(properties.get(displayGroup+"_searchResults_xpath")).getLinkCount();
		List<String> publicationDates = new ArrayList<String>();
		for(int i = 1; i <= linksCount; i++){
			publicationDates.add(new TextLabel(properties.get(displayGroup+"_searchResults_xpath")+ "[" +i+ "]"+properties.get(displayGroup+"_pubDate")).getLabelText());
		}
		return verifyPublicationDatesWithDatabase(displayGroup, searchTerm, publicationDates);
	}
	
	private boolean verifyPublicationDatesWithDatabase(String displayGroup, String searchTerm, List<String> publicationDates) throws IOException{
		if(displayGroup.equals("reference"))
			displayGroup = properties.get("K12-Reference");
		if(displayGroup.equals("primarysources"))
			displayGroup = properties.get("K12-PrimarySources");
		List<String> publicationDatesInDB = OceanDatabaseReadFile.readResultsFromFile(properties.get("all"), displayGroup, properties.get("relevance"), properties.get("publicationDate"), searchTerm);
		for(int i = 0; i < publicationDates.size(); i++){
			if(! publicationDates.get(i).equals(publicationDatesInDB.get(i))){
				return false;
			}
		}
		return false;
	}
	
}
