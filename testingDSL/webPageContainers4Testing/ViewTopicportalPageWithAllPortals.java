package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import k12.BaseWebPageTest;

public class ViewTopicportalPageWithAllPortals extends BasePageContainer{

	public ViewTopicportalPageWithAllPortals() throws Exception {
		super();
	}

	public boolean verifyUserisInportalPageAfterclickingTopic() throws Exception{
		//new Link(properties.get("topic_inside_portlet")).click();
		return (new PageElementWithIdAttribute(properties.get("topic_inside_portlet"))
				.isPresent());

	} 
	
	public boolean verifyPortletsInPortalPage()throws Exception{
		new Link(properties.get("topic_inside_portlet")).click();
		if(new PageElementWithIdAttribute(properties.get("topics_detailed_overview"))
		.isPresent()&&new PageElementWithIdAttribute(properties.get("expertsPicks_portal"))
		.isPresent()&&new PageElementWithIdAttribute(properties.get("news_Link"))
		.isPresent()&&new PageElementWithIdAttribute(properties.get("reference_Link"))
		.isPresent()&&new PageElementWithIdAttribute(properties.get("statistics_Link"))
		.isPresent()&&new PageElementWithIdAttribute(properties.get("websites_label"))
		.isPresent())
			return true;
		return false;
		
	}
	
	public boolean verifyPageforKeywordNotHavingPortalPage()throws Exception{
		return (!new PageElementWithIdAttribute(properties.get("topics_detailed_overview"))
				.isPresent());

	}
	
	public boolean verifyPortletsFromOceanServices()throws Exception{
		//Need to update after getting clarification of how to verify		
		
		
		return true;
	}
	
	
	public boolean verifyDifferentMethodsForNavigationToPortalPage()throws Exception{
		new Link(properties.get("topic_inside_portlet")).click();
		if(new PageElementWithIdAttribute(properties.get("topics_detailed_overview"))
		.isPresent())
		{
			new TextBox(properties.get("searchBox")).type("contraception");
			new PageButton(properties.get("findButton")).clickAndWait();
			return (new PageElementWithIdAttribute(properties.get("topics_detailed_overview"))
			.isPresent());
		}
		return false;
	}
	
	public boolean verifyBasicSearchOptionFromPortalPage()throws Exception{
		return (new PageElementWithIdAttribute(properties.get("searchBox"))
		.isPresent());
	}
	
	public boolean verifyBreadcrumb() throws Exception{
		return new TextLabel(properties.get("breadcrumb_navigation_list") + "[2]").getLabelText().contains("Search Results");
	}
	
}
