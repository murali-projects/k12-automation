package webPageContainers4Testing.dev;


public class GoogleAnalyticsElement extends BaseDevPageContainer {
	
	protected String locator = properties.get("google_analytics_element");
	protected String javascriptLocator = properties.get("google_analytics_javascript_element");
	
	public GoogleAnalyticsElement() throws Exception {
		super();
	}
	
	public boolean isPresent() {
		return selenium.isElementPresent(locator);
	}
	
	public Number getJavaScriptBlockCount() {
		return selenium.getXpathCount(javascriptLocator);
	}
	
}