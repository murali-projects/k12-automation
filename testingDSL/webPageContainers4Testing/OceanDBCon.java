package webPageContainers4Testing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.SeleniumSingletonFactory;
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class OceanDBCon extends BasePageContainer{

	//Content Group
	public static final String REFERENCE = "Reference";
	
	//SortBy Group
	public static final String RELEVANCE = "Relevance";
	public static final String PUBLICATION_TITLE = "PubTitle";
	public static final String DOCUMENT_TITLE = "DocTitle";
	//SortBy Group need support from OceanDataBase.
	public static final String DOCUMENT_ID = "DocumentId";
	public static final String DOC_TITLE_ANNOTATION = "DocTitleAnnotation";
	public static final String DOC_TYPES = "DocTypes";
	public static final String PUBLICATION_DATE = "PublicationDate";
	
	private int noOfResultRows; 
	private final String RESULTS_TABLE_XPATH = "//div/div[3]/table[2]/tbody/tr";
	
	private final String OCEAN_DATA_BASE_URL = "http://tg-dxluc9.oh.gale.com:8585/index.jsp";
	private final String SELECT_CONTENT_SETS_XPATH = "//div[@id='content']/div/a/b";  
	private final String XPATH_TO_SELECT_OPTION_IN_CONTENT_SETS = "//div/div[3]/form/table/tbody/tr/td[2]/select";
	private final String VALUE_FOR_SELECT_OPTION_IN_CONTENT_SETS = "value=OVRC";
	private final String SUBMIT = "Submit";
	private final String OPERATORS_XPATH = "//div[@id='content']/form/table[2]/tbody/tr/td[1]/table/tbody/tr[@INDEX@]/td[2]/select";
	private final String OPERATORS_VALUE ="label=AND";
	private final String INDICES_XPATH = "//div[@id='content']/form/table[2]/tbody/tr/td[1]/table/tbody/tr[@INDEX@]/td[3]/select";
	private final String INDICES_VALUE_1 = "label=KE";
	private final String INDICES_VALUE_2 = "label=AB";
	private final String INDICES_VALUE_3 = "label=ABLS";
	private final String PARTITIONS = "partitions";
	private final String PARTITIONS_VALUE = "label=ALL";
	private final String TERMS ="terms";
	private final String CONTENT_GROUP = "contentGroup";
	private final String SORT = "sort";
	private final String SEARCH_BUTTON_XPATH = "//input[@value='Search']";
	private final String SEARCH_RESULTS_COUNT_XPATH = "//div[@id=\"content\"]/p[3]/b[2]";
	
	private Map<String,Integer> sortElementMap;
	
	/** 
	 * Constructor
	 * 
	 * @param sortByOptions 
	 * @throws Exception
	 */
	public OceanDBCon() throws Exception{
		connectToOceanDB();
		loadSortByMap();
	}
	
	public void getResultsFromDB(String searchTerm,String displayGroupName, String sortByName) throws Exception{
		searchResultsFor(searchTerm,displayGroupName,sortByName);
	} 
	
	private void connectToOceanDB() throws Exception{
		goToPage(OCEAN_DATA_BASE_URL);
		new Link(SELECT_CONTENT_SETS_XPATH).click();
		Thread.sleep(2000);

		new DropDownBox(XPATH_TO_SELECT_OPTION_IN_CONTENT_SETS).select(VALUE_FOR_SELECT_OPTION_IN_CONTENT_SETS);
		new PageButton(SUBMIT).clickAndWait();
		Thread.sleep(5000);
		
		new DropDownBox(OPERATORS_XPATH.replace("@INDEX@", "2")).select(OPERATORS_VALUE);
		new DropDownBox(OPERATORS_XPATH.replace("@INDEX@", "3")).select(OPERATORS_VALUE);
		
		new DropDownBox(INDICES_XPATH.replace("@INDEX@", "1")).select(INDICES_VALUE_1);
		new DropDownBox(INDICES_XPATH.replace("@INDEX@", "2")).select(INDICES_VALUE_2);
		new DropDownBox(INDICES_XPATH.replace("@INDEX@", "3")).select(INDICES_VALUE_3);
		
		new DropDownBox(PARTITIONS).select(PARTITIONS_VALUE);
	}
	
	private void searchResultsFor(String searchTerm,String displayGroupName, String sortByName) throws Exception{
		
		new TextBox(TERMS).type(searchTerm);
		
		new DropDownBox(CONTENT_GROUP).select("label="+displayGroupName);
		new DropDownBox(SORT).select("label="+sortByName);
		
		new PageButton(SEARCH_BUTTON_XPATH).clickAndWait();
		Thread.sleep(10000);
		
		noOfResultRows = selenium.getXpathCount(RESULTS_TABLE_XPATH).intValue();
	}
	
	private List<String> getDocumentResults(String sortByElement) throws Exception{
	    
		if(noOfResultRows == 0){
			return new ArrayList<String>();
		}
		
		List<String> resultsList = new ArrayList<String>();
		for(int index=2;index <= noOfResultRows;index++){
			//td[3] is for "Document Details" column in Ocean database
			//Similarly td[1] is for "S.No", td[2] is for "Publication Details".
			resultsList.add(new TextLabel(RESULTS_TABLE_XPATH+"["+index+"]/td[3]/b["+sortElementMap.get(sortByElement)+"]").getLabelText());
		}
		 
		for(String s : resultsList){
			System.out.println(sortByElement+" : "+s);
		}
		return resultsList;
	}	
	
	private List<String> getPublicationResults(String sortByElement) throws Exception{
	    
		if(noOfResultRows == 0){
			return new ArrayList<String>();
		}
		
		List<String> resultsList = new ArrayList<String>();
		for(int index=2;index <= noOfResultRows;index++){
			//td[3] is for "Document Details" column in Ocean database
			//Similarly td[1] is for "S.No", td[2] is for "Publication Details".
			resultsList.add(new TextLabel(RESULTS_TABLE_XPATH+"["+index+"]/td[2]/b["+sortElementMap.get(sortByElement)+"]").getLabelText());
		}
		 
		for(String s : resultsList){
			System.out.println(sortByElement+" : "+s);
		}
		return resultsList;
	}	
	
	private void loadSortByMap(){
		sortElementMap = new HashMap<String, Integer>();
		sortElementMap.put(RELEVANCE, 19); 
		sortElementMap.put(PUBLICATION_TITLE, 7);
		sortElementMap.put(DOCUMENT_TITLE, 4);
		
		sortElementMap.put(DOCUMENT_ID, 3);
		sortElementMap.put(DOC_TITLE_ANNOTATION, 5);
		sortElementMap.put(DOC_TYPES, 6);
		sortElementMap.put(PUBLICATION_DATE, 11);
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
	
	//Start of Reading data from file
/*	private static List<String> readFromFile(File file) {
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			List<String> sortResult = new ArrayList<String>();
	
			String line = "";
	 
			// reads the file line by line
			while ((line = reader.readLine()) != null) {
					sortResult.add(line);
			}
			// close the file
			reader.close();
			return sortResult;
		}catch (Exception e) {
			return new ArrayList<String>();
		}
	}	*/
	//End of Reading data from file	
	
	//Reading & Writing Dummy Results from file starts
	public void writeDocumentResultsToFile(String contentGroup, String sortBy,String dataBaseElement,String searchTerm) throws Exception {
		this.getResultsFromDB(searchTerm, contentGroup, sortBy);
		writeToFile(this.getDocumentResults(dataBaseElement), new File(contentGroup+"_"+sortBy+"_"+dataBaseElement+"_"+searchTerm+".txt"));
	}
	
	//Read dummy results for Pub MCODE,Publication Sub Title,Pub Cover Page, etc.
	public void writePublicationResultsToFile(String contentGroup, String sortBy,String dataBaseElement,String searchTerm)
			throws Exception {
		this.getResultsFromDB(searchTerm, contentGroup, sortBy);
		writeToFile(this.getPublicationResults(dataBaseElement), new File(contentGroup+"_"+sortBy+"_"+dataBaseElement+"_"+searchTerm+".txt"));
	}
	
/*	public static List<String> readResultsFromFile(String contentGroup, String sortBy,String dataBaseElement,String searchTerm) throws IOException{
		return readFromFile(new File(contentGroup+"_"+sortBy+"_"+dataBaseElement+"_"+searchTerm+".txt"));
	}*/	
	//Reading & Writing Dummy Results from file ends
	//-----------------------------------------------------------------------------------------------------
	
	public void writeResultsCountToFile(String contentGroup, String sortBy, String searchTerm) throws IOException, Exception{
		writeToFile(this.getResultsCount(contentGroup, sortBy, searchTerm), new File(contentGroup+"_"+sortBy+"_"+searchTerm+".txt"));
	}

/*	public static List<String> readResultsCountFromFile(String contentGroup, String sortBy, String searchTerm) throws IOException{
		return readFromFile(new File(contentGroup+"_"+sortBy+"_"+searchTerm+".txt"));
	}	*/
	
	private List<String> getResultsCount(String contentGroup, String sortBy, String searchTerm) throws Exception{
		this.getResultsFromDB(searchTerm, contentGroup, sortBy);
		List<String> resultCountList = new ArrayList<String>();
		TextLabel resultCount = new TextLabel(SEARCH_RESULTS_COUNT_XPATH);
		if(resultCount.isPresent()){
			resultCountList.add(new TextLabel(SEARCH_RESULTS_COUNT_XPATH).getLabelText().replace(",", ""));
		}else{
			resultCountList.add("0");
		}
		return resultCountList;
	}
	
	public static void main(String[] args) throws Exception {
		OceanDBCon oceanDBCon = new OceanDBCon();
		String searchTerm = "test";
		
		oceanDBCon.writeDocumentResultsToFile(searchTerm, REFERENCE, RELEVANCE, DOCUMENT_ID);
		oceanDBCon.writeDocumentResultsToFile(searchTerm, REFERENCE, RELEVANCE, DOC_TITLE_ANNOTATION);
		oceanDBCon.writeDocumentResultsToFile(searchTerm, REFERENCE, RELEVANCE, DOC_TYPES);
		oceanDBCon.writeDocumentResultsToFile(searchTerm, REFERENCE, RELEVANCE, PUBLICATION_DATE);
		//Reading & Writing Dummy Results from file ends
		
		SeleniumSingletonFactory.stopEverything();
	}
	
}


