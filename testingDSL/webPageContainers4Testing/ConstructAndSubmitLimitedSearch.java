package webPageContainers4Testing;

import java.io.IOException;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class ConstructAndSubmitLimitedSearch extends BasePageContainer{

	private String searchTerm;
	public ConstructAndSubmitLimitedSearch(String searchTerm) throws Exception {
		super();
		this.searchTerm = searchTerm;
	}
	
	public boolean verifyLimitSearchByPresent() throws InterruptedException{
		new Link(properties.get("news_viewAll")).click();
		return new TextLabel(properties.get("limit_searchby_text")).isPresent()
			&& new TextLabel(properties.get("limit_searchBy_subjects")).isPresent()
			&& new TextLabel(properties.get("limit_searchBy_docTypes")).isPresent()
			&& new TextLabel(properties.get("limit_searchBy_pubTitles")).isPresent();
	}

	public boolean verifyCountAfterLimitSearch() throws InterruptedException{
		String linkText = new Link(properties.get("limit_search_firstelement")).getLinkText();
		String count = linkText.substring(linkText.indexOf('(')+1,linkText.indexOf(')')); 
		new Link(properties.get("limit_search_firstelement")).click();
		return new TextLabel(properties.get("totalSearchResults")).getLabelText().replace(",", "").equals(count);
	}
	
	public boolean verifyLimitByPublicationTitleWorking() throws InterruptedException{
		String publicationTitleText = new Link(properties.get("limit_searchBy_pubTitles")+"/ul/li/a").getLinkText();
		String publicationTitle = publicationTitleText.substring(0, publicationTitleText.indexOf('(')).trim();
		new Link(properties.get("limit_searchBy_pubTitles")+"/ul/li/a").click();
		for(int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++){
			if(!new TextLabel(properties.get("viewAll_searchResults")+ "[" +i + "]/td[2]/span[@class='publication_title']").getLabelText().replace(",", "").equals(publicationTitle)){
				return false;
			}
		}
		return true;
	}
	
	public boolean verifyResultsAreReducedWithMoreLimiters() throws InterruptedException{
		int totalCount = Integer.parseInt(new TextLabel(properties.get("totalSearchResults")).getLabelText().replace(",", ""));
		new Link(properties.get("limit_searchBy_docTypes")+"/ul/li/a").click();
		int countAfterMultipleLimiters = Integer.parseInt(new TextLabel(properties.get("totalSearchResults")).getLabelText().replace(",", ""));
		return countAfterMultipleLimiters <= totalCount;
	}
	
	public boolean verifyLimitByResultsCountWithDB(String publicationTitle, String documentType, String subject, String displayGroup, String limitByQuery) throws Exception{
		search();
		if(!new Link(properties.get(displayGroup + "_viewAll")).isPresent())
			return true;
		new Link(properties.get(displayGroup + "_viewAll")).click();
		searchByLimiter("limit_searchBy_pubTitles", publicationTitle);
		searchByLimiter("limit_searchBy_docTypes", documentType);
		searchByLimiter("limit_searchBy_subjects", subject);
		return verifyCountWithDB(publicationTitle, documentType, subject, limitByQuery);
	}
	
	private void search() throws Exception{
		new TextBox(properties.get("searchBox")).type(searchTerm);
		new PageButton(properties.get("findButton")).clickAndWait();	
	}
	
	private boolean verifyCountWithDB(String publicationTitle, String documentType, String subject, String limitByQuery) throws IOException{
		int countInDB = Integer.parseInt(OceanDatabaseReadFile.readQueryResultsCountFromFile("", limitByQuery).get(0));
		int countInAppl = Integer.parseInt(new TextLabel(properties.get("totalSearchResults")).getLabelText().replace(",", ""));
		return countInAppl == countInDB;
	}
	
	private void searchByLimiter(String limiterType, String linkText) throws InterruptedException{
		if(!(limiterType.isEmpty() && linkText.isEmpty()))
			new Link(properties.get(limiterType)+"/ul/descendant::li/a[contains(text(),'" +linkText +"')]").click();
	}
	
}
