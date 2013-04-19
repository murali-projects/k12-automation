package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webPageElements4Testing.CheckBox;
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.RadioButton;
import webPageElements4Testing.TextLabel;

public class AdvancedSearch extends BasePageContainer{
	
	public AdvancedSearch() throws Exception {
		super();
	}
	
	public boolean verifyAdvancedSearchPageIsDisplayed() throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		return new TextLabel(properties.get("advanced_search_page")).isPresent();
	}
	
	public boolean verifyCheckboxesAvailableToLimitResults() {
		return new TextLabel(properties.get("fullTextDocuments_checkbox")).isPresent()
				&& new TextLabel(properties.get("peerReviewedPublications_checkbox")).isPresent();
	}
	
	public boolean verifyNewRowIsAdded() throws InterruptedException{
		new Link(properties.get("addNewRow")).clickWithoutWait();
		return new TextLabel(properties.get("newRow_xpath")).isPresent();
	}
	
	public boolean verifyContentTypeCheckboxesAreUnchecked(){
		for(int i = 1; i <= new TextLabel(properties.get("contentType_checkboxes")).getXpathCount(); i++){
			if(new CheckBox(properties.get("contentType_checkboxes") + "["+ i + "]/input").isChecked())
				return false;
		}
		return true;
	}
	
	public boolean verifyResultsAfterSearchingByDoctype(String docType) throws InterruptedException{
		new DropDownBox(properties.get("documentType_dropdown")).select(docType);
		new PageButton(properties.get("advanced_search_button")).click();
		return verifyDocTypesInResults(docType);
	}
	
	private boolean verifyDocTypesInResults(String docType) throws InterruptedException{
		if(isViewAllLinkPresent()){
			for(int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++){
				if(!new TextLabel(properties.get("viewAll_searchResults")+ "[" +i+ "]/td[3]").getLabelText().contains(docType))
					return false;
			}
		}
		return true;
	}
	
	private boolean isViewAllLinkPresent() throws InterruptedException{
		for(int i = 1; i <= new TextLabel(properties.get("displayGroups_searchResultsPage")).getXpathCount(); i++){
			if(new Link(properties.get("displayGroups_searchResultsPage") + "[" +i+ "]/a").isPresent()){
				new Link(properties.get("displayGroups_searchResultsPage") + "[" +i+ "]/a").click();
				return true;
			}
		}
		return false;
	}
	
	public boolean verifyResultsAfterSearchingByContentType(String[] contentTypes) throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		for(String contentType : contentTypes){
			new CheckBox(properties.get(contentType+ "_checkboxes")).click();
		}
		new PageButton(properties.get("advanced_search_button")).click();
		return new TextLabel(properties.get(contentTypes[0])+ "_label").isPresent();
	}
	
	public boolean verifyCheckboxesPresentForAllContentTypes() throws InterruptedException{
		for(int i = 1; i <= new TextLabel(properties.get("contentType_checkboxes")).getXpathCount(); i++){
			if(! new CheckBox(properties.get("contentType_checkboxes")+ "[" +i+ "]/input").isPresent())
				return false;
		}
		return true;
	}
	
	public boolean verifyPublicationDatesDropdownBoxesPresent() {
		return new DropDownBox(properties.get("fromDate_day")).isPresent()
				&& new DropDownBox(properties.get("fromDate_month")).isPresent()
				&& new DropDownBox(properties.get("fromDate_year")).isPresent()
				&& new DropDownBox(properties.get("toDate_day")).isPresent()
				&& new DropDownBox(properties.get("toDate_month")).isPresent()
				&& new DropDownBox(properties.get("toDate_year")).isPresent();
	}
	
	public boolean verifyPublicationDatesRadioButtonsPresent() {
		return new RadioButton(properties.get("allDates_radioButton")).isPresent()
				&& new RadioButton(properties.get("on_radioButton")).isPresent()
				&& new RadioButton(properties.get("before_radioButton")).isPresent()
				&& new RadioButton(properties.get("after_radioButton")).isPresent()
				&& new RadioButton(properties.get("between_radioButton")).isPresent();
	}
	
	public boolean verifyResultsAfterSelectingDates(String dateStr, String timingRadioButton) throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		new RadioButton(properties.get("before_radioButton")).click();
		String[] date = dateStr.split("-");
		new DropDownBox(properties.get("fromDate_day")).select(date[0]);
		new DropDownBox(properties.get("fromDate_month")).select(date[1]);
		new DropDownBox(properties.get("fromDate_year")).select(date[2]);
		if(timingRadioButton.equalsIgnoreCase("after"))
			return verifyDatesForAfterInResults(dateStr);
		else if(timingRadioButton.equalsIgnoreCase("before"))
			return verifyDatesForBeforeInResults(dateStr);
		else if(timingRadioButton.equalsIgnoreCase("on"))
			return verifyDatesForOnInResults(dateStr);
		return false;
	}
	
	private boolean verifyDatesForAfterInResults(String dateStr) throws InterruptedException{
		Date dateEntered = new Date();
		List<Date> datesList = getDatesFromApplication();
		for(Date date : datesList){
			if(! date.after(dateEntered)){
				return false;
			}
		}
		return true;
	}
	
	private boolean verifyDatesForBeforeInResults(String dateStr) throws InterruptedException{
		Date dateEntered = new Date();
		List<Date> datesList = getDatesFromApplication();
		for(Date date : datesList){
			if(! date.before(dateEntered)){
				return false;
			}
		}
		return true;
	}
	
	private boolean verifyDatesForOnInResults(String dateStr) throws InterruptedException{
		Date dateEntered = new Date();
		List<Date> datesList = getDatesFromApplication();
		for(Date date : datesList){
			if(! date.equals(dateEntered)){
				return false;
			}
		}
		return true;
	}
	
	private List<Date> getDatesFromApplication() throws InterruptedException{
		List<Date> datesInAppl = new ArrayList<Date>();
		String[] publicationDate;
		if(isViewAllLinkPresent()){
			for(int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++){
				publicationDate = new TextLabel(properties.get("viewAll_searchResults")+ "[" +i+ "]" +properties.get("pubDate")).getLabelText().split("-");
				datesInAppl.add(new Date(publicationDate[2] + "-" + (getMonthInWords(publicationDate[1]))+ publicationDate[0]));
			}
		}
		else{
			for(int i = 1; i <= new TextLabel(properties.get("displayGroups_searchResultsPage")).getXpathCount(); i++){
				for(int j = 1; j <= new TextLabel(properties.get("displayGroups_searchResultsPage")+"[" +i +"]"+ properties.get("searchResults")).getXpathCount(); j++){
					publicationDate = new TextLabel(properties.get("displayGroups_searchResultsPage")+ "[" +i+ "]" +properties.get("pubDate")).getLabelText().split("-");
					datesInAppl.add(new Date(publicationDate[2] + "-" + (getMonthInWords(publicationDate[1]))+ publicationDate[0]));
				}
			}
		}
		return datesInAppl;
	}
	
	private static String getMonthInWords(String monthInDigits){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("01", "January");
		map.put("02", "February");
		map.put("03", "March");
		map.put("04", "April");
		map.put("05", "May");
		map.put("06", "June");
		map.put("07", "July");
		map.put("08", "August");
		map.put("09", "September");
		map.put("10", "October");
		map.put("11", "November");
		map.put("12", "December");
		
		return map.get(monthInDigits);
	}
	
	public boolean verifyAfterClickingAllDatesRadioButton() throws InterruptedException {
		new Link(properties.get("advanced_search_link")).click();
		new RadioButton(properties.get("allDates_radioButton")).click();
		return !selenium.isEditable(properties.get("fromDate"))
				&& !selenium.isEditable(properties.get("toDate"));
	}
	
	public boolean verifyAfterClickingBetweenRadioButton() throws InterruptedException {
		new Link(properties.get("advanced_search_link")).click();
		new RadioButton(properties.get("allDates_radioButton")).click();
		return selenium.isEditable(properties.get("fromDate"))
				&& selenium.isEditable(properties.get("toDate"));
	}
	
	public boolean verifyAfterClickingRadioButtons() throws InterruptedException {
		new Link(properties.get("advanced_search_link")).click();
		new RadioButton(properties.get("on_radioButton")).click();
		if(selenium.isEditable(properties.get("toDate")))
			return false;
		new RadioButton(properties.get("after_radioButton")).click();
		if(selenium.isEditable(properties.get("toDate")))
			return false;
		new RadioButton(properties.get("before_radioButton")).click();
		if(selenium.isEditable(properties.get("toDate")))
			return false;
		return true;
	}
	
	public boolean verifyResultsAfterSelectingBetweenRadioButton(String fromDate, String toDate) throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		new RadioButton(properties.get("between_radioButton")).click();
		String[] date1 = fromDate.split("-");
		new DropDownBox(properties.get("fromDate_day")).select(date1[0]);
		new DropDownBox(properties.get("fromDate_month")).select(date1[1]);
		new DropDownBox(properties.get("fromDate_year")).select(date1[2]);
		
		String[] date2 = fromDate.split("-");
		new DropDownBox(properties.get("toDate_day")).select(date2[0]);
		new DropDownBox(properties.get("toDate_month")).select(date2[1]);
		new DropDownBox(properties.get("toDate_year")).select(date2[2]);
		return verifyDatesForBetween(fromDate, toDate);
	}
	
	private boolean verifyDatesForBetween(String fromDate, String toDate) throws InterruptedException{
		Date date1 = new Date(fromDate);
		Date date2 = new Date(toDate);
		List<Date> datesList = getDatesFromApplication();
		for(Date date : datesList){
			if(! date.after(date1) && !date.before(date2)){
				return false;
			}
		}
		return true;
	}
	
	public boolean isUserAbleToSelectDocumentType() throws InterruptedException{
		new DropDownBox(properties.get("documentType_dropdrown")).select("Essay");
		return selenium.isSomethingSelected(properties.get("documentType_dropdrown"));
	}
	
	public boolean isUserAbleToSelectMultipleContentLevels(){
		new CheckBox(properties.get("basic_checkbox")).click();
		new CheckBox(properties.get("intermediate_checkbox")).click();
		return new CheckBox(properties.get("basic_checkbox")).isChecked() &&
		new CheckBox(properties.get("intermediate_checkbox")).isChecked();
	}
	
	public boolean isUserAbleToSelectContentLevel(){
		new CheckBox(properties.get("advanced_checkbox")).click();
		return new CheckBox(properties.get("advanced_checkbox")).isChecked();
	}
	
	public boolean isUserAbleToSelectMinMaxLexileScores(){
		new DropDownBox(properties.get("lexileScore_dropdrown")).select(new String[]{properties.get("minimum_lexileScore"), properties.get("maximum_lexileScore")});
		return selenium.isSomethingSelected(properties.get("lexileScore_dropdrown"));
	}
	
	public boolean verifyResultsAfterSearchingByLexileScore(String lexileScore) throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		new DropDownBox(properties.get("lexileScore_dropdown")).select(lexileScore);
		new PageButton(properties.get("advanced_search_button")).click();
		return verifyLexileScoreInResults(lexileScore);
	}
	
	private boolean verifyLexileScoreInResults(String lexileScore) throws InterruptedException{
		if(isViewAllLinkPresent()){
			for(int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++){
				if(!new TextLabel(properties.get("viewAll_searchResults")+ "[" +i+ "]/td[4]/a/img").getAttribute("alt").equals(lexileScore))
					return false;
			}
		}
		return true;
	}

}
