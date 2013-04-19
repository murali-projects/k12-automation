package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextLabel;

public class UserSortInDropDown extends BasePageContainer {

    private String reference_results_xpath;  
    private String news_results_xpath;  
    private String magazines_results_xpath;  
    private String journals_results_xpath;
    private String images_results_xpath;  
    private String audio_results_xpath;
		 
    private Map<String,String> displayGroupHeadingLabelsMap;
    private Map<String,String> sortBySelectOptionLabelMap; 
    
	private String searchString;
	
	private Map<String,List<String>> displayGroupResultsMap;
	private Map<String,List<String>> sortResultsFromDataBaseMap;
   
	public UserSortInDropDown() throws Exception {
		super();
		displayGroupHeadingLabelsMap =getDisplayGroupLabelsAndXPaths();
		sortBySelectOptionLabelMap = getSortByDisplayNamesAndValues();
		
		displayGroupResultsMap = new HashMap<String, List<String>>();
		sortResultsFromDataBaseMap = new HashMap<String, List<String>>();
		reference_results_xpath = properties.get("reference_searchResults_xpath")+"[@INDEX@]";
		news_results_xpath = properties.get("news_searchResults_xpath")+"[@INDEX@]";
		magazines_results_xpath = properties.get("magzines_searchResults_xpath")+"[@INDEX@]";
		journals_results_xpath = properties.get("journals_searchResults_xpath")+"[@INDEX@]";
		images_results_xpath = properties.get("images_searchResults_xpath")+"[@INDEX@]";
		audio_results_xpath = properties.get("audio_searchResults_xpath")+"[@INDEX@]";
		
	} 
	 
	private Map<String,String> getDisplayGroupLabelsAndXPaths(){
		displayGroupHeadingLabelsMap = new HashMap<String, String>();
		displayGroupHeadingLabelsMap.put("Reference Articles", properties.get("reference_label"));
		displayGroupHeadingLabelsMap.put("News", properties.get("news_label"));
		displayGroupHeadingLabelsMap.put("Magazines", properties.get("magazines_label"));
		displayGroupHeadingLabelsMap.put("Academic Journals", properties.get("academicJournals_label"));
		displayGroupHeadingLabelsMap.put("Images", properties.get("images_label"));
		displayGroupHeadingLabelsMap.put("Audio", properties.get("audio_label"));
		
		return displayGroupHeadingLabelsMap;
	}
	
	private Map<String,String> getSortByDisplayNamesAndValues(){
		sortBySelectOptionLabelMap = new HashMap<String, String>();
		sortBySelectOptionLabelMap.put(properties.get("sort_all_display_groups"), "allDispGrps");
		sortBySelectOptionLabelMap.put(properties.get("sort_desc_publication_date"), "descPubDate");
		sortBySelectOptionLabelMap.put(properties.get("sort_desc_relevance"), "descRelv");
		sortBySelectOptionLabelMap.put(properties.get("sort_asc_content_level"), "ascContLev");
		sortBySelectOptionLabelMap.put(properties.get("sort_reset_to_default"), "defaultSort");
		
		return sortBySelectOptionLabelMap;		
	}
	
	public void search(String searchTerm) throws InterruptedException{
		this.searchString = searchTerm;
	}		
	
	public boolean searchValidation() throws Exception{

		return verifySearchSuccessMsgIsDisplayed() &&
			   checkNarrowResultsSectionIsDisplayed() &&
		       verifyDisplayGroupsAreDisplayed();
	}
	
	public boolean verifyDisplayGroupsAreDisplayed() throws Exception{
		for(String dispGrpLabel : displayGroupHeadingLabelsMap.keySet()){
			if(!(dispGrpLabel.equals(new TextLabel(displayGroupHeadingLabelsMap.get(dispGrpLabel)).getLabelText()))){
				return false;
			}
		}
		return true;
	}	

	public boolean verifySearchSuccessMsgIsDisplayed() throws Exception{
		
		//Check the Search Success display message
		String searchSuccessMsgDisplayed = new TextLabel(properties.get("success_message_xpath")).getLabelText();
		return searchSuccessMsgDisplayed.equals("Search results: Keyword = "+searchString) ? true : false;

	}	

	private boolean checkNarrowResultsSectionIsDisplayed() throws Exception{
		 
		return (verifyNarrowResultsLabelsIsDisplayed() &&
				checkAllDropDownBoxIsDisplayed() &&
				new PageButton(properties.get("right_arrow_button_xpath")).isPresent());
	}
	
	private boolean verifyNarrowResultsLabelsIsDisplayed() throws Exception{
		
		//Check the Search Success display message
		String narrowResultsLabel = new TextLabel(properties.get("narrow_results_xpath")).getLabelText();
		return narrowResultsLabel.equals(properties.get("narrow_results")) ? true : false;
	}	 

	private boolean checkAllDropDownBoxIsDisplayed() throws Exception{

		return (new DropDownBox(properties.get("subject_terms_drop_down")).isPresent() &&
				new DropDownBox(properties.get("sortby_dropdown_xpath")).isPresent() &&
				new DropDownBox(properties.get("limit_dropdown_xpath")).isPresent());		
	}	

	public boolean verifyOptionsInSortByDropDownIsDisplayed() throws Exception{
		
		//check sortByList box is displayed.
		if(new DropDownBox(properties.get("limit_dropdown_xpath")).isPresent()){
			
			//check dropdown list contains the valid options.
			return verifySortByDropDownIsDisplayedWithValues();
		}
		return false;
		
	}
	
	private boolean verifySortByDropDownIsDisplayedWithValues() throws Exception{

		 return verifyDropDownOptionsPresent(new DropDownBox(properties.get("sortby_option_xpath")).getSelectOptionsCount(),
				 properties.get("sortby_option_xpath"));
	}
	 
	private boolean verifyDropDownOptionsPresent(int selectOptionCount, String dropDownXpath) throws Exception{
		for(int option=1;option<=selectOptionCount;option++){
			if(!sortBySelectOptionLabelMap.keySet().contains(new TextLabel(dropDownXpath+"["+option+"]").getLabelText()))
				return false;
		}		
		return true;
	}
	
	public boolean confirmAllDisplayGroupsOnThePageOptionIsSelected() throws Exception{
		return confirmTheSelectedOptionIsCarriedBySortFilter(sortBySelectOptionLabelMap.get(properties.get("sort_all_display_groups")),
				properties.get("sort_all_display_groups"));
	} 

	public boolean confirmDescendingPublicationDateIsSelected() throws Exception{
		return confirmTheSelectedOptionIsCarriedBySortFilter(sortBySelectOptionLabelMap.get(properties.get("sort_desc_publication_date")),
				properties.get("sort_desc_publication_date"));
	}	

	public boolean confirmDescendingRelevanceIsSelected() throws Exception{
		return confirmTheSelectedOptionIsCarriedBySortFilter(sortBySelectOptionLabelMap.get(properties.get("sort_desc_relevance")),
				properties.get("sort_desc_relevance"));
	}	

	public boolean confirmAscendingContentLevelIsSelected() throws Exception{
		return confirmTheSelectedOptionIsCarriedBySortFilter(sortBySelectOptionLabelMap.get(properties.get("sort_asc_content_level")),
				properties.get("sort_asc_content_level"));
	}
	
	public boolean confirmResetToDefaultIsSelected() throws Exception{
		return confirmTheSelectedOptionIsCarriedBySortFilter(sortBySelectOptionLabelMap.get(properties.get("sort_reset_to_default")),
				properties.get("sort_reset_to_default"));
	}	
	
	private boolean confirmTheSelectedOptionIsCarriedBySortFilter( String selectedOptionValue, String selectedOptionLabel) throws Exception{
		DropDownBox sortByDropDown = new DropDownBox(properties.get("sortby_selection_xpath"));
		
		sortByDropDown.select(selectedOptionLabel);
		
		return sortByDropDown.getSelectedOptionValue().equals(selectedOptionValue)&&
			   sortByDropDown.getSelectedOptionLabel().equals(selectedOptionLabel);
	}
	
	public boolean validateSortResultsForAllDisplayGroupsOnThePage() throws Exception{
		
		clickRightArrowButtonAndGetTheResults();
		
		//TODO. need to modify the SortBy name.
		getSortResultsFromDataBase(properties.get("reference_articles"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("news"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("magazines"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("academic_journals"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("images"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("audio"), properties.get("publication_dates"));
		
		return validateResults();
		
	}
	
	public boolean validateSortResultsForDescendingPublicationDate() throws Exception{
		
		clickRightArrowButtonAndGetTheResults();
		
		//TODO need to modify the SortBy name.
		getSortResultsFromDataBase(properties.get("reference_articles"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("news"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("magazines"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("academic_journals"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("images"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("audio"), properties.get("publication_dates"));
		
		return validateResults();
	}
	
	public boolean validateSortResultsForDescendingRelevance() throws Exception{
		
		clickRightArrowButtonAndGetTheResults();
		
		//TODO need to modify the SortBy name.
		getSortResultsFromDataBase(properties.get("reference_articles"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("news"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("magazines"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("academic_journals"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("images"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("audio"), properties.get("publication_dates"));
		
		return validateResults();
	}	
	
	public boolean validateSortResultsForAscendingContentLevel() throws Exception{
		
		clickRightArrowButtonAndGetTheResults();
		
		//TODO need to modify the SortBy name.
		getSortResultsFromDataBase(properties.get("reference_articles"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("news"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("magazines"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("academic_journals"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("images"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("audio"), properties.get("publication_dates"));
		
		return validateResults();
	}		
	
	public boolean validateSortResultsForDefaultSorts() throws Exception{
		
		clickRightArrowButtonAndGetTheResults();
		
		//TODO need to modify the SortBy name.		
		getSortResultsFromDataBase(properties.get("reference_articles"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("news"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("magazines"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("academic_journals"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("images"), properties.get("publication_dates"));
		getSortResultsFromDataBase(properties.get("audio"), properties.get("publication_dates"));
		
		return validateResults();
	}
	
	private void clickRightArrowButtonAndGetTheResults() throws Exception{
		
		new PageButton(properties.get("right_arrow_button_xpath")).clickAndWait();
		
		removeOldData();
		retrieveSortResultsFromApplication();		
	}
	
	private void removeOldData(){
		displayGroupResultsMap.clear();
		sortResultsFromDataBaseMap.clear();
	}

	private void retrieveSortResultsFromApplication() throws Exception{
		getSortResultsForRefrecnceArticle();
		getSortResultsForNews();
		getSortResultsForMagazines(); 
		getSortResultsForAcademicJournals();
		getSortResultsForImages();
		getSortResultsForAudio();
	}	
	
	private void getSortResultsForRefrecnceArticle() throws Exception{
		List<String> sortResults = new ArrayList<String>();
		int count = new TextLabel(properties.get("reference_searchResults_xpath")).getXpathCount();
		for(int resultRow=1; resultRow<=count; resultRow++){
			sortResults.add(new TextLabel(reference_results_xpath.replace("@INDEX@", ""+resultRow)).getLabelText());
		}
		displayGroupResultsMap.put("Reference Articles", sortResults);
	}
	
	private void getSortResultsForNews() throws Exception{
		List<String> sortResults = new ArrayList<String>();
		int count = new TextLabel(properties.get("news_searchResults_xpath")).getXpathCount();
		for(int resultRow=1; resultRow<=count; resultRow++){
			sortResults.add(new TextLabel(news_results_xpath.replace("@INDEX@", ""+resultRow)).getLabelText());
		}
		displayGroupResultsMap.put("News", sortResults);
	}
	
	private void getSortResultsForMagazines() throws Exception{
		List<String> sortResults = new ArrayList<String>();
		int count = new TextLabel(properties.get("magazines_searchResults_xpath")).getXpathCount();
		for(int resultRow=1; resultRow<=count; resultRow++){
			sortResults.add(new TextLabel(magazines_results_xpath.replace("@INDEX@", ""+resultRow)).getLabelText());
		}
		displayGroupResultsMap.put("Magazines", sortResults);
	}	
	
	private void getSortResultsForAcademicJournals() throws Exception{
		List<String> sortResults = new ArrayList<String>();
		int count = new TextLabel(properties.get("journals_searchResults_xpath")).getXpathCount();
		for(int resultRow=1; resultRow<=count; resultRow++){
			sortResults.add(new TextLabel(journals_results_xpath.replace("@INDEX@", ""+resultRow)).getLabelText());
		}
		displayGroupResultsMap.put("Academic Journals", sortResults);
	}		

	private void getSortResultsForImages() throws Exception{
		List<String> sortResults = new ArrayList<String>();
		int count = new TextLabel(properties.get("images_searchResults_xpath")).getXpathCount();
		for(int resultRow=1; resultRow<=count; resultRow++){
			sortResults.add(new TextLabel(images_results_xpath.replace("@INDEX@", ""+resultRow)).getLabelText());
		}
		displayGroupResultsMap.put("Images", sortResults);
	}			

	private void getSortResultsForAudio() throws Exception{
		List<String> sortResults = new ArrayList<String>();
		int count = new TextLabel(properties.get("audio_searchResults_xpath")).getXpathCount();
		for(int resultRow=1; resultRow<=count; resultRow++){
			sortResults.add(new TextLabel(audio_results_xpath.replace("@INDEX@", ""+resultRow)).getLabelText());
		}
		displayGroupResultsMap.put("Audio", sortResults);
	}		
	
	private void getSortResultsFromDataBase(String displayGroupName, String sortBy) throws IOException{
        if(sortBy.equalsIgnoreCase("DocTitleAnnotations")){
        	sortResultsFromDataBaseMap.put(displayGroupName, OceanDatabaseReadFile.readResultsFromFile(properties.get("all"),properties.get("reference"),properties.get("relevance"),properties.get("docTitleAnnotation"),this.searchString));
        }else if(sortBy.equalsIgnoreCase("DocumentIds")){
        	sortResultsFromDataBaseMap.put(displayGroupName, OceanDatabaseReadFile.readResultsFromFile(properties.get("all"),properties.get("reference"),properties.get("relevance"),properties.get("documentId"),this.searchString));
        }else if(sortBy.equalsIgnoreCase("DocumentTitles")){
        	sortResultsFromDataBaseMap.put(displayGroupName, OceanDatabaseReadFile.readResultsFromFile(properties.get("all"),properties.get("reference"),properties.get("relevance"),properties.get("documentTitle"),this.searchString));
        }else if(sortBy.equalsIgnoreCase("DocumentTypes")){
        	sortResultsFromDataBaseMap.put(displayGroupName, OceanDatabaseReadFile.readResultsFromFile(properties.get("all"),properties.get("reference"),properties.get("relevance"),properties.get("docTypes"),this.searchString));
        }else if(sortBy.equalsIgnoreCase("PublicationDates")){
        	sortResultsFromDataBaseMap.put(displayGroupName, OceanDatabaseReadFile.readResultsFromFile(properties.get("all"),properties.get("reference"),properties.get("relevance"),properties.get("publicationDate"),this.searchString));
        }else if(sortBy.equalsIgnoreCase("RelevanceWts")){
        	sortResultsFromDataBaseMap.put(displayGroupName, OceanDatabaseReadFile.readResultsFromFile(properties.get("all"),properties.get("reference"),properties.get("relevance"),properties.get("relevance"),this.searchString));
        }
	}
	
	private boolean validateResults(){
		return  verifyResults(displayGroupResultsMap.get(properties.get("reference_articles")),sortResultsFromDataBaseMap.get(properties.get("reference_articles"))) &&
				verifyResults(displayGroupResultsMap.get(properties.get("news")),sortResultsFromDataBaseMap.get(properties.get("news"))) &&
				verifyResults(displayGroupResultsMap.get(properties.get("magazines")),sortResultsFromDataBaseMap.get(properties.get("magazines"))) &&
				verifyResults(displayGroupResultsMap.get(properties.get("academic_journals")),sortResultsFromDataBaseMap.get(properties.get("academic_journals"))) &&
				verifyResults(displayGroupResultsMap.get(properties.get("images")),sortResultsFromDataBaseMap.get(properties.get("images"))) &&
				verifyResults(displayGroupResultsMap.get(properties.get("audio")),sortResultsFromDataBaseMap.get(properties.get("audio")));
	}	
	
	private boolean verifyResults(List<String> displayGroupResultsList, List<String> displayGroupResultsFromDataBase){
		
		if((displayGroupResultsList != null && displayGroupResultsFromDataBase != null) &&		
		   (displayGroupResultsList.size() == displayGroupResultsFromDataBase.size())){
							
			for(int index = 0; index < displayGroupResultsList.size(); index++){
				if(!displayGroupResultsList.get(index).equals(displayGroupResultsFromDataBase.get(index))){
					return  false;
				}  
			}
		}
		return true;
	}
	
}
