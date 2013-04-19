package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class SearchUsingOperators extends BasePageContainer {

	public SearchUsingOperators() throws Exception {
		super();
	}

	private int getLinksCount(){
		return new Link(properties.get("displayGroups_searchResultsPage")+"[1]"+ properties.get("searchResults")).getLinkCount();
	}
	
	private List<String> getResults() throws InterruptedException{
		List<String> list = new ArrayList<String>();
		for(int i = 1; i <= getLinksCount(); i++){
			new Link(properties.get("displayGroups_searchResultsPage")+"[1]"+ properties.get("searchResults")+"[" +i+ "]/h3/a").click();
			list.add(new TextLabel(properties.get("description_in_detailedPage")).getLabelText().toLowerCase());
			new Url().goBackToPreviousPage();
		}
		return list;
	}
	
	public boolean validateSingleWildCharacterSearch(String searchTerm) throws InterruptedException {
		if (searchTerm.contains("?"))
			searchTerm = searchTerm.replace("?", ".");
		List<String> list = getResults();
		for (int i = 0; i < list.size(); i++) {
				if(!(searchWildCharactersPattern(list.get(i).split(" "), searchTerm)))
					return false;
		}
		return true;
	}

	public boolean validateMultipleWildCharacterSearch(String searchTerm) throws Exception {
		List<String> list = getResults();
		for (int i = 0; i < list.size(); i++) {
			if (!(searchMultipleWildCharactersPattern(list.get(i).split(" "), searchTerm)))
				return false;
		}
		return true;
	}
	
	public boolean validateExclamationWildCharacterSearch(String searchTerm) throws Exception {
		List<String> list = getResults();
		for (int i = 0; i < list.size(); i++) {
			if (!(searchExclamationWildCharactersPattern(list.get(i).split(" "), searchTerm)))
				return false;
		}
		return true;
	}
	
	private boolean searchMultipleWildCharactersPattern(String[] description, String searchTerm){
		searchTerm = searchTerm.replace("*", "");
		for (String word : description) {
			if(word.toLowerCase().startsWith(searchTerm))
				return true;
		}
		return false;
	}

	private boolean searchExclamationWildCharactersPattern(String[] description, String searchTerm){
		searchTerm = searchTerm.replace("!", "");
		for (String word : description) {					
			if(word.toLowerCase().startsWith(searchTerm) && word.toLowerCase().length() <= searchTerm.length()+1)
				return true;
		}
		return false;
	}
	
	private boolean searchWildCharactersPattern(String[] description, String searchTerm) {
		Pattern p = Pattern.compile(searchTerm);
		for (int i = 0; i <= description.length; i++) {
			Matcher m = p.matcher(description[i]);
			if(m.matches())
				return true;
		}
		return false;
	}

	public boolean validateBooleanPattern(String searchTerm, String booleanOperator) throws InterruptedException {
		List<String> list = getResults();
		for (int i = 0; i < list.size(); i++) {
			if(! searchBooleanPattern(list.get(i).split(" "), searchTerm
				.split(booleanOperator), booleanOperator))
				return false;
		}
		return true;
	}

	public boolean validateNestedOperator(String searchTerm) throws InterruptedException{
		
		//Returns text within "()" 
		String[] nestedSearchTerm = searchTerm.substring(searchTerm.indexOf('(')+1, searchTerm.indexOf(')')).split(" ");
		String nestedBooleanOperator = "OR";
		String booleanOperator = "";
		String searchTermExcludingNestedTerm = "";
		
		if(nestedSearchTerm.length == 3){
			nestedBooleanOperator = nestedSearchTerm[1].trim();
		}
		String[] nestedSearchTermWithoutOperators = searchTerm.substring(searchTerm.indexOf('(')+1, searchTerm.indexOf(')')).split(nestedBooleanOperator);
		if(! searchTerm.substring(searchTerm.indexOf(')')).isEmpty()){
			searchTermExcludingNestedTerm = searchTerm.substring(0,searchTerm.indexOf('('));
			booleanOperator = searchTermExcludingNestedTerm.trim().split(" ")[1];
		}
		else if(! searchTerm.substring(searchTerm.indexOf('(')).isEmpty()){
			searchTermExcludingNestedTerm = searchTerm.substring(0,searchTerm.indexOf('('));
			booleanOperator = searchTermExcludingNestedTerm.trim().split("")[1];
		}
		List<String> list = getResults();
		for (int i = 0; i < list.size(); i++) {
			if (!(searchBooleanPattern(list.get(i).split(" "), nestedSearchTermWithoutOperators, nestedBooleanOperator)
					&& (searchBooleanPattern(list.get(i).split(" "), searchTermExcludingNestedTerm.split(booleanOperator), booleanOperator))))
				return false;
		}
		return true;
	}
	
	public boolean validateProximitySearchOperator(String searchTerm, String operator) throws InterruptedException {
		List<String> list = getResults();
		for (int i = 0; i < list.size(); i++) {
			if (!(searchProximityOperator(list.get(i).split(" "), searchTerm.split(operator), operator)))
				return false;
		}
		return true;
	}
	
	public boolean validateDateRangeOperator(String searchTerm){
		for (int i = 1; i <= getLinksCount(); i++) {
			if (!searchToOperator(searchTerm.split("-"), new TextLabel(properties.get("displayGroups_searchResultsPage")+
					properties.get("searchResults") + "[" +i+ "]" +
					properties.get("searchResultsPage_pubDate")).getLabelText()))
				return false;
		}
		return true;
	}
	
	public boolean verifyLexileScoreInResults(String searchTerm, String operator) throws InterruptedException{
		//LX <=600
		String lexileScore = searchTerm.split(operator)[1].trim();
		for (int i = 1; i <= new TextLabel(properties.get("displayGroups_searchResultsPage")).getXpathCount(); i++) {
			if (new TextLabel(properties.get("displayGroups_searchResultsPage")+ "/div[" + i + "]/a").isPresent()){
				new Link(properties.get("displayGroups_searchResultsPage")+ "/div[" + i + "]/a").click();
				return verifyLexileScores(lexileScore);
			}
		}
		return true;
	}
	
	public boolean verifyPhraseContainsResults(String searchTerm) throws InterruptedException{
		List<String> list = getResults();
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).contains(searchTerm))
				return false;
		}
		return true;
	}
	
	public boolean verifyPhraseExactResults(String searchTerm){
		//TODO:
		return false;
	}
	
	private boolean verifyLexileScores(String lexileScore){
		String toolTip;
		for(int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++){
			selenium.mouseOver(properties.get("viewAll_searchResults") + "["+ i + "]/td[4]/a/img");
			toolTip = new TextLabel(properties.get("viewAll_searchResults") +"[" + i + "]/td[4]/a/img").getAttribute("alt");
			if(!toolTip.substring(toolTip.indexOf("Lexile Measure = ")).equals(lexileScore)){
				return false;
			}
		}
		return true;
	}
	
	private boolean searchBooleanPattern(String[] description,
			String[] searchValues, String booleanOperator) {
		boolean isBoolean = false;
		if(booleanOperator.equalsIgnoreCase("and"))
			isBoolean = searchProximityOperator(description, searchValues[0].trim().split(" "), "N4")
					&& searchProximityOperator(description, searchValues[1].trim().split(" "), "N4");
		else if(booleanOperator.equalsIgnoreCase("or"))
			isBoolean = searchProximityOperator(description, searchValues[0].trim().split(" "), "N4") 
					|| searchProximityOperator(description, searchValues[1].trim().split(" "), "N4");
		else if(booleanOperator.equalsIgnoreCase("not"))
			isBoolean = searchProximityOperator(description, searchValues[0].trim().split(" "), "N4") 
					&& !searchProximityOperator(description, searchValues[1].trim().split(" "), "N4");
		return isBoolean;
	}
	
	private boolean searchProximityOperator(String[] description,
			String[] searchValues, String operator){
		
		List<String> contentValuesList = new ArrayList<String>(Arrays.asList(description));
		int firstWordIndex = -1;
		int secondWordIndex = -1;
		int count = 0;
		
		for(int i =0; i < searchValues.length; i++){
			if(contentValuesList.contains(searchValues[i].trim())){
				if(count == 0)
					firstWordIndex = contentValuesList.indexOf(searchValues[i].trim());
				else
					secondWordIndex = contentValuesList.indexOf(searchValues[i].trim());
			}
			count++;
		}
		if(searchValues.length == 1)
			return firstWordIndex != -1;
		return checkIndexes(firstWordIndex, secondWordIndex, operator);
	}
	
	private boolean checkIndexes(int firstWordIndex, int secondWordIndex, String operator){
		if(secondWordIndex == -1 || firstWordIndex == -1)
			return false;
		int noOfWords = Integer.parseInt(operator.substring(1));
		if(operator.startsWith("w") || operator.startsWith("W")){
			return (secondWordIndex - firstWordIndex < noOfWords);
		}
		else if(operator.startsWith("n") || operator.startsWith("N")){
			return (secondWordIndex - firstWordIndex < noOfWords)||(firstWordIndex - secondWordIndex < noOfWords);
		}
		return false;
	}
	
	// 120100101-120100131
	@SuppressWarnings("deprecation")
	private boolean searchToOperator(String[] dates, String publicationDateStr){
		String s[] = publicationDateStr.split("-");
	    Date publicationDate = new Date(s[2]+ "-" +(getMonthInWords(s[1]))+s[0]);
		String fromDate = dates[0];
		String toDate = dates[1];
		
		int fromDateyear = Integer.parseInt(fromDate.substring(1,5))-1900;
		int fromDateMonth = Integer.parseInt(fromDate.substring(5,7))-1;
		int fromDateday = Integer.parseInt(fromDate.substring(7,9));
		int toDateyear = Integer.parseInt(toDate.substring(1,5))-1900;
		int toDateMonth = Integer.parseInt(toDate.substring(5,7))-1;
		int toDateday = Integer.parseInt(toDate.substring(7,9));
		
		Date date1 = new Date(fromDateyear, fromDateMonth, fromDateday);
		Date date2 = new Date(toDateyear, toDateMonth, toDateday);

		if (publicationDate.before(date2) && publicationDate.after(date1)) {
			return true;
		}
		return false;
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
}
