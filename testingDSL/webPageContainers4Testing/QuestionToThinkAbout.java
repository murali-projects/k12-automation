package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class QuestionToThinkAbout extends BasePageContainer{
	

	public QuestionToThinkAbout() throws Exception {
		super();
	
	}

	public boolean verifyViewPointsDisplayGroup() throws InterruptedException {
		int count = new Link(properties.get("viewpoints_searchResults_xpath")).getLinkCount();
		if (count > 0)
		return true;
		return false;
	}

	public boolean verifyDetailedDocument() throws InterruptedException {
		String title= new TextLabel(properties.get("viewpoints_searchResults_xpath")+"[1]/h3/a").getLabelText();
		new Link(properties.get("viewpoints_searchResults_xpath")+"[1]/h3/a").click();
		String titleInDoc = new TextLabel(properties.get("titleXpath")).getLabelText();
		if(title.equals(titleInDoc))
		return true;
		return false;
			
	}

	public boolean verifyQuestionTable() {
		if(new TextLabel(properties.get("questionToTable")).isPresent())
		return true;
		return false;
	}

	public boolean verifyQuestionsInNumbered() {
		if(new TextLabel(properties.get("questionTableNumberFormat")).isPresent())
			return true;
			return false;
	}

	public boolean verifyNotDisplayed(String displayGroup) throws InterruptedException {
		new Link(properties.get(displayGroup+"_searchResults_xpath")+"[1]/h3/a").click();
		if(!new TextLabel(properties.get("questionToTable")).isPresent())
			return true;
			return false;
	}
 

	
	
	
}
