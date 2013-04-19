package webPageElements4Testing;

import util.SeleniumSingletonFactory;

import com.thoughtworks.selenium.DefaultSelenium;

public class BasePageElement {
	
	protected DefaultSelenium selenium;

	public BasePageElement() {
		try
		{
			selenium = SeleniumSingletonFactory.getInstance();
		}catch (Exception e) {
			throw new RuntimeException("Getting Instance from Selenium Single Factory failed",e);
		}
		
	}	
	
}
