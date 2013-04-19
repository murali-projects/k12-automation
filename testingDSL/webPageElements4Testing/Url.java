package webPageElements4Testing;

import webPageContainers4Testing.EnvironmentProperties;

public class Url extends BasePageElement{

	public Url() {
		super();
	}
	
	public String getUrl(){
		return selenium.getLocation();
	}
	
	public void goToPage(String url) {
		selenium.open(url);		
		selenium.waitForPageToLoad(EnvironmentProperties.getInstance().getStandardPageLoadWaitTime());
	}	
	
	public void focus(String locator) throws Exception{
		Thread.sleep(10000);
		selenium.focus(locator);
	}
	
	public void waitForPopup(String windowId){
		selenium.waitForPopUp(windowId,"30000");
	}
	

	public void goBackToPreviousPage(){
		selenium.goBack();
		selenium.waitForPageToLoad(EnvironmentProperties.getInstance().getStandardPageLoadWaitTime());
	}
	
	public void mouseOver(String locator){
		selenium.mouseOver(locator);
	}
	public void selectWindow(String locator){
		 selenium.selectWindow(locator);
	}
	
	public static String getCurrentUrl() {
		return new Url().getUrl();
	}
	
	public void closeWindow(){
		selenium.close();
	}

}
