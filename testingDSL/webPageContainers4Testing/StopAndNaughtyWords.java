package webPageContainers4Testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class StopAndNaughtyWords extends BasePageContainer{
 
	private List<String> stopWordsList;
	private List<String> naughtyWordsList;
	private List<String> wordsList;
	private Properties stopWordProperties;
	private static final String stopWordsFile = "properties\\default.stopwords.properties";
	private static final String naughtyWordsFile = "properties\\default.naughtywords.properties";
	private static final String STOP_WORD = "stopWord";
	private static final String NAUGHTY_WORD = "naughtyWord";
	private static String searchString = "";
	
	public StopAndNaughtyWords() throws Exception {
		super();

		//Creating Properties object to retrieve stopwords/naughty words from the properties file
		stopWordProperties = new Properties();
		
		//Add stopwords to stopWordsList
		stopWordsList = loadWordsList(stopWordsFile);
		
		//Add naughty words from naughtyWords.properties file to naughtyWordsList
		naughtyWordsList = loadWordsList(naughtyWordsFile);
	}
 
	public boolean searchWithStopWord() throws Exception {
		boolean isStopWord = isListContainsSpecifiedText(searchString, stopWordsList);
		if (isStopWord) {
			return new TextLabel(properties.get("caution_messages_xpath")).getLabelText().contains(properties.get("noResults_message"));
		} else {
			return false;
		}	
	}
	
	private List<String> getResults() throws Exception{
		List<String> list = new ArrayList<String>();
		if(new TextLabel(properties.get("caution_messages_xpath")).isPresent()){
			return list;
		}
		for(int i = 1; i <= new TextLabel(properties.get("displayGroups_searchResultsPage")+"[1]"+ properties.get("searchResults")).getXpathCount(); i++){
			new Link(properties.get("displayGroups_searchResultsPage")+ properties.get("searchResults")+"[" +i+ "]/h3/a").click();
			list.add(new TextLabel(properties.get("description_in_detailedPage")).getLabelText());
			new Url().goBackToPreviousPage();
		}
		return list;
	}
	
	public boolean searchWithStopWordsInSentenceWithoutQuotes() throws Exception{
		List<String> results = getResults();
		List<String> stopWordsInSearchString = getStopWordsInSentence(searchString);
		
		//If search string contains only stopwords
		if(searchString.split(" ").length == stopWordsInSearchString.size()){
			return (results.size() > 0) ? false : true;
		}
		//If search string contains stopwords and non-stop words
		for (int i = 0; i < results.size(); i++) {
			if (checkStopWordsInResult(results.get(i), stopWordsInSearchString))
				return false;
		}
		return true;
	}
	
	public boolean searchWithStopWordsInSentenceWithQuotes() throws Exception{
		// Removes quotes from the search string and returns all the stop words present in the search String
		List<String> stopWords = getStopOrNaughtyWordsInSentenceWithQuotes(searchString, STOP_WORD);

		// Checking if the result has stop word in it
		List<String> results = getResults();
		for (int i = 0; i < results.size(); i++) {
			if (checkStopWordsInResult(results.get(i), stopWords))
				return true;
		}
		// else If there is no stop word in the result
		return false;
	}
	
	public boolean searchWithNaughtyWord() throws Exception {
		boolean isNaughtyWord = isListContainsSpecifiedText(searchString, naughtyWordsList);
		if (isNaughtyWord) {
			return new TextLabel(properties.get("caution_messages_xpath")).getLabelText().contains(properties.get("noResults_message"));
		} else {
			return false;
		}	
	}
	
	public boolean searchWithNaughtyWordsInSentence() throws Exception{
		List<String> naughtyWordsInSearchString = getNaughtyWordsInSentence(searchString);
		List<String> results = getResults();
		if(searchString.split(" ").length == naughtyWordsInSearchString.size()){
			if(results.size() > 0)
				return false;
			else
				return true;
		}
		for (int i = 0; i < results.size(); i++) {
			if (checkNaughtyWordsInResult(results.get(i), naughtyWordsInSearchString))
				return false;
		}
		return true;
	}
	
	public boolean searchWithStopAndNaughtyWordsInSentenceWithQuotes() throws Exception{
		List<String> results = getResults();
		List<String> stopWordsInSearchTerm = getStopOrNaughtyWordsInSentenceWithQuotes(searchString, STOP_WORD);
		List<String> naughtyWordsInSearchTerm = getStopOrNaughtyWordsInSentenceWithQuotes(searchString, NAUGHTY_WORD );
		for (int i = 0; i < results.size(); i++) {
			// If the result has naughty words or if it has no stop words then returns false
			if (checkNaughtyWordsInResult(results.get(i), naughtyWordsInSearchTerm)
					|| !checkStopWordsInResult(results.get(i), stopWordsInSearchTerm))
				return false;
		}
		return true;	
	}
	
	public boolean searchWithStopAndNaughtyWordsInSentenceWithoutQuotes() throws Exception{
		List<String> results = getResults();
		List<String> stopWordsInSearchTerm = getStopWordsInSentence(searchString);
		List<String> naughtyWordsInSearchTerm = getNaughtyWordsInSentence(searchString); 
			
		for (int i = 0; i < results.size(); i++) {
			// If stop words or naughty words are present in the result text
			if (checkNaughtyWordsInResult(results.get(i), naughtyWordsInSearchTerm)
					|| checkStopWordsInResult(results.get(i), stopWordsInSearchTerm))
				return false;
		}
		return true;
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

	private boolean isListContainsSpecifiedText(String text,List<String> list){
		return list.contains(text.trim());
	}

	private List<String> getStopWordsInSentence(String searchTerm){
       List<String> list = new ArrayList<String>();
		Scanner stringTokens = new Scanner(searchTerm);
		List<String> stopWords = addStopWordsToList(stringTokens, list);
				
		return stopWords;		
		
	}
	
	private List<String> addStopWordsToList(Scanner stringTokens, List<String> list){
		while(stringTokens.hasNext()){
			String str = stringTokens.next();
			if(isListContainsSpecifiedText(str, stopWordsList))
				list.add(str);			
		}
		return list;
	}
	
	public static void search(String searchTerm) throws InterruptedException{
		searchString = searchTerm;
	}
	
	private List<String> getStopOrNaughtyWordsInSentenceWithQuotes(String searchTerm, String type) throws Exception{
		
		List<String> list = new ArrayList<String>();
		Scanner searchStringTokens = new Scanner(searchTerm);
		String currSearchToken = "";
		while(searchStringTokens.hasNext()){
			currSearchToken = searchStringTokens.next().replace("\"", "");
			if(checkStopOrNaughtyWord(currSearchToken, type)){
				list.add(currSearchToken);
			}
		}
		return list;
	}
	
	private boolean checkStopOrNaughtyWord(String word, String type) throws Exception{
		if(type.equals(STOP_WORD))
			return isListContainsSpecifiedText(word, stopWordsList);
		else
			return isListContainsSpecifiedText(word, naughtyWordsList);
		
	}
	
	public List<String> getNaughtyWordsInSentence(String searchTerm){
		List<String> list = new ArrayList<String>();
		Scanner searchStringTokens = new Scanner(searchTerm);
		List<String> naughtyWords = addNaughtyWordsToList(searchStringTokens, list);
		return naughtyWords;	
	}
	
	private List<String> addNaughtyWordsToList(Scanner stringTokens, List<String> list){
		while(stringTokens.hasNext()){
			String str = stringTokens.next();
			if(isListContainsSpecifiedText(str, naughtyWordsList))
				list.add(str);			
		}
		return list;
	}
	
	private boolean checkNaughtyWordsInResult(String result, List<String> naughtyWords){
		//Splitting the result into words
		Scanner resultTextTokens = new Scanner(result);
		while(resultTextTokens.hasNext()){
			String str = resultTextTokens.next().replace("\"", "");
			if(isListContainsSpecifiedText(str, naughtyWords))
				return true;
		}
		return false;
	}
	
	private boolean checkStopWordsInResult(String result, List<String> stopWords){
		//Splitting the result into words
		Scanner resultTextTokens = new Scanner(result);
		while(resultTextTokens.hasNext()){
			String str = resultTextTokens.next();
			if(isListContainsSpecifiedText(str, stopWords))
				return true;
		}
		return false;
	}
}

