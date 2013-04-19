package webPageContainers4Testing.dev;

import org.apache.tools.ant.types.Environment;

import webPageContainers4Testing.EnvironmentProperties;
import webPageElements4Testing.Link;


public class HomePage extends BaseDevPageContainer {
	public static final String HOME_PAGE_TITLE = "K-12";
	
	private String locator; 
	
	private Link advancedSearchLink;
	
	public HomePage() throws Exception {
		goToPage(EnvironmentProperties.getInstance().getHomePageUrl() + "/portal/default" );
		locator = properties.get("searchBox");
		advancedSearchLink = new Link(properties.get("page_all.portlet_top.advanced_search.link"));
		
	}
	public boolean verifyTextExists(String text) {
		return selenium.isTextPresent(text);
	}

	public void typeSearchText(String text) throws Exception {
		selenium.type(locator, "");
		selenium.typeKeys(locator, text);
		Thread.sleep(1000);

	}
	
	public void clickOnAdvancedSearch() throws InterruptedException {
		advancedSearchLink.click();
	}
}
