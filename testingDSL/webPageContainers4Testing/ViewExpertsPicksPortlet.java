package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewExpertsPicksPortlet extends BasePageContainer{
	private List<String> topicsList;
	
	public ViewExpertsPicksPortlet() throws Exception {
		super();
		storeTopics();
	}
	
	private void storeTopics(){
		topicsList = new ArrayList<String>();
		for(int i = 1; i <= new Link(properties.get("expertsPicks_portal_links")).getLinkCount(); i++){
			topicsList.add(new Link(properties.get("expertsPicks_portal_links")+ "[" +i+ "]/h3/a").getLinkText());
		}
	}
	
	public boolean verifyTopicsCount(){
		return new Link(properties.get("expertsPicks_portal_links")).getLinkCount() <= 10;
	}
	
	public boolean verifyElementNotPresent(String element){
		return !new Link(properties.get(element)).isPresent();
	}
	
	public boolean verifyTopicsAreAccessible() throws InterruptedException{
		String homePageUrl = new Url().getUrl();
		for(int i = 1; i <= new Link(properties.get("expertsPicks_portal_links")).getLinkCount(); i++){
			new Link(properties.get("expertsPicks_portal_links")+ "[" +i+ "]/h3/a").click();
			if(new Url().getUrl().equals(homePageUrl))
				return false;
			new Url().goBackToPreviousPage();
		}
		return true;
	}
	
	public boolean verifyTopicOverviewIsDisplayed() throws InterruptedException{
		new Link(properties.get("expertsPicks_portal_links")+ "/h3/a").click();
		return new TextLabel(properties.get("detail_page")).isPresent();
	} 
	
	public boolean verifyViewFullOverviewLinkIsDisplayed() throws InterruptedException{
		return new Link(properties.get("viewFullOverview_link")).isPresent();
	} 
	
	public boolean verifyViewFullOverviewLinkIsAccessible() throws InterruptedException{
		String urlBeforeClickingLink = new Url().getUrl();
		new Link(properties.get("viewFullOverview_link")).click();
		return (!new Url().getUrl().equals(urlBeforeClickingLink) && !new TextLabel(properties.get("error_stacktrace")).isPresent());
	}
	
	public boolean verifyOtherTopicLinksNotPresent(){
		for(int i = 1; i <= new Link(properties.get("topicPortal_links")).getLinkCount(); i++){
			if(! new TextLabel(topicsList.get(i)).isPresent())
				return false;
		}
		return true;
	}
}
