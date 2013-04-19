package webPageContainers4Testing;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import utils.RobotWebBrowser;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewPrinterFriendlyDocument extends BasePageContainer{

	public ViewPrinterFriendlyDocument() throws Exception {
		super();
	}
	
	public boolean verifyPrintOptionAvailable(String displayGroup) throws InterruptedException{
		selenium.windowMaximize();
		if(displayGroup.equals("images"))
			new Link(properties.get(displayGroup+"_searchResults_xpath")).click();
		else
			new Link(properties.get(displayGroup+"_searchResults_xpath")+ "/h3/a").click();
		boolean isPrintOptionPresent = new Link(properties.get("images_print")).isPresent();
		new Url().goBackToPreviousPage();
		return isPrintOptionPresent;
	}
	
	public boolean verifyPrintOptionAvailableFromViewAll(String displayGroup) throws InterruptedException{
		new Link(properties.get(displayGroup+ "_viewAll")).click();
		new Link(properties.get("viewAll_searchResults")+ "/td[2]/h3/a").click();
		return new Link(properties.get("images_print")).isPresent();
	}

	public boolean verifyTooltipIsPresent(){
		return (!new TextLabel(properties.get("images_print")+"/a").getAttribute("title").isEmpty());
	}
	
	public boolean verifyPrintPreviewWindowExists() throws InterruptedException{
		new Link(properties.get("images_print")).clickWithoutWait();
		String[] windowNames = selenium.getAllWindowNames();
		if (windowNames[windowNames.length-1].equals("printPreview")) {
			return true;
		}
		return false;
	}
	
	public boolean verifyPrintOptionIsWorking() throws InterruptedException, AWTException{
		new Url().selectWindow("printPreview");
		clickCancelOnPopup();
		selenium.close();
		new Url().selectWindow("");
		boolean isPrintLinkWorking = verifyPrintPreviewWindowExists();
		return isPrintLinkWorking;
	}
	
	public boolean verifyPrintOptionNotAvailableInNewWindow() throws AWTException, InterruptedException{
		clickCancelOnPopup();
		selenium.selectWindow("printPreview");
		return (! new Link(properties.get("images_print")).isPresent());
	}
	
	public boolean verifyPrintWindowExistsAfterClickingCancel(){
		String[] windowNames = selenium.getAllWindowNames();
		if (windowNames[windowNames.length-1].equals("printPreview")) {
			return true;
		}
		return false;
	}
	
	private void clickCancelOnPopup() throws AWTException{
		RobotWebBrowser.typeKey(KeyEvent.VK_ESCAPE, 1000);
	}

}
