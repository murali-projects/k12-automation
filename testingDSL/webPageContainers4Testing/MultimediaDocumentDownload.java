package webPageContainers4Testing;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import utils.RobotWebBrowser;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class MultimediaDocumentDownload extends BasePageContainer{
	
	private String fileName = "S07912Document";
	private String url = "file://D:/CengageNew/";
	private boolean isDisclaimerPresent;
	private String searchTerm;
	public MultimediaDocumentDownload(String searchTerm) throws Exception {
		super();
		this.searchTerm = searchTerm;
	}
	
	public boolean checkDocumentToolsArePresent(String displaygroup) throws InterruptedException{
		new Link(properties.get(displaygroup + "_search_link")).click();
		checkDisclaimerPresent();
		return new TextLabel(properties.get("videos_print")).isPresent()
			&& new TextLabel(properties.get("videos_download")).isPresent()
			&& new TextLabel(properties.get("videos_email")).isPresent()
			&& new TextLabel(properties.get("videos_translate")).isPresent();
	}
	
	public boolean verifyToolsAreAccessible() throws InterruptedException, AWTException{
		return verifyEmailLinkAccessible()
			&& verifyDownloadLinkAccessible();
	}
	
	public boolean checkDetailsInPrintPreviewDocument() throws Exception{
		new Link(properties.get("videos_print")).clickWithoutWait();
		String[] windowNames = selenium.getAllWindowNames();
		selenium.selectWindow(windowNames[windowNames.length-1]);
		selenium.windowFocus();
		RobotWebBrowser.typeKey(KeyEvent.VK_ESCAPE, 1000);
		return verifyPrintDocument();
	}
	
	public boolean checkDetailsForDownloadAndTranslate(String displayGroup) throws Exception{
		return checkDetailsInDownloadedHtmlDocument(displayGroup)
				&& checkDetailsInTranslateDocument();
	}
	
	public boolean checkDetailsInDownloadedHtmlDocument(String displayGroup) throws Exception{
		new DownloadDocumentInHTMLFormat().downloadHtmlFile(fileName, "");
		selenium.open(url + fileName + ".html");
		Thread.sleep(2000);
		return verifyDownloadedDocument(displayGroup);
	}
	
	public boolean checkDetailsInTranslateDocument(){
		new Link(properties.get("videos_translate")).clickWithoutWait();
		new PageButton(properties.get("translate_button")).click();
		String[] windowTitles = selenium.getAllWindowTitles();
		selenium.selectWindow(windowTitles[windowTitles.length-1]);
		selenium.windowFocus();
		boolean areItemsDisplayed = !windowTitles[windowTitles.length-1].isEmpty()
			&& new TextLabel(properties.get("disclaimer")).isPresent()
			&& new TextLabel(properties.get("gale_document")).isPresent() 
			&& new TextLabel(properties.get("source_citation")).isPresent()
			&& new TextLabel(properties.get("detail_page")).isPresent();
		selenium.close();
		selenium.selectWindow("");
		return areItemsDisplayed;
	}
	
	public boolean verifyRelatedTopicsAndToolsNotAvailableInHtmlDocument(){
		selenium.open(url + fileName + ".html");
		boolean areItemsPresent = !new TextLabel(properties.get("tools")).isPresent()
				&& !new TextLabel(properties.get("relatedsubjectstab")).isPresent();
		return areItemsPresent;
	}
	
	public boolean verifyNextPreviousLinksNotAvailableInHtmlDocument(){
		return !new Link(properties.get("images_nextLink")).isPresent()
				&& !new TextLabel(properties.get("images_previousLink")).isPresent();
	}
	
	private void goToDetailedPage(String displayGroup) throws Exception{
		selenium.open(EnvironmentProperties.getInstance().getHomePageUrl());
		new TextBox(properties.get("searchBox")).type(searchTerm);
	    new PageButton(properties.get("findButton")).clickAndWait();	
	    new Link(properties.get(displayGroup + "_search_link")).click();
	}
	
	private void checkDisclaimerPresent(){
		isDisclaimerPresent = new TextLabel(properties.get("disclaimer")).isPresent();
	}
	
	private boolean verifyPrintDocument() throws Exception{
		boolean areDetailsDisplayed = verifyDocument();
		selenium.close();
		return areDetailsDisplayed;
	}
	
	private boolean verifyDownloadedDocument(String displayGroup) throws Exception{
		boolean areDetailsDisplayed = verifyDocument();
		selenium.open(EnvironmentProperties.getInstance().getHomePageUrl());
		goToDetailedPage(displayGroup);
		return areDetailsDisplayed;
	}
	
	private boolean verifyDocument() throws Exception{
		String[] windowTitles = selenium.getAllWindowTitles();
		boolean areItemsDisplayed = !windowTitles[windowTitles.length-1].isEmpty()
				&& new TextLabel(properties.get("detail_page")).isPresent()
				&& new TextLabel(properties.get("gale_document")).isPresent() 
				&& new TextLabel(properties.get("source_citation")).isPresent() 
//				&& new TextLabel(properties.get("downloadedDocument_shortCitation")).isPresent() 
				&& !new TextLabel(properties.get("thumbnail")).isPresent()
				&& verifyNoHyperlinksExist();
		if(isDisclaimerPresent)
			areItemsDisplayed = areItemsDisplayed && new TextLabel(properties.get("disclaimer")).isPresent();
		return areItemsDisplayed;
	}
	
	private boolean verifyNoHyperlinksExist(){
		for(int i = 1; i <= new TextLabel(properties.get("transcript_xpath")).getXpathCount(); i++){
			if(new TextLabel(properties.get("transcript_xpath") + "[" + i + "]/a").isPresent())
				return false;
		}
		return true;
	}
	
	private boolean verifyEmailLinkAccessible() throws InterruptedException{
		new Link(properties.get("email_link")).clickWithoutWait();
		Thread.sleep(2000);
		boolean linkAccessible = false;
		if (new TextLabel(properties.get("email_dialog")).isPresent())
			linkAccessible = true;
		new Link(properties.get("email_cancel")).clickWithoutWait();
		return linkAccessible;
	}
	
	private boolean verifyDownloadLinkAccessible() throws InterruptedException, AWTException{
		new Link(properties.get("download_link")).clickWithoutWait();
		Thread.sleep(2000);
		boolean linkAccessible = false;
		if (new TextLabel(properties.get("download_dialog")).isPresent())
			linkAccessible = true;
		RobotWebBrowser.typeKey(KeyEvent.VK_ESCAPE, 10000);
		return linkAccessible;
	}
	

}
