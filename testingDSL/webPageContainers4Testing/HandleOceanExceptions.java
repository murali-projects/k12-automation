package webPageContainers4Testing;

import webPageElements4Testing.TextLabel;

public class HandleOceanExceptions extends BasePageContainer{

	public HandleOceanExceptions() throws Exception {
		super();
	}
	
	public boolean verifyErrorPageIsNotDisplayed(){
		return !new TextLabel("errorStackTrace").isPresent();
	}
	
	public boolean verifyCautionMessagesAreDisplayed(){
		String cautionMessages = new TextLabel(properties.get("caution_messages_xpath")).getLabelText();
		return new TextLabel(properties.get("searchResults_msg_xpath")).isPresent() &&
			cautionMessages.contains(properties.get("noResults_message")) &&
			cautionMessages.contains(properties.get("incorrectSpelling_message"));
	}
	
	public boolean verifyResultsAreDisplayed(){
		return (new TextLabel(properties.get("reference_searchResults_xpath")).isPresent() ||
			new TextLabel(properties.get("news_searchResults_xpath")).isPresent() ||
			new TextLabel(properties.get("magazines_searchResults_xpath")).isPresent() ||
			new TextLabel(properties.get("images_searchResults_xpath")).isPresent() ||
			new TextLabel(properties.get("primarysources_searchResults_xpath")).isPresent() ||
			new TextLabel(properties.get("statistics_searchResults_xpath")).isPresent() ||
			new TextLabel(properties.get("viewpoints_searchResults_xpath")).isPresent() ||
			new TextLabel(properties.get("audio_searchResults_xpath")).isPresent() ||
			new TextLabel(properties.get("videos_searchResults_xpath")).isPresent() ||
			new TextLabel(properties.get("academicJournals_searchResults_xpath")).isPresent() ||
			new TextLabel(properties.get("websites_searchResults_xpath")).isPresent());
	}
	
	public boolean verifyOnlyCautionMessagesDisplayed(){
		return (verifyCautionMessagesAreDisplayed());
				//&& ! new TextLabel(properties.get("")).isPresent());
	}
}
