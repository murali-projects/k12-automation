package webPageContainers4Testing;

import util.SeleniumSingletonFactory;
import utils.PropertyReader;

import com.thoughtworks.selenium.DefaultSelenium;

public class BasePageContainer {

	protected static DefaultSelenium selenium;
	protected PropertyReader properties;
	
	public BasePageContainer() throws Exception {
		init();
	}
	
	private void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
		loadProperties();
	}

	protected void loadProperties() {
		properties = new PropertyReader("properties/default.search.properties");
	}

	protected void goToPage(String url) {
		selenium.open(url);		
		selenium.waitForPageToLoad(EnvironmentProperties.getInstance().getStandardPageLoadWaitTime());
	}
	
	public boolean verifyTitleContains(String expectedTitle) {
		return selenium.getTitle().contains(expectedTitle);
	}
	
	public String getValue(String locator){
		return selenium.getValue(locator);
	}
	
	public String getTitle(){
		return selenium.getTitle();
	}
	
}
