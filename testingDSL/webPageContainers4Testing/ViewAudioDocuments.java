package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewAudioDocuments extends BasePageContainer{

	
	private TextLabel audioDisplayGroupLabel;
	Link audioContentItem;
	
	public ViewAudioDocuments() throws Exception {
		super();
		audioContentItem = new Link(properties.get("audio_content_item"));
	}
	
	public boolean checkAudioArticlesDisplayGroupIsPresent () throws Exception{
		audioDisplayGroupLabel = new TextLabel(properties.get("audio_display_group_title"));
		if(audioDisplayGroupLabel.isPresent() && audioDisplayGroupLabel.getLabelText().equals(properties.get("audio")))
			return true;
		return false;
	}
	
	public boolean checkAudioContentLinksArePresent() throws Exception{
		
		if(checkAudioArticlesDisplayGroupIsPresent()){
			if(new TextLabel(properties.get("audio_article_links")).getXpathCount() > 0)
				return true;
		}
		return false;
	}
	
	public boolean verifyContentItemIsViewed() throws InterruptedException, Exception{
		
		//Click on the content items of audio
		audioContentItem.click();
		
		//Now click back button of the browser 
		new Url().goBackToPreviousPage();
		
		if(verifyContentItem(audioContentItem)){
			return true;
		}
		return false;
	}
	
	private boolean verifyContentItem(Link contentItem) throws Exception{

		// Move mouse over the audio content item to verify the text
		selenium.mouseOver(contentItem.getLinkText());

		// Put focus on the tooltip to retrieve the text
		new Url().focus("//");

		String msg = new TextLabel("").getLabelText();
		return msg.equals(properties.get("tooltip_message")) ? true : false;
	}
	
	public boolean verifyDetailedViewIsDisplayed() throws Exception{
		
		int noOfAudioArticles = new TextLabel(properties.get("audio_article_links")).getXpathCount();
		for(int i = 1; i <= noOfAudioArticles; i++){
			Thread.sleep(2000);
			new Link(properties.get("audio_article_links")+"["+i+"]"+ "/h3/a").click();
			
			if(! (new TextLabel(properties.get("audio_details")).isPresent() && new TextLabel(properties.get("audio_source")).isPresent())){
				return false;
			}
			new Url().goBackToPreviousPage();
		}
		return true;
	}
}
