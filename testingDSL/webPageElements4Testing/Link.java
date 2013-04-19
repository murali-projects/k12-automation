package webPageElements4Testing;

import webPageContainers4Testing.EnvironmentProperties;

public class Link extends PageElementWithIdAttribute {
	
	public Link(String locator)  {
		super(locator);
	}

	public void click(String linkToClick) {
		selenium.click(linkToClick);
	}
	
	public void clickWithoutWait() {
		selenium.click(this.locator);
	}

	public void ajaxClickAndWait() {
		selenium.click(this.locator);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Oops");
			//nothing I can do about this
		}
		//We should enhance this method to use the following method but for now we will just wait
		//selenium.waitForCondition(script, timeout);
	}

	public void click() throws InterruptedException {
		selenium.click(this.locator);
		selenium
				.waitForPageToLoad(EnvironmentProperties.getInstance().getStandardPageLoadWaitTime());
	}

	public int getLinkCount() {
		return selenium.getXpathCount(this.locator).intValue();
	}

	public String getLinkText(String locator) {
		return selenium.getText(locator);
	}

	public String getLinkText() {
		return selenium.getText(locator);
	}
	
	public String getLinkAttribute(String linkAttributeLocator){
		return selenium.getAttribute(linkAttributeLocator);
	}

}
