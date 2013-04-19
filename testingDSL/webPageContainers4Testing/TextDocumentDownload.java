package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class TextDocumentDownload extends BasePageContainer{

	private String fileName = "S07912Document";
	private String url = "file://D:/CengageNew/";
	
	public TextDocumentDownload() throws Exception {
		super();
	}
	
	public boolean verifyDownloadOptionAvailable() throws InterruptedException{
		new Link(properties.get("primarysources_searchResults_xpath")+"/h3/a").click();
		return new Link(properties.get("download_link")).isPresent();
	}
	
	public boolean checkDownloadedHtmlDocument() throws Exception{
		new DownloadDocumentInHTMLFormat().downloadHtmlFile(fileName, "");
		selenium.open(url + fileName + ".html");
		Thread.sleep(2000);
		return verifyItemsDisplayedInHtmlDocument();
	}
	
	public boolean checkHtmlDocumentOfCriticalThinkingDocuments() throws Exception{
		new Link(properties.get("viewpoints_searchResults_xpath")+"/h3/a").click();
		new DownloadDocumentInHTMLFormat().downloadHtmlFile(fileName, "");
		selenium.open(url + fileName + ".html");
		Thread.sleep(2000);
		return  verifyItemsDisplayedInCriticalThinkingDocument();
	}
	
	public boolean verifyHtmlDocumentForAllDisplayGroups() throws Exception{
		return verifyHtmlDocumentForDisplayGroup("news")
				&& verifyHtmlDocumentForDisplayGroup("magazines")
				&& verifyHtmlDocumentForDisplayGroup("K12-Reference");
	}
	
	public boolean verifyRelatedTopicsAndToolsNotAvailableInHtmlDocument(){
		selenium.open(url + fileName + ".html");
		boolean areItemsPresent = !new TextLabel(properties.get("tools")).isPresent()
				&& !new TextLabel(properties.get("relatedsubjectstab")).isPresent();
		return areItemsPresent;
	}
	
	private boolean verifyHtmlDocumentForDisplayGroup(String displayGroup) throws Exception{
		new TextBox(properties.get("searchBox")).type("war");
	    new PageButton(properties.get("findButton")).clickAndWait();	
	    new Link(properties.get(displayGroup + "_searchResults_xpath")+"/h3/a").click();
	    return checkDownloadedHtmlDocument();
	}
	
	private boolean verifyItemsDisplayedInCriticalThinkingDocument(){
		String[] windowTitles = selenium.getAllWindowTitles();
		boolean areItemsDisplayed = !windowTitles[windowTitles.length-1].isEmpty()
				&& new TextLabel(properties.get("downloadedDocument_documentTitle")).isPresent() 
				&& new TextLabel(properties.get("gale_document")).isPresent() 
				&& new TextLabel(properties.get("source_citation")).isPresent()
				&& new TextLabel(properties.get("words_portlet")).isPresent()
				&& new TextLabel(properties.get("questionToTable")).isPresent()
				&& !new TextLabel(properties.get("take_quiz")).isPresent()
				//audio links count
				&& !(new TextLabel("//a[@class='words_to_know_term']/div/img").getXpathCount() == 0)
				&& !new TextLabel(properties.get("readSpeakerLink")).isPresent();
//				&& 	new TextLabel(properties.get("downloadedDocument_shortCitation")).isPresent()
//				&& 	new TextLabel(properties.get("downloadedDocument_furtherReadings")).isPresent();
		selenium.open(EnvironmentProperties.getInstance().getHomePageUrl());
		return areItemsDisplayed;
	}
	
	private boolean verifyItemsDisplayedInHtmlDocument(){
		String[] windowTitles = selenium.getAllWindowTitles();
		boolean areItemsDisplayed = !windowTitles[windowTitles.length-1].isEmpty()
				&& new TextLabel(properties.get("downloadedDocument_documentTitle")).isPresent()
				&& new TextLabel(properties.get("gale_document")).isPresent() 
				&& verifyNoHyperlinksExist() 
				&& new TextLabel(properties.get("source_citation")).isPresent(); 
//				&& new TextLabel(properties.get("downloadedDocument_shortCitation")).isPresent() 
//				&& new TextLabel(properties.get("downloadedDocument_furtherReadings")).isPresent();
		selenium.open(EnvironmentProperties.getInstance().getHomePageUrl());
		return areItemsDisplayed;
	}
	
	private boolean verifyNoHyperlinksExist(){
		for(int i = 1; i <= new TextLabel(properties.get("transcript_xpath")).getXpathCount(); i++){
			if(new TextLabel(properties.get("transcript_xpath") + "[" + i + "]/a").isPresent())
				return false;
		}
		return true;
	}
	
}
