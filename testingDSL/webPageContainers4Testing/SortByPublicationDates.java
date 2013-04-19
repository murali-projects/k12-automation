package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class SortByPublicationDates extends BasePageContainer{

	private Link displayGroupTab;
	public SortByPublicationDates() throws Exception {
		super();
		
	}
	
	public boolean verifyResultsAreSortedByPublDate(String displayGroup) throws Exception{
		boolean isValidate = false;
		if(verifyDisplayGroupExists(displayGroup)){
			if(verifyPublicationDatesAreInDescOrder(displayGroup)){
				isValidate = true;
				new Url().goBackToPreviousPage();
			}
		}
		return isValidate;
	}
	
	public boolean verifyDisplayGroupExists(String displayGroup) throws Exception{
		//Tabs are present on left side of the page.
		//TODO:xpaths need to be changed after implementing tabs in the Appl	
		displayGroupTab = new Link(properties.get(displayGroup+"_viewAll"));
		if(displayGroupTab.isPresent()){
			displayGroupTab.click();
			return true;
		}
		return false;
	}
	
	private List<String> getPublicationDates(String displayGroup) throws Exception{
		List<String> publicationDatesList = new ArrayList<String>();
		//TODO:xpaths need to be changed after implementing tabs in the Appl
		int noOfResults = new TextLabel(properties.get(displayGroup+"_results")+"/td[2]").getXpathCount();
		for(int i = 1; i <= noOfResults; i++){
			publicationDatesList.add( new TextLabel(properties.get(displayGroup+ "_results")
					+ "[" + i + "]"
					+ properties.get(displayGroup+ "_results_publicationDate")).getLabelText());
		}
		return publicationDatesList;
	}
	
	@SuppressWarnings("deprecation")
	private boolean verifyPublicationDatesAreInDescOrder(String displayGroup) throws Exception{
		boolean isValidate = true;
		List<String> publicationDatesInAppl = getPublicationDates(displayGroup);
		int count=0;
		
		for (int i = 0; i <= publicationDatesInAppl.size(); i++) {
			count++;
			if (count < publicationDatesInAppl.size()) {
				String[] date1 = publicationDatesInAppl.get(i).split("-");
				String[] date2 = publicationDatesInAppl.get(i+1).split("-");
				if (publicationDatesInAppl.get(i).equals(publicationDatesInAppl.get(i+1))) {
					//check if document titles are displayed in ascending order
					isValidate = verifyDocumentTitlesAreInAscOrder(displayGroup, i+1);
				}else if(new Date(date1[1]+"/"+date1[2]+"/"+date1[0]).before(new Date(date2[1]+"/"+date2[2]+"/"+date2[0]))){
					isValidate = false;
				}
			}
			if(!isValidate)
				break;
		}
		return isValidate;
		
	}
	
	private List<String> getDocumentTitles(String displayGroup, int documentTitleIndex) throws Exception{
		List<String> documentTitlesList = new ArrayList<String>();
		// TODO:xpaths need to be changed after implementing tabs in the Appl		
		documentTitlesList.add(new TextLabel(properties.get(displayGroup+ "_results")
				+ "["
				+ documentTitleIndex
				+ "]"
				+ properties.get(displayGroup+ "_results_documentTitle")).getLabelText());
		documentTitlesList.add(new TextLabel(properties.get(displayGroup + "_results")
				+ "["
				+ (documentTitleIndex + 1)
				+ "]"
				+ properties.get(displayGroup+ "_results_documentTitle")).getLabelText());

		return documentTitlesList;
	}
	
	private boolean verifyDocumentTitlesAreInAscOrder(String displayGroup, int documentTitleIndex) throws Exception {
		boolean isValidate = false;
		List<String> documentTitles = getDocumentTitles(displayGroup, documentTitleIndex);
		int count=0;
		for (int i = 0; i <= documentTitles.size(); i++) {
			count++;
			if (count < documentTitles.size()) {
				if ((int) documentTitles.get(i).charAt(0) <= 
					(int) documentTitles.get(i + 1).charAt(0)) {
					isValidate = true;
				} else {
					isValidate = false;
					break;
				}
			}

		}
		return isValidate;
	}


}

