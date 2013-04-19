package webPageContainers4Testing;

import java.util.Arrays;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextLabel;

public class SortSearchResultsFromSelectionList extends BasePageContainer {

	private SortSearchPageResults sortSearchPageResults;
	private final String narrowResultsXPath = "//div[@id='nrwres']";
    private final String subjectTermsDropDownXPath = "//select[@name='subjterm']";
    private final String sortByDropDownXPath = "//select[@name='sortby']";
    private final String limitDropDownXPath = "//select[@name='limit']";
    private final String narrowResults = "Narrow results";
    private final String rightArrowButtonXpath = "//img[@alt='rightArrowButton']";
    private final String searchString = "test";
    private final String relevance ="Relevance";
    private final String sortSearchResultPageForRelivace = "Sorting Results for \""+relevance+"\" returned the following";
    
    private final String sortSearchResultPageForRelivaceXPath ="//div[1]/div[@id='srtrelc']";
    private final List<String> sortByDropDownOptionsList = Arrays.<String>asList(new String[]{"Relevance","Publication Date",
																"Publication title","Doc Title"});
    private final String sortByDropDownOptionsXPath = "//select[@name='sortby']/option";
    private final List<String> displayGroupLabels = Arrays.asList(new String[]{"Reference Articles","News",
															    "Magazines","Academic Journals",
															    "Images","Audio"});
    
    private final String[] displayGroupDBColumnNames = new String[]{"Reference","Biography","Bios"};
    
    private final List<String> displayGroupLabelsXPath = Arrays.asList(new String[]{"//div[@id='refArticle']","//div[@id='news']",
																       "//div[@id='magazines']","//div[@id='academicjournals']",
																       "//div[@id='images']","//div[@id='audio']"});       
    //SortBy Items
    //This sortBy elements to be passed as it is to the OceanDatabaseConnectivity(i.e Utility file)
	private final String[] sortBy = {"documentTitle","documentId","documentType","relevanceWt"};

    /**
     * Constructor 
     * @throws Exception
     */
	public SortSearchResultsFromSelectionList() throws Exception {
		super();
		sortSearchPageResults = new SortSearchPageResults();
	}
	
	/**
	 * Verify's search success or not by verifying
	 * search success message and 
	 * narrow results section(i.e narrow results label and subjectTerms,sortBy,limit dropdown box) is displayed.
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean searchValidation() throws Exception{
		sortSearchPageResults.search(this.searchString);

		return sortSearchPageResults.verifySearchSuccessMsgIsDisplayed() &&
					checkNarrowResultsSectionIsDisplayed() &&
		            sortSearchPageResults.verifyDisplayGroupsAreDisplayed();
	}
	
	/**
	 * Checks NarrowResults section is displayed.
	 * narrow results section(i.e narrow results label and subjectTerms,sortBy,limit dropdown box) 
	 * and RightArrow button is displayed.
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	private boolean checkNarrowResultsSectionIsDisplayed() throws Exception{
		 
		return (verifyNarrowResultsLabelsIsDisplayed() &&
				checkAllDropDownBoxIsDisplayed() &&
				new PageButton(rightArrowButtonXpath).isPresent());
	}
	
	/**
	 * Checks Narrow Results label is displayed.
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	private boolean verifyNarrowResultsLabelsIsDisplayed() throws Exception{
		
		//Check the Search Success display message
		String narrowResultsLabel = new TextLabel(narrowResultsXPath).getLabelText();
		return narrowResultsLabel.equals(narrowResults) ? true : false;
	}	 

	/**
	 * check all dropdown boxes are displayed.
	 * i.e subjectTerms, sortBy and limits dropdown boxes
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	private boolean checkAllDropDownBoxIsDisplayed() throws Exception{

		return (new DropDownBox(subjectTermsDropDownXPath).isPresent() &&
				new DropDownBox(sortByDropDownXPath).isPresent() &&
				new DropDownBox(limitDropDownXPath).isPresent());		
	}	

	/**
	 * OnClicking the sortBy DropDown check all options are present.
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean onClickSortByDropDownDisplayOptions() throws Exception{
		
		//search for a searchterm
		sortSearchPageResults.search(this.searchString);
		
		//check search is successful and sortByList box is displayed.
		if(sortSearchPageResults.verifySearchSuccessMsgIsDisplayed() &&	new DropDownBox(sortByDropDownXPath).isPresent()){
			
			//check dropdown list contains the valid options.
			return checkSortByDropDownIsDisplayedWithValues();
		}
		return false;
		
	}
	
	/**
	 * verify all options in the DropDown are present.
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	private boolean checkSortByDropDownIsDisplayedWithValues() throws Exception{
		 return validateDropDownOptions(new DropDownBox(sortByDropDownOptionsXPath).getSelectOptionsCount(),
										sortByDropDownOptionsXPath);
	}
	
	/**
	 * Verify all options in the DropdownList are valid.
	 * 
	 * @param selectOptionCount
	 * @param dropDownXpath
	 * @return boolean
	 * @throws Exception
	 */
	private boolean validateDropDownOptions(int selectOptionCount, String dropDownXpath) throws Exception{
		for(int option=1;option<=selectOptionCount;option++){
			if(!sortByDropDownOptionsList.contains(new TextLabel(dropDownXpath+"["+option+"]").getLabelText()))
				return false;
		}		
		return true;
	}
    
	
	/**
	 * validate the selected option in the SortBy dropdown.
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	/*public boolean validateSelectedOptionInSortByDropDown() throws Exception{
		
			if(onClickSortByDropDownDisplayOptions()){
	
				//Click RelevanceOption in SortBy DropDown 
				//option[3] is for selecting the Relevance option.
				new DropDownBox(sortByDropDownXPath).select(sortByDropDownOptionsXPath+"["+3+"]");
				
				//Verifying whether RevelanceOption is selected or not.
				return new DropDownBox(sortByDropDownXPath).getSelectedOption().equals(relevance);
			}
			return false;
	}*/
	
	/**
	 * Clicking the right arrow button and verifying sort result page is displayed.
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	/*public boolean clickRightArrowButton() throws Exception{
		
		//selecting the Relevance option in SortBy dropdown.
		if(validateSelectedOptionInSortByDropDown()){
			
			//Clicking the RightArrow button
			new PageButton(rightArrowButtonXpath).clickAndWait();
			return verifySortResultPageIsDisplayed() ? true : false;
		}
		
		return false;
	}*/
	
	/**
	 * Verify SortPage for Relevance is displayed.
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	private boolean verifySortResultPageIsDisplayed() throws Exception{
		
		//Check the Sort Search page is displayed.
		String sortSearchPage = new TextLabel(sortSearchResultPageForRelivaceXPath).getLabelText();
		return sortSearchPage.equals(sortSearchResultPageForRelivace) ? true : false;		
	}
	
	/**
	 * Verifying display group count displayed in application is 
	 * equal to count retrieved from the Ocean DB.
	 * This count is verified for all display groups.
	 * 
	 * @return boolean
	 * @throws Exception 
	 */
	/*public boolean validateSortResultsCount() throws Exception{
		for(String displayGroupName : displayGroupDBColumnNames){
			if(!(getDisplayGroupCount(displayGroupName) ==  getDisplayGroupCountFromOceanDataBase(displayGroupName))){
				return false;
			}
		}
		return true;
	}*/
	
	/**
	 * Captures the display group count from the application
	 * 
	 * @param dispGroupName
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 */
	private int getDisplayGroupCount(String dispGroupName) throws Exception {
		
		//get display group count from the application
		int index = displayGroupLabels.indexOf(dispGroupName);
		
		if(index!=-1){
			//Get the XPath for display group name 
			//and get the display group label and count.
            String displayGroupNameAndCountValue  =	new TextLabel(displayGroupLabelsXPath.get(index)).getLabelText();
            return Integer.parseInt(displayGroupNameAndCountValue.substring(displayGroupNameAndCountValue.indexOf("(")+1, displayGroupNameAndCountValue.indexOf(")")));
		}
		return 0;
	}
	
	/**
	 * Retrieves the display group count from the Ocean Database.
	 * 
	 * @param dispGroupName
	 * @return
	 * @throws IOException
	 */
/*	private int getDisplayGroupCountFromOceanDataBase(String dispGroupName) throws IOException{
		int count = 0;
		count = OceanDatabaseConnectivity.getDisplayGroupCount(dispGroupName);
		return count;
	}*/
}
	
	
	
