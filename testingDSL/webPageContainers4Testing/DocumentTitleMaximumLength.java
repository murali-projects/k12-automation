package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class DocumentTitleMaximumLength extends BasePageContainer{

	private String displayGroup;
	public DocumentTitleMaximumLength(String displayGroup) throws Exception {
		super();
		this.displayGroup = displayGroup;
	}
	
	public boolean verifyElipsesDisplayedInSearchResultsPage() throws InterruptedException{
		String documentTitleInSearchResultsPage;
		for(int i = 1; i <= new TextLabel(properties.get(displayGroup + "_searchResults_xpath")).getXpathCount(); i++){
			documentTitleInSearchResultsPage = new TextLabel(properties.get(displayGroup + "_searchResults_xpath")+ "["+i+ "]/h3/a").getLabelText();
			int documentTitleLength = getDocumentTitleInDetailedPage(properties.get(displayGroup + "_searchResults_xpath")+ "["+i+ "]/h3/a").length();
			new Url().goBackToPreviousPage();
			if(documentTitleLength > 30){
				if(!documentTitleInSearchResultsPage.endsWith("..."))
					return false;
			}
		}
		return true;
	}
	
	private String getDocumentTitleInDetailedPage(String locator) throws InterruptedException{
		new Link(locator).click();
		String documentTitle = new TextLabel(properties.get("detail_page")).getLabelText();
		return documentTitle;
	}
	
	public boolean verifyDetailedPageDocTitleFromSearchResultsPage() throws InterruptedException{
		String documentTitleInSearchResultsPage;
		for(int i = 1; i <= new TextLabel(properties.get(displayGroup + "_searchResults_xpath")).getXpathCount(); i++){
			documentTitleInSearchResultsPage = new TextLabel(properties.get(displayGroup + "_searchResults_xpath")+ "["+i+ "]/h3/a").getLabelText();
			if(documentTitleInSearchResultsPage.length() > 30){
				if(!verifyDocumentTitleInDetailedPage(properties.get(displayGroup + "")+ "["+i+ "]/h3/a"))
					return false;
				}
			}
		return true;
	}
	
	private boolean verifyDocumentTitleInDetailedPage(String locator) throws InterruptedException {
		String documentTitle = new Link(locator).getLinkText();
		if (documentTitle.endsWith("...")) {
			String documentTitleInDetailedPage = getDocumentTitleInDetailedPage(locator);
			new Url().goBackToPreviousPage();
			if (!documentTitleInDetailedPage.startsWith(documentTitle))
				return false;
		}
		new Url().goBackToPreviousPage();
		return true;
	}
	
	public boolean verifyDocumentTitleLengthInViewAllPage() throws InterruptedException{
		new Link(properties.get(displayGroup + "_viewAll")).click();
		for(int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++){
			if(new TextLabel(properties.get("viewAll_searchResults")+ "["+i +"]/td[2]/h3/a").getLabelText().length() > 30){
				new Url().goBackToPreviousPage();
				return false;
			}
		}
		new Url().goBackToPreviousPage();
		return true;
	}
	
	public boolean verifyElipsesDisplayedInViewAllPage() throws InterruptedException{
		String documentTitleInViewAllPage;
		int count = new TextLabel(properties.get("viewAll_searchResults")).getXpathCount();
		for(int i = count; i <= count-3; i++){
			documentTitleInViewAllPage = new TextLabel(properties.get("viewAll_searchResults")+ "["+i +"]/td[2]/h3/a").getLabelText();
			int documentTitleLength = getDocumentTitleInDetailedPage(properties.get("viewAll_searchResults")+ "["+i +"]/td[2]/h3/a").length();
			new Url().goBackToPreviousPage();
			if(documentTitleLength > 30){
				if(!documentTitleInViewAllPage.endsWith("..."))
					return false;
			}
		}
		return true;
	}
	
	public boolean verifyDetailedPageDocTitleFromViewAllPage() throws InterruptedException{
		int count = new TextLabel(properties.get("viewAll_searchResults")).getXpathCount();
		for(int i = count; i <= count-3; i++){
			if(!verifyDocumentTitleInDetailedPage(properties.get("viewAll_searchResults")+ "["+i +"]/td[2]/h3/a"))
				return false;
		}
		return true;
	}
	
	public boolean verifyNoElipsesAreDisplayedInDetailedPage() throws InterruptedException{
		new Link(properties.get(displayGroup + "_searchResults_xpath")+ "/h3/a").click();
		String detailedDocTitleFromSearchResultsPage = new TextLabel(properties.get("detail_page")).getLabelText();
		new Url().goBackToPreviousPage();
		new Link(displayGroup+ "_viewAll").click();
		String detailedDocTitleFromViewAllPage = new TextLabel(properties.get("viewAll_searchResults")+ "/td[2]/h3/a").getLabelText();
		new Url().goBackToPreviousPage();
		return (!detailedDocTitleFromSearchResultsPage.endsWith("...") && !detailedDocTitleFromViewAllPage.endsWith("..."));
	}
	
	public boolean verifyDocumentTitlesLessThanMaxlengthAreAccessible() throws InterruptedException{
		String documentTitle;
		for(int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++){
			documentTitle = new TextLabel(properties.get("viewAll_searchResults")+ "["+i +"]/td[2]/h3/a").getLabelText();
			if(documentTitle.length() <= 30){
				new Link(properties.get("viewAll_searchResults")+ "["+i +"]/td[2]/h3/a").click();
				return getDocumentTitleInDetailedPage(properties.get("viewAll_searchResults")+ "["+i +"]/td[2]/h3/a").isEmpty();
			}
		}
		return true;
	}
	
	public boolean verifyDocTitleLengthForAllBuckets(){
		for(int i = 1; i <= new TextLabel(properties.get("displayGroups_searchResultsPage")).getXpathCount(); i++){
			for(int j = 1; j <= new TextLabel(properties.get("displayGroups_searchResultsPage")+"[" +i +"]"+ properties.get("searchResults")).getXpathCount(); j++){
				String docTitle = new TextLabel(properties.get("displayGroups_searchResultsPage")+ "[" +i+ "]" +properties.get("searchResults")+"["+i+"]/h3/a").getLabelText();
				if(docTitle.length() > 30)
					return false;
			}
		}
		return true;
	}
}
