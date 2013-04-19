package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewNewsDocuments extends BasePageContainer {
	private TextLabel newsDisplayGroupLabel;

	public ViewNewsDocuments() throws Exception {
		super();
	}
	
	public boolean checkNewsDisplayGroupIsPresent () throws Exception{
		newsDisplayGroupLabel = new TextLabel(properties.get("news_display_group_title"));
		if(newsDisplayGroupLabel.isPresent() && newsDisplayGroupLabel.getLabelText().equals(properties.get("news")))
			return true;
		return false;
	}
	
	public boolean checkNewsContentLinksArePresent() throws Exception{
		
		if(checkNewsDisplayGroupIsPresent()){
			if(new TextLabel(properties.get("news_links")).getXpathCount() > 0)
				return true;
		}
		return false;
	}
	
	public boolean verifyDocumentIsDisplayed() throws InterruptedException, Exception{
		String locator = properties.get("news_links") + "[1]/h3/a";
		String firstDocumentTitle = new TextLabel(locator).getLabelText();
		Link firstDocumentLink = new Link(locator);
		
		Thread.sleep(2000);
		
		//Click on link to view news detail
		firstDocumentLink.click();
		
		Thread.sleep(2000);
		
		TextLabel newsDetailTitle = new TextLabel(properties.get("news_details_title"));
		
		String detailPagetitle = newsDetailTitle.getLabelText();
		
		//Now click back button of the browser 
		new Url().goBackToPreviousPage();
		
		Thread.sleep(2000);
		return detailPagetitle.equals(firstDocumentTitle);
	}

	/*
	public boolean verifyDetailedViewIsDisplayed() throws Exception{
		
		int noOfAudioArticles = new TextLabel(properties.get("news_links")).getXpathCount();
		for(int i = 1; i <= noOfAudioArticles; i++){
			Thread.sleep(2000);
			new Link(properties.get("news_links")+"["+i+"]"+ "/h3/a").click();
			Thread.sleep(2000);
			if(! (new TextLabel("//div[@class='content']").isPresent() && new TextLabel("//div[@class='source_citation']").isPresent())){
				return false;
			}
			new Url().goBackToPreviousPage();
		}
		Thread.sleep(2000);
		return true;
	}*/
}
