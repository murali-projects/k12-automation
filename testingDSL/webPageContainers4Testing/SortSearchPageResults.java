package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.CheckBox;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class SortSearchPageResults extends BasePageContainer {

	private TextBox searchTextBox;
	private PageButton searchButton, applyButton, displayGroupTab;
	private CheckBox checkBox;
	private String searchString = "Aldehydes";
	private final String[] displayGroupLabels;
    
    private final String[] sortBy = {"documentTitle","documentId","documentType","relevanceWt"};
 
    public SortSearchPageResults() throws Exception {
		super();
		// Gets search textbox and search button instances present in Homepage
		searchTextBox = new TextBox(properties.get("searchFieldXPath"));
		searchButton = new PageButton(properties.get("searchButtonXPath"));
		
		displayGroupLabels = new String[]{"reference","news",
	             "magazines","academicJournals",
	             "images","audio"};
	}
    
	public boolean searchValidation() throws Exception{
		
		//Entering searchTerm in text field and clicking search button
		search();
		
		if(verifySearchSuccessMsgIsDisplayed() && verifyDisplayGroupsAreDisplayed()){
			
			//Clicking on the Magazine DisplayGroup
			displayGroupTab = new PageButton(properties.get(displayGroupLabels[0]+"_label"));
			displayGroupTab.clickAndWait();
			
			if(checkAllSubTabsInDisplayGroupsAreDisplayed())
				//if AllSub tabs are displayed then return true	
				return true;
		}
		
		return false;
	}
	
	public void search() throws InterruptedException{
		
		//Enters search term in the search text field
		searchTextBox.type(this.searchString);
		
		//Clicks on search button
		searchButton.clickAndWait();
		Thread.sleep(5000);
	}	
	
	public void search(String searchString) throws InterruptedException{
		this.searchString = searchString;
		
		//Enters search term in the search text field
		searchTextBox.type(searchString);
		
		//Clicks on search button
		searchButton.clickAndWait();
		Thread.sleep(5000);
	}
	
	public boolean verifySearchSuccessMsgIsDisplayed() throws Exception{
		
		String searchSuccessMsgExpected = "Your search for \""+this.searchString+"\" returned the following";
		
		//Check the Search Success display message
		String searchSuccessMsgDisplayed = new TextLabel(properties.get("searchWordXPath")).getLabelText();
		return searchSuccessMsgDisplayed.equals(searchSuccessMsgExpected) ? true : false;
		
	}
	
	public boolean verifyDisplayGroupsAreDisplayed() throws Exception{

		//Retrieving the displayGroup Names from StringArray
		for(String displayGroupLabel : displayGroupLabels){
			    
			    //verifying the displayGroup Names with displayGroupLabels in the Application.
			    //if one or more displayGroupLabels is not displayed the validation fails 
				if(!validateDisplayGroupName(displayGroupLabel))

					//if one or more DisplayGroup name is not matching then return false
					return false;
			}
		
		return true;
	}
	
	private boolean validateDisplayGroupName(String displayGroupLabel) throws Exception{
		 
		  boolean isDisplayGroupNameDisplayed = false;
		  if(displayGroupLabel.equals(getDisplayGroupLabel(displayGroupLabel))) {
			  isDisplayGroupNameDisplayed = true;
		  }
		  return isDisplayGroupNameDisplayed;
	}
	
	private String getDisplayGroupLabel(String displayGroupLabel) throws Exception{
		
		//Retrieving the XPath from Map
        String displayGroupLabelXPath = properties.get(displayGroupLabel+"_label");
        
        //Retrieving the DisplayGroupLabel from the Application,
        //based on the XPath.
        String displayGroupName = new TextLabel(displayGroupLabelXPath).getLabelText();		
        
        return displayGroupName;
	}
	
	private boolean checkAllSubTabsInDisplayGroupsAreDisplayed() throws Exception{
		boolean isAllSubTabsDisplayed = false;
		if(checkSubTabInDisplayGroupsAreDisplayed("Subject Terms",properties.get("subjectTermsXPath")) &&
		   checkSubTabInDisplayGroupsAreDisplayed("Sort By",properties.get("sortByXPath")) &&
		   checkSubTabInDisplayGroupsAreDisplayed("Limit",properties.get("limitXPath"))){
			isAllSubTabsDisplayed = true;
		}
		return isAllSubTabsDisplayed;
	}

	private boolean checkSubTabInDisplayGroupsAreDisplayed(String tabName,String tabXPath) throws Exception{
		boolean isTabDisplayed = false; 
		if(tabName.equals(new TextLabel(tabXPath).getLabelText())){
			isTabDisplayed = true;
		}
		return isTabDisplayed;
	}
	
	public boolean clickDocTitleCheckBox() throws Exception{
		
		boolean isCheckBoxClicked = false;
		if(verifySearchSuccessMsgIsDisplayed() && validateDisplayGroupName(displayGroupLabels[0])){
			
			//Clicking on the Magazine DisplayGroup
			displayGroupTab = new PageButton(properties.get(displayGroupLabels[0]+"_label"));
			displayGroupTab.clickAndWait();
			
			if(checkSubTabInDisplayGroupsAreDisplayed("Sort By",properties.get("sortByXPath"))){

				//Clicking on the CheckBox present in the SortPage tab.
				checkBox = new CheckBox(properties.get("docTitleXPath"));
				checkBox.click();
				isCheckBoxClicked = true;
			}
		}
		return isCheckBoxClicked;
	}

	public boolean isCheckBoxChecked() {
		try {
			checkBox = new CheckBox(properties.get("docTitleXPath"));
			return checkBox.isChecked();
		} catch (Exception e) {
			return false;
		}
	} 
	
	/**
	 * click on Apply button
	 * 
	 * @return boolean
	 */
	public boolean isApplyButtonClicked(){
		try {
			applyButton = new PageButton(properties.get("applyButtonXPath"));
			applyButton.clickAndWait();
			return true;
		} catch (Exception e) { 
			return false;
		}
	}

	public boolean validateSortedResults() throws Exception{
		
		List<String> sortResultsList = getSortResults();
		
		//displayGroup name : magazine, sortedBy : docTitle
		List<String> sortResultsListFromDataBase = getSortListFromOceanDataBase(displayGroupLabels[0], sortBy[0]);
		
		//verify sortResults count displayed in the results page are equal to 
		//sort results retrieved from the data base. 
		if(sortResultsList.size()==sortResultsListFromDataBase.size())
			//verify the sort results with the results retrieved from the database.
			return verifySortResultsMatchesWithTheDataBase(sortResultsList,sortResultsListFromDataBase);
		return false;
	}
	
	private boolean verifySortResultsMatchesWithTheDataBase(List<String> sortResults, List<String> sortResultsFromDB) throws Exception{
		int sortListCount = sortResults.size();
		for(int count=0; count<sortListCount; count++){
			
			//verify the sort results displayed in the application with 
			//the data retrieved from the data base.
			if(!sortResults.get(count).equals(sortResultsFromDB.get(count)))
				return false;
		}
		return true;
	}
	
	private  List<String> getSortResults() throws Exception{
		List<String> sortList = new ArrayList<String>();
		
		int count = new TextLabel(properties.get("sortResultsXPath")).getXpathCount();
		for(int resultRow=1; resultRow <= count; resultRow++ )
			sortList.add(new TextLabel(properties.get("sortResultsXPath")+"["+resultRow+"]").getLabelText());
		return sortList;
	}

	private List<String> getSortListFromOceanDataBase(String displayGroupName, String sortItemName) throws Exception{
		return OceanDatabaseReadFile.readResultsFromFile(properties.get("all"),
				properties.get("reference"),
				properties.get("relevance"),
				properties.get("documentTitle"),this.searchString);
	}
}