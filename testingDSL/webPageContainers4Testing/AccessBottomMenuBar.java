package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class AccessBottomMenuBar extends BasePageContainer{
	
	public AccessBottomMenuBar() throws Exception {
		super();
	}

	public boolean verifyBottomMenuBarContainsLink(String linkName) throws Exception{
		return new Link(properties.get(linkName+"_link")).isPresent();
	}
	
	public boolean isNewWindowOpenedOnClickingLink(String linkName) throws InterruptedException, Exception{
		new Link(properties.get(linkName+"_link")).clickWithoutWait();
		Thread.sleep(2000);
		String[] windowsAfterClickingLink = selenium.getAllWindowNames();
		selenium.selectWindow(windowsAfterClickingLink[windowsAfterClickingLink.length-1]);
		selenium.windowFocus();
		boolean verifyWindowUrl = new Url().getUrl().contains(properties.get(linkName + "_url"));
		selenium.close();
		selenium.selectWindow("");
		return  verifyWindowUrl;
	}
	
	public boolean verifyEndSessionLinkIsWorking() throws InterruptedException{
		new Link("endSession_link").click();
		return new TextLabel(properties.get("session_termination_page")).isPresent();
	}
}
