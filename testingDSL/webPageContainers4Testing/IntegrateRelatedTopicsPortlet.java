package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class IntegrateRelatedTopicsPortlet extends BasePageContainer{

	public IntegrateRelatedTopicsPortlet() throws Exception {
		super();
		}

	public boolean verifyTopicsAssociatedIsDisplayed() throws Exception{
		   return(new TextLabel(properties.get("topics_associated")).isPresent());
	}
	
	public boolean verifyOtherPortletPresent() throws Exception{
		return(new TextLabel(properties.get("news_Link")).isPresent()&&new TextLabel(properties.get("reference_Link")).isPresent());
			
	}
	
	public boolean verifyViewAllPresentForOtherBuckets() throws Exception{
		return(new TextLabel(properties.get("view_all_news")).isPresent()&&new TextLabel(properties.get("topics_associated_statistics")).isPresent());
	}
	
	public boolean verifyClickingTopicsInPortlet() throws Exception{
		new Link(properties.get("topic_inside_portlet")).click();
		return true;
	}
	
	public boolean verifyTopicsPortletNotPresentInOtherPages() throws Exception{
		new Link(properties.get("news_link")).click();
		return(!new TextLabel(properties.get("topics_associated")).isPresent());
		
	}
	
	public boolean verifyDetailedOverviewIsPresent() throws Exception{
		new Link(properties.get("topic_inside_portlet")).click();
		return(new TextLabel(properties.get("topics_detailed_overview")).isPresent());
		
	}
	
	public boolean verifyOtherBucketsalongWithDetailedOverview() throws Exception{
		new Link(properties.get("topic_inside_portlet")).click();
		return(new TextLabel(properties.get("news_Link")).isPresent()&&new TextLabel(properties.get("reference_Link")).isPresent());
		
		
	}
	
	
}
