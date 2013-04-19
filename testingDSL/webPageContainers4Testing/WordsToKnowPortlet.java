package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class WordsToKnowPortlet extends BasePageContainer{

	public WordsToKnowPortlet() throws Exception {
		super();
		
	}

	public boolean verifyDisplayGroup() {
	 return (new TextLabel(properties.get("viewpoints_label")).isPresent());

	}
 
	public boolean verifyDetailedDocument() throws InterruptedException {
		String title= new TextLabel(properties.get("viewpoints_searchResults_xpath")+"[1]/h3/a").getLabelText();
		new Link(properties.get("viewpoints_searchResults_xpath")+"[1]/h3/a").click();
		String titleInDoc = new TextLabel(properties.get("titleXpath")).getLabelText();
		return(title.equals(titleInDoc));

			
	}

	public boolean verifyWordsPortlet() {
		return (new TextLabel(properties.get("words_portlet")).isPresent());
   
	}

	public boolean verifyWordsPortletHighlighted() {
		for(int i=1 ; i<=7; i++){
			if(!new TextLabel(properties.get("words_portlet_highlighted")+ i +"]/span[2]").isPresent())
				return false;
		}
         return true;		
		
	}

	public boolean verifyDefinitionOfWord() {
		//new Url().mouseOver(properties.get("words_portlet_highlighted"));
		for(int i=1; i<=7;i++){
		if(!new TextLabel(properties.get("words_portlet_highlighted")+ i+"]/span[4]").isPresent())
		return false;	
		}
	return true;
	}

	public boolean verifyWordsList() {
		for(int i=1;i<=7;i++){
			
		if(!(new TextLabel(properties.get("words_portlet_highlighted")+ i +"]/span[1]").isPresent()
				&&
				new TextLabel(properties.get("words_portlet_highlighted")+ i +"]/span[2]").isPresent()
				&&
				new TextLabel(properties.get("words_portlet_highlighted")+ i +"]/span[4]").isPresent()))
	return false;
		}
	return true;
	}
	public boolean verifyWordsToKnowHeading() {
		return new TextLabel(properties.get("words_to_know_heading")).isPresent();
	}
	
	public boolean verifyMeaningandPartsOfSpeechAvailable() {
		for(int i=1;i<=7;i++){
			
			if(!(new TextLabel(properties.get("words_portlet_highlighted")+ i +"]/span[2]").isPresent()
					&&
					new TextLabel(properties.get("words_portlet_highlighted")+ i +"]/span[3]").isPresent()
					&&
					new TextLabel(properties.get("words_portlet_highlighted")+ i +"]/span[4]").isPresent()))
		return false;
			}
		return true;
	
}
}
