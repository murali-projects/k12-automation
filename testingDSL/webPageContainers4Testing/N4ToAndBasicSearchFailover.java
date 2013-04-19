package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class N4ToAndBasicSearchFailover extends BasePageContainer{

	public N4ToAndBasicSearchFailover() throws Exception {
		super();
	}
	
	public boolean verifySearchResults(String searchTerm, String displayGroup) throws Exception{
		String[] wordsToSearch = null;
		if(searchTerm.contains("or")){
			wordsToSearch = searchTerm.split("or");
		}
		else if(searchTerm.contains("with")){
			wordsToSearch = searchTerm.split("with");
		}
		else if(searchTerm.contains("nor")){
			wordsToSearch = searchTerm.split("nor");
		}
		else if(searchTerm.contains("but")){
			wordsToSearch = searchTerm.split("but");
		}
		return verifyResults(displayGroup, wordsToSearch);
	}
	
	private boolean verifyResults(String displayGroup, String[] wordsToSearch) throws Exception{
		boolean isVerified = true;
		int noOfLinks = new TextLabel(properties.get(displayGroup+"_searchResults_xpath")).getXpathCount();
		for(int i = 1; i <= noOfLinks; i++){
			if (validateN4SearchOperator(new TextLabel(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + i + "]/h3/a").getLabelText().split(" "),
					wordsToSearch))
				continue;
			else if (!verifyResultsWithAndOperator(displayGroup, wordsToSearch)) {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}

	private boolean validateN4SearchOperator(String[] result, String[] wordsToSearch) {
		List<String> resultList = new ArrayList<String>(Arrays.asList(result));
		int firstWordIndex = 0;
		int secondWordIndex = 0;

		if (resultList.contains(wordsToSearch[0].trim()))
			firstWordIndex = resultList.indexOf(wordsToSearch[0].trim());
		if (resultList.contains(wordsToSearch[1].trim()))
			secondWordIndex = resultList.indexOf(wordsToSearch[1].trim());
			return (secondWordIndex - firstWordIndex == 4);
	}
	
	private boolean verifyResultsWithAndOperator(String displayGroup, String[] wordsInSearchTerm) throws Exception{
		new TextBox(properties.get("searchBox")).type(wordsInSearchTerm[0]+ "and" + wordsInSearchTerm[1]);
		new PageButton(properties.get("findButton")).clickAndWait();
		int noOfLinks = new TextLabel(properties.get(displayGroup+"_searchResults_xpath")).getXpathCount();
		for(int i = 1; i <= noOfLinks; i++){
			if(! (new TextLabel(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + i + "]/h3/a").getLabelText().contains(wordsInSearchTerm[0]) && 
					new TextLabel(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + i + "]/h3/a").getLabelText().contains(wordsInSearchTerm[0])))
					return false;
		}
		
		return true;
	}
}
