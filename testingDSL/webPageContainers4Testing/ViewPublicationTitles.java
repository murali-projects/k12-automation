package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.TextLabel;

public class ViewPublicationTitles extends BasePageContainer{

	public ViewPublicationTitles() throws Exception {
		super();
	}
	
	public boolean checkPublicationTitlesAreDisplayed(String displayGroup) throws Exception{
		int noOfLinks = new TextLabel(properties.get(displayGroup+"_searchResults_xpath")).getXpathCount();
		for(int i = 1; i <= noOfLinks; i++){
			if(! new TextLabel(properties.get(displayGroup+"_searchResults_xpath")+"["+i+"]"+ properties.get(displayGroup+"_pubTitle")).isPresent()){
				return false;
			}
		}
		return true;
	}
	
	public boolean verifypublicationTitles(String searchTerm, String displayGroup) throws Exception{
		if(checkPublicationTitlesAreDisplayed(displayGroup)){
			List<String> publicationTitlesInAppl = getPublicationTitles(displayGroup);
			return verifyPublicationTitlesWithDB(searchTerm, displayGroup, publicationTitlesInAppl);
		}
		return false;
	}
	
	private List<String> getPublicationTitles(String displayGroup) throws Exception{
		int noOfLinks = new TextLabel(properties.get(displayGroup+"_searchResults_xpath")).getXpathCount();
		List<String> publicationTitlesList = new ArrayList<String>();
		String publicationTitle = "";
		for(int i = 1; i <= noOfLinks; i++){
			publicationTitle = new TextLabel(properties.get(displayGroup+"_searchResults_xpath")+ "[" +i+ "]"+properties.get(displayGroup+"_pubTitle")).getLabelText();
			
			//Since a special character is displayed at the end, we are excluding that
			publicationTitlesList.add(publicationTitle.substring(0, publicationTitle.length()-1));
		}
		return publicationTitlesList;
	}
	
	private boolean verifyPublicationTitlesWithDB(String searchTerm, String displayGroup,
												  List<String> publicationTitlesInAppl) throws IOException{
		if(displayGroup.equals("reference")){
			displayGroup = "K12-Reference";
		}
		List<String> publicationTitlesInDB = OceanDatabaseReadFile.readResultsFromFile(
				properties.get("all"),displayGroup,
				properties.get("relevance"), 
				properties.get("publicationTitle"), searchTerm);
		if ((publicationTitlesInDB != null && publicationTitlesInAppl != null)) {
			
			for (int i = 0; i < publicationTitlesInAppl.size(); i++) {
				if (!publicationTitlesInAppl.get(i).equals(publicationTitlesInDB.get(i))) 
					return false;
			}
		}
		return true;
	}
}
