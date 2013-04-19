package webPageContainers4Testing;

import java.awt.event.KeyEvent;

import utils.RobotWebBrowser;
import webPageElements4Testing.Link;
import webPageElements4Testing.RadioButton;

public class CreateAPABookCitableType extends BasePageContainer{

	private String filePath;
	private String fileName = "S07676Document";
	private String url = "file://D:/CengageNew/";
	
	public CreateAPABookCitableType() throws Exception {
		super();
	}
	
	public boolean verifyCitationToolsAvailable() throws InterruptedException{
		new Link(properties.get("reference"+"_searchResults_xpath")+"/h3/a").click();
		return new Link(properties.get("citation_link")).isPresent();
	}
	
	public boolean verifyAPARadioButtonAvailable() throws InterruptedException{
		new Link(properties.get("citation_link")).clickWithoutWait();
		return new RadioButton(properties.get("apa_radioButton")).isPresent();
	}

	public boolean verifyAPACitationInDocument() throws Exception{
		new RadioButton(properties.get("apa_radioButton")).click();
		downloadHtmlFile(fileName);
		return verifyDocument(fileName);
	}
	
	public void downloadHtmlFile(String downloadFileName)throws Exception {
		selenium.windowMaximize();
		filePath = FileReaderForDownloads
				.getFileAbsolutePathToDownload(downloadFileName);
		RobotWebBrowser.setFireFoxSettings();
		new Link(properties.get("citation_download_button")).clickWithoutWait();
		RobotWebBrowser.delay(1500);
		RobotWebBrowser.typeHotKey('s', 15000);
		RobotWebBrowser.typeKey(KeyEvent.VK_ENTER, 10000);
		RobotWebBrowser.delay(1000);
		RobotWebBrowser.mouseEvent(1000);
		RobotWebBrowser.typeString(filePath, 500);
		RobotWebBrowser.delay(1000);
		RobotWebBrowser.typeKey(KeyEvent.VK_ENTER, 10000);
		RobotWebBrowser.delay(1000);
		RobotWebBrowser.closeDownLoadList();
	}
	
	public boolean verifyDocument(String fileName){
		selenium.open(url + fileName + ".html");
		//TODO:Check APA citation rules here
		return false;
	}
}
