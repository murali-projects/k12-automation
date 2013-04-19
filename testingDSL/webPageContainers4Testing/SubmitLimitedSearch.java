package webPageContainers4Testing;

import webPageContainers4Testing.BasePageContainer;
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class SubmitLimitedSearch extends BasePageContainer{
	private int totalResultsCount;
	public SubmitLimitedSearch() throws Exception {
		super();
	}
	
	public boolean verifyBasicSearchResultsAreDisplayed() throws InterruptedException{
		boolean areResultsDisplayed = false;
		for(int i = 1; i <= new TextLabel(properties.get("displayGroups_searchResultsPage")).getXpathCount(); i++){
			if(new TextLabel(properties.get("displayGroups_searchResultsPage")+ "[" +i+ "]" +properties.get("searchResults")+"/h3/a").isPresent()){
				areResultsDisplayed = true;
				break;
			}
		}
		return areResultsDisplayed && isViewAllLinkWorking();
	}
	
	private boolean isViewAllLinkWorking() throws InterruptedException{
		for(int i = 1; i <= new TextLabel(properties.get("displayGroups_searchResultsPage")).getXpathCount(); i++){
			if(new Link(properties.get("displayGroups_searchResultsPage")+ "[" +i+ "]" +"/a").isPresent()){
				new Link(properties.get("displayGroups_searchResultsPage")+ "[" +i+ "]" +"/a").click();
				return (new TextLabel(properties.get("currentResults")).isPresent() &&
						new TextLabel(properties.get("totalResults")).isPresent());
			}
		}
		return true;
	}
	
	public boolean verifyLimitByDropdownPresent(){
		return new DropDownBox(properties.get("limitBy_dropdrown")).isPresent();
	}
	
	public boolean verifyResultsAfterApplyingLimitSearch() throws InterruptedException{
		totalResultsCount = Integer.parseInt(new TextLabel(properties.get("totalResults")).getLabelText().replace(",", ""));
		String firstDocTitle = new TextLabel(properties.get("viewAll_searchResults")+"/td[2]/h3/a").getLabelText();
		new DropDownBox(properties.get("limitBy_dropdrown")).select("Publication Title");
		//click first link
		new Link("").click();
		return !new TextLabel(properties.get("viewAll_searchResults")+"/td[2]/h3/a").getLabelText().equals(firstDocTitle);
	}
	
	public boolean verifyResultsCountAfterLimitBySearch(){
		return Integer.parseInt(new TextLabel(properties.get("totalResults")).getLabelText().replace(",", "")) < totalResultsCount;
	}
	
	public boolean verifyResultsCountAfterApplyingLimitSearchAgain() throws InterruptedException{
		totalResultsCount = Integer.parseInt(new TextLabel(properties.get("totalResults")).getLabelText().replace(",", ""));
		new DropDownBox(properties.get("limitBy_dropdrown")).select("Subject");
		//click first link
		new Link("").click();
		return Integer.parseInt(new TextLabel(properties.get("totalResults")).getLabelText().replace(",", "")) < totalResultsCount;
	}
	
	public boolean verifyAfterApplyingMultipleLimitersSearch(){
		new DropDownBox(properties.get("limitBy_dropdrown")).select("Document Type");
		new DropDownBox(properties.get("limitBy_dropdrown")).select("Publication Title");
		new DropDownBox(properties.get("limitBy_dropdrown")).select("Subject");
		//TODO:Implementation not yet completed
		return false;
	}

	public boolean verifyLimitSearchIsNotAppliedForOtherDisplayGroups(){
		//TODO:Implementation not yet completed
		return false;
	}
}
