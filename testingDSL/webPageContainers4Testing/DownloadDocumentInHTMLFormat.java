package webPageContainers4Testing;

import java.awt.event.KeyEvent;
import java.io.File;
import utils.RobotWebBrowser;
import webPageElements4Testing.Link;
import webPageElements4Testing.RadioButton;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class DownloadDocumentInHTMLFormat extends BasePageContainer {

	private String filePath;
	private String fileFullPath = "D:\\CengageNew\\DownloadDocument.html";

	public DownloadDocumentInHTMLFormat() throws Exception {
		super();
	}

	public boolean downloadOptionAvailable(String displayGroup)
			throws InterruptedException {
		for (int i = 1; i <= new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")).getXpathCount(); i++) {
			new Link(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + i + "]/h3/a").click();
			if (!new Link(properties.get("download_link")).isPresent()) {
				return false;
			}
			new Url().goBackToPreviousPage();
		}
		return true;
	}

	public boolean downloadOptionAvailableFromViewAll(String displayGroup)
			throws InterruptedException {
		new Link(properties.get(displayGroup + "_viewAll")).click();
		new Link(properties.get("viewAll_searchResults") + "[" + 1
				+ "]/td[2]/h3/a").click();
		if (!new Link(properties.get("download_link")).isPresent())
			return false;
		return true;
	}

	public boolean clickDownloadLink(String displayGroup)
			throws InterruptedException {
		new Link(properties.get(displayGroup + "_searchResults_xpath") + "["
				+ 1 + "]/h3/a").click();
		new Link(properties.get("download_link")).clickWithoutWait();
		if (new TextLabel(properties.get("download_dialog")).isPresent())
			return true;
		return false;
	}

	public boolean chooseHtmlOption() throws InterruptedException {
		return htmlOptionIsChecked() /*&& plainTextOptionIsChecked()*/
				&& pdfOptionIsChecked();
	}

	private boolean htmlOptionIsChecked() throws InterruptedException {
		new RadioButton(properties.get("download_html")).click();
		return new RadioButton(properties.get("download_html")).isChecked();
	}

	private boolean plainTextOptionIsChecked() throws InterruptedException {
		new RadioButton(properties.get("download_txt")).click();
		return new RadioButton(properties.get("download_txt")).isChecked();
	}

	private boolean pdfOptionIsChecked() throws InterruptedException {
		new RadioButton(properties.get("download_pdf")).click();
		return new RadioButton(properties.get("download_pdf")).isChecked();
	}

	public boolean downloadHtmlFile(String downloadFileName, String displayGroup)
			throws Exception {
		selenium.windowMaximize();
		filePath = FileReaderForDownloads
				.getFileAbsolutePathToDownload(downloadFileName);
		RobotWebBrowser.setFireFoxSettings();
		new Link(properties.get("download_link")).clickWithoutWait();
		htmlOptionIsChecked();
		new Link(properties.get("download_button_in_download")).clickWithoutWait();
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
		if(new Link(properties.get("cancel_button")).isPresent())
			new Link(properties.get("cancel_button")).clickWithoutWait();
		File file = new File(fileFullPath);
		if (file.exists())
			return true;
		return false;
	}

	public boolean downloadedFileIsHtmlOrNot() {
		String fileDownloaded = FileReaderForDownloads
				.getFileFromDirectory(new File(filePath));
		if (FileReaderForDownloads.getFileExtension(fileDownloaded).contains(
				"html")){
			File file = new File(fileFullPath);
			if (file.exists())
				file.delete();
			return true;
		}
		return false;
	}
}
