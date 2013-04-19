package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import webPageElements4Testing.CheckBox;
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class SubmitAdvancedSearchQuery extends BasePageContainer{

	Map<String, String> indexesMap;
	String[] displayGroups = {"news","K12-Reference","statistics","viewpoints","magazines"};
	List<Integer> resultscountInDB = new ArrayList<Integer>();
	List<Integer> resultscountInAppl = new ArrayList<Integer>();
	
	public SubmitAdvancedSearchQuery() throws Exception {
		super();
		loadIndexesMap();
	}
	
	private void loadIndexesMap(){
		indexesMap = new HashMap<String, String>();
		indexesMap.put("KE", "Keyword");
		indexesMap.put("SU", "Subject");
		indexesMap.put("PU", "Publication Title");
		indexesMap.put("PB", "Publisher Name");
		indexesMap.put("TI", "Document Title");
		indexesMap.put("AU", "Author");
		indexesMap.put("TX", "Entire Document");
	}
	
	public boolean verifyDefaultIndexIsKeyword() throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		return new DropDownBox(properties.get("indexes_dropdown1"))
				.getSelectedOptionLabel().equals("Keyword");
	}

	public boolean verifyUserCanSearchMultipleTermsUsingConnectors() throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		new Link(properties.get("addNewRow")).clickWithoutWait();
		List<String> operators = new ArrayList<String>(Arrays.asList(new DropDownBox(properties.get("operator1")).getSelectOptions()));
		return new DropDownBox(properties.get("indexes_dropdown1")).getSelectOptions().length > 1
				&& operators.contains("And")
				&& operators.contains("Or")
				&& operators.contains("Not");
	}
	
	public boolean verifyResultsWithCombinationOfBooleanOperators(String index1,
			String searchTerm1, String connector1, String index2,
			String searchTerm2, String connector2, String index3,
			String searchTerm3) throws Exception{
		
		searchWithCombinationOfBooleanOperators(index1,
				searchTerm1, connector1, index2,
				searchTerm2, connector2, index3,
				searchTerm3);
		
		for (String displayGroup : displayGroups) {
			resultscountInDB.add(Integer.parseInt(OceanDatabaseReadFile
					.readAdvancedResultsCountFromFile(index1, searchTerm1,
							connector1.toLowerCase(), index2, searchTerm2,
							connector2.toLowerCase(), index3, searchTerm3,
							properties.get("all"), properties.get(displayGroup), "").get(0)));
		}
		return verifyResultsCountWithDB();
	}
	
	public boolean verifyIndexesInDropdown() throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		List<String> indexes = new ArrayList<String>(Arrays.asList(new DropDownBox(properties.get("indexes_dropdown1")).getSelectOptions()));
		Iterator<String> iter = indexesMap.values().iterator();
		while(iter.hasNext()){
			if(!indexes.contains(iter.next()))
				return false;
		}
		return true;
	}
	
	public boolean verifyNewRowIsAdded(){
		new Link(properties.get("addNewRow")).clickWithoutWait();
		return new DropDownBox(properties.get("indexes_dropdown1")).isPresent() &&
			new DropDownBox(properties.get("operator1")).isPresent();
	}
	
	public boolean verifyTwoSearchButtonsPresent() {
		return new PageButton(properties.get("top_search_button")).isPresent()
				&& new PageButton(properties.get("bottom_search_button")).isPresent();
	}
	
	public boolean verifyProximityOperatorSearch(String searchTerm) throws Exception{
		new TextBox(properties.get("search_textbox1")).type(searchTerm);
		new DropDownBox(properties.get("indexes_dropdown1")).select(indexesMap.get("KE"));
		int dBCount = Integer.parseInt(OceanDatabaseReadFile
				.readResultsCountFromFile(properties.get("all"),
						displayGroups[1], properties.get("relevance"),
						searchTerm).get(0));
		new PageButton(properties.get("bottom_search_button")).clickAndWait();
		return getResultsCountFromAppl("reference") == dBCount;
	}
	
	public boolean verifyBreadCrumb() throws InterruptedException{
		Thread.sleep(3000);
		return new TextLabel(properties.get("breadcrumb_searchResults")).isPresent();
	}
	
	public boolean checkMsgIsDisplayedForMaxRows() throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		Link addRow = new Link(properties.get("addNewRow"));
		while(addRow.getLinkText().equals("Add row")){
			addRow.clickWithoutWait();
		}
		return addRow.getLinkText().equals("Maximum Reached");
	}
	
	public boolean verifySearchResultsPageDisplayed() throws Exception{
		new Link(properties.get("advanced_search_link")).click();
		new TextBox(properties.get("search_textbox1")).type("war");
		new PageButton(properties.get("bottom_search_button")).clickAndWait();
		Thread.sleep(3000);
		return new TextLabel(properties.get("breadcrumb_searchResults")).isPresent();
	}
	
	public boolean verifyDatesInResults(String fromDate, String toDate) throws InterruptedException{
		String[] date1 = fromDate.split("-");
		new DropDownBox(properties.get("fromDate_day")).select(date1[0]);
		new DropDownBox(properties.get("fromDate_month")).select(date1[1]);
		new DropDownBox(properties.get("fromDate_year")).select(date1[2]);
		
		String[] date2 = fromDate.split("-");
		new DropDownBox(properties.get("toDate_day")).select(date2[0]);
		new DropDownBox(properties.get("toDate_month")).select(date2[1]);
		new DropDownBox(properties.get("toDate_year")).select(date2[2]);
		new PageButton(properties.get("bottom_search_button")).clickAndWait();
		return verifyDates(fromDate, toDate);
	}
	
	public boolean checkResultsDisplayedForSameToAndFromDate(){
		new DropDownBox(properties.get("fromDate_month")).select("January");
		new DropDownBox(properties.get("fromDate_day")).select("10");
		new DropDownBox(properties.get("fromDate_year")).select("2000");
		new DropDownBox(properties.get("toDate_month")).select("January");
		new DropDownBox(properties.get("toDate_day")).select("10");
		new DropDownBox(properties.get("toDate_year")).select("2000");
		new PageButton(properties.get("bottom_search_button")).clickAndWait();
		return new TextLabel(properties.get("displayGroups_searchResultsPage")).isPresent(); 
	}
	
	public boolean verifyDocTypesInResults(String docType) throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		new DropDownBox(properties.get("documentType_dropdrown")).select(docType);
		new PageButton(properties.get("add_docType_button")).click();
		new PageButton(properties.get("bottom_search_button")).clickAndWait();
		if(isViewAllLinkPresent()){
			for(int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++){
				if(!new TextLabel(properties.get("viewAll_searchResults")+ "[" +i+ "]/td[3]").getLabelText().contains(docType))
					return false;
			}
		}
		return true;
	}
	
	public boolean checkOnlyOneDisplayGroupDisplayed(String displayGroup) throws InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		new CheckBox(properties.get(displayGroup +"_checkbox")).click();
		new PageButton(properties.get("bottom_search_button")).clickAndWait();
		return new TextLabel(properties.get("displayGroups_searchResultsPage")).getXpathCount() == 1 &&
			new TextLabel(properties.get(displayGroup+"_searchResults_xpath")).isPresent();
	}
	
	public boolean verifyResultsForContentGroup(String[] contentGroups) throws NumberFormatException, IOException, InterruptedException{
		new Link(properties.get("advanced_search_link")).click();
		clearLists();
		for(String contentGroup : contentGroups){
			new CheckBox(properties.get(contentGroup+"_checkbox")).click();
			resultscountInDB.add(Integer.parseInt(OceanDatabaseReadFile
					.readResultsCountFromFile(properties.get("all"),
							contentGroup, properties.get("relevance"), "").get(
							0)));
		}
		new PageButton(properties.get("bottom_search_button")).clickAndWait();
		for(String contentGroup : contentGroups){
			resultscountInAppl.add(getResultsCountFromAppl(contentGroup));
		}
		return verifyResultsCountWithDB();
	}
	
	public boolean verifyWarningMsgDisplayedForLexileScore() throws Exception{
		new TextBox(properties.get("lexileScore_min")).type(properties.get("maximum_lexileScore"));
		new TextBox(properties.get("lexileScore_max")).type(properties.get("minimum_lexileScore"));
		return new TextLabel("lexileScore_warningMsg").isPresent();
	}
	
	public boolean verifyWarningMsgDisplayedForNonIntegerLexileScore() throws Exception{
		new TextBox(properties.get("lexileScore_min")).type(properties.get("maximum_lexileScore"));
		return new TextLabel("lexileScore_warningMsg").isPresent();
	}
	
	private void clearLists(){
		resultscountInAppl.clear();
		resultscountInDB.clear();
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
	
	private void searchWithCombinationOfBooleanOperators(String index1,
			String searchTerm1, String connector1, String index2,
			String searchTerm2, String connector2, String index3,
			String searchTerm3) throws Exception{
		new Link(properties.get("advanced_search_link")).click();
		new DropDownBox(properties.get("indexes_dropdown1")).select(indexesMap.get(index1));
		new TextBox(properties.get("search_textbox1")).type(searchTerm1);
		
		new Link(properties.get("addNewRow")).clickWithoutWait();
		new DropDownBox(properties.get("operator1")).select(connector1);
		new DropDownBox(properties.get("indexes_dropdown2")).select(indexesMap.get(index2));
		new TextBox(properties.get("search_textbox2")).type(searchTerm2);
		
		if(!(connector2.isEmpty() && searchTerm3.isEmpty() && searchTerm3.isEmpty())){
			new Link(properties.get("addNewRow")).clickWithoutWait();
			new DropDownBox(properties.get("operator2")).select(connector2);
			new DropDownBox(properties.get("indexes_dropdown3")).select(indexesMap.get(index3));
			new TextBox(properties.get("search_textbox3")).type(searchTerm3);
		}
		new PageButton(properties.get("bottom_search_button")).clickAndWait();
		resultscountInAppl = getResultsCountFromAppl();
	}
	
	//KE war OR SU soldiers AND TX weapons NOT TI peace
	//TY Article and ACID (14223820)
	public boolean verifyResultsWithMultipleCombinations(String limitTo, String[] documentTypes, String fromDate, String toDate, String displayGroup, String searchQuery, String limitQuery) throws Exception{
		String[] findElements = searchQuery.split(" ");
		new Link(properties.get("advanced_search_link")).click();
		new DropDownBox(properties.get("indexes_dropdown1")).select(indexesMap.get(findElements[0]));
		new TextBox(properties.get("search_textbox1")).type(findElements[1]);
		if(findElements.length == 5){
			selectSearchQueryElements(1, findElements[2], findElements[3], findElements[4]);
		}else if(findElements.length == 8){
			selectSearchQueryElements(1, findElements[2], findElements[3], findElements[4]);
			selectSearchQueryElements(2, findElements[5], findElements[6], findElements[7]);
		}else if(findElements.length == 11){
			selectSearchQueryElements(1, findElements[2], findElements[3], findElements[4]);
			selectSearchQueryElements(2, findElements[5], findElements[6], findElements[7]);
			selectSearchQueryElements(3, findElements[8], findElements[9], findElements[10]);
		}else if(findElements.length == 14){
			selectSearchQueryElements(1, findElements[2], findElements[3], findElements[4]);
			selectSearchQueryElements(2, findElements[5], findElements[6], findElements[7]);
			selectSearchQueryElements(3, findElements[8], findElements[9], findElements[10]);
			selectSearchQueryElements(4, findElements[11], findElements[12], findElements[13]);
		}
		selectLimitQueryElements(limitTo, documentTypes, fromDate, toDate);	
		new PageButton(properties.get("bottom_search_button")).clickAndWait();
		return verifyQueryResultsCountWithDB(displayGroup, searchQuery, limitQuery);
	}
	
	private void selectSearchQueryElements(int rowNo, String connector, String index, String searchTerm) throws Exception{
		new Link(properties.get("addNewRow")).clickWithoutWait();
		new DropDownBox("//select[@id='operator_"+rowNo+"']").select(connector);
		new DropDownBox("//select[@id='index_"+rowNo+"']").select(indexesMap.get(index));
		new TextBox("//input[@id='term_"+rowNo+"']").type(searchTerm);
	}
	
	private void selectLimitQueryElements(String limitTo, String[] documentTypes, String fromDate, String toDate) throws InterruptedException{
		if(!limitTo.isEmpty())
			new CheckBox(properties.get(limitTo+"_checkbox")).click();
		selectDates(fromDate, toDate);
		selctDocumentTypes(documentTypes);
	}
	
	private void selectDates(String fromDate, String toDate){
		if(!fromDate.isEmpty()){
		String[] date1 = fromDate.split("-");
			new DropDownBox(properties.get("fromDate_day")).select(date1[0]);
			new DropDownBox(properties.get("fromDate_month")).select(date1[1]);
			new DropDownBox(properties.get("fromDate_year")).select(date1[2]);
		}
		if(!fromDate.isEmpty()){
			String[] date2 = fromDate.split("-");
			new DropDownBox(properties.get("toDate_day")).select(date2[0]);
			new DropDownBox(properties.get("toDate_month")).select(date2[1]);
			new DropDownBox(properties.get("toDate_year")).select(date2[2]);
		}
	}
	
	private void selctDocumentTypes(String[] docTypes) throws InterruptedException{
		if(docTypes.length > 0){
			new DropDownBox(properties.get("documentType_dropdrown")).select(docTypes);
			new Link(properties.get("add_docType_button")).click();
		}
	}
	
	private boolean verifyQueryResultsCountWithDB(String displayGroup, String searchQuery, String limitQuery) throws IOException{
		List<String> resultsCountInDB = OceanDatabaseReadFile.readQueryResultsCountFromFile(searchQuery, limitQuery);
		int resultsCountInAppl = getCountFromViewAllLabel(new TextLabel(properties
				.get(displayGroup + "_viewAll")).getLabelText());
		return resultsCountInAppl == resultsCountInDB.indexOf(0);
	}
	
	private int getResultsCountFromAppl(String displayGroup)
			throws InterruptedException {
		// If displaygroup not present
		if (!new TextLabel(properties.get(displayGroup + "_searchResults_xpath")).isPresent()) {
			return 0;
		} else {
			// if viewAll link present
			if (new TextLabel(properties.get(displayGroup + "_viewAll")).isPresent())
				return getCountFromViewAllLabel(new TextLabel(properties
						.get(displayGroup + "_viewAll")).getLabelText());
			else
				return new TextLabel(properties.get(displayGroup+ "_searchResults_xpath")).getXpathCount();
		}
	}
	
	private List<Integer> getResultsCountFromAppl() throws InterruptedException{
		for(int i = 0; i < displayGroups.length; i++){
			//If displaygroup not present
			if(!new TextLabel(properties.get(displayGroups[i]+"_searchResults_xpath")).isPresent()){
				resultscountInAppl.add(0);
			}else{
				//if viewAll link present
				if(new TextLabel(properties.get(displayGroups[i]+"_viewAll")).isPresent())
					resultscountInAppl.add(getCountFromViewAllLabel(new TextLabel(properties.get(displayGroups[i]+"_viewAll")).getLabelText()));
				else
					resultscountInAppl.add(new TextLabel(properties.get(displayGroups[i]+"_searchResults_xpath")).getXpathCount());
			}
		}
		return resultscountInAppl;
	}
	
	private boolean verifyResultsCountWithDB() throws InterruptedException{
		for(int i = 0; i < displayGroups.length; i++){
			if(!(resultscountInAppl.get(i) == resultscountInDB.get(i)))
				return false;
		}
		return true;
	}
	
	private int getCountFromViewAllLabel(String viewAllLabel){
		return Integer.parseInt(viewAllLabel.split(" ")[2].replace(",", ""));
	}
	
	private List<Date> getDatesFromApplication() throws InterruptedException{
		List<Date> datesInAppl = new ArrayList<Date>();
		String[] publicationDate;
		for(int i = 1; i <= new TextLabel(properties.get("displayGroups_searchResultsPage")).getXpathCount(); i++){
			for(int j = 1; j <= new TextLabel(properties.get("displayGroups_searchResultsPage")+"[" +i +"]"+ properties.get("searchResults")).getXpathCount(); j++){
				publicationDate = new TextLabel(properties.get("displayGroups_searchResultsPage")+ "[" +i+ "]" +properties.get("pubDate")).getLabelText().split("-");
				datesInAppl.add(new Date(publicationDate[2] + "-" + (getMonthInWords(publicationDate[1]))+ publicationDate[0]));
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
	
	private boolean verifyDates(String fromDate, String toDate) throws InterruptedException{
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
}

