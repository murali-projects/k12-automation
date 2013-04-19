package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewVideoDocument extends BasePageContainer{

	private String currentUrl;
	
	public ViewVideoDocument() throws Exception {
		super();
		currentUrl= new Url().getUrl();
	}
	
	public boolean verifyTopicPortalTabIsPresent() throws Exception{
		return(new TextLabel(properties.get("VideoTopicPortalTabXpath")).isPresent());
	}
	public boolean verifyTopicPortalTabIsWorking() throws Exception{
		new Link(properties.get("VideoTopicPortalTabXpath")).click();
		return(currentUrl.contains("videocontent.jsp") && currentUrl
			.contains("windowstate=maximized"));
	}
	public boolean verifyContentLinksIsDisplayed() throws Exception{
		for(int i=0;i<=2;i++){
			if(!(new TextLabel(properties.get("VideoDocumentPageContentLinksXpath")+ "["+i+"]").isPresent())){
				return false;
			}
		}
		return true;
	}

	public boolean verifyContentLinksIsWorking() throws Exception{
		new Link(properties.get("VideoDocumentPageContentLinksXpath ")).click();
		return(currentUrl.contains("videocontent.jsp") && currentUrl
			.contains("windowstate=maximized"));
	}

}
