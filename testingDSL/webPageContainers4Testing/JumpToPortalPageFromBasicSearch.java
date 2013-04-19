package webPageContainers4Testing;

import java.awt.Button;
import java.awt.TextField;

import webPageElements4Testing.PageButton;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class JumpToPortalPageFromBasicSearch extends BasePageContainer {

	public JumpToPortalPageFromBasicSearch() throws Exception {
		super();
	}

	public boolean verifyNavigationByEnteringTopic() throws Exception{
		return (new PageElementWithIdAttribute(properties.get("topics_detailed_overview"))
		.isPresent());

	}
	
	
	
	public boolean verifyNavigationByEnteringNonTopicSearchTerm() throws Exception{
		 return(new TextLabel(properties.get("topics_associated")).isPresent());
	
	}
	
	public boolean verifyTopicRelatedPage()throws Exception{
		
		if (new PageElementWithIdAttribute(properties.get("topics_detailed_overview"))
		.isPresent()&&new PageElementWithIdAttribute(properties.get("news_link"))
		.isPresent())
	return true;
	return false;
	}
}
