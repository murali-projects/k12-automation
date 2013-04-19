package webPageContainers4Testing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class OceanDatabaseConnectivity extends BasePageContainer{
	
	private int noOfResultRows; 
	private Map<String,Integer> sortElementMap;
	
	public OceanDatabaseConnectivity() throws Exception{
		connectToOceanDB();
		loadSortByMap(); 
	}
	
	public void getResultsFromDB(String searchTerm, String partition, String displayGroupName, String sortByName) throws Exception{
		searchResultsFor(searchTerm,partition,displayGroupName,sortByName);
	} 
	
	private void connectToOceanDB() throws Exception{
		goToPage(properties.get("oceanDatabase_url"));
		new Link(properties.get("select_contentSets")).click();
		Thread.sleep(2000);

		new DropDownBox(properties.get("select_option_contentSets_xpath")).select(properties.get("select_option_contentSets_value"));
		new PageButton(properties.get("submit")).clickAndWait();
//		Thread.sleep(5000);
		
		new DropDownBox(properties.get("operators_xpath").replace("@INDEX@", "2")).select(properties.get("operators_value"));
		new DropDownBox(properties.get("operators_xpath").replace("@INDEX@", "3")).select(properties.get("operators_value"));
		
		new DropDownBox(properties.get("indices_xpath").replace("@INDEX@", "1")).select(properties.get("indices_value1"));
		new DropDownBox(properties.get("indices_xpath").replace("@INDEX@", "2")).select(properties.get("indices_value2"));
		new DropDownBox(properties.get("indices_xpath").replace("@INDEX@", "3")).select(properties.get("indices_value3"));
		
	}
	
	private void searchResultsFor(String searchTerm, String partition, String displayGroupName, String sortByName) throws Exception{
		//connectToOceanDB();
		
		new TextBox(properties.get("terms1")).type(searchTerm);
		new DropDownBox(properties.get("partitions")).select("label="+partition);
		new DropDownBox(properties.get("contentGroup")).select("label="+displayGroupName);
		new DropDownBox(properties.get("sort")).select("label="+sortByName);
		
		new PageButton(properties.get("searchButton")).clickAndWait();
//		Thread.sleep(10000);
		
		noOfResultRows = selenium.getXpathCount(properties.get("results_table")).intValue();
	}
	
	protected List<String> getDocumentResults(String databaseElement) throws Exception{
	    
		if(noOfResultRows == 0){
			return new ArrayList<String>();
		}
		List<String> resultsList = new ArrayList<String>();
		for(int index=2;index <= noOfResultRows;index++){
			if(databaseElement.equals("PublicationDate"))
				resultsList.add(getPublicationDate(new TextLabel(properties.get("results_table")+"["+index+"]/td[3]/b["+sortElementMap.get(databaseElement)+"]").getLabelText()));
			else if(databaseElement.equals("DocTypes"))
				resultsList.add(new TextLabel(properties.get("results_table")+"["+index+"]/td[3]/b["+sortElementMap.get(databaseElement)+"]").getLabelText().replace(" ", "").replace("[", "").replace("]", ""));
			else 
				resultsList.add(new TextLabel(properties.get("results_table")+"["+index+"]/td[3]/b["+sortElementMap.get(databaseElement)+"]").getLabelText());
		}
		 
		return resultsList;
	}	
	
	
	protected List<String> getDocumentResultsForMorePages(String sortByElement) throws Exception{
	    
		if(noOfResultRows == 0){
			return new ArrayList<String>();
		}
		
		List<String> resultsList = new ArrayList<String>();
		for(int index=2;index <= noOfResultRows;index++){
			//td[3] is for "Document Details" column in Ocean database
			//Similarly td[1] is for "S.No", td[2] is for "Publication Details".
			resultsList.add(new TextLabel(properties.get("results_table")+"["+index+"]/td[3]/b["+sortElementMap.get(sortByElement)+"]").getLabelText());
			if(index==noOfResultRows && new Link("//div[@id='content']/table[1]/tbody/tr/td[2]/a/b").isPresent()){
				new Link(properties.get("next_ocean")).click();
				noOfResultRows = selenium.getXpathCount(properties.get("results_table")).intValue();
				index=1;
			}if(resultsList.size()>100){
				break;
			}
		}
		 
		return resultsList;
	}	
	
	private String getPublicationDate(String publicationDate) {
		String year = "";
		String month = "";
		String day = "";
		if (!publicationDate.isEmpty()) {
			year = publicationDate.substring(1, 5);

			if (!year.isEmpty()) {
				month = publicationDate.substring(5, 7);
				day = publicationDate.substring(7);
				// Assigning month name
//				month = getMonth(month);
				day = month.isEmpty() ? "" : day;
				if(month.isEmpty() && day.isEmpty()){
					publicationDate = year;
				}else
					publicationDate = (year + "-" + month + "-" + day);
			}
		} else
			publicationDate = "";
		return publicationDate;
	}
	
	private List<String> getPublicationResults(String databaseElement) throws Exception{
	    
		if(noOfResultRows == 0){
			return new ArrayList<String>();
		}
		
		List<String> resultsList = new ArrayList<String>();
		for(int index=2;index <= noOfResultRows;index++){
			if(databaseElement.equals(properties.get("targetAudience")))
				resultsList.add(getTargetAudience(new TextLabel(properties.get("results_table")+"["+index+"]/td[2]/b["+sortElementMap.get(databaseElement)+"]").getLabelText()));
			else
				resultsList.add(new TextLabel(properties.get("results_table")+"["+index+"]/td[2]/b["+sortElementMap.get(databaseElement)+"]").getLabelText());
		}
		return resultsList;
	}	
	
	private String getTargetAudience(String pubAdditionalData) {
		String[] values = pubAdditionalData.substring(1, pubAdditionalData
				.lastIndexOf('}')).split("=");
		String targetAudience = values[values.length - 1];

		if (targetAudience.equals("Academic")|| targetAudience.equals("Professional")) 
			return "Advanced";

		else if (targetAudience.equals("Adult")|| targetAudience.equals("General")
				|| targetAudience.equals("High school") || targetAudience.equals("Middle school")
				|| targetAudience.equals("Secondary school") || targetAudience.equals("Students")
				|| targetAudience.equals("Trade") || targetAudience.equals("Young adult"))
			return "Intermediate";
		else if (targetAudience.equals("Children's--Upper elementary")|| targetAudience.equals("Children's--Lower elementary")
				|| targetAudience.equals("Pre-elementary school"))
			return "Basic";
		else
			return "";

	}
	
	private void loadSortByMap(){
		sortElementMap = new HashMap<String, Integer>();
		sortElementMap.put(properties.get("relevance"), 19); 
		sortElementMap.put(properties.get("publicationTitle"), 7);
		sortElementMap.put(properties.get("documentTitle"), 4);
		
		sortElementMap.put(properties.get("documentId"), 3);
		sortElementMap.put(properties.get("docTitleAnnotation"), 5);
		sortElementMap.put(properties.get("docTypes"), 6);
		sortElementMap.put(properties.get("publicationDate"), 11);
		sortElementMap.put(properties.get("targetAudience"), 44);
	}

	//Start of Writing data to file 
	private static void writeToFile(List<String> sortResultsList, File sortResultsFile) throws IOException{
		 
		FileWriter writer = new FileWriter(getFile(sortResultsFile), true);
		for(String sortResult : sortResultsList){
			writer.write(sortResult);
			writer.append('\n');  
		} 
		writer.close();
	}
	
	private static File getFile(File file) throws IOException{
		
		if(file.exists())
			file.delete();
		file.createNewFile();
		
		return file;
	}
	//End of Writing data to file
	
	public void writeResultsToFile(String partition, String contentGroup, String sortBy,String dataBaseElement,String searchTerm) throws Exception {
		writeToFile(getResults(partition, contentGroup, sortBy,dataBaseElement, searchTerm), new File(partition+"_"+contentGroup+"_"+sortBy+"_"+dataBaseElement+"_"+searchTerm.replaceAll("\"", "-")+".txt"));
	}
	
	public void writeResultsCountToFile(String partition, String contentGroup, String sortBy, String searchTerm) throws IOException, Exception{
		writeToFile(this.getResultsCount(partition, contentGroup, sortBy, searchTerm), new File(partition+"_"+contentGroup+"_"+sortBy+"_"+searchTerm.replaceAll("\"", "-")+".txt"));
	}
	
	public void writeMoreResultsToFile(String partition, String contentGroup, String sortBy,String dataBaseElement,String searchTerm) throws Exception {
		this.getResultsFromDB(searchTerm, partition, contentGroup, sortBy);
		writeToFile(getDocumentResultsForMorePages(dataBaseElement), new File(partition+"_"+contentGroup+"_"+sortBy+"_"+dataBaseElement+"_"+searchTerm.replaceAll("\"", "-")+".txt"));
	}
	
	public List<String> getResults(String partition, String contentGroup, String sortBy,String dataBaseElement,String searchTerm) throws Exception{
		this.getResultsFromDB(searchTerm, partition, contentGroup, sortBy);
		
		if(dataBaseElement.equalsIgnoreCase(properties.get("relevance"))
				|| dataBaseElement.equalsIgnoreCase(properties.get("publicationDate"))
				|| dataBaseElement.equalsIgnoreCase(properties.get("documentTitle"))
				|| dataBaseElement.equalsIgnoreCase(properties.get("documentId"))
				|| dataBaseElement.equalsIgnoreCase(properties.get("docTitleAnnotation"))
				|| dataBaseElement.equalsIgnoreCase(properties.get("docTypes"))) {
			return this.getDocumentResults(dataBaseElement);
		} else if (dataBaseElement.equalsIgnoreCase(properties.get("publicationTitle"))
				|| dataBaseElement.equals(properties.get("targetAudience"))) {
			return this.getPublicationResults(dataBaseElement);
		}
		return new ArrayList<String>();		
		
	}

	public List<String> getResultsCount(String partition, String contentGroup, String sortBy, String searchTerm) throws Exception{
		this.getResultsFromDB(searchTerm, partition, contentGroup, sortBy);
		List<String> resultCountList = new ArrayList<String>();
		TextLabel resultCount = new TextLabel(properties.get("searchResultsCount"));
		if(resultCount.isPresent()){
			resultCountList.add(new TextLabel(properties.get("searchResultsCount")).getLabelText().replace(",", ""));
		}else{
			resultCountList.add("0");
		}
		return resultCountList;
	}
	
	public void writeRelatedSubjectsCountToFile(String category, String partition, String contentGroup, String sortBy, String searchTerm) throws IOException, Exception{
		new DropDownBox(properties.get("category")).select(properties.get("subject"));
		writeToFile(this.getResultsCount(partition, contentGroup, sortBy, searchTerm), new File(category+ "_"+ partition+"_"+contentGroup+"_"+sortBy+"_"+searchTerm.replaceAll("\"", "-")+".txt"));
	}
	
	private void searchAdvancedSearchResultsFor(String index1,
			String searchTerm1, String connector1, String index2,
			String searchTerm2, String connector2, String index3,
			String searchTerm3, String partition, String contentGroup,
			String sortBy) throws Exception{
		
		new DropDownBox(properties.get("indices_xpath").replace("@INDEX@", "1")).select(index1);
		new TextBox(properties.get("terms1")).type(searchTerm1);
		
		new DropDownBox(properties.get("operators_xpath").replace("@INDEX@", "2")).select(properties.get(connector1));
		new DropDownBox(properties.get("indices_xpath").replace("@INDEX@", "2")).select(index2);
		new TextBox(properties.get("terms2")).type(searchTerm2);
		
		if(!(index3.isEmpty() && connector2.isEmpty() && searchTerm3.isEmpty())){
			new DropDownBox(properties.get("operators_xpath").replace("@INDEX@", "3")).select(properties.get(connector2));
			new DropDownBox(properties.get("indices_xpath").replace("@INDEX@", "3")).select(index3);
			new TextBox(properties.get("terms3")).type(searchTerm3);
		}
		new DropDownBox(properties.get("partitions")).select("label="+partition);
		if(!contentGroup.isEmpty()){
			new DropDownBox(properties.get("contentGroup")).select("label="+contentGroup);
		}
		if(!sortBy.isEmpty()){
			new DropDownBox(properties.get("sort")).select("label="+sortBy);
		}
		new PageButton(properties.get("searchButton")).clickAndWait();
		
		noOfResultRows = selenium.getXpathCount(properties.get("results_table")).intValue();
	}
	
	public List<String> getAdvancedSearchResultsCount(String index1,
			String searchTerm1, String connector1, String index2,
			String searchTerm2, String connector2, String index3,
			String searchTerm3, String partition, String contentGroup,
			String sortBy) throws Exception{
		searchAdvancedSearchResultsFor(index1,
				searchTerm1, connector1, index2,
				searchTerm2, connector2, index3,
				searchTerm3, partition, contentGroup,
				sortBy);
		List<String> resultCountList = new ArrayList<String>();
		TextLabel resultCount = new TextLabel(properties.get("searchResultsCount"));
		if(resultCount.isPresent()){
			resultCountList.add(new TextLabel(properties.get("searchResultsCount")).getLabelText().replace(",", ""));
		}else{
			resultCountList.add("0");
		}
		return resultCountList;
	}
	
	public void writeAdvancedSearchResultsCountToFile(String index1,
			String searchTerm1, String connector1, String index2,
			String searchTerm2, String connector2, String index3,
			String searchTerm3, String partition, String contentGroup,
			String sortBy) throws IOException, Exception {
		writeToFile(getAdvancedSearchResultsCount(index1, searchTerm1,
				connector1, index2, searchTerm2, connector2, index3,
				searchTerm3, partition, contentGroup, sortBy), new File(index1
				+ "_" + searchTerm1 + "_" + connector1 + "_" + index2 + "_"
				+ searchTerm2 + "_" + connector2 + "_" + index3 + "_"
				+ searchTerm3 + "_" + partition + "_" + contentGroup + "_"
				+ sortBy + ".txt"));
	}
	
	private List<String> getResultsCountForQuery(String searchQuery, String limitQuery) throws Exception{
		new Link(properties.get("queryTester")).click();
		new TextBox(properties.get("searchQuery_textbox")).type(searchQuery);
		new TextBox(properties.get("limitQuery_textbox")).type(limitQuery);
		new PageButton(properties.get("query_submitButton")).clickAndWait();
		List<String> resultCountList = new ArrayList<String>();
		TextLabel resultCount = new TextLabel(properties.get("query_results_count"));
		if(resultCount.isPresent()){
			resultCountList.add(resultCount.getLabelText().replace(",", ""));
		}else{
			resultCountList.add("0");
		}
		return resultCountList;
	}
	
	public void writeQueryResultsCountToFile(String searchQuery, String limitQuery) throws IOException, Exception{
		writeToFile(getResultsCountForQuery(searchQuery, limitQuery), new File(searchQuery.replace(" ", "_")+".txt"));
	}
	
	public void gc(Object obj){
		obj = null;
		this.goToPage("http://tg-dxproj16.oh.gale.com:8180/portal");
	}
	
}


