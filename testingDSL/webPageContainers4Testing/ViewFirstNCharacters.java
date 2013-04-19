package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewFirstNCharacters extends BasePageContainer{

	private String descriptionInSearchResultsPage;
	private String descriptionInViewAllPage;
	
	public ViewFirstNCharacters() throws Exception {
		super();
	}
	
	public boolean verifyDescriptionIsPresent() {
		return isDescriptionPresentForDisplayGroup("reference")
				&& isDescriptionPresentForDisplayGroup("news")
				&& isDescriptionPresentForDisplayGroup("magazines")
				&& isDescriptionPresentForDisplayGroup("primarysources")
				&& isDescriptionPresentForDisplayGroup("journals");
	}
	
	private boolean isDescriptionPresentForDisplayGroup(String displayGroup){
		for(int i = 1; i <= new TextLabel(properties.get(displayGroup+ "_searchResults_xpath")).getXpathCount(); i++){
			if(! new TextLabel(properties.get(displayGroup+ "_searchResults_xpath")+properties.get("document_description_searchResultsPage")).isPresent())
				return false;
		}
		return true;
	}
	
	public boolean verifyDescriptionInDetailedPage() throws InterruptedException{
		String description = new TextLabel(properties.get("news_searchResults_xpath")+properties.get("document_description_searchResultsPage")).getLabelText();
		descriptionInSearchResultsPage = description.substring(0, description.length()-3);
		new Link(properties.get("news_searchResults_xpath")+"/h3/a").click();
		String descriptionInDetailedPage = new TextLabel(properties.get("document_description_detailedPage")).getLabelText();
		return descriptionInDetailedPage.startsWith(descriptionInSearchResultsPage);
	}
	
	public boolean verifyDesriptionFromViewAllPage() throws InterruptedException{
		new Url().goBackToPreviousPage();
		new Link(properties.get("news_viewAll")).click();
		String description = new TextLabel(properties.get("viewAll_searchResults")+"/td[2]"+properties.get("document_description_viewAllPage")).getLabelText();
		descriptionInViewAllPage = description.substring(0, description.length()-3);
		new Link(properties.get("viewAll_searchResults")+"/td[2]/h3/a").click();
		String descriptionInDetailedPage = new TextLabel(properties.get("document_description_detailedPage")).getLabelText();
		return descriptionInDetailedPage.startsWith(descriptionInViewAllPage);
	}

	public boolean verifyDescIsSameInBucketAndViewAllPage(){
		return descriptionInSearchResultsPage.equals(descriptionInViewAllPage);
	}
}
