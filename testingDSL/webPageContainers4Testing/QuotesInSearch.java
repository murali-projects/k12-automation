package webPageContainers4Testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import webPageElements4Testing.TextLabel;

public class QuotesInSearch extends BasePageContainer{
	
	private Properties stopWordProperties;
	
	private List<String> stopWordsList;
	private List<String> naughtyWordsList;	
	private List<String> wordsList;	
	private List<String> resultList;
	
	private final String STOP_WORDS_FILE = "properties/default.stopwords.properties";
	private final String NAUGHTY_WORDS_FILE = "properties/default.naughtywords.properties";	
	
	private String searchString;
	
	public QuotesInSearch() throws Exception { 
		super(); 
		//Creating Properties object to retrieve stopwords/naughty words from the properties file
		stopWordProperties = new Properties();
		stopWordsList = loadWordsList(STOP_WORDS_FILE);
		naughtyWordsList = loadWordsList(NAUGHTY_WORDS_FILE);	
	}
	
	public void search(String searchTerm) throws InterruptedException{
		this.searchString = searchTerm.trim();
	}	
	 
	private List<String> loadWordsList(String fileName) throws FileNotFoundException, IOException{
		wordsList = new ArrayList<String>();
		//Loads the given properties file
		stopWordProperties.load(new FileInputStream(new File(fileName)));
	
		//Adds values in the properties file to the list
        for(Object obj : stopWordProperties.keySet())
        	wordsList.add(stopWordProperties.getProperty(obj.toString()));
        
        return wordsList;		 
	}	

	private boolean isSearchResultsContainsSpecifiedText(String text,List<String> referenceArticleSearchList){
	    List<Boolean> isResultContainsSearchTextList = new ArrayList<Boolean>();
	    
	    //Retrieve result row one by one from the Reference Articles display group. 
	    for(String searchResult : referenceArticleSearchList){
	    	//check's searchResult contains search term.
	    	isResultContainsSearchTextList.add(containsIgnoreCase(searchResult,text));
	    }
	    //checks all the rows in Reference Articles display group contains search term.
	    return isResultContainsSearchTextList.contains(false) ?  false : true;			
	}	

	public boolean checkResultsContainsSearchTerm() throws Exception {
		 return validateSearchResultsForRefrecnceArticle() &&
				validateSearchResultsForNews() &&
				validateSearchResultsForMagazines() &&
				validateSearchResultsForAcademicJournals() &&
				validateSearchResultsForAcademicJournals() &&
				validateSearchResultsForImages() &&
				validateSearchResultsForAudio();
	}
	
	private boolean validateSearchResultsForRefrecnceArticle() throws Exception {
		if((new TextLabel(properties.get("reference_title_xpath")).getLabelText()).
				equals(properties.get("reference_title"))){
			return false;
		}else{
			this.searchString = this.searchString.replaceAll("\"", "").trim();
			return isSearchResultsContainsSpecifiedText(searchString,getSearchResults(properties.get("reference_searchResults_xpath")));
		}
	}	

	private boolean validateSearchResultsForNews() throws Exception {
		if((new TextLabel(properties.get("news_title_xpath")).getLabelText()).equals(properties.get("news_title"))){
			return false;
		}else{
			this.searchString = this.searchString.replaceAll("\"", "").trim();
			return isSearchResultsContainsSpecifiedText(searchString,getSearchResults(properties.get("news_searchResults_xpath")));
		}
	}
	
	private boolean validateSearchResultsForMagazines() throws Exception {
		if((new TextLabel(properties.get("magazines_title_xpath")).getLabelText()).equals(properties.get("magazines_title"))){
			return false;
		}else{
			this.searchString = this.searchString.replaceAll("\"", "").trim();
			return isSearchResultsContainsSpecifiedText(searchString,getSearchResults(properties.get("magazines_searchResults_xpath")));
		}
	}
	
	private boolean validateSearchResultsForAcademicJournals() throws Exception {
		if((new TextLabel(properties.get("journals_title_xpath")).getLabelText()).equals(properties.get("journals_title"))){
			return false;
		}else{
			this.searchString = this.searchString.replaceAll("\"", "").trim();
			return isSearchResultsContainsSpecifiedText(searchString,getSearchResults(properties.get("journals_searchResults_xpath")));
		}
	}
	
	private boolean validateSearchResultsForImages() throws Exception {
		if((new TextLabel(properties.get("images_title_xpath")).getLabelText()).equals(properties.get("images_title"))){
			return false;
		}else{
			this.searchString = this.searchString.replaceAll("\"", "").trim();
			return isSearchResultsContainsSpecifiedText(searchString,getSearchResults(properties.get("images_searchResults_xpath")));
		}
	}
	
	private boolean validateSearchResultsForAudio() throws Exception {
		if((new TextLabel(properties.get("audio_title_xpath")).getLabelText()).
				equals(properties.get("audio_title"))){
			return false;
		}else{
			this.searchString = this.searchString.replaceAll("\"", "").trim();
			return isSearchResultsContainsSpecifiedText(searchString,getSearchResults(properties.get("audio_searchResults_xpath")));
		}
	}		

	public boolean checkResultsNotHaveNaughtyWords() throws Exception{
		filterNaughtyWord();
		return checkResultsContainsSearchTerm();
	}

	public boolean checkResultsContainsQuotedText() throws Exception{
		return checkResultsContainsSearchTerm();
	}
	
	public boolean checkResultsContainsMultipleQuotedText() throws Exception{
		if((new TextLabel(properties.get("reference_title_xpath")).getLabelText()).
				equals(properties.get("reference_title"))){
			return false;
		}else{
		    return checkResultContainsQuotedText();
		}
	}
	
	private boolean checkResultContainsQuotedText() throws Exception{
		return checkResultContainsQuotedTextForRefrecnceArticle() &&
				checkResultContainsQuotedTextForNews() &&
				checkResultContainsQuotedTextForMagazines() &&
				checkResultContainsQuotedTextForAcademicJournals() &&
				checkResultContainsQuotedTextForImages() &&
				checkResultContainsQuotedTextForAudio();		
	}

	private boolean checkResultContainsQuotedTextForRefrecnceArticle() throws Exception{
	    List<Boolean> isResultContainsQuotedTextList = new ArrayList<Boolean>();
	    
	    //Retrieve result row from the Reference Articles display group.
	    for(String searchResult : getSearchResults(properties.get("reference_searchResults_xpath"))){
	    	isResultContainsQuotedTextList.add(verifyResultsHasQuotedTextOfSearchTerm(searchResult));	    }
	    
	    //checks all the rows in Reference Articles display group contains one of the quoted text.
	    return isResultContainsQuotedTextList.contains(false) ?  false : true;		
	}
	
	private boolean checkResultContainsQuotedTextForNews() throws Exception{
	    List<Boolean> isResultContainsQuotedTextList = new ArrayList<Boolean>();
	    
	    //Retrieve result row from the News Articles display group.
	    for(String searchResult : getSearchResults(properties.get("news_searchResults_xpath"))){
	    	isResultContainsQuotedTextList.add(verifyResultsHasQuotedTextOfSearchTerm(searchResult));
	    }
	    
	    //checks all the rows in News display group contains one of the quoted text.
	    return isResultContainsQuotedTextList.contains(false) ?  false : true;		
	}
	
	private boolean checkResultContainsQuotedTextForMagazines() throws Exception{
	    List<Boolean> isResultContainsQuotedTextList = new ArrayList<Boolean>();
	    
	    //Retrieve result row from the Magazines display group.
	    for(String searchResult : getSearchResults(properties.get("magazine_searchResults_xpath"))){
	    	isResultContainsQuotedTextList.add(verifyResultsHasQuotedTextOfSearchTerm(searchResult));
	    }
	    
	    //checks all the rows in Magazines display group contains one of the quoted text.
	    return isResultContainsQuotedTextList.contains(false) ?  false : true;		
	}
	
	private boolean checkResultContainsQuotedTextForAcademicJournals() throws Exception{
	    List<Boolean> isResultContainsQuotedTextList = new ArrayList<Boolean>();
	    
	    //Retrieve result row from the Academic Journals display group.
	    for(String searchResult : getSearchResults(properties.get("journals_searchResults_xpath"))){
	    	isResultContainsQuotedTextList.add(verifyResultsHasQuotedTextOfSearchTerm(searchResult));
	    }
	    
	    //checks all the rows in Academic Journals display group contains one of the quoted text.
	    return isResultContainsQuotedTextList.contains(false) ?  false : true;		
	}	
	
	private boolean checkResultContainsQuotedTextForImages() throws Exception{
	    List<Boolean> isResultContainsQuotedTextList = new ArrayList<Boolean>();
	    
	    //Retrieve result row from the Images display group.
	    for(String searchResult : getSearchResults(properties.get("images_searchResults_xpath"))){
	    	isResultContainsQuotedTextList.add(verifyResultsHasQuotedTextOfSearchTerm(searchResult));
	    }
	    
	    //checks all the rows in Images display group contains one of the quoted text.
	    return isResultContainsQuotedTextList.contains(false) ?  false : true;		
	}		
	
	private boolean checkResultContainsQuotedTextForAudio() throws Exception{
	    List<Boolean> isResultContainsQuotedTextList = new ArrayList<Boolean>();
	    
	    //Retrieve result row from the Audio display group.
	    for(String searchResult : getSearchResults(properties.get("audio_searchResults_xpath"))){
	    	isResultContainsQuotedTextList.add(verifyResultsHasQuotedTextOfSearchTerm(searchResult));
	    }
	    
	    //checks all the rows in Audio display group contains one of the quoted text.
	    return isResultContainsQuotedTextList.contains(false) ?  false : true;		
	}	
	
	private boolean verifyResultsHasQuotedTextOfSearchTerm(String searchResult){
		boolean isResultContainsQuotedText = false;
    	//get quoted text
    	for(String quotedText : getQuotedText()){
    		//check quoted text's present in the result rows(i.e text) of the display group.
    		//if searchResult contains the quotedText.
    		if(containsIgnoreCase(searchResult, quotedText)){
    			isResultContainsQuotedText = true;
    		} 
    	}		
		return isResultContainsQuotedText;
	}
	
	public boolean checkStopAndNaughtWordsAreNotDisplayedForSearchTermHavingUnMathedQuotes() throws Exception{
		filterNaughtyWord();
		filterStopWord();
		return checkResultsContainsSearchTerm();
	}
	
	public boolean verifySearchSuccessMsgIsDisplayed() throws Exception{
		
		//Check the Search Success display message
		String searchSuccessMsgDisplayed = new TextLabel(properties.get("success_message_xpath")).getLabelText();
		return searchSuccessMsgDisplayed.trim().equals("Search results: Keyword = "+this.searchString.trim()) ? true : false;

	}	
	
	public boolean checkStopAndNaughtWordsAreNotDisplayedForSearchTermWithSingleQuotes() throws Exception{
		filterNaughtyWord();
		filterStopWord();
		this.searchString = this.searchString.replaceAll("\'", "").trim();
		return checkResultsContainsSearchTerm();
	}	
	
	private List<String> getQuotedText(){
		 List<String> quotedTextList = new ArrayList<String>();
		 List<Integer> indexList = new ArrayList<Integer>();
		 int index = 0;
		 for(char c: this.searchString.toCharArray()){
			 if(c=='\"'){
				 indexList.add(index); 
				 if(indexList.size()==2){
					 String searchElement = this.searchString.substring(indexList.get(0)+1, indexList.get(1));
					 quotedTextList.add(searchElement.replaceAll("\"", "").trim()); 
					 indexList.clear();
				 }
			 }
			 index++;
		 }		
		 
		 return quotedTextList;
	} 
	
	private void filterNaughtyWord(){
		for(String naughtyWord : naughtyWordsList){
			searchString = containsIgnoreCase(searchString, naughtyWord) ? searchString.replaceAll(naughtyWord, "") : searchString;
		}
	} 
	private void filterStopWord(){
		for(String stopWord : stopWordsList){
			searchString = containsIgnoreCase(searchString, stopWord) ? searchString.replaceAll(stopWord, "") : searchString;
		}
	}
	
	private List<String> getSearchResults(String searcResultsXPath) throws Exception{
		
		//XPath count - 1, since last row contains "ViewAll"
		int count = new TextLabel(searcResultsXPath).getXpathCount()-1;
		resultList = new ArrayList<String>();
		for(int resultRow=1; resultRow<=count; resultRow++){ 
			resultList.add(new TextLabel(searcResultsXPath+"["+resultRow+"]").getLabelText());
		}
		return resultList; 
	}	  
	
	private boolean containsIgnoreCase(String source, String specifiedText){
		return source.toLowerCase().contains(specifiedText.toLowerCase());
	}
	
}
