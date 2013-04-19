package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import webPageElements4Testing.CheckBox;
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class SubmitAdvancedSearchDisplay extends BasePageContainer{
	
	String[] displayGroups = { "Academic Journals", "Audio", "Images",
			"Magazines", "News", "Primary Sources", "Reference", "Statistics",
			"Videos", "Viewpoints", "Websites", };
	String[] contentLevels = {"Basic", "Intermediate", "Advanced"};
	
	public SubmitAdvancedSearchDisplay() throws Exception {
		super();
	}
	
	public boolean verifyAdvancedSearchOptionIsAccessible() throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		return new TextLabel(properties.get("advanced_search_page")).isPresent();
	}
	
	public boolean verifyNoErrorMsgIsisplayed(){
		return !new TextLabel(properties.get("error_stacktrace")).isPresent();
	}
	
	public boolean verifyLimitToCheckboxesAreUnchecked(){
		for(int i = 1; i <= new TextLabel(properties.get("limitTo_checkboxes")).getXpathCount(); i++){
			if(new CheckBox(properties.get("limitTo_checkboxes") + "["+ i + "]/input").isChecked())
				return false;
		}
		return true;
	}
	
	public boolean verifyDisplayGroupCheckboxesPresent(){
		for(int i = 1; i <= new TextLabel(properties.get("contentType_checkboxes")).getXpathCount(); i++){
			if(!(new CheckBox(properties.get("contentType_checkboxes") + "["+ i + "]/input").isPresent()
				&& new TextLabel(properties.get("contentType_checkboxes") + "["+ i + "]/label").getLabelText().equals(displayGroups[i-1])))
				return false;
		}
		return true;
	}
	
	public boolean verifyDocTypeOptions(){
		List<String> docTypes = new ArrayList<String>(Arrays.asList(new DropDownBox(properties.get("documentType_dropdrown")).getSelectOptions()));
		//TODO:
		return docTypes.contains("Transcript");
	}
	
	public boolean verifyContentLevelCheckboxesPresent(){
		for(int i = 1; i <= new TextLabel(properties.get("contentLevel_checkboxes")).getXpathCount(); i++){
			if(!(new CheckBox(properties.get("contentLevel_checkboxes") + "["+ i + "]/input").isPresent()
				&& new TextLabel(properties.get("contentLevel_checkboxes") + "["+ i + "]/label").getLabelText().equals(contentLevels[i-1])))
				return false;
		}
		return true; 
	}
	
	public boolean verifyTwoSearchButtonsPresent() {
		return new PageButton(properties.get("top_search_button")).isPresent()
				&& new PageButton(properties.get("bottom_search_button")).isPresent();
	}
	
	public boolean verifyLexileScoreInstructions(){
		return new TextLabel(properties.get("lexileScore_instruction")).isPresent();
	}
	
	public boolean dateDropdownBoxesPresent() {
		return new DropDownBox(properties.get("fromDate_day")).isPresent()
				&& new DropDownBox(properties.get("fromDate_month")).isPresent()
				&& new DropDownBox(properties.get("fromDate_year")).isPresent()
				&& new DropDownBox(properties.get("toDate_day")).isPresent()
				&& new DropDownBox(properties.get("toDate_month")).isPresent()
				&& new DropDownBox(properties.get("toDate_year")).isPresent();
	}
	
	public boolean isSearchByIndexWorking(String searchTerm) throws Exception{
		new TextBox(properties.get("searchFor_textbox")).type(searchTerm);
		new DropDownBox(properties.get("indexes_dropdown1")).select("Document Title");
		new PageButton(properties.get("bottom_search_button")).click();
		return verifyResults(searchTerm);
	}
	
	private boolean verifyResults(String searchTerm){
		for(int i = 1; i <= new TextLabel(properties.get("displayGroups_searchResultsPage")).getXpathCount(); i++){
			for(int j = 1; j <= new TextLabel(properties.get("displayGroups_searchResultsPage")+"[" +i +"]"+ properties.get("searchResults")).getXpathCount(); j++){
				String docTitle = new TextLabel(properties.get("displayGroups_searchResultsPage")+ "[" +i+ "]" +properties.get("searchResults")+"["+j+"]/h3/a").getLabelText().toLowerCase();
				if(!docTitle.contains(searchTerm))
					return false;
			}
		}
		return true;
	}
}
