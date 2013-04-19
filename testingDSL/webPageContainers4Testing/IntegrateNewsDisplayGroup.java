package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class IntegrateNewsDisplayGroup extends BasePageContainer{

	public IntegrateNewsDisplayGroup() throws Exception {
		super();
	}

	public boolean verifyNewsDisplayGroupPresent() throws Exception{
		if(new TextLabel(properties.get("news_searchResults_xpath")).isPresent()){
			return true;
		}
		return false;
	}
	
	public boolean verifyElementDisplayedOnPage(String element) throws Exception{
		if(new TextLabel(properties.get(element)).isPresent())
			return true;
	
		return false;
	} 
	
	public boolean verifyViewAllIsLink() throws Exception{
		return new Link(properties.get("news_viewAll")).isPresent();
	}
	
	public boolean verifyNewsTitlesAreLinks() throws Exception{
		boolean verify = true;
		for (int i = 1; i <= new TextLabel(properties.get("news_searchResults_xpath")).getXpathCount(); i++) {
			if(! new Link(properties.get("news_searchResults_xpath")+"["+i+"]/h3/a").isPresent()){
				verify = false;
				break;
			}
		}
		return verify;
	}
	
	public boolean verifyViewAllLinkIsWorking() throws Exception {
		new Link(properties.get("news_viewAll")).click();
		String GroupName = new TextLabel(properties.get("viewAll_searchResults")).getLabelText();
		return (!GroupName.isEmpty());
		
	}
	
	private int getResultsCount() throws Exception {
		if (new Link(properties.get("news_viewAll")).isPresent()) {
			String viewAllText = new Link(properties.get("news_viewAll"))
					.getLinkText();
			return Integer.parseInt(viewAllText.split(" ")[2].trim().replace(
					",", ""));
		}
		return 0;
	}
	
	public boolean verifyCountIsInLineWithViewAll() throws Exception {
		boolean validate = true;
		if (new Link(properties.get("news_viewAll")).isPresent()) {
			String viewAllText = new Link(properties.get("news_viewAll")).getLinkText();
			return (! viewAllText.split(" ")[2].isEmpty());
		}
		 return validate;
	}

	public boolean verifyNewsContentLinksArePresent() throws Exception{
		if (getResultsCount() >= 3) {
			return (new TextLabel(properties
					.get("news_searchResults_xpath")).getXpathCount() == 3);
		}
		return new TextLabel(properties.get("newss_searchResults_xpath"))
				.getXpathCount() == getResultsCount();
	}
	
	private List<String> getDataFromDB(String searchTerm, String displayGroup, String sortBy, String dataToRetrieve) throws IOException{
		List<String> list = OceanDatabaseReadFile.readResultsFromFile(properties.get("all"), displayGroup, sortBy,
				dataToRetrieve, searchTerm);
		return list;
	}
	
	public boolean validateSortOrder(String searchTerm, String displayGroup, String sortBy) throws Exception{
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= new TextLabel(properties.get("news_searchResults_xpath")).getXpathCount(); i++) {
			list.add(new Link(properties.get("news_searchResults_xpath")+"["+i+"]/h3/a").getLinkText());
		}
		return compareWithDB(list, getDataFromDB(searchTerm, displayGroup, sortBy, properties.get("documentTitle")));
	}
	
	public boolean validatePublisherNames(String searchTerm, String displayGroup, String sortBy) throws Exception{
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= new TextLabel(properties.get("news_searchResults_xpath")).getXpathCount(); i++) {
			if(! new TextLabel(properties.get("news_searchResults_xpath")+"["+i+"]/span").isPresent()){
				return false;
			}
			list.add(new TextLabel(properties.get("news_searchResults_xpath")+"["+i+"]/span").getLabelText());
		}
		return compareWithDB(list, getDataFromDB(searchTerm, displayGroup, sortBy, properties.get("publisher")));
	}
	
	public void selectSortElement(String option) throws Exception {
		new DropDownBox(properties.get("sort_by_xpath")).select(option);
		selenium.waitForPageToLoad("8000");
	}
	
	public boolean validateDocTypes(String searchTerm, String displayGroup, String sortBy) throws Exception{
		List<String> list = new ArrayList<String>();
		selectSortElement("Relevance");
		for (int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++) {
			list.add(new TextLabel(properties.get("viewAll_searchResults")+"["+i+"]/td[3]").getLabelText().replace(" ", ""));
		}
		new Url().goBackToPreviousPage();
		return compareWithDB(list, getDataFromDB(searchTerm, displayGroup, sortBy, properties.get("docTypes")));
	}
	
	private boolean compareWithDB(List<String> dataInAppl, List<String> dataInDB){
		for (int i = 0; i < dataInAppl.size(); i++) {
			if (!(dataInAppl.get(i).equals(dataInDB.get(i)))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean verifyIntroTextInlineWithDocTitle() throws InterruptedException, Exception{
		boolean verified = true;
		for (int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++) {
			if(! new TextLabel(properties.get("viewAll_searchResults")+"/td[2]/p").isPresent()){
				verified = false;
				break;
			}
		}
		new Url().goBackToPreviousPage();
		return verified;
	}
	
	public boolean verifyGroupName() throws Exception{
		String groupName = new TextLabel(properties.get("news_label")).getLabelText();
		return groupName.equals("News");
	}
	
	public boolean verifyPublicationDetailsAreDisplayed() throws Exception{
		for (int i = 1; i <= new TextLabel(properties.get("news_searchResults_xpath")).getXpathCount(); i++) {
			if(! (new TextLabel(properties.get("news_searchResults_xpath")+"["+i+"]"+properties.get("news_pubTitle")).isPresent() &&
					(new TextLabel(properties.get("news_searchResults_xpath")+"["+i+"]"+properties.get("news_pubTitle")).isPresent()))){
				return false;
			}
		}
		return true;
	}
	
	public boolean verifyNewsIconIsInLineWithNewsTitle() throws Exception{
		int noOfnewsArticles = new TextLabel(properties.get("news_searchResults_xpath")).getXpathCount();
		int noOfIcons = new TextLabel(properties.get("news_searchResults_xpath")+"/a/img").getXpathCount();
		
		return (noOfnewsArticles == noOfIcons)? true : false;
	}
	
	public boolean verifyContentAfterClickingNewsLink() throws Exception{
		String newsTitle = new Link(properties.get("news_searchResults_xpath")+"/h3/a").getLinkText();
		newsTitle =newsTitle.substring(0, newsTitle.length()-1);
		new Link(properties.get("news_searchResults_xpath")+"/h3/a").click();
		return new TextLabel(properties.get("news_title_after_clicking")).getLabelText().contains(newsTitle);
	}
	
	public boolean verifyNewsLinkOpensInNewWindow() throws Exception{
		String url = new Url().getUrl();
		new Link(properties.get("news_searchResults_xpath")+"/h3/a").click();
		String currentUrl = new Url().getUrl();
		new Url().goBackToPreviousPage();
		return (! url.equals(currentUrl));
	}
	
	public boolean verifyLinkBackOptionAvailable() throws Exception{
		new Link(properties.get("news_searchResults_xpath")+"/h3/a").click();
		return new Link(properties.get("news_viewAllPage_backLink")).isPresent();
	}
	
	public boolean verifyContentAfterClickingNewsIcon() throws Exception{
		String newsTitle = new Link(properties.get("news_searchResults_xpath")+"/h3/a").getLinkText();
		new Link(properties.get("news_searchResults_xpath")+"/a/img").click();
		return new TextLabel(properties.get("news_viewAllPage_backLink")).getLabelText().equals(newsTitle);
	}
}
