package webPageElements4Testing;

import webPageContainers4Testing.EnvironmentProperties;


public class PageButton extends PageElementWithIdAttribute {

	public PageButton(String locator)  {
		super(locator);
	}

	public void clickAndWait() {
		selenium.click(locator);
		selenium.waitForPageToLoad(EnvironmentProperties.getInstance().getStandardPageLoadWaitTime());
	}
	
	public void click() {
		selenium.click(locator);
	}
}
