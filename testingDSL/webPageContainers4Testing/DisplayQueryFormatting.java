package webPageContainers4Testing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webPageElements4Testing.CheckBox;
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class DisplayQueryFormatting extends BasePageContainer{

	public static final String KEYWORD = "KEYWORD= ";
	public static final String LIMITS = " LIMITS:";
	public static final String DOCUMENT_TYPE = " DOCUMENT TYPE = ";
	public static final String PUBLICATION_TITLE = " PUBLICATION TITLE = ";
	public static final String SUBJECT = " SUBJECT = ";
	public static final String CONTENT_TYPE = " CONTENT TYPE = ";
	public static final String CONTENT_LEVEL = " CONTENT LEVELS = ";
	public static final String PUBLICATION_DATE = " PUBLICATION DATE = ";
	public static final String FULLTEXT_DOCUMENTS = " FULLTEXT DOCUMENTS = ";
	public static final String LEXILE_SCORE = " LEXILE SCORE = ";
	
	Map<String, String> indexesMap;
	private TextLabel searchResultsMsg;
	private String basicSearchTerm;
	
	public DisplayQueryFormatting(String basicSearchTerm) throws Exception {
		super();
		searchResultsMsg = new TextLabel(properties.get("searchResults_msg_xpath"));
		this.basicSearchTerm = basicSearchTerm;
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
	
	public boolean verifyMsgForKeywordSearch(){
		return new TextLabel(properties.get("searchResults_msg_xpath")).getLabelText().equals(properties.get("searchResults_msg") + KEYWORD + basicSearchTerm);
	}
	
	public boolean verifyMsgWithDateCombinationSearch(String keyword, String fromMonth, String fromDate, String fromYear, String toMonth, String toDate, String toYear) throws Exception{
		new Link(properties.get("advanced_search_link")).click();
		new TextBox(properties.get("search_textbox1")).type(keyword);
		new CheckBox(properties.get("fullTextDocuments_checkbox")).click();
		new DropDownBox(properties.get("fromDate_month")).select(fromMonth);
		new DropDownBox(properties.get("fromDate_day")).select(fromDate);
		new DropDownBox(properties.get("fromDate_year")).select(fromYear);
		new DropDownBox(properties.get("toDate_month")).select(toMonth);
		new DropDownBox(properties.get("toDate_day")).select(toDate);
		new DropDownBox(properties.get("toDate_year")).select(toYear);
		new PageButton(properties.get("bottom_search_button")).clickAndWait();
		String dateFormat = getDateFormat(fromMonth, fromDate, fromYear, toMonth, toDate, toYear);
		return new TextLabel(properties.get("searchResults_msg_xpath")).getLabelText().equals(searchResultsMsg.getLabelText() + KEYWORD + keyword + LIMITS +
				FULLTEXT_DOCUMENTS + "Y" + "And" + PUBLICATION_DATE + dateFormat);
	}
	
	public boolean checkDateFormatInMsg(String fromMonth, String fromDate, String fromYear, String toMonth, String toDate, String toYear){
		return new TextLabel(properties.get("searchResults_msg_xpath")).getLabelText().contains(getDateFormat(fromMonth, fromDate, fromYear, toMonth, toDate, toYear));
	}
	
	public boolean verifyMsgExistsInViewAllNavigation() throws InterruptedException{
		String msg = searchResultsMsg.getLabelText();
		new Link(properties.get("displayGroups_searchResultsPage")+ "/div/a").click();
		String msgDisplayedAfterNavigation = searchResultsMsg.getLabelText();
		new Url().goBackToPreviousPage();
		return msgDisplayedAfterNavigation.equals(msg) && searchResultsMsg.getLabelText().equals(msg);
	}
	
	public boolean verifyMsgExistsAfterDetailedPageNavigation() throws InterruptedException{
		String msg = searchResultsMsg.getLabelText();
		new Link(properties.get("displayGroups_searchResultsPage")+ properties.get("searchResults")+"/h3/a").click();
		String msgDisplayedAfterNavigation = searchResultsMsg.getLabelText();
		new Url().goBackToPreviousPage();
		return msgDisplayedAfterNavigation.equals(msg);
	}
	
	public boolean verifyMsgChangesAfterSearchingByRelatedSubjects() throws InterruptedException{
		String msg = searchResultsMsg.getLabelText();
		new Link(properties.get("displayGroups_searchResultsPage")+properties.get("searchResults")).click();
		new Link(properties.get("relatedsubjectstab")).click();
		return !new TextLabel(properties.get("searchResults_msg_xpath")).getLabelText().equals(msg);
	}
	
	public boolean verifyMsgAfterLimitBySearch() throws InterruptedException{
		new Link(properties.get("displayGroups_searchResultsPage")+ "/div/a").click();
		String pubTitle = new Link(properties.get("limit_searchBy_pubTitles") + "/ul/li/a").getLinkText();
		new Link(properties.get("limit_searchBy_pubTitles") + "/ul/li/a").click();
		String subject = new Link(properties.get("limit_searchBy_subjects") + "/ul/li/a").getLinkText();
		new Link(properties.get("limit_searchBy_subjects") + "/ul/li/a").click();
		return new TextLabel(properties.get("")).getLabelText().equals(PUBLICATION_TITLE + pubTitle + SUBJECT + subject);
	}
	
	public boolean verifyMsgWithAllCombinationsOfInput(String keyword, String subject, String docType, String[] contentLevels, String lexileScore) throws Exception{
		new Link(properties.get("advanced_search_link")).click();
		
		new TextBox(properties.get("search_textbox1")).type(keyword);
		new DropDownBox(properties.get("indexes_dropdown1")).select(indexesMap.get("KE"));
		new Link(properties.get("addNewRow")).clickWithoutWait();
		new DropDownBox(properties.get("operator1")).select("Or");
		new DropDownBox(properties.get("indexes_dropdown2")).select(indexesMap.get("SU"));
		new TextBox(properties.get("search_textbox2")).type(subject);
		
		new DropDownBox(properties.get("documentType_dropdrown")).select(docType);
		new CheckBox(properties.get("basic_checkbox")).click();
		new TextBox(properties.get("lexileScore_min")).type(lexileScore);
		new PageButton(properties.get("add_docType_button")).click();
		
		String contentLevelString = "";
		for(String contentLevel : contentLevels){
			contentLevelString = contentLevelString + contentLevel +properties.get("and");
		}
		return searchResultsMsg.getLabelText().equals(properties.get("searchResults_msg") + KEYWORD + keyword + "Or" + SUBJECT + subject +
				LIMITS + DOCUMENT_TYPE + docType + "And" + CONTENT_LEVEL + contentLevelString + "And" + LEXILE_SCORE + lexileScore);
	}
	
	public boolean verifyMsgWithStopWordSearch(String searchTermWithStopword) throws Exception{
		return searchResultsMsg.getLabelText().equals(properties.get("searchResults_msg") + KEYWORD + searchTermWithStopword);
	}
	                            
	public boolean verifySearchMsgWithStopAndNaughtyWords(String searchTermWithNaughtyword) throws Exception{
		List<String> naughtyWordsList = new StopAndNaughtyWords().getNaughtyWordsInSentence(searchTermWithNaughtyword);
		String searchWords = "";
		String[] searchTerm = searchTermWithNaughtyword.split(" ");
		for(String str : searchTerm){
			if(!naughtyWordsList.contains(str))
				searchWords = searchWords + str + " ";
		}
		return searchResultsMsg.getLabelText().equals(properties.get("searchResults_msg") + KEYWORD + searchWords);
	}
	
	public boolean verifyMsgExistsAfterPortalPageNavigation() throws InterruptedException{
		String msg = searchResultsMsg.getLabelText();
		new Link(properties.get("displayGroups_searchResultsPage")+ "/div[2]/a").click();
		String msgDisplayedAfterNavigation = searchResultsMsg.getLabelText();
		new Url().goBackToPreviousPage();
		return msgDisplayedAfterNavigation.equals(msg) && searchResultsMsg.getLabelText().equals(msg);
	}
	
	public boolean verifyMsgAfterSearchWithinResults(String searchTerm) throws Exception{
		String msg = searchResultsMsg.getLabelText();
		new Link(properties.get("displayGroups_searchResultsPage")+ "/div/a").click();
		new TextBox(properties.get("search_within_results")).type(searchTerm);
		new PageButton(properties.get("search_within_submit")).click();
		return searchResultsMsg.getLabelText().equals(msg + "FULL TEXT " + searchTerm);
	}
	
	public boolean verifyMsgDisplayedAfterSearchFromPortalViewAll(String searchTerm) throws Exception{
		new Link(properties.get("displayGroups_searchResultsPage")+ "/div[2]/a").click();
		new TextBox(properties.get("searchBox")).type(searchTerm);
		new PageButton(properties.get("findButton")).clickAndWait();
		return searchResultsMsg.getLabelText().equals(properties.get("searchResults_msg") + KEYWORD + searchTerm);
	}
	
	private String getDateFormat(String fromMonth, String fromDate, String fromYear, String toMonth, String toDate, String toYear){
		String dateFormat = fromYear + getMonthInNumber(fromMonth) + fromDate
				+ " - " + toYear + getMonthInNumber(toMonth) + toDate;
		return dateFormat;
	}
	
	private static String getMonthInNumber(String monthName){
		Map<String, String> map = new HashMap<String, String>();
		map.put("January","01");
		map.put("February","02");
		map.put("March","03");
		map.put("April","04");
		map.put("May","05");
		map.put("June","06");
		map.put("July","07");
		map.put("August","08");
		map.put("September","09");
		map.put("October","10");
		map.put("November","11");
		map.put("December","12");
		return map.get(monthName);
	}

}

