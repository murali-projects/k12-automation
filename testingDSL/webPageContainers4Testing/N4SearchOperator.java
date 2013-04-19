package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class N4SearchOperator extends BasePageContainer{

	public N4SearchOperator() throws Exception {
		super();
	}
	
	private int getLinksCount(String displayGroup){
		return new Link(properties.get(displayGroup+"_searchResults_xpath")).getLinkCount();
	}
	
	private List<String> getResults(String displayGroup) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		for(int i = 1; i <= getLinksCount(displayGroup); i++){
			new Link(properties.get(displayGroup+"_searchResults_xpath")+"["+i+"]" + "/h3/a").click();
			list.add(new TextLabel(properties.get("description_in_detailedPage")).getLabelText().toLowerCase());
			new Url().goBackToPreviousPage();
		}
		return list;
	}
	
	public boolean verifyResultsWithN4SearchOperator(String searchTerm, String displayGroup) throws InterruptedException {
		List<String> list = getResults(displayGroup);
		for (int i = 0; i < list.size(); i++) {
			if (!(verifySearchTermIndexes(list.get(i).split(" "), searchTerm.split("N4"))))
				return false;
		}
		return true;
	}
	
	private boolean verifySearchTermIndexes(String[] description,
			String[] searchValues){
		
		List<String> contentValuesList = new ArrayList<String>(Arrays.asList(description));
		int firstWordIndex = -1;
		int secondWordIndex = -1;
		if (contentValuesList.contains(searchValues[0].trim()))
			firstWordIndex = contentValuesList.indexOf(searchValues[0].trim());
		if (contentValuesList.contains(searchValues[1].trim()))
			secondWordIndex = contentValuesList.indexOf(searchValues[1].trim());

		if(secondWordIndex == -1 || firstWordIndex == -1)
			return false;
		return (secondWordIndex - firstWordIndex <= 4)||(firstWordIndex - secondWordIndex <= 4);
	}
	
}
