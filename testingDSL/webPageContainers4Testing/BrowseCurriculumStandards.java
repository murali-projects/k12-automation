package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class BrowseCurriculumStandards extends BasePageContainer{

	public BrowseCurriculumStandards() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifyClickingonResourcesLink()throws Exception{
		new Link(properties.get("resources_link")).click();
		return (new TextLabel(properties.get("resources_tabs")).isPresent());
	}
	
	public boolean verifySelectingCurriculumStandards() throws Exception{
		new Link(properties.get("resources_link")).click();
		new Link(properties.get("")).click();
		return (new TextLabel(properties.get("")).isPresent());
	}
	
	public boolean verifyCountryLinkPresent() throws Exception{
		return (new TextLabel(properties.get("")).isPresent());
	}

	public boolean verifyNationalStandardsLinkispresent() throws Exception{
		return (new TextLabel(properties.get("")).isPresent());
	}
	
	public boolean verifyNationalStandards() throws Exception{
		new Link(properties.get("")).click();
		return (new TextLabel(properties.get("")).isPresent());
	}
	
	public boolean verifyClickingNationalStandards() throws Exception{
		String[] windowTitles = selenium.getAllWindowTitles();
		new Link(properties.get("")+"/a").click();
		return selenium.getAllWindowTitles().length == windowTitles.length + 1;
	}
	public boolean verifyNationalStandardsPopupClosable() throws Exception{
		String[] windowTitles = selenium.getAllWindowTitles();
		selenium.selectWindow(windowTitles[windowTitles.length-1]);
		selenium.windowFocus();
		selenium.close();
		return selenium.getAllWindowTitles().length == windowTitles.length - 1;
	}
	
	public boolean verifyStateStandardsLink() throws Exception{
		if(new TextLabel(properties.get("")).isPresent()){
			new Link(properties.get("")).click();
			return (new TextLabel(properties.get("")).isPresent());
		}
		return true;
	}
	
	public boolean verifyClickingStateStandards() throws Exception{
		return (new TextLabel(properties.get("")).isPresent());
	}
	
	public boolean verifyClickingOnListOfStates() throws Exception{
		String[] windowTitles = selenium.getAllWindowTitles();
		new Link(properties.get("")+"/a").click();
		return selenium.getAllWindowTitles().length == windowTitles.length + 1;
	}
	
	public boolean verifyStateStandardsPoppupClosable() throws Exception{
		String[] windowTitles = selenium.getAllWindowTitles();
		selenium.selectWindow(windowTitles[windowTitles.length-1]);
		selenium.windowFocus();
		selenium.close();
		return selenium.getAllWindowTitles().length == windowTitles.length - 1;
	}
	
	public boolean verifyNavigationThroughBreadcurmb() throws Exception {
		return new TextLabel(properties.get("breadcrumb_list") + "/a").getLabelText().equals("Home")
		&& new TextLabel(properties.get("breadcrumb_list") + "[2]/a").getLabelText().equals("Resources");
	}

	public boolean verifyBreadcrumb() throws Exception {
		new Link(properties.get("breadcrumb_home")).click();
		return new TextLabel("Home").isPresent();

	}
	
}
