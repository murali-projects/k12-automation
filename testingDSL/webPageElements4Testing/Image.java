package webPageElements4Testing;

public class Image  extends PageElementWithIdAttribute{

	public Image(String locator) {
		super(locator);
	}
	
	public boolean isPresent() {
		return selenium.isElementPresent(locator);
	}

	public String getAltText(String attributeLocator) {
		return selenium.getAttribute(attributeLocator);
	}

	public String getAltText() {
		return selenium.getAttribute(this.locator + "/@alt");
	}

	public void clickWithoutWait() {
		selenium.click(this.locator);
	}

	public void ajaxClickAndWait() {
		selenium.click(this.locator);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Oops");
			//nothing I can do about this
		}
		//We should enhance this method to use the following method but for now we will just wait
		//selenium.waitForCondition(script, timeout);
	}

}
