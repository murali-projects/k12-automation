package webPageContainers4Testing;

import java.io.IOException;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class BiographyInfoInDocumentDisplay extends BasePageContainer{

	private String displayGroup;
	private String sortBy;
	private String searchTerm;
	
	public BiographyInfoInDocumentDisplay(String displayGroup, String sortBy, String searchTerm) throws Exception {
		super();
		this.displayGroup = displayGroup;
		this.sortBy = sortBy;
		this.searchTerm = searchTerm;
		new Link(properties.get(displayGroup + "_viewAll")).click();
	}
	
	public boolean isNarrativeBiographyDocInfoTypePresent() throws InterruptedException{
		boolean isPresent = false;
		for(int i = 1; i <= new Link(properties.get("viewAll_searchResults")).getLinkCount(); i++){
			if(new TextLabel(properties.get("viewAll_searchResults")+ "["+ i + "]/td[3]").getLabelText().contains("Biography")){
				isPresent = true;
				break;
			}
			i = clickNext(i);
		}
		return isPresent;
	}
	
	private int clickNext(int index) throws InterruptedException{
		if(index == new Link(properties.get("viewAll_searchResults")).getLinkCount()){
			if(new Link(properties.get("next")).isPresent()){
				new Link(properties.get("next")).click();
				return 0;
			}
		}
		return index;
	}
	
	private int getCurrentResults() throws Exception {
		int currentResultsCount = Integer.parseInt(new TextLabel(properties.get("currentResults")).getLabelText()
				.split("-")[0].trim());
		if(currentResultsCount > 0)
			return currentResultsCount-1;
		return currentResultsCount;
	}
	
	public boolean verifyDocInfoTypeWithDB() throws Exception{
		int index = 0;
		for(int i = 1; i <= new Link(properties.get("viewAll_searchResults")).getLinkCount(); i++){
			index = clickNext(i) + getCurrentResults();
			
			if(new TextLabel(properties.get("viewAll_searchResults")+ "["+ i + "]/td[3]").getLabelText().contains("Biography")){
				return verifyWithDB(index);
			}
		}
		return false;
	}
	
	private boolean verifyWithDB(int index) throws IOException{
		if(displayGroup.equals("reference"))
			displayGroup = "K12-Reference";
		List<String> docInfoTypesInDB = OceanDatabaseReadFile.readResultsFromFile(properties.get("all"),
				properties.get(displayGroup), properties.get(sortBy), properties.get("docTypes"), searchTerm);
		
		return docInfoTypesInDB.get(index-1).contains("Biography");
	}
	
	public boolean verifyDetailedDocumentIsDisplayed() throws InterruptedException{
		navigateToDetailedPage();
		return (new TextLabel("lifeTime").isPresent()
				&& new TextLabel("nationality").isPresent()
				&& new TextLabel("occupation").isPresent()
				&& new TextLabel("TableOfContents").isPresent()
				&& new TextLabel("BiographyEssay").isPresent() 
				&& new TextLabel("furtherReadings").isPresent());
	}
	
	private void navigateToDetailedPage() throws InterruptedException{
		for(int i = 1; i <= new Link(properties.get("viewAll_searchResults")).getLinkCount(); i++){
			if(new TextLabel(properties.get("viewAll_searchResults")+ "["+ i + "]/td[3]").getLabelText().contains("Biography")){
				new Link(properties.get("viewAll_searchResults")+ "[" + i + "]/td[2]/h3/a").click();
			}
		}
	}
	
	public boolean verifyTocIsDisplayed() throws InterruptedException{
		if(isNarrativeBiographyDocInfoTypePresent()){
			navigateToDetailedPage();
			return (new TextLabel(properties.get("toc")).isPresent());
		}
		return true;
	}
	
	public boolean verifyContentsInToc(){
		return (new Link(properties.get("biographipal_essay")).isPresent() &&
				new Link(properties.get("further_readings")).isPresent());
	}

}
