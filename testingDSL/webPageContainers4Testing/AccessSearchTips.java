package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class AccessSearchTips extends BasePageContainer{

	public AccessSearchTips() throws Exception {
		super();
	}
	
	public boolean verifyResourcesLinkIsAccessible() throws InterruptedException{
		new Link(properties.get("resources_link")).click();
		return new Link(properties.get("resources_tabs")).isPresent();
	}
	
	public boolean verifySearchTipsPageDisplayed() throws InterruptedException{
		new Link(properties.get("searchTips_link")).clickWithoutWait();
		return new TextLabel(properties.get("search_tips_content")).isPresent();
	}
	
	public boolean verifySearchTipsOpensInNewWindow() throws InterruptedException{
		String[] windowTitles = selenium.getAllWindowTitles();
		new Link(properties.get("searchTips")+"/a").click();
		return selenium.getAllWindowTitles().length == windowTitles.length + 1;
	}
	
	public boolean verifySpeakerIconsAvailableInSearchTipsPage(){
		return new TextLabel(properties.get("readSpeaker")).isPresent();
	}
	
	public boolean verifySearchTipsWindowCanBeClosed(){
		String[] windowTitles = selenium.getAllWindowTitles();
		selenium.selectWindow(windowTitles[windowTitles.length-1]);
		selenium.windowFocus();
		selenium.close();
		return selenium.getAllWindowTitles().length == windowTitles.length - 1;
	}
	
	public boolean verifyBreadcrumbInResourcesPage(){
		return new TextLabel(properties.get("breadcrumb_list") + "/a").getLabelText().equals("Home")
				&& new TextLabel(properties.get("breadcrumb_list") + "[2]/a").getLabelText().equals("Resources");
	}
	
	public boolean verifyHomePageNavigationViaBreadcrumb() throws InterruptedException{
		new Link(properties.get("breadcrumb_home")).click();
		return new TextLabel("Home").isPresent();
	}
}
