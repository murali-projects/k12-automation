package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class LinkToThirdPartyAudio extends BasePageContainer{
	private String audioTitle;
	
	public LinkToThirdPartyAudio() throws Exception {
		super();
		audioTitle = new Link(properties.get("audio_searchResults_xpath")+"/h3/a").getLinkText();
		selenium.windowMaximize();
	}
	
	public boolean verifySpeakerIconIsDisplayed() throws InterruptedException{
		new Link(properties.get("audio_searchResults_xpath")+"/h3/a").click();
		return new Link(properties.get("speaker_icon")).isPresent();
	}

	public boolean verifyAfterClickingSpeakerIcon() throws InterruptedException{
		String[] windowNames = selenium.getAllWindowNames();
		new Link(properties.get("speaker_icon")).clickWithoutWait();
//		String[] windowNames = selenium.getAllWindowNames();
		if(selenium.getAllWindowNames().length == windowNames.length+1)
			return true;
		/*if(windowNames[windowNames.length-1].equals("audioWebsite")){
			return true;
		}*/
		return false;
	}
	
	public boolean verifyAfterclickingSpeakerIconInNewWindow(){
		new Link(properties.get("speaker_icon")).clickWithoutWait();
		String[] windowNames = selenium.getAllWindowNames();
		return windowNames[windowNames.length-1].equals("");
		
	}
	
	public boolean verifyErrorMessageNotDisplayed(){
		return ! new TextLabel(properties.get("error_stacktrace")).isPresent();
	}
	
	public boolean verifyPlayPauseOptionsAvailable(){
		return new Link(properties.get("play")).isPresent() &&
			new Link(properties.get("pause")).isPresent();
	}
	
	public boolean verifyAudioTitle(){
//		new Url().selectWindow("audioWebsite");
//		selenium.close();
		new Url().selectWindow("");
		selenium.windowFocus();
		return new TextLabel(properties.get("podcast_content_document_Titlelink")).getLabelText().equals(audioTitle);
	}
}
