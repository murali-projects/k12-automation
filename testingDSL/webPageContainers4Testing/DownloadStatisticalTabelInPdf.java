package webPageContainers4Testing;
import java.awt.event.KeyEvent;
import java.io.File;

import utils.RobotWebBrowser;
import webPageElements4Testing.Link;
import webPageElements4Testing.RadioButton;
import webPageElements4Testing.TextLabel;

public class DownloadStatisticalTabelInPdf extends BasePageContainer{
	private String filePath;
	private String fileFullPath = "D:\\CengageNew\\Document.pdf";
	private static final String ABSOLUTE_PATH = "D:\\Cengage\\";
	public DownloadStatisticalTabelInPdf() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean verifyDownloadOption(String displayGroup) throws InterruptedException {
		new Link(properties.get(displayGroup + "_searchResults_xpath")
				+ "[1]/h3/a").click();
		if ((new TextLabel(properties.get("download_link")).isPresent()))
			return true;
		return false;
	}

	public boolean verifyDownloadInViewAllPage(String displayGroup) throws InterruptedException {
          new Link(properties.get(displayGroup+"_viewAll")).click();
          new Link(properties.get("viewAll_searchResults") + "/td[2]/h3/a")
			.click();
          if ((new TextLabel(properties.get("download_link")).isPresent()))
  			return true;
  		return false;
	}
	
	public boolean verifyModalWindowIsDisplayed() {
		new Link(properties.get("download_link")).clickWithoutWait();
		if (new TextLabel(properties.get("download_dialog")).isPresent())
			return true;
		return false;
	}

	public boolean verifyRadioButtonsPresent() {
		if(new RadioButton(properties.get("download_html")).isPresent()&&
			new RadioButton(properties.get("download_txt")).isPresent()&&
			new RadioButton(properties.get("download_pdf")).isPresent())
		return true;
		else
			return false;
	}
  
	public boolean pdfOptionIsChecked() throws Exception{
		new RadioButton(properties.get("download_pdf")).check();
		return(new RadioButton(properties.get("download_pdf")).isChecked());
		
	}
	
	public boolean verifyCancelAndDownloadButtons() {
       if(new RadioButton(properties.get("cancel_button")).isPresent()&&
   			new RadioButton(properties.get("download_button")).isPresent())
   		return true;
		else
			return false;
	}

	public boolean verifyModalWindowIsClosed() throws InterruptedException {
		new Link(properties.get("cancel_button")).clickWithoutWait();
		 if ((new TextLabel(properties.get("download_link")).isPresent()))
	  			return true;
	  		return false;
	}
    
	public boolean downlaodPdfFile(String downloadFileName)
			throws Exception {
		selenium.windowMaximize();
		filePath = FileReaderForDownloads
				.getFileAbsolutePathToDownloadForPdf(downloadFileName);
		RobotWebBrowser.setFireFoxSettings();
		new Link(properties.get("download_link")).clickWithoutWait();
		pdfOptionIsChecked();
		new Link(properties.get("download_button")).clickWithoutWait();
		RobotWebBrowser.delay(1000);
		RobotWebBrowser.typeHotKey('s', 10000);
		RobotWebBrowser.typeKey(KeyEvent.VK_ENTER, 10000);
		RobotWebBrowser.delay(1000);
		RobotWebBrowser.mouseEvent(1000);
		RobotWebBrowser.typeString(filePath, 500);
		RobotWebBrowser.delay(1000);
		RobotWebBrowser.typeKey(KeyEvent.VK_ENTER, 10000);
		RobotWebBrowser.delay(1000);
		RobotWebBrowser.closeDownLoadList();
		File file = new File(fileFullPath);
		if (file.exists())
						return true;
		return false;
		}

	public boolean downloadedFileIsPdfOrNot() {
		String fileDownloaded = FileReaderForDownloads
				.getFileFromDirectory(new File(filePath));
		if (FileReaderForDownloads.getFileExtension(fileDownloaded).contains(
				"pdf")){
			File file = new File(fileFullPath);
			if (file.exists())
				file.delete();
			return true;
		}
		return false;
	}

	public boolean verifyDownloadedCancelled(String downloadFileName) throws Exception {
		new Link(properties.get("download_link")).clickWithoutWait();
		pdfOptionIsChecked();
		new Link(properties.get("cancel_button")).clickWithoutWait();
		String fileFullPath = ABSOLUTE_PATH + downloadFileName+".pdf";
			File file = new File(fileFullPath);
			if (file.exists())
				return false;
			return true;
	}
	
}
