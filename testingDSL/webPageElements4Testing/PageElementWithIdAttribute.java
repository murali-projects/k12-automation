package webPageElements4Testing;

public class PageElementWithIdAttribute extends BasePageElement {
	
	protected String locator;

	public PageElementWithIdAttribute(String locator) {
//		System.out.println("PageElementWithIdAttribute : locator : "+locator);
		this.locator = locator;
	}

	public boolean isPresent() {
		return selenium.isElementPresent(locator);
	}
	public boolean isPresent(String locator) {
		return selenium.isElementPresent(locator);
	}
	
	public boolean isVisible(){
		return selenium.isVisible(locator);
	}
	public String getLocator() {
		return locator;
	}
	
	public boolean isTextPresent(){
		return selenium.isTextPresent(locator);
	}
	
}
